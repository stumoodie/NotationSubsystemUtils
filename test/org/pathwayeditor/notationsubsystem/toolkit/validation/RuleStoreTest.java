package org.pathwayeditor.notationsubsystem.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;

@RunWith(JMock.class)
public class RuleStoreTest {
    IValidationRuleStore storeAPI;

    Mockery mockery = new JUnit4Mockery();
    int ID1=1;
    int ID2=2;
    int ID3=3;
    int UNKNOWN_ID=500;
    
//    final INotationValidationService validationService = mockery.mock(INotationValidationService.class);
    
    final int EXPECTED_RULE_COUNT=3;
	@Before
	public void setUp() throws Exception {
		mockery.checking(new Expectations () {
			{
//			 allowing(validationService).getNotationSubsystem();
			}	
		});
		storeAPI = createNconfigs(3);
	}

	private RuleStore createNconfigs(int n) {
		RuleStore ruleStore = new RuleStore();
		for(int i = 1; i<=n; i++) {
			ValidationRuleDefinition def = new ValidationRuleDefinition(Integer.toString(i), "category", i, RuleLevel.OPTIONAL, RuleEnforcement.WARNING);
			ValidationRuleConfig config = new ValidationRuleConfig(def);
			ruleStore.addConfiguredRule(config);
		}
		return ruleStore;
    
	}

	@Test
	public void testContainsRule() {
		assertTrue(storeAPI.containsRule(ID1));
		assertTrue(storeAPI.containsRule(ID2));
		assertTrue(storeAPI.containsRule(ID3));
		assertFalse(storeAPI.containsRule(UNKNOWN_ID));
	}

	@Test
	public void testGetAllRuleDefinitions() {
		assertEquals(EXPECTED_RULE_COUNT, storeAPI.getAllRuleDefinitions().size());
	}

	@Test
	public void testGetConfigurableRules() {
		assertEquals(EXPECTED_RULE_COUNT, storeAPI.getConfigurableRules().size());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testGetConfigurableRulesReturnsUnmodifiableList() {
		Set<IValidationRuleConfig> configs = storeAPI.getConfigurableRules();
		configs.add(mockery.mock(IValidationRuleConfig.class));
	}

	@Test
	public void testGetRuleById() {
		assertNotNull(storeAPI.getRuleById(ID1));
		assertNotNull(storeAPI.getRuleById(ID2));
		assertNotNull(storeAPI.getRuleById(ID3));
		assertNull(storeAPI.getRuleById(UNKNOWN_ID));
	}
}
