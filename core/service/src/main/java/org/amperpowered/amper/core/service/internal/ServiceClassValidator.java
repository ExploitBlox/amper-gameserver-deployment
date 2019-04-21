/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import org.amperpowered.amper.core.service.internal.annotation.BindWith;
import org.amperpowered.amper.core.service.internal.annotation.Service;

final class ServiceClassValidator {

  static ServiceClassValidator create() {
    return new ServiceClassValidator();
  }

  private static final Class<? extends Annotation> SERVICE_ANNOTATION_CLASS = Service.class;
  private static final Class<? extends Annotation> BIND_WITH_ANNOTATION_CLASS = BindWith.class;

  boolean validateServiceClass(Class<?> serviceClass) {
    checkNotNull(serviceClass, "serviceClass cannot be null!");
    return this.validateServiceAnnotation(serviceClass) && this.validateBindWithAnnotation(serviceClass);
  }

  private boolean validateServiceAnnotation(Class<?> serviceClass) {
    checkNotNull(serviceClass, "serviceClass cannot be null!");
    return serviceClass.isAnnotationPresent(SERVICE_ANNOTATION_CLASS);
  }

  private boolean validateBindWithAnnotation(Class<?> serviceClass) {
    checkNotNull(serviceClass, "serviceClass cannot be null!");
    return serviceClass.isAnnotationPresent(BIND_WITH_ANNOTATION_CLASS);
  }

}
