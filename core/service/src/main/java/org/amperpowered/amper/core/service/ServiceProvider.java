/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Inject;
import java.util.Optional;
import org.amperpowered.amper.core.service.internal.ServiceClassParser;
import org.amperpowered.amper.core.service.internal.ServiceClassScanner;
import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.service.internal.ServiceMethodInvoker;
import org.amperpowered.amper.core.service.internal.ServiceModel;
import org.amperpowered.amper.core.service.internal.ServiceModelRegistry;

public final class ServiceProvider {

  private ServiceModelRegistry serviceModelRegistry;
  private ServiceClassParser serviceClassParser;
  private ServiceMethodInvoker serviceMethodInvoker;
  private ServiceClassScanner serviceClassScanner;

  @Inject
  public ServiceProvider(ServiceModelRegistry serviceModelRegistry,
      ServiceClassParser serviceClassParser,
      ServiceMethodInvoker serviceMethodInvoker,
      ServiceClassScanner serviceClassScanner) {
    this.serviceModelRegistry = serviceModelRegistry;
    this.serviceClassParser = serviceClassParser;
    this.serviceMethodInvoker = serviceMethodInvoker;
    this.serviceClassScanner = serviceClassScanner;
  }

  public void scanServices() {
    checkNotNull(this.serviceClassScanner, "serviceClassScanner cannot be null!");
    checkNotNull(this.serviceClassParser, "serviceClassParser cannot be null!");
    checkNotNull(this.serviceModelRegistry, "serviceModelRegistry cannot be null!");

    this.serviceClassScanner.scanServices(this.serviceClassParser, this.serviceModelRegistry);
  }

  public void invokeService(String name, ServiceLifeCycle serviceLifeCycle) {
    checkNotNull(name, "name cannot be null!");
    checkNotNull(serviceLifeCycle, "serviceLifeCycle cannot be null!");
    checkNotNull(this.serviceModelRegistry, "serviceModelRegistry cannot be null!");
    checkNotNull(this.serviceMethodInvoker, "serviceMethodInvoker cannot be null!");

    Optional<ServiceModel> optionalServiceModel = this.serviceModelRegistry.require(name);

    optionalServiceModel.ifPresent(serviceModel -> {
      if (serviceLifeCycle.equals(ServiceLifeCycle.ENABLING) && serviceModel.enablingMethod() != null) {
        this.serviceMethodInvoker.invoke(serviceModel.enablingMethod(), serviceModel.nestedClass());
      } else if (serviceLifeCycle.equals(ServiceLifeCycle.DISABLING) && serviceModel.disablingMethod() != null) {
        this.serviceMethodInvoker.invoke(serviceModel.disablingMethod(), serviceModel.nestedClass());
      }
    });
  }
}
