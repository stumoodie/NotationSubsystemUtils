package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IObjectType;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition.PropDefnType;

@RunWith(JMock.class)
public class PropertyDefinitionFilterTest {
	private static final int ALL_EXPECTED_SIZE = 4;
	private static final int EDITABLE_EXPECTED_SIZE = 2;
	private static final int FORMATTED_EXPECTED_SIZE = 3;
	private static final int NUMBER_EXPECTED_SIZE = 1;
	private static final int PLAIN_EXPECTED_SIZE = 1;
	private static final int VISUALISABLE_EXPECTED_SIZE = 2;
	private static final int EMPTY_EXPECTED_SIZE = 0;
	private Mockery context = new JUnit4Mockery();
	private PropertyDefinitionFilter testInstance;
	private IObjectType testOT;
	private IPropertyDefinition testProperty;

	@Before
	public void setUp() throws Exception {
		this.testOT = context.mock(IObjectType.class);
		this.testProperty = context.mock(IPropertyDefinition.class);
		this.testInstance = new PropertyDefinitionFilter(testOT);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testPropertyDefinitionFilterNullParameter() {
		new PropertyDefinitionFilter(null);
	}

	@Test
	public final void testGetAllProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getAllProperties();
		assertEquals("Set is expected size", ALL_EXPECTED_SIZE, actualProps.size());
		context.assertIsSatisfied();
	}

	@Test
	public final void testGetAllPropertiesWhenEmpty() {
		context.checking(new Expectations(){{
			never (testProperty).compareTo(testProperty);
		}});
		Set<IPropertyDefinition> actualProps = this.testInstance.getAllProperties();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
		context.assertIsSatisfied();
	}

	@Test
	public final void testGetAllPropertiesIterator() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Iterator<IPropertyDefinition> iter = this.testInstance.getAllPropertiesIterator();
		int actualSize = 0;
		while(iter.hasNext()){
			assertNotNull("has valid element", iter.next());
			actualSize++;
		}
		assertEquals("Set is expected size", ALL_EXPECTED_SIZE, actualSize);
		context.assertIsSatisfied();
	}

	@Test(expected=NoSuchElementException.class)
	public final void testGetAllPropertiesIteratorWhenEmpty() {
		context.checking(new Expectations(){{
			never (testProperty).compareTo(testProperty);
		}});
		Iterator<IPropertyDefinition> iter = this.testInstance.getAllPropertiesIterator();
		assertFalse("no next item", iter.hasNext());
		context.assertIsSatisfied();
		iter.next();
	}

	@Test
	public final void testGetEditableProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).isEditable();
			   will(onConsecutiveCalls(
			       returnValue(true),
			       returnValue(false),
			       returnValue(true),
			       returnValue(false)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getEditableProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EDITABLE_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetEditablePropertiesWhenNone() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).isEditable();
			   will(onConsecutiveCalls(
			       returnValue(false),
			       returnValue(false),
			       returnValue(false),
			       returnValue(false)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getEditableProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetFormattedTextProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.PLAIN_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getFormattedTextProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", FORMATTED_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetFormattedTextPropertiesWhenNone() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.NUMBER),
			       returnValue(PropDefnType.PLAIN_TEXT),
			       returnValue(PropDefnType.PLAIN_TEXT),
			       returnValue(PropDefnType.NUMBER)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getFormattedTextProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetNumberProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.NUMBER),
			       returnValue(PropDefnType.FORMATTED_TEXT)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getNumberProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", NUMBER_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetNumberPropertiesWhenNone() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.PLAIN_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getNumberProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetObjectTypeNotNull() {
		assertNotNull("ObjectType is defined", this.testInstance.getObjectType());
	}

	@Test
	public final void testGetTextProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.PLAIN_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getTextProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", PLAIN_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetTextPropertiesWhenNone() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).getType();
			   will(onConsecutiveCalls(
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.FORMATTED_TEXT),
			       returnValue(PropDefnType.NUMBER),
			       returnValue(PropDefnType.FORMATTED_TEXT)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getTextProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetVisualisableProperties() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).isVisualisable();
			   will(onConsecutiveCalls(
			       returnValue(true),
			       returnValue(false),
			       returnValue(true),
			       returnValue(false)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getVisualisableProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", VISUALISABLE_EXPECTED_SIZE, actualProps.size());
	}

	@Test
	public final void testGetVisualisablePropertiesWhenNone() {
		context.checking(new Expectations(){{
			atLeast(1).of (testProperty).compareTo(testProperty);
			   will(onConsecutiveCalls(
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1),
			       returnValue(1)
			    ));
			atLeast(1).of (testProperty).isVisualisable();
			   will(onConsecutiveCalls(
			       returnValue(false),
			       returnValue(false),
			       returnValue(false),
			       returnValue(false)
			    ));
		}});
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		this.testInstance.add(testProperty);
		Set<IPropertyDefinition> actualProps = this.testInstance.getVisualisableProperties();
		context.assertIsSatisfied();
		assertEquals("Set is expected size", EMPTY_EXPECTED_SIZE, actualProps.size());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testAddIsNull() {
		this.testInstance.add(null);
	}

}
