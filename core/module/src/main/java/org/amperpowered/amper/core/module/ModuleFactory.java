/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import org.amperpowered.amper.core.module.internal.AmperModule;

public interface ModuleFactory {

  static ModuleFactory vanilla() {
    return new VanillaModuleFactory();
  }

  void registerModulesRecurvesly(Path modulePath);

  boolean requireModules();

  Optional<AmperModule> requireModule(String name);

  Collection<AmperModule> amperModules();
}
