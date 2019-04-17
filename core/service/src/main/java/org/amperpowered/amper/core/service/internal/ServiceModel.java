/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import java.lang.reflect.Method;
import java.util.Optional;

public interface ServiceModel {

  static ServiceModel begin() {
    return new ServiceModelBuilder();
  }

  String name();

  ServiceModel withName(String name);

  Class<?> bindingClass();

  ServiceModel withBindingClass(Class<?> bindingClass);

  Optional<Method> enablingMethod();

  ServiceModel withEnablingMethod(Method enablingMethod);

  Optional<Method> disablingMethod();

  ServiceModel withDisablingMethod(Method disablingMethod);
}
