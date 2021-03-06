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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;

@RunWith(JMock.class)
public class RuleDefinitionTest {

	ValidationRuleDefinition definition; // used for set up
	IValidationRuleDefinition definitionAPI;// used to test assertions
	Mockery mockery = new JUnit4Mockery();
	final INotationValidationService validationService = mockery.mock(INotationValidationService.class);
	final INotationValidationService differentValidationService = mockery.mock(INotationValidationService.class);
	String EXPECTED_NAME = "NAME";
	String EXPECTED_CATEGORY = "GRAPH";
	int EXPECTED_RULE_NUMBER = 1;
	private final INotationSubsystem notationSubSystem = mockery.mock(INotationSubsystem.class);
	int DIFFERENT_RULE_NUMBER = 2;
	String DIFFERENT_NAME = "NAME2";
	RuleLevel EXPECTED_LEVEL = RuleLevel.MANDATORY;

	@Before
	public void setUp() throws Exception {
		mockery.checking(new Expectations() {
			
			{
				allowing(validationService).getNotationSubsystem();will(returnValue(notationSubSystem));
				allowing(differentValidationService).getNotationSubsystem();
			}
		});
		definition = createARuleDefinitionUsingExpectedValues();
		definitionAPI = definition;
	}

	private ValidationRuleDefinition createARuleDefinitionUsingExpectedValues() {
		return new ValidationRuleDefinition(EXPECTED_NAME, EXPECTED_CATEGORY, EXPECTED_RULE_NUMBER, EXPECTED_LEVEL, RuleEnforcement.ERROR);
	}

	private ValidationRuleDefinition createARuleWithHigherNumber() {
		return new ValidationRuleDefinition(EXPECTED_NAME, EXPECTED_CATEGORY, DIFFERENT_RULE_NUMBER, EXPECTED_LEVEL, RuleEnforcement.ERROR);
	}

	private ValidationRuleDefinition createARuleWithDifferentContext() {
		return new ValidationRuleDefinition(EXPECTED_NAME, EXPECTED_CATEGORY, EXPECTED_RULE_NUMBER, EXPECTED_LEVEL, RuleEnforcement.ERROR);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRuleNumber() {
		assertEquals(EXPECTED_RULE_NUMBER, definitionAPI.getRuleNumber());
	}

	@Test
	public void testGetName() {
		assertEquals(EXPECTED_NAME, definitionAPI.getName());
	}

	@Test
	public void testGetDescriptionNotNull() {
		assertNotNull(definitionAPI.getDescription());
	}

	@Test
	public void testGetDetailedDescription() {
		assertNotNull(definitionAPI.getDetailedDescription());
		definition.setDetailedDesc("DescriptionHTML");
		assertEquals("DescriptionHTML", definitionAPI.getDetailedDescription());
	}

	@Test
	public void testGetRuleCategory() {
		assertEquals(EXPECTED_CATEGORY, definitionAPI.getRuleCategory());

	}

	@Test
	public void testEquals() {
		ValidationRuleDefinition SAME_DEFINITION = createARuleDefinitionUsingExpectedValues();
		assertEquals(SAME_DEFINITION, definition);

		ValidationRuleDefinition RULENUMBERCHANGE_DEFINITION = createARuleWithHigherNumber();
		assertFalse(definition.equals(RULENUMBERCHANGE_DEFINITION));

		ValidationRuleDefinition RULE_CONTEXT_CHANGE_DEFINITION = createARuleWithDifferentContext();
		assertTrue(definition.equals(RULE_CONTEXT_CHANGE_DEFINITION));
	}

	@Test
	public void testHashCode() {
		ValidationRuleDefinition SAME_DEFINITION = createARuleDefinitionUsingExpectedValues();
		assertEquals(SAME_DEFINITION.hashCode(), definition.hashCode());

		ValidationRuleDefinition RULENUMBERCHANGE_DEFINITION = createARuleWithHigherNumber();
		assertFalse(definition.hashCode() == RULENUMBERCHANGE_DEFINITION.hashCode());

		ValidationRuleDefinition RULE_CONTEXT_CHANGE_DEFINITION = createARuleWithDifferentContext();
		assertTrue(definition.hashCode() == RULE_CONTEXT_CHANGE_DEFINITION.hashCode());
	}
	
	@Test
	public void testSortInAScendingRuleNeumberOrder() {
		ValidationRuleDefinition SAME_DEFINITION = createARuleDefinitionUsingExpectedValues();
		ValidationRuleDefinition RULENUMBERCHANGE_DEFINITION = createARuleWithHigherNumber();
		List<IValidationRuleDefinition> list = Arrays.asList(new IValidationRuleDefinition[]{RULENUMBERCHANGE_DEFINITION, SAME_DEFINITION});
		Collections.sort(list);
		assertTrue(list.get(0).getRuleNumber() < list.get(1).getRuleNumber());
	}

}
