/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.amperpowered.amper.core.guice.GuiceFactory;

final class VanillaServiceMethodInvoker implements ServiceMethodInvoker {

  @Override
  public void invoke(Method method, Class<?> bindingClass) {
    Preconditions.checkNotNull(method, "method cannot be null!");
    Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");

    GuiceFactory guiceFactory = GuiceFactory.vanilla();

    guiceFactory.getInstance(bindingClass).ifPresent(object -> {
      try {
        method.invoke(object);
      } catch (IllegalAccessException | InvocationTargetException cause) {
        cause.printStackTrace();
      }
    });
  }
}
