/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.amperpowered.amper.core.guice.GuiceProvider;

@Singleton
public final class ServiceMethodInvoker {

  public static ServiceMethodInvoker create() {
    return new ServiceMethodInvoker();
  }

  public void invoke(Method method, Class<?> bindingClass) {
    checkNotNull(method, "method cannot be null!");
    checkNotNull(bindingClass, "bindingClass cannot be null!");

    GuiceProvider guiceProvider = GuiceProvider.create();

    guiceProvider.getInstance(bindingClass).ifPresent(object -> {
      try {
        method.invoke(object);
      } catch (IllegalAccessException | InvocationTargetException cause) {
        cause.printStackTrace();
      }
    });
  }
}
