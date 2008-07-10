package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IObjectTypeParentingRules;


@RunWith(JMock.class)
public class RootMapObjectTypeTest {
	private Mockery context = new JUnit4Mockery();
	private RootMapObjectType testInstance;
	private IContext testContext;
	private static enum TestTypes { TEST_ROOT }; 
	
	@Before
	public void setUp() throws Exception {
		this.testContext = context.mock(IContext.class);
		this.testInstance = new RootMapObjectType(this.testContext, TestTypes.TEST_ROOT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testRootMapObjectTypeNullType() {
		new RootMapObjectType(this.testContext, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testRootMapObjectTypeNullCtx() {
		new RootMapObjectType(null, TestTypes.TEST_ROOT);
	}

	@Test
	public final void testGetContext() {
		IContext actualCtx = this.testInstance.getContext();
		assertEquals("Context the same", this.testContext, actualCtx);
	}

	@Test
	public final void testGetTypeCode() {
		int actual = this.testInstance.getTypeCode();
		assertEquals("type code correct", TestTypes.TEST_ROOT.ordinal(), actual);
	}

	@Test
	public final void testGetTypeName() {
		String actual = this.testInstance.getTypeName();
		assertEquals("type code correct", TestTypes.TEST_ROOT.name(), actual);
	}

	@Test
	public final void testGetShapeParentingRules() {
		IObjectTypeParentingRules parentingRules = this.testInstance.getShapeParentingRules();
		assertNotNull("parenting rules set", parentingRules);
	}
}
