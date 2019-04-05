/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import org.amperpowered.amper.module.internal.AmperModule;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleFactory {

  @NonNull
  static ModuleFactory vanilla() {
    return new VanillaModuleFactory();
  }

  void registerModulesRecurvesly(@NonNull Path modulePath);

  boolean requireModules();

  @NonNull
  Optional<AmperModule> requireModule(@NonNull String name);

  @NonNull
  Collection<AmperModule> amperModules();
}
