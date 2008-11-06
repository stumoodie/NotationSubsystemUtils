package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


@RunWith(JMock.class)
public class ShapeParentingRulesTest {
	private Mockery context = new JUnit4Mockery();
	private ShapeParentingRules testInstance;
	private IShapeObjectType testOwningInstance;
//	private enum ObjectType { TEST1{ public String toString() { return "1"; } }};
	
	@Before
	public void setUp() throws Exception {
		this.testOwningInstance = this.context.mock(IShapeObjectType.class);
		this.testInstance = new ShapeParentingRules(this.testOwningInstance);
	}

	@After
	public void tearDown() throws Exception {
		this.testInstance = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testShapeParentingRulesNullParam() {
		new ShapeParentingRules(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testAddChildNullParam() {
		this.testInstance.addChild(null);
	}

	@Test
	public final void testAddChild() {
		final IShapeObjectType shapeType = this.context.mock(IShapeObjectType.class);
		this.context.checking(new Expectations(){{
//			allowing(shapeType).getTypeCode();
//			will(returnValue(ObjectType.TEST1));
		}});
		this.testInstance.addChild(shapeType);
		boolean actualResult = this.testInstance.isValidChildByCode(shapeType);
		assertEquals("child found", true, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testIsValidParentByCodeNull() {
		this.testInstance.isValidChildByCode(null);
	}

	@Test
	public final void testGetObjectType() {
		assertEquals("objectType the same", this.testOwningInstance, this.testInstance.getObjectType());
	}

}
