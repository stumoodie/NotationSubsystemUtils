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
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;


@RunWith(JMock.class)
public class LinkObjectTypeTest {

	private Mockery mockery= new JUnit4Mockery();
	private LinkObjectType lot;
	private ILinkAttributeDefaults linkAttributeDefaults =mockery.mock(ILinkAttributeDefaults.class);
	private ILinkTerminusDefaults linkTerminusDefinition = mockery.mock(ILinkTerminusDefaults.class);
	private int positiveId=1;
	private int negativeId=-1;
	private String description="a";
	private String emptyDescription="";
	private String name="name";
	private String emptyName="";
	private INotationSyntaxService iNotationSyntaxService= mockery.mock(INotationSyntaxService.class);
	
	
	@Test (expected =IllegalArgumentException.class )
	public void testLinkAttributeDefaultsNullThrowsException(){
		lot=new LinkObjectType(null, positiveId, description, name, iNotationSyntaxService, linkTerminusDefinition);
	}
	@Test
	public void testLinkAttributeDefaultsNotNullValid(){
		lot=new LinkObjectType(linkAttributeDefaults, positiveId, description, name, iNotationSyntaxService, linkTerminusDefinition);
		assertEquals(linkAttributeDefaults,lot.getDefaultLinkAttributes());
	}
	
	@Test (expected =IllegalArgumentException.class )
	public void testLinkAttributeDefaultsSetToNullThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults, positiveId, description, name, iNotationSyntaxService, linkTerminusDefinition);
		lot.setDefaultLinkAttributes(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUniqueIdNotPositiveThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,negativeId, description, name, iNotationSyntaxService, linkTerminusDefinition);
	}
	
	@Test
	public void testUniqueIdPositive(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, description, name, iNotationSyntaxService, linkTerminusDefinition);
		assertEquals(positiveId,lot.getUniqueId());
	}
	
	@Test
	public void testDescriptionEmptyStringValid(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService, linkTerminusDefinition);
		assertEquals(emptyDescription,lot.getDescription());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNullDescriptionThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService, linkTerminusDefinition);
		lot.setDescription(null);
	}
	
	@Test
	public void testSetDescriptionValid(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription, name, iNotationSyntaxService, linkTerminusDefinition);
		lot.setDescription("not empty");
		assertEquals("not empty",lot.getDescription());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullDescriptionThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, null, name, iNotationSyntaxService, linkTerminusDefinition);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testNameNullThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,null, iNotationSyntaxService, linkTerminusDefinition);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNameEmptyThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,emptyName, iNotationSyntaxService, linkTerminusDefinition);
	}
	
	@Test
	public void testNameValid(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertEquals(name,lot.getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNullNotationSyntaxServiceThrowsException(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, null, linkTerminusDefinition);
	}
	
	@Test
	public void testNotationSyntaxServiceValid(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertEquals(iNotationSyntaxService, lot.getSyntaxService());
	}
	
	@Test
	public void testHashCodeUsesSyntaxService() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType sameSyntaxService = new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		ILinkObjectType differentSyntaxService = new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, differentService, linkTerminusDefinition);
		assertTrue(lot.hashCode()==sameSyntaxService.hashCode());
		assertFalse(lot.hashCode()==differentSyntaxService.hashCode());
	}
	
	@Test
	public void testHashCodeUsesUniqueId() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType sameId = new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType differentId = new LinkObjectType(linkAttributeDefaults,positiveId+1, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertTrue(lot.hashCode()==sameId.hashCode());
		assertFalse(lot.hashCode()==differentId.hashCode());
	}
	
	@Test
	public void testEqualsOnId(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType sameId = new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType differentId = new LinkObjectType(linkAttributeDefaults,positiveId+1, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertTrue(lot.equals(sameId));
		assertFalse(lot.equals(differentId));
	}
	
	@Test
	public void testEqualsOnSyntaxService(){
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		ILinkObjectType sameSyntaxService = new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		INotationSyntaxService differentService = mockery.mock(INotationSyntaxService.class);
		assertTrue(lot.equals(sameSyntaxService));
		assertFalse(lot.equals(differentService));
	}
	
	@Test
	public final void testGetLinkConnectionRulesIsNotNullByDefault() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getLinkConnectionRules());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public final void testSetLinkConnectionRulesNullThrowsException() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getLinkConnectionRules());
		lot.setLinkConnectionRules(null);
	}

	@Test
	public final void testGetSourceTerminusDefinitionIsNotNullByDefault() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getSourceTerminusDefinition());
	}
	
	@Test
	public final void testGetTargetTerminusDefinitionIsNotNullByDefault() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getTargetTerminusDefinition());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testSetSourceTerminusDefinitionNotThrowsException() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getSourceTerminusDefinition());
		lot.setSourceTerminusDefinition(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testSetTargetTerminusDefinitionNullThrowsException() {
		lot=new LinkObjectType(linkAttributeDefaults,positiveId, emptyDescription,name, iNotationSyntaxService, linkTerminusDefinition);
		assertNotNull(lot.getTargetTerminusDefinition());
		lot.setTargetTerminusDefinition(null);
	}

}
