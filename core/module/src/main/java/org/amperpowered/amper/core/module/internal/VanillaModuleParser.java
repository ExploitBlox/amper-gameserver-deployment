/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import com.google.common.base.Preconditions;
import org.amperpowered.amper.core.module.internal.annotation.Module;
import org.amperpowered.amper.core.module.internal.annotation.Requires;

final class VanillaModuleParser implements ModuleParser {

  @Override
  public ModuleModel parseModule(Class<?> amperModuleClass) {
    Preconditions.checkNotNull(amperModuleClass, "amperModuleClass cannot be null!");

    Module module = amperModuleClass.getDeclaredAnnotation(Module.class);

    ModuleModel moduleModel = ModuleModel.begin()
        .withName(module.name())
        .withVersion(module.version())
        .withAuthor(module.author());

    if (amperModuleClass.isAnnotationPresent(Requires.class)) {
      Requires requires = amperModuleClass.getDeclaredAnnotation(Requires.class);
      moduleModel.withRequires(requires.value());
    }

    try {
      AmperModule amperModule = (AmperModule) amperModuleClass.newInstance();
      moduleModel.withAmperModule(amperModule);

    } catch (InstantiationException | IllegalAccessException cause) {
      cause.printStackTrace();
    }

    return moduleModel;
  }
}
