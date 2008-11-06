package org.pathwayeditor.contextadapter.toolkit.ndom;

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
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;

public class GeometryTest {

	private Mockery mockery = new JUnit4Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};

	private AbstractNDOMParser ndom=new TestNDOMParser.ParserStub();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationNoBends180() {
		final ILinkEdge l=mockery.mock(ILinkEdge.class);
		final IShapeNode s=mockery.mock(IShapeNode.class);
		final IShapeNode t=mockery.mock(IShapeNode.class);
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(s).getAttribute().getLocation();
			will(returnValue(new Location(25,25)));
			atLeast(1).of(l).getBendpointsI();
			will(returnValue(new ArrayList<IBendpoint>()));
			one(l).getTarget();
			will(returnValue(t));
			atLeast(1).of(t).getCentre();
			will(returnValue(new Location(5,25)));
		}});
		Location a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Location(-20,0), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationBends180() {
		final ILinkEdge l=mockery.mock(ILinkEdge.class);
		final IShapeNode s=mockery.mock(IShapeNode.class);
		final ArrayList<IBendpoint> bpl = new ArrayList<IBendpoint>();
		final IBendpoint bp = this.mockery.mock(IBendpoint.class);
		bpl.add(bp);
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(s).getCentre();
			will(returnValue(new Location(25,25)));
			atLeast(1).of(l).getBendpoints();
			will(returnValue(bpl));
			atLeast(1).of(bp).getFirstRelativeDimension();
			will(returnValue(new Size(5,25)));
		}});
		Location a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Location(5,25), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.GeometryUtils#getAngle(org.pathwayeditor.businessobjectsAPI.Location, org.pathwayeditor.businessobjectsAPI.Location)}.
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