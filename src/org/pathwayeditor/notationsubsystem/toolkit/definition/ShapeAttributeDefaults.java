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

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Colour;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.rendering.GenericFont;

public class ShapeAttributeDefaults implements IShapeAttributeDefaults {
    private static final double DEFAULT_LINE_WIDTH = 1.0;
	private static final GenericFont DEFAULT_FONT = new GenericFont();
	private String shapeType;
    private Colour fillColour = Colour.WHITE;
    private Colour lineColour = Colour.BLACK;
    private LineStyle lineStyle = LineStyle.SOLID;
    private double lineWidth = DEFAULT_LINE_WIDTH; 
    private Dimension size;
	private final PropertyDefinitionContainer properties = new PropertyDefinitionContainer();
	private Colour fontColour = Colour.BLACK;
	private GenericFont font = DEFAULT_FONT;
    
    public ShapeAttributeDefaults() {
    }
    
    @Override
	public Colour getFillColour() {
        return this.fillColour;
    }

    @Override
	public Colour getLineColour() {
        return this.lineColour;
    }

    @Override
	public LineStyle getLineStyle() {
        return this.lineStyle;
    }

    @Override
	public double getLineWidth() {
        return this.lineWidth;
    }

    @Override
	public String getShapeDefinition() {
        return this.shapeType;
    }

    @Override
	public Dimension getSize() {
        return this.size;
    }

    public void addPropertyDefinition(IPropertyDefinition defn) {
        this.properties.addDefinition(defn);
    }
    
    public void setShapeDefinition(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setFillColour(Colour fillColour) {
        this.fillColour = fillColour;
    }

    public void setLineColour(Colour lineColour) {
        this.lineColour = lineColour;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

	@Override
	public boolean containsPropertyDefinition(String name) {
		return this.properties.containsPropertyDefinition(name);
	}

	@Override
	public IPropertyDefinition getPropertyDefinition(String name) {
		return this.properties.getPropertyDefinition(name);
	}

	@Override
	public int numPropertyDefinitions() {
		return this.properties.numPropertyDefinitions();
	}

	@Override
	public boolean containsPropertyDefinition(IPropertyDefinition defn) {
		return this.properties.containsPropertyDefinition(defn);
	}

	@Override
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		return this.properties.propertyDefinitionIterator();
	}
	
	public void setFontColour(Colour fontColour) {
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
