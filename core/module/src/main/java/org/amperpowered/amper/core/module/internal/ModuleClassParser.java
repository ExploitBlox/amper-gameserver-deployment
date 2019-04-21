/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import org.amperpowered.amper.core.module.internal.ModuleModel.Builder;
import org.amperpowered.amper.core.module.internal.annotation.Module;
import org.amperpowered.amper.core.module.internal.annotation.Requires;

@Singleton
public final class ModuleClassParser {

  public static ModuleClassParser create() {
    return new ModuleClassParser();
  }

  private static final ModuleClassValidator MODULE_CLASS_VALIDATOR = ModuleClassValidator.create();

  public Optional<ModuleModel> parseModuleClassToModel(Class<?> moduleClass) {
    checkNotNull(moduleClass, "moduleClass cannot be null!");

    if (!MODULE_CLASS_VALIDATOR.validateModuleAnnotation(moduleClass)) {
      return Optional.empty();
    }

    Module module = moduleClass.getDeclaredAnnotation(Module.class);

    return Optional.of(parseModuleClassToModel(moduleClass, module));
  }

  private ModuleModel parseModuleClassToModel(Class<?> moduleClass, Module module) {
    checkNotNull(moduleClass, "moduleClass cannot be null!");
    checkNotNull(module, "module cannot be null!");

    ModuleModel.Builder moduleModelBuilder = ModuleModel.newBuilder()
        .withName(module.name())
        .withAuthor(module.author())
        .withVersion(module.version())
        .withNestedModule(this.tryParseModuleClassToModuleObject(moduleClass));

    this.tryParseRequiredModulesFromModuleClassToModel(moduleClass, moduleModelBuilder);

    return moduleModelBuilder.create();
  }


  private void tryParseRequiredModulesFromModuleClassToModel(
      Class<?> moduleClass,
      Builder moduleModelBuilder) {
    checkNotNull(moduleClass, "moduleClass cannot be null!");
    checkNotNull(moduleModelBuilder, "moduleModelBuilder cannot be null!");

    if (MODULE_CLASS_VALIDATOR.validateRequiresAnnotation(moduleClass)) {
      Requires requires = moduleClass.getDeclaredAnnotation(Requires.class);

      moduleModelBuilder.withRequiredModules(Arrays.asList(requires.value()));
    }
  }

  private AmperModule tryParseModuleClassToModuleObject(Class<?> moduleClass) {
    checkNotNull(moduleClass, "moduleClass cannot be null!");

    try {
      return (AmperModule) moduleClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
        NoSuchMethodException cause) {
      cause.printStackTrace();
    }

    return null;
  }

}
