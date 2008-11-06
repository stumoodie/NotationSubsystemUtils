package org.pathwayeditor.contextadapter.toolkit.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReport;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationReportItem;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleConfig;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleEnforcement;
import org.pathwayeditor.businessobjects.notationsubsystem.IValidationRuleDefinition.RuleLevel;

@RunWith(JMock.class)
public class RuleValidationReportBuilderTest {
    IRuleValidationReportBuilder reportBuilderAPI;
    RuleValidationReportBuilderExt reportBuilderImpl;
    
    Mockery mockery = new JUnit4Mockery();
	final ICanvas map = mockery.mock(ICanvas.class);
	final IValidationRuleStore store = mockery.mock(IValidationRuleStore.class);
	final IValidationRuleConfig	 CONFIG1 = mockery.mock(IValidationRuleConfig.class);
	final IValidationRuleDefinition mandatoryRuleDefinition = mockery.mock(IValidationRuleDefinition.class) ;
	final IValidationRuleDefinition optionalRuleDefinition = mockery.mock(IValidationRuleDefinition.class) ;
	// allows modulation of state
    class RuleValidationReportBuilderExt extends RuleValidationReportBuilder {
    	public RuleValidationReportBuilderExt(IValidationRuleStore store, ICanvas map) {
			super(store, map);
		}
    	void setState(int state) {
    		this.state=state;
    	}
    }
	@Before
	public void setUp() throws Exception {
		reportBuilderImpl = new RuleValidationReportBuilderExt(store, map);
		reportBuilderAPI= reportBuilderImpl;
		mockery.checking(new Expectations() {
			{
				allowing(mandatoryRuleDefinition).getRuleLevel();will(returnValue(RuleLevel.MANDATORY));
				allowing(optionalRuleDefinition).getRuleLevel();will(returnValue(RuleLevel.OPTIONAL));
			}
		});
	}


	@Test
	public void testRuleValidationReportBuilderIsReadyToValidateAfterConstruction() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(store, map);
		assertTrue(reportBuilderAPI.isReadyToValidate());
		assertFalse(reportBuilderAPI.isValidating());
		assertFalse(reportBuilderAPI.isComplete());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRuleValidationReportBuilderDoesNotAllowNullStore() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(null, map);	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRuleValidationReportBuilderDoesNotAllowNullMap() {
		reportBuilderAPI = new RuleValidationReportBuilderExt(store, null);	
	}

	@Test
	public void testValidationReportCanBeCalledImmediatelyAfterConstructionOnlyIfNoRulesToCheck() {
	    reportBuilderAPI.createValidationReport();
	}
	
	
	
	@Test
	public void testCreateValidationReportCanBeCalledImmediatelyDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
	    reportBuilderAPI.createValidationReport();
	    assertTrue(reportBuilderAPI.isComplete());
	}

	@Test
	public void testGetRuleStore() {
		assertEquals(store, reportBuilderAPI.getRuleStore());
	}

	@Test(expected=IllegalStateException.class)
	public void testGetValidationReportNotAllowedAfterCreation() {
       reportBuilderAPI.getValidationReport();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetValidationReportNotAllowedDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
        reportBuilderAPI.getValidationReport();
	}
	
	@Test
	public void testGetValidationReportOnlyAllowedAfterCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
        IValidationReport report = reportBuilderAPI.getValidationReport();
        assertEquals(map, report.getCanvas());
	}

	@Test
	public void testIsCompleteIsFalseAfterBuilderConstruction() {
		assertFalse(reportBuilderAPI.isComplete());	
	}
	
	@Test
	public void testIsCompleteIsFalseDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
		assertFalse(reportBuilderAPI.isComplete());		
	}

	@Test
	public void testIsReadyToValidateIsTrueAfterBuilderConstruction() {
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testIsReadyToValidateIsFalseAfterBuilderCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		assertFalse(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testIsReadyToValidateIsTrueAfterBuilderCompletionAndReset() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}

	@Test
	public void testIsValidatingIsFalseAfterBuilderConstruction() {
		assertFalse(reportBuilderAPI.isValidating());
	}

	@Test
	public void testResetApplicableAfterCreation() {
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test
	public void testResetApplicableAfterCompletion() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.COMPLETED);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testResetNotApplicableDuringValidation() {
		reportBuilderImpl.setState(RuleValidationReportBuilder.VALIDATING);
		reportBuilderAPI.reset();
		assertTrue(reportBuilderAPI.isReadyToValidate());
	}

	@Test
	public void testSetRuleFailed() {
		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		final int RULE_ID=1;
		setUpRuleAssertions(configs, RULE_ID);
		reportBuilderAPI.setRuleFailed(null, mandatoryRuleDefinition, "Failed");
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(1, report.getValidationReportItems().size());
		assertEquals(IValidationReportItem.Severity.ERROR, report.getValidationReportItems().get(0).getSeverity());
	}
	
	@Test
	public void testRuleFailureOnlyAddedToReportIfMustBeRunIsTrue (){
		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		final int RULE_ID=1;
		setUpOptionalRuleAssertions(configs, RULE_ID);
		reportBuilderAPI.setRuleFailed(null, optionalRuleDefinition, "Failed");
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(0, report.getValidationReportItems().size());
	}

	private void setUpRuleAssertions(final Set<IValidationRuleConfig> configs, final int RULE_ID) {
		mockery.checking(new Expectations() {
			{atLeast(1).of(mandatoryRuleDefinition).getRuleNumber(); will(returnValue(RULE_ID));}
			{allowing(CONFIG1).getValidationRuleDefinition(); will(returnValue(mandatoryRuleDefinition));}
			{allowing(CONFIG1).getCurrentRuleEnforcement(); will(returnValue(RuleEnforcement.ERROR));}
		//	{atLeast(1).of(store).getConfigurableRules();will(returnValue(configs));}
			{atLeast(1).of(store).containsRule(RULE_ID);will(returnValue(true));}
			{atLeast(1).of(store).getRuleConfigByID(RULE_ID);will(returnValue(CONFIG1));}
		});
	}
	private void setUpOptionalRuleAssertions(final Set<IValidationRuleConfig> configs, final int RULE_ID) {
		mockery.checking(new Expectations() {
			{atLeast(1).of(optionalRuleDefinition).getRuleNumber(); will(returnValue(RULE_ID));}
			{allowing(CONFIG1).getValidationRuleDefinition(); will(returnValue(optionalRuleDefinition));}
			{allowing(CONFIG1).getCurrentRuleEnforcement(); will(returnValue(RuleEnforcement.ERROR));}
		//	{atLeast(1).of(store).getConfigurableRules();will(returnValue(configs));}
			{atLeast(1).of(store).containsRule(RULE_ID);will(returnValue(true));}
			{atLeast(1).of(store).getRuleConfigByID(RULE_ID);will(returnValue(CONFIG1));}
		});
	}

	@Test
	public void testSetRulePassed() {
//		final Set<IValidationRuleConfig> configs = new HashSet<IValidationRuleConfig> (Arrays.asList(new IValidationRuleConfig[]{CONFIG1}));
		reportBuilderAPI.setRulePassed(mandatoryRuleDefinition);
		reportBuilderAPI.createValidationReport();
		IValidationReport report = reportBuilderAPI.getValidationReport();
		assertEquals(0, report.getValidationReportItems().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetRuleFailedDoesNotAcceptRulesFromOutsideRuleStore () {
		final IValidationRuleDefinition def = mockery.mock(IValidationRuleDefinition.class);
		final int RULE_ID=1;
		mockery.checking(new Expectations() {
			{atLeast(1).of(def).getRuleNumber(); will(returnValue(RULE_ID));}
			{atLeast(1).of(store).containsRule(RULE_ID);will(returnValue(false));}
		});
		reportBuilderAPI.setRuleFailed(null, def, "Any message");
	}

}
