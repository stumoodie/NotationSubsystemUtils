package org.pathwayeditor.contextadapter.toolkit.ndom;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjectsAPI.IMapObject;
import org.pathwayeditor.businessobjectsAPI.ITextProperty;


public class ModelObjectTest {

	private static final String ASCII_NAME = "name";

	private static final String NAME = "<name>";
	private static final String DESCRIPTION = "description>";
	private static final String DETAILED_DESCRIPTION = "detailed description";

	private Mockery mockery = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private String goodId = "c001";

	private String badId = "!goodId";

	private ModelObject mObject = mockery.mock(ModelObject.class);

	// private
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCode() {
		ModelObject stObject = new ModelObject(goodId, NAME, ASCII_NAME) {
		};
		assertEquals(goodId.hashCode(), stObject.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModelObjectStringStringStringBadId() {
		ModelObject stObject = new ModelObject(badId, NAME, ASCII_NAME) {
		};
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModelObjectStringNullString() {
		ModelObject stObject = new ModelObject(goodId, null, ASCII_NAME) {
		};
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModelObjectStringStringNull() {
		ModelObject stObject = new ModelObject(goodId, NAME, null) {
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModelObjectWithEmptyStringNotAllowed() {
		String EMPTY_STRING_ID="";
		ModelObject stObject = new ModelObject(EMPTY_STRING_ID, NAME, null) {
		};
	}

	@Test
	public void testModelObjectStringStringString() {
		ModelObject stObject = new ModelObject(goodId, NAME, ASCII_NAME) {
		};
		assertEquals(goodId, stObject.getId());
		assertEquals(NAME, stObject.getName());
		assertEquals(ASCII_NAME, stObject.getASCIIName());
	}

	@Test
	public void testModelObjectStringIMapObject() {
		final IMapObject mo = mockery.mock(IMapObject.class);
		final ITextProperty name = mockery.mock(ITextProperty.class);
		final ITextProperty descr = mockery.mock(ITextProperty.class);
		final ITextProperty detDescr = mockery.mock(ITextProperty.class);
		mockery.checking(new Expectations() {
			{
				atLeast(2).of(mo).getName();
				will(returnValue(name));
			}
			{
				one(name).getHTML();
				will(returnValue(NAME));
			}
			{
				one(name).getString();
				will(returnValue(ASCII_NAME));
			}
			{
				one(mo).getDescription();
				will(returnValue(descr));
			}
			{
				one(descr).getHTML();
				will(returnValue(DESCRIPTION));
			}
			{
				one(mo).getDetailedDescription();
				will(returnValue(detDescr));
			}
			{
				one(detDescr).getHTML();
				will(returnValue(DETAILED_DESCRIPTION));
			}
		});
		ModelObject stObject = new ModelObject(goodId, mo) {
		};
		assertEquals(goodId, stObject.getId());
		assertEquals(NAME, stObject.getName());
		assertEquals(ASCII_NAME, stObject.getASCIIName());
		assertEquals(DESCRIPTION, stObject.getDescription());
		assertEquals(DETAILED_DESCRIPTION, stObject.getDetailedDescription());
	}

	@Test
	public void testToString() {
		ModelObject stObject = new ModelObject(goodId, NAME, ASCII_NAME) {
		};
		stObject.setDescription(DESCRIPTION);
		stObject.setDetailedDescription(DETAILED_DESCRIPTION);
		assertEquals("ModelObject:[\nID:" + goodId + "\nname:" + ASCII_NAME
				+ "\nDescription:" + DESCRIPTION + "\nDetailedDescription:"
				+ DETAILED_DESCRIPTION + "\n]\n", stObject.toString());
	}

	@Test
	public void testEqualsObject() {
		ModelObject stObject = new ModelObject(goodId, NAME, ASCII_NAME) {
		};
		assertTrue(stObject.equals(stObject));
		assertTrue(stObject.equals(new ModelObject(goodId, NAME, ASCII_NAME) {
		}));
		assertTrue((new ModelObject(goodId, NAME, ASCII_NAME) {
		}).equals(stObject));
		assertFalse(stObject.equals(new ModelObject("id1", NAME, ASCII_NAME) {
		}));
	}

}

/*
 * $Log$
 * Revision 1.1  2008/07/10 12:06:37  nhanlon
 * extraction of Toolkit project
 *
 * Revision 1.3  2008/06/27 13:18:46  radams
 * *** empty log message ***
 *
 * Revision 1.2  2008/06/26 13:09:07  radams
 * *** empty log message ***
 *
 * Revision 1.1  2008/06/02 10:27:40  asorokin
 * NDOM facility
 *
 */