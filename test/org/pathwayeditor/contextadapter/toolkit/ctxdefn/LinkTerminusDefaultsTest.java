package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class LinkTerminusDefaultsTest {

	private LinkTerminusDefaults templ;
	private LinkTerminusDefaults obj;
	private LinkEndDecoratorShape src=LinkEndDecoratorShape.ARROW;
	private Size srcEndSize=new Size(20,20);
	private IPropertyDefinition srcP=new PlainTextPropertyDefinition("test","valie",true,true);
	private short gap= (short) 2;
	private Size termSize = new Size(0, 0);;
	private PrimitiveShapeType term = PrimitiveShapeType.RECTANGLE;
	private RGB termColor = new RGB(255, 255, 255);
	
	@Before
	public void setUp() throws Exception {
		templ=new LinkTerminusDefaults();
		templ.setTermDecoratorType(term);
		templ.setGap(gap);
		templ.setLinkEndDecoratorShape(src);
		templ.setEndSize(srcEndSize);
		templ.setTermSize(termSize);
		templ.setTermColour(termColor);
		templ.addPropertyDefinition(srcP);
	}
	
	@Test
	public void testLinkTerminusDefaults() {
		obj=new LinkTerminusDefaults(templ);
		assertEquals(src, obj.getEndDecoratorType());
		assertEquals(srcEndSize, obj.getEndSize());
		assertEquals(gap, obj.getGap());
		assertEquals(termSize, obj.getTermSize());
		assertEquals(term, obj.getTermDecoratorType());
		assertEquals(termColor, obj.getTermColour());
		assertEquals(1, obj.getPropertyDefinitionNumber());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testLinkTerminusDefaultsNullTemplate() {
		obj=new LinkTerminusDefaults(null);
	}

}
