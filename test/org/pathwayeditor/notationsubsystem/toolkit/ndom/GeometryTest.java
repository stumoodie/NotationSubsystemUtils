package org.pathwayeditor.notationsubsystem.toolkit.ndom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.Dimension;

public class GeometryTest {

	private Mockery mockery = new JUnit4Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};

//	private AbstractNDOMParser ndom=new nDOMParserTest.ParserStub();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationNoBends180() {
		final ILinkEdge l=mockery.mock(ILinkEdge.class);
		final ILinkAttribute lAtt=mockery.mock(ILinkAttribute.class);
		final IShapeAttribute sAtt=mockery.mock(IShapeAttribute.class);
		final IShapeAttribute tAtt=mockery.mock(IShapeAttribute.class);
		final IShapeNode s=mockery.mock(IShapeNode.class);
		final IShapeNode t=mockery.mock(IShapeNode.class);
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(s).getAttribute();
			will(returnValue(sAtt));
			atLeast(1).of(sAtt).getLocation();
			will(returnValue(new Point(25,25)));
			atLeast(1).of(sAtt).getSize();
			will(returnValue(new Dimension(20,20)));
			atLeast(1).of(l).getAttribute();
			will(returnValue(lAtt));
			atLeast(1).of(lAtt).numBendPoints();
			will(returnValue(0));
			atLeast(1).of(lAtt).bendPointIterator();
			will(returnIterator(new ArrayList<IBendPoint>()));
			one(l).getTargetShape();
			will(returnValue(t));
			atLeast(1).of(t).getAttribute();
			will(returnValue(tAtt));
			atLeast(1).of(tAtt).getLocation();
			will(returnValue(new Point(5,25)));
			atLeast(1).of(tAtt).getSize();
			will(returnValue(new Dimension(20,20)));
		}});
		Point a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Point(-20,0), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationBends180() {
		final ILinkEdge l=mockery.mock(ILinkEdge.class);
		final IShapeNode s=mockery.mock(IShapeNode.class);
		final ILinkAttribute lAtt=mockery.mock(ILinkAttribute.class);
		final IShapeAttribute sAtt=mockery.mock(IShapeAttribute.class);
		final IShapeAttribute tAtt=mockery.mock(IShapeAttribute.class);
		final IShapeNode t=mockery.mock(IShapeNode.class);
		final ArrayList<IBendPoint> bpl = new ArrayList<IBendPoint>();
		final IBendPoint bp = this.mockery.mock(IBendPoint.class);
		bpl.add(bp);
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(s).getAttribute();
			will(returnValue(sAtt));
			atLeast(1).of(sAtt).getLocation();
			will(returnValue(new Point(25,25)));
			atLeast(1).of(sAtt).getSize();
			will(returnValue(new Dimension(20,20)));
			atLeast(1).of(l).getAttribute();
			will(returnValue(lAtt));
			atLeast(1).of(lAtt).numBendPoints();
			will(returnValue(1));
			atLeast(1).of(lAtt).bendPointIterator();
			will(returnIterator(bpl));
			atLeast(1).of(bp).getLocation();
			will(returnValue(new Point(30,50)));
			atLeast(1).of(l).getTargetShape();
			will(returnValue(t));
			atLeast(1).of(l).getSourceShape();
			will(returnValue(s));
			atLeast(1).of(t).getAttribute();
			will(returnValue(tAtt));
			atLeast(1).of(tAtt).getLocation();
			will(returnValue(new Point(5,25)));
			atLeast(1).of(tAtt).getSize();
			will(returnValue(new Dimension(20,20)));
		}});
		Point a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Point(5,25), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.ndom.GeometryUtils#getAngle(org.pathwayeditor.businessobjectsAPI.Point, org.pathwayeditor.businessobjectsAPI.Point)}.
	 */
	@Ignore
	@Test
	public void testGetAngle() {
		fail("Not yet implemented"); // TODO
	}


	
}


/*
 * $Log: GeometryTest.java,v $
 * Revision 1.1  2008/07/10 12:06:37  nhanlon
 * extraction of Toolkit project
 *
 * Revision 1.4  2008/06/27 13:18:46  radams
 * *** empty log message ***
 *
 * Revision 1.3  2008/06/06 11:50:43  asorokin
 * *** empty log message ***
 *
 * Revision 1.1  2008/06/02 10:27:40  asorokin
 * NDOM facility
 *
 */