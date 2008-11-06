package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeParentingRules;

@RunWith(JMock.class)
public class ShapeObjectTypeTest {
	private Mockery mockery= new JUnit4Mockery();
	private ShapeObjectType shapeObjectType;
	private IShapeAttributeDefaults shapeAttributeDefaults =mockery.mock(IShapeAttributeDefaults.class);
	private int positiveId=1;
	private int negativeId=-1;
	private String description="a";
	private String emptyDescription="";
	private String name="name";
	private String emptyName="";
	private INotationSyntaxService iNotationSyntaxService = mockery.mock(INotationSyntaxService.class);
	
	
	
	@Test (expected =IllegalArgumentException.class )
	public void testShapeAttributeDefaultsNullThrowsException(){
		shapeObjectType=new ShapeObjectType(null, positiveId, description, name, iNotationSyntaxService);
	}
	@Test
	public void testShapeAttributeDefaultsNotNullValid(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults, positiveId, description, name, iNotationSyntaxService);
		assertEquals(shapeAttributeDefaults,shapeObjectType.getDefaultAttributes());
	}
	
	@Test (expected =IllegalArgumentException.class )
	public void testShapeAttributeDefaultsSetToNullThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults, positiveId, description, name, iNotationSyntaxService);
		shapeObjectType.setShapeAttributeDefaults(null);
	}
	
	@Test
	public void testParentingRulesAreNotNull(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults, positiveId, description, name, iNotationSyntaxService);
		IShapeParentingRules notNull=shapeObjectType.getParentingRules();
		assertNotNull(notNull);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUniqueIdNotPositiveThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,negativeId, description, name, iNotationSyntaxService);
	}
	
	@Test
	public void testUniqueIdPositive(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, description, name, iNotationSyntaxService);
		assertEquals(positiveId,shapeObjectType.getUniqueId());
	}
	
	@Test
	public void testDescriptionEmptyStringValid(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService);
		assertEquals(emptyDescription,shapeObjectType.getDescription());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNullDescriptionThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService);
		shapeObjectType.setDescription(null);
	}
	
	@Test
	public void testSetDescriptionValid(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService);
		shapeObjectType.setDescription("not empty");
		assertEquals("not empty",shapeObjectType.getDescription());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullDescriptionThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, null, name, iNotationSyntaxService);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNameNullThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,null, iNotationSyntaxService);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNameEmptyThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,emptyName, iNotationSyntaxService);
	}
	
	@Test
	public void testNameValid(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		assertEquals(name,shapeObjectType.getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNullNotationSyntaxServiceThrowsException(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, null);
	}
	
	@Test
	public void testNotationSyntaxServiceValid(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		assertEquals(iNotationSyntaxService,shapeObjectType.getSyntaxService());
	}
	
	@Test
	public void testHashCodeUsesSyntaxService() {
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType sameSyntaxService = new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		IShapeObjectType differentSyntaxService = new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, differentService);
		assertTrue(shapeObjectType.hashCode()==sameSyntaxService.hashCode());
		assertFalse(shapeObjectType.hashCode()==differentSyntaxService.hashCode());
	}
	
	@Test
	public void testHashCodeUsesUniqueId() {
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType sameId = new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType differentId = new ShapeObjectType(shapeAttributeDefaults,positiveId+1, emptyDescription,name, iNotationSyntaxService);
		assertTrue(shapeObjectType.hashCode()==sameId.hashCode());
		assertFalse(shapeObjectType.hashCode()==differentId.hashCode());
	}
	
	@Test
	public void testEqualsOnId(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType sameId = new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType differentId = new ShapeObjectType(shapeAttributeDefaults,positiveId+1, emptyDescription,name, iNotationSyntaxService);
		assertTrue(shapeObjectType.equals(sameId));
		assertFalse(shapeObjectType.equals(differentId));
	}
	
	@Test
	public void testEqualsOnSyntaxService(){
		shapeObjectType=new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		IShapeObjectType sameSyntaxService = new ShapeObjectType(shapeAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		assertTrue(shapeObjectType.equals(sameSyntaxService));
		assertFalse(shapeObjectType.equals(differentService));
	}
	

}
