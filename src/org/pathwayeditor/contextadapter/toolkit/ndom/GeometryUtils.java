package org.pathwayeditor.contextadapter.toolkit.ndom;

import java.util.List;

import org.pathwayeditor.businessobjectsAPI.IBendpoint;
import org.pathwayeditor.businessobjectsAPI.ILink;
import org.pathwayeditor.businessobjectsAPI.IShape;
import org.pathwayeditor.businessobjectsAPI.Location;
import org.pathwayeditor.businessobjectsAPI.Size;

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
		public static Location getTgtDirection(ILink l, IShape s) {
			Location sl = s.getCentre();
			Location tl = null;
			List<IBendpoint> bp = l.getBendpoints();
			Location al =null;
			if (bp == null || bp.size() == 0) {
				// calculate center-to-center direction
				tl = l.getSource().getCentre();
				al=new Location(tl.getX() - sl.getX(), tl.getY() - sl.getY());
			} else {
				// calculate center-to-bend-point direction
				Size bps=bp.get(bp.size() - 1).getSecondRelativeDimension();
				al=new Location(-1*bps.getWidth(),-1*bps.getHeight());
			}
			 
	//		double le=Math.sqrt(al.getX()*al.getX()+al.getY()*al.getY());
			return al;
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
	public static Location getSrcLocation(ILink l, IShape s) {
		Location sl = s.getCentre();
		Location tl = null;
		List<IBendpoint> bp = l.getBendpoints();
		Location al =null;
		if (bp == null || bp.size() == 0) {
			// calculate center-to-center direction
			tl = l.getTarget().getCentre();
			al=new Location(tl.getX() - sl.getX(), tl.getY() - sl.getY());
		} else {
			// calculate center-to-bend-point direction
			Size bps=bp.get(0).getFirstRelativeDimension();
			al=new Location(bps.getWidth(),bps.getHeight());
		}
		 
	
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