package org.pathwayeditor.contextadapter.toolkit.ndom;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.pathwayeditor.businessobjectsAPI.IBendpoint;
import org.pathwayeditor.businessobjectsAPI.ILink;
import org.pathwayeditor.businessobjectsAPI.IShape;
import org.pathwayeditor.businessobjectsAPI.Location;
import org.pathwayeditor.businessobjectsAPI.Size;

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
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILink, org.pathwayeditor.businessobjectsAPI.IShape)}.
	 */
	@Test
	public void testGetSrcLocationNoBends180() {
		final ILink l=mockery.mock(ILink.class);
		final IShape s=mockery.mock(IShape.class);
		final IShape t=mockery.mock(IShape.class);
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(s).getCentre();
			will(returnValue(new Location(25,25)));
			atLeast(1).of(l).getBendpoints();
			will(returnValue(new ArrayList<IBendpoint>()));
			one(l).getTarget();
			will(returnValue(t));
			atLeast(1).of(t).getCentre();
			will(returnValue(new Location(5,25)));
		}});
		Location a=ndom.getSrcLocation(l, s);
		assertEquals(new Location(-20,0), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILink, org.pathwayeditor.businessobjectsAPI.IShape)}.
	 */
	@Test
	public void testGetSrcLocationBends180() {
		final ILink l=mockery.mock(ILink.class);
		final IShape s=mockery.mock(IShape.class);
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
		Location a=ndom.getSrcLocation(l, s);
		assertEquals(new Location(5,25), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.contextadapter.toolkit.ndom.AbstractNDOMParser#getAngle(org.pathwayeditor.businessobjectsAPI.Location, org.pathwayeditor.businessobjectsAPI.Location)}.
	 */
	@Ignore
	@Test
	public void testGetAngle() {
		fail("Not yet implemented"); // TODO
	}


	
}


/*
 * $Log$
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