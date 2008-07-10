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
import org.pathwayeditor.contextadapter.publicapi.IObjectType;
import org.pathwayeditor.contextadapter.publicapi.IShapeObjectType;


@RunWith(JMock.class)
public class RootMapParentingRulesTest {
	private Mockery context = new JUnit4Mockery();
	private RootMapParentingRules testInstance;
	private IObjectType testObjectType;
//	private static enum TestTypes { TEST1 };
	
	@Before
	public void setUp() throws Exception {
		this.testObjectType = context.mock(IObjectType.class);
		this.testInstance = new RootMapParentingRules(this.testObjectType);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testRootMapParentingRulesNullParam() {
		new RootMapParentingRules(null);
	}

	@Test
	public final void testGetObjectType() {
		IObjectType actual = this.testInstance.getObjectType();
		assertEquals("Object type correct", this.testObjectType, actual);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testAddChildNullParam() {
		this.testInstance.addChild(null);
	}
	
	@Test
	public final void testIsValidChildByCodeOk() {
		final IShapeObjectType testShapeType = this.context.mock(IShapeObjectType.class);
		this.context.checking(new Expectations(){{
//			allowing(testShapeType).getTypeCode();
//			will(returnValue(TestTypes.TEST1));
		}});
		this.testInstance.addChild(testShapeType);
		this.context.assertIsSatisfied();
		assertTrue("child set", this.testInstance.isValidChildByCode(testShapeType));
	}

	@Test
	public final void testIsValidChildByCodeNone() {
		final IShapeObjectType testShapeType = this.context.mock(IShapeObjectType.class);
		this.context.checking(new Expectations(){{
		}});
		this.context.assertIsSatisfied();
		assertFalse("child set", this.testInstance.isValidChildByCode(testShapeType));
	}

}
