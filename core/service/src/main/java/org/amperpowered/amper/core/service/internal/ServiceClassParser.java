/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Singleton;
import java.lang.reflect.Method;
import java.util.Optional;
import org.amperpowered.amper.core.service.internal.annotation.BindWith;
import org.amperpowered.amper.core.service.internal.annotation.Runnable;
import org.amperpowered.amper.core.service.internal.annotation.Service;

@Singleton
public final class ServiceClassParser {

  public static ServiceClassParser create() {
    return new ServiceClassParser();
  }

  private static final ServiceClassValidator SERVICE_CLASS_VALIDATOR = ServiceClassValidator.create();

  public Optional<ServiceModel> parseServiceClassToModel(Class<?> serviceClass) {
    checkNotNull(serviceClass, "serviceClass cannot be null!");

    if (!SERVICE_CLASS_VALIDATOR.validateServiceClass(serviceClass)) {
      return Optional.empty();
    }

    Service service = serviceClass.getDeclaredAnnotation(Service.class);
    BindWith bindWith = serviceClass.getDeclaredAnnotation(BindWith.class);

    ServiceModel.Builder serviceModelBuilder = ServiceModel.newBuilder()
        .withName(service.value())
        .withNestedClass(bindWith.value());

    this.parseMethods(serviceModelBuilder, serviceClass.getDeclaredMethods());

    return Optional.of(serviceModelBuilder.create());
  }

  private void parseMethods(ServiceModel.Builder serviceModelBuilder, Method[] methods) {
    checkNotNull(serviceModelBuilder, "serviceModelBuilder cannot be null!");
    checkNotNull(methods, "methods cannot be null!");

    for (Method method : methods) {
      if (method.isAnnotationPresent(Runnable.class)) {
        ServiceLifeCycle serviceLifeCycle = method.getDeclaredAnnotation(Runnable.class).value();
        if (serviceLifeCycle.equals(ServiceLifeCycle.ENABLING)) {
          serviceModelBuilder.withEnablingMethod(method);
        } else if (serviceLifeCycle.equals(ServiceLifeCycle.DISABLING)) {
          serviceModelBuilder.withDisablingMethod(method);
        }
      }
    }
  }

}
