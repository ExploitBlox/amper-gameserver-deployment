/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.amperpowered.amper.module.internal.AmperModule;
import org.amperpowered.amper.module.internal.ModuleIndex;
import org.amperpowered.amper.module.internal.ModuleIndexRegistry;
import org.amperpowered.amper.module.internal.ModuleParser;
import org.amperpowered.amper.module.internal.ModuleScanner;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.pmw.tinylog.Logger;

final class VanillaModuleFactory implements ModuleFactory {

  private static final ModuleIndexRegistry MODULE_INDEX_REGISTRY = ModuleIndexRegistry.vanilla();
  private static final ModuleScanner MODULE_SCANNER = ModuleScanner.vanilla();
  private static final ModuleParser MODULE_PARSER = ModuleParser.vanilla();

  @Override
  public void registerModulesRecurvesly(@NonNull Path modulePath) {
    Preconditions.checkNotNull(modulePath, "modulePath cannot be null!");

    Logger.info("Registering modules...");

    try {
      for (Class<?> amperModuleClass : MODULE_SCANNER.scanModules(modulePath)) {
        ModuleIndex moduleIndex = MODULE_PARSER.parseModule(amperModuleClass);
        MODULE_INDEX_REGISTRY.register(moduleIndex);
      }
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  @Override
  public boolean requireModules() {
    return MODULE_INDEX_REGISTRY.moduleIndices()
        .stream()
        .flatMap(moduleIndex -> Arrays.stream(moduleIndex.requires()))
        .allMatch(requiredModule -> MODULE_INDEX_REGISTRY.require(requiredModule).isPresent());
  }

  @NonNull
  @Override
  public Optional<AmperModule> requireModule(@NonNull String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return MODULE_INDEX_REGISTRY.moduleIndices()
        .stream()
        .filter(moduleIndex -> moduleIndex.name().equalsIgnoreCase(name))
        .map(ModuleIndex::amperModule)
        .findFirst();
  }

  @NonNull
  @Override
  public Collection<AmperModule> amperModules() {
    return MODULE_INDEX_REGISTRY.moduleIndices()
        .stream()
        .map(ModuleIndex::amperModule)
        .collect(Collectors.toList());
  }
}
