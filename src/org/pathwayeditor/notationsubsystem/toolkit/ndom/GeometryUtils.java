package org.pathwayeditor.notationsubsystem.toolkit.ndom;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.figure.geometry.Point;
import org.pathwayeditor.figure.geometry.Dimension;

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
		public static Point getTgtDirection(ILinkEdge l, IShapeNode s) {
			Point sl = getCenter(s);//s.getAttribute().getLocation();
			Point tl = getCenter(l.getSourceShape());//.getAttribute().getLocation();
			Iterator <IBendPoint> bp = l.getAttribute().bendPointIterator();
			Point al =null;
			IBendPoint last=null;
			int numBenpoints=l.getAttribute().numBendPoints();
			if (bp.hasNext()) {
				while (bp.hasNext()){
					last =bp.next();
				}
				float weight=numBenpoints / ((float) numBenpoints + 1);
				// calculate center-to-bend-point direction
				tl = getPoint(l,last,weight);
			}
			al=new Point(tl.getX() - sl.getX(), tl.getY() - sl.getY());
			return al;
		}

	static Point getPoint(ILinkEdge l,IBendPoint last,float weight) {
		Point tl;
		Point a1=getCenter(l.getSourceShape());//.getAttribute().getLocation();
		Point a2=getCenter(l.getTargetShape());//.getAttribute().getLocation();
		double sourceXOffset = last.getLocation().getX()-l.getSourceShape().getAttribute().getLocation().getX();
		double targetXOffset=last.getLocation().getX()-l.getTargetShape().getAttribute().getLocation().getX();
		double sourceYOffset=last.getLocation().getY()-l.getSourceShape().getAttribute().getLocation().getY();
		double targetYOffset=last.getLocation().getY()-l.getTargetShape().getAttribute().getLocation().getY();
		double x = ((a1.getX() +sourceXOffset) * (1f - weight) + weight * (a2.getX() + targetXOffset));
		double y = ((a1.getY() + sourceYOffset) * (1f - weight) + weight * (a2.getY() + targetYOffset));
		tl=new Point(x,y);
		return tl;
	}
	
	static Point getCenter(IShapeNode s){
		Point sl = s.getAttribute().getLocation();
		Dimension sz=s.getAttribute().getSize();
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
	public static Point getSrcLocation(ILinkEdge l, IShapeNode s) {
		Point sl = getCenter(s);//s.getAttribute().getLocation();
		Point tl = getCenter(l.getTargetShape());//.getAttribute().getLocation();
		Iterator <IBendPoint> bp = l.getAttribute().bendPointIterator();
		Point al =null;
		IBendPoint last=null;
		int numBenpoints=l.getAttribute().numBendPoints();
		if (bp.hasNext()) {
			while (bp.hasNext()){
				last =bp.next();
			}
			float weight=numBenpoints / ((float) numBenpoints + 1);
			// calculate center-to-bend-point direction
			tl = getPoint(l,last,weight);
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