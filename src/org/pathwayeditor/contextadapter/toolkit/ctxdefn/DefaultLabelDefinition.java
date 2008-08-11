package org.pathwayeditor.contextadapter.toolkit.ctxdefn;

import org.pathwayeditor.businessobjects.constants.LineStyle;
import org.pathwayeditor.contextadapter.publicapi.ILabelDefinition;


public class DefaultLabelDefinition implements ILabelDefinition {

	private int fillColourBlue = 255;
	private int fillColourGreen = 255;
	private int fillColourRed = 255;
	private int lineColourBlue=0;
	private int lineColourGreen=0;
	private int lineColourRed=0;
	private LineStyle lineStyle=LineStyle.SOLID;
	private int lineWidth=1;
	private boolean hasFill=true; 
	private boolean hasLine=true;

	public DefaultLabelDefinition(){
		
	}
	
	public DefaultLabelDefinition(boolean hasFill, boolean hasLine) {
		this.hasFill = hasFill;
		this.hasLine = hasLine;
	}

	public DefaultLabelDefinition(int fillColourBlue, int fillColourGreen,
			int fillColourRed, int lineColourBlue, int lineColourGreen,
			int lineColourRed, LineStyle lineStyle, int lineWidth,
			boolean hasFill, boolean hasLine) {
		this.fillColourBlue = fillColourBlue;
		this.fillColourGreen = fillColourGreen;
		this.fillColourRed = fillColourRed;
		this.lineColourBlue = lineColourBlue;
		this.lineColourGreen = lineColourGreen;
		this.lineColourRed = lineColourRed;
		this.lineStyle = lineStyle;
		this.lineWidth = lineWidth;
		this.hasFill = hasFill;
		this.hasLine = hasLine;
	}

	public int getFillColourBlue() {
		return fillColourBlue;
	}

	public int getFillColourGreen() {
		return fillColourGreen;
	}

	public int getFillColourRed() {
		return fillColourRed;
	}

	public int getLineColourBlue() {
		return lineColourBlue;
	}

	public int getLineColourGreen() {
		return lineColourGreen;
	}

	public int getLineColourRed() {
		return lineColourRed;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public boolean hasFill() {
		return hasFill;
	}

	public boolean hasLine() {
		return hasLine;
	}

}


/*
 * $Log: DefaultLabelDefinition.java,v $
 * Revision 1.1  2008/07/10 12:06:36  nhanlon
 * extraction of Toolkit project
 *
 * Revision 1.1  2008/06/14 13:18:08  asorokin
 * Configurable Labels. At the moment the only focus was transparency (noFill) and borders(noLine).
 *
 */