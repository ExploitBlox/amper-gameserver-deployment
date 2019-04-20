/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import org.amperpowered.amper.core.module.internal.annotation.Module;
import org.amperpowered.amper.core.module.internal.annotation.Requires;

final class ModuleClassValidator {

  public static ModuleClassValidator create() {
    return new ModuleClassValidator();
  }

  private static final Class<? extends Annotation> MODULE_ANNOTATION_CLASS = Module.class;
  private static final Class<? extends Annotation> REQUIRES_ANNOTATION_CLASS = Requires.class;

  boolean validateModuleAnnotation(Class<?> moduleClass) {
    Preconditions.checkNotNull(moduleClass, "moduleClass cannot be null!");

    return moduleClass.isAnnotationPresent(MODULE_ANNOTATION_CLASS);
  }

  boolean validateRequiresAnnotation(Class<?> moduleClass) {
    Preconditions.checkNotNull(moduleClass, "moduleClass cannot be null!");

    return moduleClass.isAnnotationPresent(REQUIRES_ANNOTATION_CLASS);
  }

}
