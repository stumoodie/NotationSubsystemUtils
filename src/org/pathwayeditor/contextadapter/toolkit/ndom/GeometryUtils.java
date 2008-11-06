package org.pathwayeditor.contextadapter.toolkit.ndom;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ILinkEdge;
import org.pathwayeditor.businessobjects.drawingprimitives.IShapeNode;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;

/**
 * Class contains context-neutral geometry calculation utilities 
 * to be used in parsing of diagrams and validation.
 * <br>$Id:$
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
		public static Location getTgtDirection(ILinkEdge l, IShapeNode s) {
			Location sl = s.getAttribute().getLocation();
			Location tl = l.getSourceShape().getAttribute().getLocation();
			Iterator <IBendPoint> bp = l.getAttribute().bendPointIterator();
			Location al =null;
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
			al=new Location(tl.getX() - sl.getX(), tl.getY() - sl.getY());
			return al;
		}

	private static Location getPoint(ILinkEdge l,IBendPoint last,float weight) {
		Location tl;
		Location a1=l.getSourceShape().getAttribute().getLocation();
		Location a2=l.getTargetShape().getAttribute().getLocation();
		int sourceXOffset = last.getLocation().getX()-l.getSourceShape().getAttribute().getLocation().getX();
		int targetXOffset=last.getLocation().getX()-l.getTargetShape().getAttribute().getLocation().getX();
		int sourceYOffset=last.getLocation().getY()-l.getSourceShape().getAttribute().getLocation().getY();
		int targetYOffset=last.getLocation().getY()-l.getTargetShape().getAttribute().getLocation().getY();
		int x = (int)((a1.getX() +sourceXOffset) * (1f - weight) + weight * (a2.getX() + targetXOffset));
		int y = (int)((a1.getY() + sourceYOffset) * (1f - weight) + weight * (a2.getY() + targetYOffset));
		tl=new Location(x,y);
		return tl;
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
	public static Location getSrcLocation(ILinkEdge l, IShapeNode s) {
		Location sl = s.getAttribute().getLocation();
		Location tl = l.getTargetShape().getAttribute().getLocation();
		Iterator <IBendPoint> bp = l.getAttribute().bendPointIterator();
		Location al =null;
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
		al=new Location(tl.getX() - sl.getX(), tl.getY() - sl.getY());
		return al;
	}

	/**
	 * calculate angle between two vectors. 
	 * @param srcLoc
	 * @param newLoc
	 * @return
	 */
	public static double getAngle(Location srcLoc, Location newLoc) {
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