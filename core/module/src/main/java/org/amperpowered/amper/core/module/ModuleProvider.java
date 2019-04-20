/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.amperpowered.amper.core.module.internal.AmperModule;
import org.amperpowered.amper.core.module.internal.ModuleClassParser;
import org.amperpowered.amper.core.module.internal.ModuleClassScanner;
import org.amperpowered.amper.core.module.internal.ModuleModel;
import org.amperpowered.amper.core.module.internal.ModuleModelRegistry;

public final class ModuleProvider {

  private ModuleModelRegistry moduleModelRegistry;
  private ModuleClassScanner moduleClassScanner;
  private ModuleClassParser moduleClassParser;

  @Inject
  public ModuleProvider(ModuleModelRegistry moduleModelRegistry,
      ModuleClassScanner moduleClassScanner,
      ModuleClassParser moduleClassParser) {
    this.moduleModelRegistry = moduleModelRegistry;
    this.moduleClassScanner = moduleClassScanner;
    this.moduleClassParser = moduleClassParser;
  }

  public void registerModules() {
    checkNotNull(this.moduleModelRegistry, "moduleModelRegistry cannot be null!");
    checkNotNull(this.moduleClassScanner, "moduleClassScanner cannot be null!");
    checkNotNull(this.moduleClassParser, "moduleClassParser cannot be null!");

    try {
      for (Class<?> moduleClass : moduleClassScanner.scanModules()) {
        moduleClassParser.parseModuleClassToModel(moduleClass)
            .ifPresent(moduleModel -> moduleModelRegistry.register(moduleModel));
      }
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  public boolean requireModules() {
    checkNotNull(this.moduleModelRegistry, "moduleModelRegistry cannot be null!");

    return moduleModelRegistry.moduleModels()
        .stream()
        .flatMap(moduleModel -> Arrays.stream(moduleModel.requiredModules().toArray()))
        .allMatch(requiredModule -> moduleModelRegistry.require(String.valueOf(requiredModule)).isPresent());
  }


  public Optional<AmperModule> requireModule(String name) {
    checkNotNull(name, "name cannot be null!");
    checkNotNull(this.moduleModelRegistry, "moduleModelRegistry cannot be null!");

    return moduleModelRegistry.moduleModels()
        .stream()
        .filter(moduleModel -> moduleModel.name().equalsIgnoreCase(name))
        .map(ModuleModel::nestedModule)
        .findFirst();
  }

  public Collection<AmperModule> nestedModules() {
    checkNotNull(this.moduleModelRegistry, "moduleModelRegistry cannot be null!");

    return moduleModelRegistry.moduleModels()
        .stream()
        .map(ModuleModel::nestedModule)
        .collect(Collectors.toList());
  }

}
