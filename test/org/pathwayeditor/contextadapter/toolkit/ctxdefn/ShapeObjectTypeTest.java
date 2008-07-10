package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IPropertyDefinition;


@RunWith(JMock.class)
public class ShapeObjectTypeTest {
	private Mockery context = new JUnit4Mockery();
	private ShapeObjectType testInstance;
	private ShapeObjectType testOtherInstance1;
	private ShapeObjectType testOtherInstance2;
	private ShapeObjectType testOtherInstance3;
	private IContext testContext1;
	private IContext testContext2;
	private static enum TestType { TEST1, TEST2 };
	
	@Before
	public void setUp() throws Exception {
		this.testContext1 = new GeneralContext("123454", "Test1", "Test1", 1, 0, 0);
		this.testContext2 = new GeneralContext("123453636", "Test2", "Test2", 1, 0, 0);
		this.testInstance = new ShapeObjectType(this.testContext1, TestType.TEST1);
		this.testOtherInstance1 = new ShapeObjectType(this.testContext1, TestType.TEST2);
		this.testOtherInstance2 = new ShapeObjectType(this.testContext1, TestType.TEST1);
		this.testOtherInstance3 = new ShapeObjectType(this.testContext2, TestType.TEST1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHashCode() {
		assertEquals("hashCode equals", this.testInstance.hashCode(), this.testOtherInstance2.hashCode());
		boolean typeCodeTest = this.testInstance.hashCode() == this.testOtherInstance1.hashCode();
		boolean ctxTest = this.testInstance.hashCode() == this.testOtherInstance3.hashCode();
		assertFalse("type code not equal", typeCodeTest);
		assertFalse("context not equal", ctxTest);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testShapeObjectTypeNullCtx() {
		new ShapeObjectType(null, TestType.TEST1);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testShapeObjectTypeNullType() {
		new ShapeObjectType(this.testContext1, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testAddPropertyNullParam() {
		this.testInstance.addProperty(null);
	}

	@Test
	public final void testAddProperty() {
		final IPropertyDefinition testProp = this.context.mock(IPropertyDefinition.class);
		this.context.checking(new Expectations(){
		});
		this.testInstance.addProperty(testProp);
		int actualPropCount = this.testInstance.getPropertiesFilter().getAllProperties().size();
		int expectedPropCount = 1;
		this.context.assertIsSatisfied();
		assertEquals("property added", expectedPropCount, actualPropCount);
	}

	@Test
	public final void testGetTypeCode() {
		assertEquals("correct type code", TestType.TEST1.ordinal(), this.testInstance.getTypeCode());
	}

	@Test
	public final void testGetTypeName() {
		assertEquals("correct type name", TestType.TEST1.name(), this.testInstance.getTypeName());
	}

	@Test
	public final void testGetContext() {
		assertEquals("correct context", this.testContext1, this.testInstance.getContext());
	}

	@Test
	public final void testGetPropertiesFilter() {
		assertNotNull("filter initialised", this.testInstance.getPropertiesFilter());
	}

	@Test
	public final void testEqualsObject() {
		assertTrue("equals", this.testInstance.equals(this.testOtherInstance2));
		assertTrue("equals", this.testInstance.equals(this.testInstance));
		assertTrue("equals", this.testOtherInstance2.equals(this.testInstance));
		boolean typeCodeTest = this.testInstance.equals(this.testOtherInstance1);
		boolean ctxTest = this.testInstance.equals(this.testOtherInstance3);
		assertFalse("type code not equal", typeCodeTest);
		assertFalse("context not equal", ctxTest);
		assertFalse("not equal to null", this.testInstance.equals(null));
		assertFalse("not equal to another class", this.testInstance.equals(this));
	}

	@Test
	public final void testGetParentingRules() {
		assertNotNull("parenting rules initialised", this.testInstance.getParentingRules());
	}

}
