package org.pathwayeditor.notationsubsystem.toolkit.definition;

import static org.junit.Assert.assertNotNull;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;

//Note - the bulk of the class uses AbstractObjectType and this is tested elsewhere

@RunWith(JMock.class)
public class RootObjectTypeTest {
	private Mockery mockery= new JUnit4Mockery();
	private int positiveId=1;
	private String description="a";
	private String name="name";
	private INotationSyntaxService iNotationSyntaxService = mockery.mock(INotationSyntaxService.class);
	private IRootObjectType rootObjectType = new RootObjectType(positiveId,description,name,iNotationSyntaxService);

	@Test
	public final void testShapeParentingRulesAreNotNullByDefault() {
		assertNotNull(rootObjectType.getParentingRules());
	}
}
