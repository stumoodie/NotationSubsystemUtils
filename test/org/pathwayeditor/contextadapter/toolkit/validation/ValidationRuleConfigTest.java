package org.pathwayeditor.contextadapter.toolkit.validation;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition.RuleLevel;

@RunWith(JMock.class)
public class ValidationRuleConfigTest {
    IValidationRuleConfig configAPI;
    IValidationRuleConfig configImpl = configAPI;
	IValidationRuleDefinition MANDATORY, OPTIONAL, GUIDELINE;
	Mockery mockery = new JUnit4Mockery();
	
	final IContext mockContext = mockery.mock(IContext.class);
	@Before
	public void setUp() throws Exception {
		MANDATORY= createMandatoryRuleDefinition();
		OPTIONAL = createOptionalRuleDefinition();
		GUIDELINE = createGuidelineRuleDefinition();
		
	}

	private IValidationRuleDefinition createMandatoryRuleDefinition() {
		return new ValidationRuleDefinition(mockContext,"MANDATORY","LAYOUT",1, RuleLevel.MANDATORY);
	}
	
	private IValidationRuleDefinition createOptionalRuleDefinition() {
		return new ValidationRuleDefinition(mockContext,"OPTIONAL","LAYOUT",2, RuleLevel.OPTIONAL);
	}
	
	private IValidationRuleDefinition createGuidelineRuleDefinition() {
		return new ValidationRuleDefinition(mockContext,"GUIDELINE","LAYOUT",3, RuleLevel.GUIDELINE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidationRuleConfig() {
		configAPI = new ValidationRuleConfig(MANDATORY, true, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidationRuleConfigFailsIfRuleIsNull() {
		configAPI = new ValidationRuleConfig(null, true, true);
	}

	@Test
	public void testGetValidationRuleDefinition() {
		configAPI = new ValidationRuleConfig(MANDATORY, true, true);
		assertEquals(MANDATORY, configAPI.getValidationRuleDefinition());
	}

	@Test
	public void testIsErrorRuleIsConfiguredFromConstructor() {
		configAPI = new ValidationRuleConfig(MANDATORY, true, true);
		assertTrue(configAPI.isErrorRule());
		
		configAPI = new ValidationRuleConfig(GUIDELINE, true, false);
		assertFalse(configAPI.isErrorRule());
	}
	
	@Test
	public void testErrorRuleFLagIgnoredForMandatoryRules() {
		configAPI = new ValidationRuleConfig(MANDATORY, true, false);
		assertTrue(configAPI.isErrorRule());
	}
	
	@Test
	public void testErrorRuleFLagIgnoredForOptionalRules() {
		configAPI = new ValidationRuleConfig(OPTIONAL, true, false);
		assertTrue(configAPI.isErrorRule());
	}
	
	@Test
	public void testIsWarningRuleOnlyAppliesToGuidelines() {
		configAPI = new ValidationRuleConfig(GUIDELINE, true, false);
		assertTrue(configAPI.isWarningRule());
	}
	
	@Test
	public void testIsWarningAlwaysFalseForMAndatoryRule() {
		configAPI = new ValidationRuleConfig(MANDATORY, true, false);
		assertFalse(configAPI.isWarningRule());
	}
	
	@Test
	public void testIsWarningAlwaysFalseForOptionalRule() {
		configAPI = new ValidationRuleConfig(OPTIONAL, true, false);
		assertFalse(configAPI.isWarningRule());
	}

	@Test
	public void testMustBeRunAlwaysTrueForMandatoryRule() {
		configAPI = new ValidationRuleConfig(MANDATORY, false, false);
		assertTrue(configAPI.mustBeRun());
		configAPI.setMustBeRun(false);
		assertTrue(configAPI.mustBeRun());
	}
	
	@Test
	public void testMustBeRunConfigurableForOptionalRules() {
		configAPI = new ValidationRuleConfig(OPTIONAL, false, false);
		assertFalse(configAPI.mustBeRun());
		configAPI.setMustBeRun(true);
		assertTrue(configAPI.mustBeRun());
	}
	
	@Test
	public void testMustBeRunConfigurableForGuidelineRules() {
		configAPI = new ValidationRuleConfig(GUIDELINE, false, false);
		assertFalse(configAPI.mustBeRun());
		configAPI.setMustBeRun(true);
		assertTrue(configAPI.mustBeRun());
	}

	@Test
	public void testPromoteToErrorIgnoredByMandatoryRules() {
		configAPI = new ValidationRuleConfig(MANDATORY, false, false);
		configAPI.promoteToError(false);
		assertTrue(configAPI.isErrorRule());
	}

	@Test
	public void testPromoteToErrorConfigurableForGuidelineRule() {
		configAPI = new ValidationRuleConfig(GUIDELINE, false, false);
		assertFalse(configAPI.isErrorRule());
		configAPI.promoteToError(true);
		assertTrue(configAPI.isErrorRule());
		assertFalse(configAPI.isWarningRule());
		configAPI.promoteToError(false);
		assertFalse(configAPI.isErrorRule());
		assertTrue(configAPI.isWarningRule());
	}
	
    final int UNIQUE_OTHER_ID=5000;
	@Test
	public void testHashCode() {
		IValidationRuleDefinition other = new ValidationRuleDefinition(mockContext,"MANDATORY","LAYOUT",UNIQUE_OTHER_ID, RuleLevel.MANDATORY);
		boolean ANY_BOOLEAN = false;
		configAPI = new ValidationRuleConfig(GUIDELINE, ANY_BOOLEAN, ANY_BOOLEAN);
		configImpl = configAPI;
		ValidationRuleConfig config2 = new ValidationRuleConfig(other, ANY_BOOLEAN, ANY_BOOLEAN);
		assertFalse(config2.hashCode()==configImpl.hashCode());
		
	}

	@Test
	public void testEquals() {
		IValidationRuleDefinition other = new ValidationRuleDefinition(mockContext,"MANDATORY","LAYOUT",UNIQUE_OTHER_ID, RuleLevel.MANDATORY);
		boolean ANY_BOOLEAN = false;
		configAPI = new ValidationRuleConfig(GUIDELINE, ANY_BOOLEAN, ANY_BOOLEAN);
		IValidationRuleConfig config2 = new ValidationRuleConfig(other, ANY_BOOLEAN, ANY_BOOLEAN);
		assertFalse(config2.equals(configAPI));
	}
	
	@Test
	public void testSortInAscendingRuleNeumberOrder() {
		ValidationRuleConfig conf1 = new ValidationRuleConfig(MANDATORY, false, false);
		ValidationRuleConfig conf2 = new ValidationRuleConfig(OPTIONAL, false, false);
		ValidationRuleConfig conf3 = new ValidationRuleConfig(GUIDELINE, false, false);
		List<IValidationRuleConfig> list = Arrays.asList(new IValidationRuleConfig[]{conf2, conf3, conf1});
		Collections.sort(list);
		assertTrue(list.get(0).getValidationRuleDefinition().getRuleNumber() < list.get(1).getValidationRuleDefinition().getRuleNumber());
		assertTrue(list.get(1).getValidationRuleDefinition().getRuleNumber() < list.get(2).getValidationRuleDefinition().getRuleNumber());
	}

}
