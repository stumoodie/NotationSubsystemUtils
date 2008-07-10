package org.pathwayeditor.contextadapter.toolkit.validation;

import java.util.Set;

import org.pathwayeditor.contextadapter.publicapi.IValidationRuleConfig;

/**
 * Context adapters should implement this interface to populate a
 * set of {@link ValidationRuleConfig} items.
 * @author Richard Adams
 *
 */
public interface IDefaultValidationRuleConfigLoader {

 /**
  * This method can return a hard-coded set of {@link IValidationRuleConfig} items
  * or may obtain the set of rules from a file-system, for example.
  * @return A <code>Set</code> of {@link IValidationRuleConfig}  items.
  */
 Set<IValidationRuleConfig> loadDefaultRuleConfigurations();
}
