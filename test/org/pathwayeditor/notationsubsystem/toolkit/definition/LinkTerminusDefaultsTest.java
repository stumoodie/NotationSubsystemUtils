package org.pathwayeditor.notationsubsystem.toolkit.definition;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.notationsubsystem.toolkit.definition.LinkTerminusDefaults;
import org.pathwayeditor.notationsubsystem.toolkit.definition.PlainTextPropertyDefinition;

public class LinkTerminusDefaultsTest {

	private LinkTerminusDefaults obj;
//	private LinkTerminusDefaults obj;
	private LinkEndDecoratorShape src=LinkEndDecoratorShape.ARROW;
	private Size srcEndSize=new Size(20,20);
	private IPropertyDefinition srcP=new PlainTextPropertyDefinition("test","valie",true,true);
	private short gap= (short) 2;
	private Size termSize = new Size(0, 0);;
	private PrimitiveShapeType term = PrimitiveShapeType.RECTANGLE;
	private RGB termColor = new RGB(255, 255, 255);
	
	@Before
	public void setUp() throws Exception {
		obj=new LinkTerminusDefaults(null);
		obj.setTermDecoratorType(term);
		obj.setGap(gap);
		obj.setEndDecoratorType(src);
		obj.setEndSize(srcEndSize);
		obj.setTermSize(termSize);
		obj.setTermColour(termColor);
		obj.addPropertyDefinition(srcP);
	}
	
	@Test
	public void testLinkTerminusDefaults() {
		assertEquals(src, obj.getEndDecoratorType());
		assertEquals(srcEndSize, obj.getEndSize());
		assertEquals(gap, obj.getGap());
		assertEquals(termSize, obj.getTermSize());
		assertEquals(term, obj.getTermDecoratorType());
		assertEquals(termColor, obj.getTermColour());
		assertEquals(1, obj.getPropertyDefinitionNumber());
	}

}
