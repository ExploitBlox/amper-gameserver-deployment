/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public final class ServiceModelRegistry {

  public static ServiceModelRegistry create() {
    return new ServiceModelRegistry();
  }

  private static final List<ServiceModel> SERVICE_MODELS = new ArrayList<>();

  public void register(ServiceModel serviceModel) {
    checkNotNull(serviceModel, "serviceModel cannot be null!");

    SERVICE_MODELS.add(serviceModel);
  }

  public Optional<ServiceModel> require(String name) {
    checkNotNull(name, "name cannot be null!");

    return SERVICE_MODELS.stream()
        .filter(serviceModel -> serviceModel.name().equalsIgnoreCase(name))
        .findFirst();
  }
}
