/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service;

import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ServiceFactory {

  @NonNull
  static ServiceFactory vanilla() {
    return new VanillaServiceFactory();
  }

  void scanServices();

  void invokeService(@NonNull String name, @NonNull ServiceLifeCycle serviceLifeCycle);
}
