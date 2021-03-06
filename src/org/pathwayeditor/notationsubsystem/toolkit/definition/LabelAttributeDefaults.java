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

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.rendering.GenericFont;

public class LabelAttributeDefaults implements ILabelAttributeDefaults {
	private static final GenericFont DEFAULT_FONT = new GenericFont();
	private Colour fillColour = new Colour(255, 255, 255);
	private Colour lineColour = new Colour(0, 0, 0);
	private LineStyle lineStyle = LineStyle.SOLID;
	private double lineWidth = 1;
	private LabelLocationPolicy labelLocationPolicy = LabelLocationPolicy.CENTRE;
	private Dimension minimumSize;
	private Format displayFormat = null;
	private GenericFont font = DEFAULT_FONT;
	private Colour fontColour = Colour.BLACK;

	public LabelAttributeDefaults() {
	}

	@Override
	public Colour getFillColour() {
		return fillColour;
	}

	public void setFillColour(Colour in) {
		fillColour = in;
	}

	@Override
	public Colour getLineColour() {
		return lineColour;
	}

	public void setLineColour(Colour in) {
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

	public void setDisplayFormat(Format displayFormat){
		this.displayFormat = displayFormat;
	}
	
	@Override
	public Format getDisplayFormat() {
		return this.displayFormat;
	}

	public void setFontColour(Colour fontColour){
		this.fontColour = fontColour;
	}
	
	@Override
	public Colour getFontColour() {
		return fontColour;
	}

	public void setFont(GenericFont font){
		this.font = font;
	}
	
	@Override
	public GenericFont getFont() {
		return font;
	}

}
