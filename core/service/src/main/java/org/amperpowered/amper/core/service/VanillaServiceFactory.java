/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service;

import com.google.common.base.Preconditions;
import java.util.Optional;
import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.service.internal.ServiceMethodInvoker;
import org.amperpowered.amper.core.service.internal.ServiceModel;
import org.amperpowered.amper.core.service.internal.ServiceRegistry;
import org.amperpowered.amper.core.service.internal.ServiceScanner;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.pmw.tinylog.Logger;

final class VanillaServiceFactory implements ServiceFactory {

  @Override
  public void scanServices() {
    Logger.info("Scanning all services...");

    ServiceScanner serviceScanner = ServiceScanner.vanilla();
    serviceScanner.scanServices();
  }

  @Override
  public void invokeService(@NonNull String name, @NonNull ServiceLifeCycle serviceLifeCycle) {
    Preconditions.checkNotNull(name, "name cannot be null!");
    Preconditions.checkNotNull(serviceLifeCycle, "serviceLifeCycle cannot be null!");

    ServiceRegistry serviceRegistry = ServiceRegistry.vanilla();
    ServiceMethodInvoker serviceMethodInvoker = ServiceMethodInvoker.vanilla();

    Optional<ServiceModel> optionalServiceModel = serviceRegistry.serviceModel(name);

    optionalServiceModel.ifPresent(serviceModel -> {
      if (serviceLifeCycle.equals(ServiceLifeCycle.ENABLING)) {
        serviceModel.enablingMethod()
            .ifPresent(method -> serviceMethodInvoker.invoke(method, serviceModel.bindingClass()));
      } else if (serviceLifeCycle.equals(ServiceLifeCycle.DISABLING)) {
        serviceModel.disablingMethod()
            .ifPresent(method -> serviceMethodInvoker.invoke(method, serviceModel.bindingClass()));
      }
    });
  }
}
