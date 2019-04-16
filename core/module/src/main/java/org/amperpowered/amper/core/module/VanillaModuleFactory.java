/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.amperpowered.amper.core.module.internal.AmperModule;
import org.amperpowered.amper.core.module.internal.ModuleModel;
import org.amperpowered.amper.core.module.internal.ModuleModelRegistry;
import org.amperpowered.amper.core.module.internal.ModuleParser;
import org.amperpowered.amper.core.module.internal.ModuleScanner;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.pmw.tinylog.Logger;

final class VanillaModuleFactory implements ModuleFactory {

  private static final ModuleModelRegistry MODULE_MODEL_REGISTRY = ModuleModelRegistry.vanilla();

  @Override
  public void registerModulesRecurvesly(@NonNull Path modulePath) {
    Preconditions.checkNotNull(modulePath, "modulePath cannot be null!");

    Logger.info("Registering modules...");

    try {
      ModuleScanner moduleScanner = ModuleScanner.vanilla();
      ModuleParser moduleParser = ModuleParser.vanilla();

      for (Class<?> moduleClass : moduleScanner.scanModules(modulePath)) {
        ModuleModel moduleModel = moduleParser.parseModule(moduleClass);
        MODULE_MODEL_REGISTRY.register(moduleModel);
      }
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  @Override
  public boolean requireModules() {
    return MODULE_MODEL_REGISTRY.moduleIndices()
        .stream()
        .flatMap(moduleIndex -> Arrays.stream(moduleIndex.requires()))
        .allMatch(requiredModule -> MODULE_MODEL_REGISTRY.require(requiredModule).isPresent());
  }

  @NonNull
  @Override
  public Optional<AmperModule> requireModule(@NonNull String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return MODULE_MODEL_REGISTRY.moduleIndices()
        .stream()
        .filter(moduleIndex -> moduleIndex.name().equalsIgnoreCase(name))
        .map(ModuleModel::amperModule)
        .findFirst();
  }

  @NonNull
  @Override
  public Collection<AmperModule> amperModules() {
    return MODULE_MODEL_REGISTRY.moduleIndices()
        .stream()
        .map(ModuleModel::amperModule)
        .collect(Collectors.toList());
  }
}
