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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPointContainer;
import org.pathwayeditor.businessobjects.drawingprimitives.IDrawingNodeAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkAttribute;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Point;

import uk.ac.ed.inf.graph.compound.CompoundNodePair;
import uk.ac.ed.inf.graph.compound.ICompoundEdge;
import uk.ac.ed.inf.graph.compound.ICompoundNode;

/**
 * Class contains context-neutral geometry calculation utilities 
 * to be used in parsing of diagrams and validation.
 * <br>$Id$
 * @author Anatoly Sorokin
 * @date 5 Aug 2008
 * 
 */
public class GeometryUtils {

	/**
		 * Calculate location from that link point to shape. Method will return coordinates of unit
		 * vector collinear to the last segment of the link.<br>
		 * Precondition:<br>
		 * shape <code>s</code> is target for link <code>l</code>.
		 * 
		 * @param l
		 *            link
		 * @param s
		 *            source shape
		 * @return direction of the last segment of the link.
		 */
		public static Point getTgtDirection(ICompoundEdge l, ICompoundNode s) {
			Point sl = getCenter(s);//s.getAttribute().getLocation();
			CompoundNodePair nodePair = l.getConnectedNodes();
			Point tl = getCenter(nodePair.getOutNode());//.getAttribute().getLocation();
			IBendPointContainer bpContainer = ((ILinkAttribute)l.getAttribute()).getBendPointContainer();
			Iterator <Point> bp = bpContainer.bendPointIterator();
			Point al =null;
			Point last=null;
			int numBenpoints = bpContainer.numBendPoints();
			if (bp.hasNext()) {
				while (bp.hasNext()){
					last =bp.next();
				}
				float weight=numBenpoints / ((float) numBenpoints + 1);
				// calculate center-to-bend-point direction
				tl = getPoint(l.getConnectedNodes(), last, weight);
			}
			al=new Point(tl.getX() - sl.getX(), tl.getY() - sl.getY());
			return al;
		}

	static Point getPoint(CompoundNodePair l, Point last,float weight) {
		Point tl;
		Point a1=getCenter(l.getOutNode());//.getAttribute().getLocation();
		Point a2=getCenter(l.getInNode());//.getAttribute().getLocation();
		IDrawingNodeAttribute outAttribute = (IDrawingNodeAttribute)l.getOutNode().getAttribute();
		IDrawingNodeAttribute inAttribute = (IDrawingNodeAttribute)l.getInNode().getAttribute();
		double sourceXOffset = last.getX() - outAttribute.getBounds().getOrigin().getX();
		double targetXOffset=last.getX() - inAttribute.getBounds().getOrigin().getX();
		double sourceYOffset=last.getY() - outAttribute.getBounds().getOrigin().getY();
		double targetYOffset=last.getY() - inAttribute.getBounds().getOrigin().getY();
		double x = ((a1.getX() +sourceXOffset) * (1f - weight) + weight * (a2.getX() + targetXOffset));
		double y = ((a1.getY() + sourceYOffset) * (1f - weight) + weight * (a2.getY() + targetYOffset));
		tl=new Point(x,y);
		return tl;
	}
	
	static Point getCenter(ICompoundNode s){
		IDrawingNodeAttribute attribute = (IDrawingNodeAttribute)s.getAttribute();
		Point sl = attribute.getBounds().getOrigin();
		Dimension sz = attribute.getBounds().getDimension();
		Point cl=new Point(sl.getX()+sz.getWidth()/2, sl.getY()+sz.getHeight()/2);
		return cl;
	}

	/**
	 * Calculate location that link point from shape. Method will return coordinates of unit
	 * vector collinear to the first segment of the link.<br>
	 * Precondition:<br>
	 * shape <code>s</code> is source for link <code>l</code>.
	 * 
	 * @param l
	 *            link
	 * @param s
	 *            source shape
	 * @return direction of the first segment of the link.
	 */
	public static Point getSrcLocation(ICompoundEdge l, ICompoundNode s) {
		Point sl = getCenter(s);//s.getAttribute().getLocation();
		Point tl = getCenter(l.getConnectedNodes().getInNode());//.getAttribute().getLocation();
		ILinkAttribute linkAttribute = (ILinkAttribute)l.getAttribute();
		IBendPointContainer bpContainer = linkAttribute.getBendPointContainer();
		Iterator <Point> bp = bpContainer.bendPointIterator();
		Point al =null;
		Point last=null;
		int numBenpoints = bpContainer.numBendPoints();
		if (bp.hasNext()) {
			while (bp.hasNext()){
				last =bp.next();
			}
			float weight=numBenpoints / ((float) numBenpoints + 1);
			// calculate center-to-bend-point direction
			tl = getPoint(l.getConnectedNodes(), last, weight);
		}
		al=new Point(tl.getX() - sl.getX(), tl.getY() - sl.getY());
		return al;
	}

	/**
	 * calculate angle between two vectors. 
	 * @param srcLoc
	 * @param newLoc
	 * @return
	 */
	public static double getAngle(Point srcLoc, Point newLoc) {
		double angle = 0;
		double prod = srcLoc.getX() * newLoc.getX() + srcLoc.getY()
				* newLoc.getY();
		angle = prod
				/ Math.sqrt((srcLoc.getX() * srcLoc.getX() + srcLoc.getY()
						* srcLoc.getY())
						* (newLoc.getX() * newLoc.getX() + newLoc.getY()
								* newLoc.getY()));
		return angle;
	}

}


/*
 * $Log:$
 */