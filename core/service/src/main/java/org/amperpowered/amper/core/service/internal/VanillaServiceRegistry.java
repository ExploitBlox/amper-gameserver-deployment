/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaServiceRegistry implements ServiceRegistry {

  static final ServiceRegistry SERVICE_REGISTRY = new VanillaServiceRegistry();
  private static final List<ServiceModel> SERVICE_MODELS = new ArrayList<>();

  @Override
  public void register(@NonNull ServiceModel serviceModel) {
    Preconditions.checkNotNull(serviceModel, "serviceModel cannot be null!");

    SERVICE_MODELS.add(serviceModel);
  }

  @NonNull
  @Override
  public Optional<ServiceModel> serviceModel(@NonNull String name) {
    return SERVICE_MODELS.stream()
        .filter(serviceModel -> serviceModel.name().equalsIgnoreCase(name))
        .findFirst();
  }
}
