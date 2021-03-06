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
package org.pathwayeditor.notationsubsystem.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;

@RunWith(JMock.class)
public class ValidationRuleConfigTest {
	IValidationRuleConfig configAPI;
	IValidationRuleConfig configImpl = configAPI;
	IValidationRuleDefinition MANDATORY, OPTIONAL;
	Mockery mockery = new JUnit4Mockery();

	final INotationValidationService notationValidationService = mockery.mock(INotationValidationService.class);

	@Before
	public void setUp() throws Exception {
		mockery.checking(new Expectations() {
			{
				allowing(notationValidationService).getNotationSubsystem();
			}
		});
		MANDATORY = createMandatoryRuleDefinition();
		OPTIONAL = createOptionalRuleDefinition();
	}

	private IValidationRuleDefinition createMandatoryRuleDefinition() {
		return new ValidationRuleDefinition("MANDATORY", "LAYOUT", 1, RuleLevel.MANDATORY, RuleEnforcement.ERROR);
	}

	private IValidationRuleDefinition createOptionalRuleDefinition() {
		return new ValidationRuleDefinition("OPTIONAL", "LAYOUT", 2, RuleLevel.OPTIONAL, RuleEnforcement.ERROR);
	}

	@Test
	public void testValidationRuleConfig() {
		configAPI = new ValidationRuleConfig(MANDATORY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidationRuleConfigFailsIfRuleIsNull() {
		configAPI = new ValidationRuleConfig(null);
	}

	@Test
	public void testGetValidationRuleDefinition() {
		configAPI = new ValidationRuleConfig(MANDATORY);
		assertEquals(MANDATORY, configAPI.getValidationRuleDefinition());
	}
	
	@Test
	public void testSetEnforcementToIgnoreValidOptionalRule() {
		configAPI = new ValidationRuleConfig(OPTIONAL);
		configAPI.setRuleEnforcement(RuleEnforcement.IGNORE);
	}
	@Test
	public void testSetEnforcementToWarningValidOptionalRule() {
		configAPI = new ValidationRuleConfig(OPTIONAL);
		configAPI.setRuleEnforcement(RuleEnforcement.WARNING);
	}
	@Test
	public void testSetEnforcementToErrorValidOptionalRule() {
		configAPI = new ValidationRuleConfig(OPTIONAL);
		configAPI.setRuleEnforcement(RuleEnforcement.ERROR);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetEnforcementToIgnoreThrowsExceptionForMandatoryRule() {
		configAPI = new ValidationRuleConfig(MANDATORY);
		assertTrue(configAPI.getValidationRuleDefinition().getRuleLevel().equals(RuleLevel.MANDATORY));
		configAPI.setRuleEnforcement(RuleEnforcement.IGNORE);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetEnforcementToWarningThrowsExceptionForMandatoryRule() {
		configAPI = new ValidationRuleConfig(MANDATORY);
		assertTrue(configAPI.getValidationRuleDefinition().getRuleLevel().equals(RuleLevel.MANDATORY));
		configAPI.setRuleEnforcement(RuleEnforcement.WARNING);
	}
	
	final int UNIQUE_OTHER_ID = 5000;

	@Test
	public void testHashCode() {
		IValidationRuleDefinition other = new ValidationRuleDefinition("MANDATORY", "LAYOUT", UNIQUE_OTHER_ID, RuleLevel.MANDATORY, RuleEnforcement.ERROR);
		configAPI = new ValidationRuleConfig(OPTIONAL);
		configImpl = configAPI;
		ValidationRuleConfig config2 = new ValidationRuleConfig(other);
		assertFalse(config2.hashCode() == configImpl.hashCode());

	}

	@Test
	public void testEquals() {
		IValidationRuleDefinition other = new ValidationRuleDefinition("MANDATORY", "LAYOUT", UNIQUE_OTHER_ID, RuleLevel.MANDATORY, RuleEnforcement.ERROR);
		configAPI = new ValidationRuleConfig(OPTIONAL);
		IValidationRuleConfig config2 = new ValidationRuleConfig(other);
		assertFalse(config2.equals(configAPI));
	}
	
	@Test
	public void testSortInAscendingRuleNeumberOrder() {
		ValidationRuleConfig conf1 = new ValidationRuleConfig(MANDATORY);
		ValidationRuleConfig conf2 = new ValidationRuleConfig(OPTIONAL);
		List<IValidationRuleConfig> rulesList = Arrays.asList(new IValidationRuleConfig[]{conf2, conf1});
		Collections.sort(rulesList);
		assertTrue(rulesList.get(0).getValidationRuleDefinition().getRuleNumber() < rulesList.get(1).getValidationRuleDefinition().getRuleNumber());
	}

}
