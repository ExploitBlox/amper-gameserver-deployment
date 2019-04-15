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
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaServiceMethodInvoker implements ServiceMethodInvoker {

  @Override
  public void invoke(@NonNull Method method, @NonNull Class<?> bindingClass) {
    Preconditions.checkNotNull(method, "method cannot be null!");
    Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");

    try {
      method.invoke(bindingClass.newInstance());
    } catch (IllegalAccessException | InvocationTargetException | InstantiationException cause) {
      cause.printStackTrace();
    }
  }
}
