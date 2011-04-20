/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package org.pathwayeditor.notationsubsystem.toolkit.ndom;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeAttribute;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

public class GeometryTest {

	private final Mockery mockery = new JUnit4Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private ICompoundGraph mockGraph;
	private ICompoundEdge l;
	private ILinkAttribute lAtt;
	private IShapeAttribute sAtt;
	private IShapeAttribute tAtt;
	private ICompoundNode s;
	private ICompoundNode t;
	private IBendPointContainer bpContainer;

//	private AbstractNDOMParser ndom=new nDOMParserTest.ParserStub();
	
	@Before
	public void setUp() throws Exception {
		mockGraph = mockery.mock(ICompoundGraph.class, "mockGraph");
		l = mockery.mock(ICompoundEdge.class);
		lAtt = mockery.mock(ILinkAttribute.class);
		sAtt = mockery.mock(IShapeAttribute.class);
		tAtt = mockery.mock(IShapeAttribute.class);
		s = mockery.mock(ICompoundNode.class);
		t = mockery.mock(ICompoundNode.class);
		bpContainer = mockery.mock(IBendPointContainer.class, "bpContainer");
		this.mockery.checking(new Expectations() {{
			allowing(s).getGraph(); will(returnValue(mockGraph));
			allowing(s).getAttribute(); will(returnValue(sAtt));
			
			allowing(t).getGraph(); will(returnValue(mockGraph));
			allowing(t).getAttribute(); will(returnValue(tAtt));
			atLeast(1).of(sAtt).getBounds(); will(returnValue(new Envelope(25,25, 20, 20)));
			atLeast(1).of(l).getAttribute(); will(returnValue(lAtt));
			atLeast(1).of(bpContainer).numBendPoints(); will(returnValue(1));
//			atLeast(1).of(bpContainer).bendPointIterator();	will(returnIterator(bpContainer));
			atLeast(1).of(tAtt).getBounds(); will(returnValue(new Envelope(5,25, 20,20)));
			allowing(lAtt).getBendPointContainer(); will(returnValue(bpContainer));
		}});
		this.mockery.checking(new Expectations() {{
			atLeast(1).of(l).getConnectedNodes(); will(returnValue(new CompoundNodePair(s, t)));
		}});
	}

	@After
	public void tearDown() throws Exception {
		this.mockGraph = null;
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationNoBends180() {
		this.mockery.checking(new Expectations() {{
			allowing(bpContainer).bendPointIterator();	will(returnIterator());
		}});
//		this.mockery.checking(new Expectations() {{
//			atLeast(1).of(s).getAttribute(); will(returnValue(sAtt));
//			
//			atLeast(1).of(sAtt).getBounds(); will(returnValue(new Envelope(25,25, 20, 20)));
//			atLeast(1).of(l).getAttribute(); will(returnValue(lAtt));
//			atLeast(1).of(bpContainer).numBendPoints(); will(returnValue(1));
//			atLeast(1).of(bpContainer).bendPointIterator();	will(returnIterator());
//			atLeast(1).of(l).getConnectedNodes(); will(returnValue(new CompoundNodePair(s, t)));
//			atLeast(1).of(t).getAttribute(); will(returnValue(tAtt));
//			atLeast(1).of(tAtt).getBounds(); will(returnValue(new Envelope(5,25, 20,20)));
//		}});
		Point a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Point(-20,0), a);
		this.mockery.assertIsSatisfied();
	}

	/**
	 * Test method for {@link org.pathwayeditor.notationsubsystem.toolkit.ndom.GeometryUtils#getSrcLocation(org.pathwayeditor.businessobjectsAPI.ILinkEdge, org.pathwayeditor.businessobjectsAPI.IShapeNode)}.
	 */
	@Test
	public void testGetSrcLocationBends180() {
//		final ICompoundEdge l=mockery.mock(ICompoundEdge.class, "l");
//		final ICompoundNode s=mockery.mock(ICompoundNode.class, "s");
//		final ILinkAttribute lAtt=mockery.mock(ILinkAttribute.class, "lAtt");
//		final IShapeAttribute sAtt=mockery.mock(IShapeAttribute.class, "sAtt");
//		final IShapeAttribute tAtt=mockery.mock(IShapeAttribute.class, "tAtt");
//		final ICompoundNode t=mockery.mock(ICompoundNode.class, "t");
//		final IBendPointContainer bpContainer = mockery.mock(IBendPointContainer.class, "bpContainer");
//		final ArrayList<Point> bpl = new ArrayList<Point>();
//		bpl.add(new Point(30, 50));
		this.mockery.checking(new Expectations() {{
			allowing(bpContainer).bendPointIterator();	will(returnIterator(new Point(30, 50)));
		}});
		Point a=GeometryUtils.getSrcLocation(l, s);
		assertEquals(new Point(5,25), a);
		this.mockery.assertIsSatisfied();
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