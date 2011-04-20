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

import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IValidationRuleStore;


public interface INDOMValidationService {
	
	/**
	 * @return true if during validation an NDOM was constructed
	 */
	public boolean ndomWasCreated();
	
	
	/**
	 * @param map map to be validated by this class
	 */
	public void setMapToValidate(IModel map);

	/**
	 *  implementers must generate a validation report and (if possible) an NDOM during validation 
	 */
	void validateMap();

	boolean isReadyToValidate();
	
	public IValidationReport getValidationReport();
	
	public INdomModel getNDOM();

	public IValidationRuleStore getRuleStore();

	public boolean hasBeenValidated();

	public IModel getMapBeingValidated();

}
