package org.pathwayeditor.notationsubsystem.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IDefaultValidationRuleConfigLoader;
import org.pathwayeditor.notationsubsystem.toolkit.validation.IValidationRuleStore;
import org.pathwayeditor.notationsubsystem.toolkit.validation.RuleStore;
import org.pathwayeditor.notationsubsystem.toolkit.validation.ValidationRuleConfig;
import org.pathwayeditor.notationsubsystem.toolkit.validation.ValidationRuleDefinition;
@RunWith(JMock.class)
public class RuleStoreTest {
    IValidationRuleStore storeAPI;

    Mockery mockery = new JUnit4Mockery();
    Set<IValidationRuleConfig> configs;
    int ID1=1;
    int ID2=2;
    int ID3=3;
    int UNKNOWN_ID=500;
    
    final IDefaultValidationRuleConfigLoader loader = mockery.mock(IDefaultValidationRuleConfigLoader.class);
    final INotationValidationService validationService = mockery.mock(INotationValidationService.class);
    
    final int EXPECTED_RULE_COUNT=3;
	@Before
	public void setUp() throws Exception {
		mockery.checking(new Expectations () {
			{
			 allowing(validationService).getNotationSubsystem();
			}	
		});
		configs = createNconfigs(3);
		mockery.checking(new Expectations () {
			{
				allowing(loader).loadDefaultRuleConfigurations();will(returnValue(configs));
			}
		});
		storeAPI = RuleStore.getInstance(loader);
	}

	private Set<IValidationRuleConfig> createNconfigs(int n) {
		Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig>();
		for(int i = 1; i<=n; i++) {
			ValidationRuleDefinition def = new ValidationRuleDefinition(validationService, Integer.toString(i), "category", i, RuleLevel.OPTIONAL, RuleEnforcement.WARNING);
			ValidationRuleConfig config = new ValidationRuleConfig(def);
			configs.add(config);
		}
		return configs;
    
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
	
	@Test
	public void testAlteringConfigDoesNotChangeDefaults() {
		IValidationRuleConfig config = storeAPI.getRuleConfigByID(1);
		assertFalse(config.getCurrentRuleEnforcement().equals(RuleEnforcement.ERROR));
		config.setRuleEnforcement(RuleEnforcement.ERROR);
		assertTrue(config.getCurrentRuleEnforcement().equals(RuleEnforcement.ERROR));
		// check default unchanged.
		Set<IValidationRuleConfig>defaults = storeAPI.getDefaultRuleConfigurations();
		assertTrue(defaultIsStillNotAnError(defaults));
	}

	private boolean defaultIsStillNotAnError(Set<IValidationRuleConfig> defaults) {
	   for(IValidationRuleConfig conf: defaults) {
		   if(conf.getValidationRuleDefinition().getRuleNumber()==1){
			   return !conf.getCurrentRuleEnforcement().equals(RuleEnforcement.ERROR);
		   }
	   }
	   return false;
	}

}
