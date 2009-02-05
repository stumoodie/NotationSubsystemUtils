
package org.pathwayeditor.notationsubsystem.toolkit.definition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;


/**
 * @author smoodie
 *
 */
@RunWith(JMock.class)
public class LinkConnectionRulesTest {
	static enum TestTypes { ONE, TWO, THREE };
	private LinkConnectionRules testInstance;
	private final Mockery mockery = new JUnit4Mockery();
	private IShapeObjectType typeOne; 
	private IShapeObjectType typeTwo; 
	private IShapeObjectType typeThree; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		typeOne = mockery.mock(IShapeObjectType.class, "typeOne");
		typeTwo = mockery.mock(IShapeObjectType.class, "typeTwo");
//		mockery.checking(new Expectations(){{
//			allowing(typeOne).getTypeCode(); will(returnValue(typeOne));
//		}
//		});
		typeThree = mockery.mock(IShapeObjectType.class, "typeThree");
//		mockery.checking(new Expectations(){{
//			allowing(typeThree).getTypeCode(); will(returnValue(typeTwo));
//		}
//		});
		final ILinkObjectType link = mockery.mock(ILinkObjectType.class);
		this.testInstance = new LinkConnectionRules(link);
	}

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.definition.LinkConnectionRules#LinkConnectionRules()}.
	 * Test uninitialised state
	 */
	@Test
	public final void testLinkConnectionRules() {
//		final ILinkObjectType link = mockery.mock(ILinkObjectType.class);
//		final IShapeObjectType typeOne = mockery.mock(IShapeObjectType.class);
//		final IShapeObjectType typeThree = mockery.mock(IShapeObjectType.class);
//		mockery.checking(new Expectations(){{
////			allowing(typeOne).getTypeCode(); will(returnValue(typeOne));
//		}});
//		this.testInstance = new LinkConnectionRules(link);
		assertFalse("no valid source set, yet", this.testInstance.isValidSource(typeOne));
		assertFalse("no valid target set, yet", this.testInstance.isValidTarget(typeOne, typeThree));
	}

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.definition.LinkConnectionRules#addConnection(org.pathwayeditor.contextadapter.publicapi.IShapeObjectType, org.pathwayeditor.contextadapter.publicapi.IShapeObjectType)}.
	 * Test that methods connections are set up correctly
	 */
	@Test
	public final void testAddConnection() {
		this.testInstance.addConnection(typeOne, typeTwo);
		assertTrue("source set correctly", this.testInstance.isValidSource(typeOne));
		assertFalse("source set correctly", this.testInstance.isValidSource(typeTwo));
		assertTrue("target set correctly", this.testInstance.isValidTarget(typeOne, typeTwo));
		assertFalse("target set correctly", this.testInstance.isValidTarget(typeTwo, typeOne));
	}
	
	 @Test(expected=IllegalArgumentException.class)
	 public final void testAddConnectionNullSrc() {
		this.testInstance.addConnection(null, typeThree);
	 }

	 @Test(expected=IllegalArgumentException.class)
	 public final void testAddConnectionNullTgt() {
		this.testInstance.addConnection(typeOne, null);
	 }

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.definition.LinkConnectionRules#isValidSource(java.lang.Enum)}.
	 */
	@Test
	public final void testIsValidSource() {
		assertFalse("no valid source set, yet", this.testInstance.isValidSource(typeOne));
		this.testInstance.addConnection(typeOne, typeThree);
		assertTrue("source set correctly", this.testInstance.isValidSource(typeOne));
		assertFalse("source set correctly", this.testInstance.isValidSource(typeThree));
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testNullIsValidSource() {
		this.testInstance.isValidSource(null);
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.definition.LinkConnectionRules#isValidTarget(java.lang.Enum, java.lang.Enum)}.
	 */
	@Test
	public final void testIsValidTarget() {
		assertFalse("no valid target set, yet", this.testInstance.isValidTarget(typeOne, typeTwo));
		this.testInstance.addConnection(typeOne, typeThree);
		assertTrue("target set correctly", this.testInstance.isValidTarget(typeOne, typeThree));
		assertFalse("target set correctly", this.testInstance.isValidTarget(typeTwo, typeOne));
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testNullIsValidTargetNullSource() {
		this.testInstance.isValidTarget(null, typeOne);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testNullIsValidTargetNullTarget() {
		this.testInstance.isValidTarget(typeThree, null);
	}
	
}
