/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ServiceMethodInvoker {

  @NonNull
  static ServiceMethodInvoker vanilla() {
    return new VanillaServiceMethodInvoker();
  }

  void invoke(@NonNull Method method);
}
