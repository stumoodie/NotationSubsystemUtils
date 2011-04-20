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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;

public class LinkTerminusDefaultsTest {

	private static final double DELTA = 0.0001;
	private LinkTerminusDefaults obj;
	private LinkEndDecoratorShape src=LinkEndDecoratorShape.ARROW;
	private Dimension srcEndSize=new Dimension(20,20);
	private IPropertyDefinition srcP=new PlainTextPropertyDefinition("test","valie",true,true);
	private double gap= 2.0;
	
	@Before
	public void setUp() throws Exception {
		obj=new LinkTerminusDefaults(null);
		obj.setGap(gap);
		obj.setEndDecoratorType(src);
		obj.setEndSize(srcEndSize);
		obj.addPropertyDefinition(srcP);
	}
	
	@Test
	public void testLinkTerminusDefaults() {
		assertEquals(src, obj.getEndDecoratorType());
		assertEquals(srcEndSize, obj.getEndSize());
		assertEquals(gap, obj.getGap(), DELTA);
		assertEquals(1, obj.getPropertyDefinitionNumber());
	}

}
