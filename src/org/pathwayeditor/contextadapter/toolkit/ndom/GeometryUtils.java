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
			/*
	Point a1 = getConnection().getSourceAnchor().getReferencePoint();
	Point a2 = getConnection().getTargetAnchor().getReferencePoint();
	
	Point p = new Point();
	Dimension dim1 = d1.getCopy(), dim2 = d2.getCopy();
	
	getConnection().translateToAbsolute(dim1);
	getConnection().translateToAbsolute(dim2);
	
	p.x = (int)((a1.x + dim1.width) * (1f - weight) + weight * (a2.x + dim2.width));
	p.y = (int)((a1.y + dim1.height) * (1f - weight) + weight * (a2.y + dim2.height));
	getConnection().translateToRelative(p);
	return p;
			 */
			Location sl = s.getCentre();
			Location tl = l.getSource().getCentre();
			List<IBendpoint> bp = l.getBendpoints();
			Location al =null;
			if (bp == null || bp.size() == 0) {
				// calculate center-to-center direction
				tl = l.getSource().getCentre();
			} else {
				// calculate center-to-bend-point direction
				tl = getPoint(l, bp.size()-1);
			}
			al=new Location(tl.getX() - sl.getX(), tl.getY() - sl.getY());
			 
	//		double le=Math.sqrt(al.getX()*al.getX()+al.getY()*al.getY());
			return al;
		}

	private static Location getPoint(ILink l,int i) {
		Location tl;
		Location a1=l.getSource().getCentre();
		Location a2=l.getTarget().getCentre();
		List<IBendpoint> bp = l.getBendpoints();
		IBendpoint bendpoint = bp.get(i);
		Size dim1 = bendpoint.getFirstRelativeDimension();
		Size dim2 = bendpoint.getSecondRelativeDimension();
		float weight=(i + 1) / ((float) bp.size() + 1);
		int x = (int)((a1.getX() + dim1.getWidth()) * (1f - weight) + weight * (a2.getX() + dim2.getWidth()));
		int y = (int)((a1.getY() + dim1.getHeight()) * (1f - weight) + weight * (a2.getY() + dim2.getHeight()));
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
	public static Location getSrcLocation(ILink l, IShape s) {
		Location sl = s.getCentre();
		Location tl = null;
		List<IBendpoint> bp = l.getBendpoints();
		Location al =null;
		if (bp == null || bp.size() == 0) {
			// calculate center-to-center direction
			tl = l.getTarget().getCentre();
		} else {
			// calculate center-to-bend-point direction
			tl = getPoint(l, bp.size()-1);
//			
//			Size bps=bp.get(0).getFirstRelativeDimension();
//			al=new Location(bps.getWidth(),bps.getHeight());
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