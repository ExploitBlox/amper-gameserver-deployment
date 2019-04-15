/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import org.amperpowered.amper.core.service.internal.annotation.Runnable;
import org.amperpowered.amper.core.service.internal.annotation.Service;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaServiceComposer implements ServiceComposer {

  @NonNull
  @Override
  public Optional<ServiceModel> composeService(@NonNull Class<?> serviceClass) {
    Preconditions.checkNotNull(serviceClass, "serviceClass cannot be null!");

    if (!serviceClass.isAnnotationPresent(Service.class)) {
      return Optional.empty();
    }

    Service service = serviceClass.getDeclaredAnnotation(Service.class);

    ServiceModel serviceModel = ServiceModel.begin()
        .withName(service.value());

    this.composeMethods(serviceModel, serviceClass.getDeclaredMethods());

    return Optional.of(serviceModel);
  }

  private void composeMethods(@NonNull ServiceModel serviceModel, @NonNull Method[] methods) {
    Preconditions.checkNotNull(serviceModel, "serviceModel cannot be null!");
    Preconditions.checkNotNull(methods, "methods cannot be null!");

    Arrays.stream(methods)
        .filter(method -> method.isAnnotationPresent(Runnable.class))
        .forEach(method -> {
          Runnable runnable = method.getDeclaredAnnotation(Runnable.class);
          ServiceLifeCycle serviceLifeCycle = runnable.value();
          if (serviceLifeCycle.equals(ServiceLifeCycle.ENABLING)) {
            serviceModel.withEnablingMethod(method);
          } else if (serviceLifeCycle.equals(ServiceLifeCycle.DISABLING)) {
            serviceModel.withDisablingMethod(method);
          }
        });
  }
}
