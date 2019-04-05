/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module.internal;

import com.google.common.base.Preconditions;
import org.amperpowered.amper.module.internal.annotation.Module;
import org.amperpowered.amper.module.internal.annotation.Requires;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaModuleParser implements ModuleParser {

  @NonNull
  @Override
  public ModuleIndex parseModule(@NonNull Class<?> amperModuleClass) {
    Preconditions.checkNotNull(amperModuleClass, "amperModuleClass cannot be null!");

    Module module = amperModuleClass.getDeclaredAnnotation(Module.class);

    ModuleIndex moduleIndex = ModuleIndex.begin()
        .withName(module.name())
        .withVersion(module.version())
        .withAuthor(module.author());

    if (amperModuleClass.isAnnotationPresent(Requires.class)) {
      Requires requires = amperModuleClass.getDeclaredAnnotation(Requires.class);
      moduleIndex.withRequires(requires.value());
    }

    try {
      AmperModule amperModule = (AmperModule) amperModuleClass.newInstance();
      moduleIndex.withAmperModule(amperModule);

    } catch (InstantiationException | IllegalAccessException cause) {
      cause.printStackTrace();
    }

    return moduleIndex;
  }
}
