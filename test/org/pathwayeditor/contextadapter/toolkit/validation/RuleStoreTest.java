package org.pathwayeditor.contextadapter.toolkit.validation;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.contextadapter.publicapi.IContext;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;
import org.pathwayeditor.contextadapter.publicapi.IValidationRuleDefinition.RuleLevel;
@RunWith(JMock.class)
public class RuleStoreTest {
    IValidationRuleStore storeAPI;

    Mockery mockery = new JUnit4Mockery();
	
    final IDefaultValidationRuleConfigLoader loader = mockery.mock(IDefaultValidationRuleConfigLoader.class);
    Set<IValidationRuleConfig> configs;
    int ID1=1;
    int ID2=2;
    int ID3=3;
    int UNKNOWN_ID=500;
    
    final IContext context = mockery.mock(IContext.class);
    
    final int EXPECTED_RULE_COUNT=3;
	@Before
	public void setUp() throws Exception {
		configs = createNconfigs(3);
		mockery.checking(new Expectations () {
			{allowing(loader).loadDefaultRuleConfigurations();will(returnValue(configs));}
		});
		storeAPI = RuleStore.getInstance(loader);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	

	private Set<IValidationRuleConfig> createNconfigs(int n) {
		Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig>();
		for(int i = 1; i<=n; i++) {
			ValidationRuleDefinition def = new ValidationRuleDefinition(context, Integer.toString(i), "category", i, RuleLevel.GUIDELINE);
			ValidationRuleConfig config = new ValidationRuleConfig(def, true, false);
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
		assertFalse(config.isErrorRule());
		
		config.promoteToError(true);
		
		assertTrue(config.isErrorRule());
		// check default unchanged.
		Set<IValidationRuleConfig>defaults = storeAPI.getDefaultRuleConfigurations();
		assertTrue(defaultIsStillNotAnError(defaults));
	}

	private boolean defaultIsStillNotAnError(Set<IValidationRuleConfig> defaults) {
	   for(IValidationRuleConfig conf: defaults) {
		   if(conf.getValidationRuleDefinition().getRuleNumber()==1){
			   return !conf.isErrorRule();
		   }
	   }
	   return false;
	}

}
