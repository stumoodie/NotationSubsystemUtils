package org.pathwayeditor.notationsubsystem.toolkit.definition;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class LinkTerminusDefaultsTest {

	private static final double DELTA = 0.0001;
	private LinkTerminusDefaults obj;
	private LinkEndDecoratorShape src=LinkEndDecoratorShape.ARROW;
	private Dimension srcEndSize=new Dimension(20,20);
	private IPropertyDefinition srcP=new PlainTextPropertyDefinition("test","valie",true,true);
	private double gap= 2.0;
	
	@Before
	public void setUp() throws Exception {
		obj=new LinkTerminusDefaults(null);
		obj.setGap(gap);
		obj.setEndDecoratorType(src);
		obj.setEndSize(srcEndSize);
		obj.addPropertyDefinition(srcP);
	}
	
	@Test
	public void testLinkTerminusDefaults() {
		assertEquals(src, obj.getEndDecoratorType());
		assertEquals(srcEndSize, obj.getEndSize());
		assertEquals(gap, obj.getGap(), DELTA);
		assertEquals(1, obj.getPropertyDefinitionNumber());
	}

}
