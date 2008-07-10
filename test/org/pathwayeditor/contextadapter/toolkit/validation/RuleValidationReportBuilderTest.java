package org.pathwayeditor.contextadapter.toolkit.validation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjectsAPI.IMap;
import org.pathwayeditor.contextadapter.publicapi.IValidationReport;
import org.pathwayeditor.contextadapter.publicapi.IValidationReportItem;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;

@RunWith(JMock.class)
public class RuleValidationReportBuilderTest {
    IRuleValidationReportBuilder reportBuilderAPI;
    RuleValidationReportBuilderExt reportBuilderImpl;
    
    // allows modulation of state
    class RuleValidationReportBuilderExt extends RuleValidationReportBuilder {
    	public RuleValidationReportBuilderExt(IValidationRuleStore store, IMap map) {
			super(store, map);
		}
    	void setState(int state) {
    		this.state=state;
    	}
    
    }
    
    Mockery mockery = new JUnit4Mockery();
	final IMap map = mockery.mock(IMap.class);
	final IValidationRuleStore store = mockery.mock(IValidationRuleStore.class);
	final IValidationRuleDefinition DEF1 = mockery.mock(IValidationRuleDefinition.class);
	final IValidationRuleConfig	 CONFIG1 = mockery.mock(IValidationRuleConfig.class);
	
	
	
	@Before
	public void setUp() throws Exception {
		reportBuilderImpl = new RuleValidationReportBuilderExt(store, map);
		reportBuilderAPI= reportBuilderImpl;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRuleValidationReportBuilderIsReadyToValidateAfterConstruction() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(store, map);
		assertTrue(reportBuilderAPI.isReadyToValidate());
		assertFalse(reportBuilderAPI.isValidating());
		assertFalse(reportBuilderAPI.isComplete());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRuleValidationReportBuilderDoesNotAllowNullStore() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(null, map);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRuleValidationReportBuilderDoesNotAllowNullMap() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(store, null);	
	}

	@Test
	public void testValidationReportCanBeCalledImmediatelyAfterConstructionOnlyIfNoRulesToCheck() {
	    reportBuilderAPI.createValidationReport();
	}
	
	
	
	@Test
	public void testCreateValidationReportCanBeCalledImmediatelyDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
	    reportBuilderAPI.createValidationReport();
	    assertTrue(reportBuilderAPI.isComplete());
	}

	@Test
	public void testGetRuleStore() {
		assertEquals(store, reportBuilderAPI.getRuleStore());
	}

	@Test(expected=IllegalStateException.class)
	public void testGetValidationReportNotAllowedAfterCreation() {
       reportBuilderAPI.getValidationReport();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetValidationReportNotAllowedDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
        reportBuilderAPI.getValidationReport();
	}
	
	@Test
	public void testGetValidationReportOnlyAllowedAfterCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
        IValidationReport report = reportBuilderAPI.getValidationReport();
        assertEquals(map, report.getMap());
	}

	@Test
	public void testIsCompleteIsFalseAfterBuilderConstruction() {
		assertFalse(reportBuilderAPI.isComplete());	
	}
	
	@Test
	public void testIsCompleteIsFalseDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
		assertFalse(reportBuilderAPI.isComplete());		
	}

	@Test
	public void testIsReadyToValidateIsTrueAfterBuilderConstruction() {
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testIsReadyToValidateIsFalseAfterBuilderCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		assertFalse(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testIsReadyToValidateIsTrueAfterBuilderCompletionAndReset() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}

	@Test
	public void testIsValidatingIsFalseAfterBuilderConstruction() {
		assertFalse(reportBuilderAPI.isValidating());
	}

	@Test
	public void testResetApplicableAfterCreation() {
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testResetApplicableAfterCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testResetNotApplicableDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}

	@Test
	public void testSetRuleFailed() {
		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		final int RULE_ID=1;
		setUpRuleAssertions(configs, RULE_ID);
		mockery.checking(new Expectations() {
			{atLeast(1).of(CONFIG1).mustBeRun(); will(returnValue(true));}	;
	});
		reportBuilderAPI.setRuleFailed(null, DEF1, "Failed");
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(1, report.getValidationReportItems().size());
		assertEquals(IValidationReportItem.Severity.ERROR, report.getValidationReportItems().get(0).getSeverity());
	}
	
	
	@Test
	public void testRuleFsilureOnlyAddedToReportIfMustBeRunIsTrue (){
		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		final int RULE_ID=1;
		setUpRuleAssertions(configs, RULE_ID);
		mockery.checking(new Expectations() {
			{atLeast(1).of(CONFIG1).mustBeRun(); will(returnValue(false));}	;
	    });
		reportBuilderAPI.setRuleFailed(null, DEF1, "Failed");
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(0, report.getValidationReportItems().size());
	}

	private void setUpRuleAssertions(final Set<IValidationRuleConfig> configs, final int RULE_ID) {
		mockery.checking(new Expectations() {
			{atLeast(1).of(DEF1).getRuleNumber(); will(returnValue(RULE_ID));}
			{allowing(CONFIG1).getValidationRuleDefinition(); will(returnValue(DEF1));}
			{allowing(CONFIG1).isErrorRule(); will(returnValue(true));}
		//	{atLeast(1).of(store).getConfigurableRules();will(returnValue(configs));}
			{atLeast(1).of(store).containsRule(RULE_ID);will(returnValue(true));}
			{atLeast(1).of(store).getRuleConfigByID(RULE_ID);will(returnValue(CONFIG1));}
		});
	}
	
	

	@Test
	public void testSetRulePassed() {
		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		reportBuilderAPI.setRulePassed(DEF1);
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(0, report.getValidationReportItems().size());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetRuleFailedDoesNotAcceptRulesFromOutsideRuleStore () {
		final IValidationRuleDefinition def = mockery.mock(IValidationRuleDefinition.class);
		final int RULE_ID=1;
		mockery.checking(new Expectations() {
			{atLeast(1).of(def).getRuleNumber(); will(returnValue(RULE_ID));}
			{atLeast(1).of(store).containsRule(RULE_ID);will(returnValue(false));}
		});
		reportBuilderAPI.setRuleFailed(null, def, "Any message");
	}

}
