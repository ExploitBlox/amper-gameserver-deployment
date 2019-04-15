/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ServiceRegistry {

  @NonNull
  static ServiceRegistry vanilla() {
    return VanillaServiceRegistry.SERVICE_REGISTRY;
  }

  void register(@NonNull ServiceModel serviceModel);

  @NonNull
  Optional<ServiceModel> serviceModel(@NonNull String name);
}
