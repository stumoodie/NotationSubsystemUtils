package org.pathwayeditor.notationsubsystem.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.notationsubsystem.toolkit.validation.DefaultValidationReport;

@RunWith(JMock.class)
public class DefaultValidationReportTest {
    private IValidationReport report;
    Mockery mockery = new JUnit4Mockery();
	
    final ICanvas map = mockery.mock(ICanvas.class);
    final IValidationReportItem item1 = mockery.mock(IValidationReportItem.class);
    List<IValidationReportItem>contents ;
    
	@Before
	public void setUp() throws Exception {
		
	}

	private List<IValidationReportItem> createASingleItemList() {
		   final List<IValidationReportItem> contents = new ArrayList<IValidationReportItem>();
		   contents.add(item1);
		   return contents;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullConstructorMapShouldThrowIllegalArgumentException() {
		report = new DefaultValidationReport(null, createASingleItemList());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullConstructorReportListShouldThrowIllegalArgumentException() {
		report = new DefaultValidationReport(map, null);	
	}
	
	@Test
	public void testReportRecordsCreationTime(){
		Date BEFORE = Calendar.getInstance().getTime();
		System.out.println(BEFORE.getTime());
		report = new DefaultValidationReport(map, createASingleItemList());	
		Date AFTER = Calendar.getInstance().getTime();
	
		assertFalse(BEFORE.after(report.getValidationTime()));
		assertFalse(AFTER.before(report.getValidationTime()));
	}
	

	
	static Date creationDate = Calendar.getInstance().getTime();
	/*Test specific subclass to enable tw od ifferent reports to have same date for testing equals.
	 * @author Richard Adams
	 *
	 */
	class DefaultValidationReportWithSettableDate extends DefaultValidationReport {
		public DefaultValidationReportWithSettableDate(ICanvas map, List<IValidationReportItem> itemList) {
			super(map, itemList);
		}

		public Date getValidationTime() {
			return creationDate;
		}
	}
	@Test
	public void testReportsAreEqualIfCreationTimeAndMapAreTheSame() throws InterruptedException{
		report = new DefaultValidationReportWithSettableDate(map, createASingleItemList());	
		IValidationReport SAME_TIME_REPORT =  new DefaultValidationReportWithSettableDate(map, createASingleItemList());	
		assertEquals(report, SAME_TIME_REPORT);
		Thread.sleep(10);
		IValidationReport LATER_REPORT =  new DefaultValidationReport(map, createASingleItemList());
		assertFalse(report.equals(LATER_REPORT));
	}
	
	@Test
	public void testHashCode() throws InterruptedException {
		report = new DefaultValidationReportWithSettableDate(map, createASingleItemList());	
		IValidationReport SAME_TIME_REPORT =  new DefaultValidationReportWithSettableDate(map, createASingleItemList());	
		assertEquals(report.hashCode(), SAME_TIME_REPORT.hashCode());
		Thread.sleep(20);
		IValidationReport LATER_REPORT =  new DefaultValidationReport(map, createASingleItemList());
		assertFalse(report.hashCode()==LATER_REPORT.hashCode());
	}

	

	@Test
	public void testGetMap() {
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertEquals(map, report.getCanvas());
	}

	@Test
	public void testGetValidationReportItems() {
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertEquals(1, report.getValidationReportItems().size());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetValidationReportItemsIsUnmodifiableForAdding() {
		report = new DefaultValidationReport(map, createASingleItemList());	
	    final IValidationReportItem secondItem = mockery.mock(IValidationReportItem.class);
	    List<IValidationReportItem> items = report.getValidationReportItems();
	    items.add(secondItem);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetValidationReportItemsIsUnmodifiableForRemoving() {
		report = new DefaultValidationReport(map, createASingleItemList());	
	    List<IValidationReportItem> items = report.getValidationReportItems();
	    items.remove(item1);
	}

	

	@Test
	public void testHasWarningsTrueIfThereIsABrokenGuidelineRule() {
		configureReportToHaveGuidelineRule();
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertTrue(report.hasWarnings());
	}

	private void configureReportToHaveGuidelineRule() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.WARNING));}
		
		});
	}
	
	@Test
	public void testHasWarningsFalseIfThereIsNoBrokenGuidelineRule() {
		configureReportToHaveAErrorRule();
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertFalse(report.hasWarnings());
	}

	private void configureReportToHaveAErrorRule() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.ERROR));}
		
		});
	}

	@Test
	public void testIsMapValidIsTrueIfOnlyGuidelinesArePresent() {
		configureReportToHaveGuidelineRule();
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertTrue(report.isMapValid());
	}
	
	@Test
	public void testMapIsNotValidIfOptionalRuleApplied() {
		configureReportToHaveAnOptionalRuleBroken();
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertFalse(report.isMapValid());
	}
	
	private void configureReportToHaveAnOptionalRuleBroken() {
		mockery.checking(new Expectations() {
			{atLeast(1).of(item1).getSeverity(); will(returnValue(IValidationReportItem.Severity.ERROR));}
		
		});
	}

	@Test
	public void testMapIsNotValidIfMAndatoryRuleApplied() {
		configureReportToHaveAErrorRule();
		report = new DefaultValidationReport(map, createASingleItemList());	
		assertFalse(report.isMapValid());
	}

	@Test
	public void testIsReportCurrentIfMapLastModifiedBeforeReportCreated() throws Exception{
		final Date MAP_NOT_MODIFIED = Calendar.getInstance().getTime();
		Thread.sleep(10);
		report = new DefaultValidationReport(map, createASingleItemList());	
		mockery.checking(new Expectations() {
			{one(map).getModified();will(returnValue(MAP_NOT_MODIFIED));}
			
		});
		assertTrue(report.isReportCurrent());
		
	}
	
	@Test
	public void testReportNotCurrentIfMapModifiedAfterReportCreated() throws Exception{
		report = new DefaultValidationReport(map, createASingleItemList());	
		Thread.sleep(30);
		final Date MAP_MODIFIED_AFTER_REPORT_CREATION = Calendar.getInstance().getTime();
		mockery.checking(new Expectations() {
			{one(map).getModified();will(returnValue(MAP_MODIFIED_AFTER_REPORT_CREATION));}
			
		});
		assertFalse(report.isReportCurrent());
		
	}
	
	@Test
	public void testDateIsImmutable() throws Exception{
		report = new DefaultValidationReport(map, createASingleItemList());	
        Date date = report.getValidationTime();
        date.setTime(1000000);
        Date d2 = report.getValidationTime();
        assertFalse(d2.equals(date));
		
	}
	
	@Test
	public void testEquals() {
		report = new DefaultValidationReport(map, createASingleItemList());
		assertEquals(report ,report);
		assertFalse(report.equals(null));
	}

	

}
