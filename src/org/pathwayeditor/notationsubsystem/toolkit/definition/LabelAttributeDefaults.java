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
package org.pathwayeditor.notationsubsystem.toolkit.definition;

import java.text.Format;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;

public class LabelAttributeDefaults implements ILabelAttributeDefaults {
	private RGB fillColour = new RGB(255, 255, 255);
	private RGB lineColour = new RGB(0, 0, 0);
	private LineStyle lineStyle = LineStyle.SOLID;
	private double lineWidth = 1;
	private LabelLocationPolicy labelLocationPolicy = LabelLocationPolicy.CENTRE;
	private boolean noFill = true;
	private boolean noBorder = true;
	private Dimension minimumSize;
	private Format displayFormat = null;

	public LabelAttributeDefaults() {
	}

	@Override
	public RGB getFillColour() {
		return fillColour;
	}

	public void setFillColour(RGB in) {
		fillColour = in;
	}

	@Override
	public RGB getLineColour() {
		return lineColour;
	}

	public void setLineColour(RGB in) {
		lineColour = in;
	}

	@Override
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle in) {
		lineStyle = in;
	}

	@Override
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double width) {
		lineWidth = width;
	}

    public void setLabelLocationPolicy(LabelLocationPolicy labelLocationPolicy){
    	this.labelLocationPolicy = labelLocationPolicy;
    }
    
	@Override
	public LabelLocationPolicy getLabelLocationPolicy() {
		return this.labelLocationPolicy;
	}

	public void setMinimumSize(Dimension newMinSize){
		this.minimumSize = newMinSize;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return this.minimumSize;
	}

	public void setNoBorder(boolean noBorder){
		this.noBorder = noBorder;
	}
	
	@Override
	public boolean hasNoBorder() {
		return this.noBorder;
	}

	public void setNoFill(boolean noFill){
		this.noFill = noFill;
	}
	
	@Override
	public boolean hasNoFill() {
		return this.noFill;
	}

	public void setDisplayFormat(Format displayFormat){
		this.displayFormat = displayFormat;
	}
	
	@Override
	public Format getDisplayFormat() {
		return this.displayFormat;
	}

}
