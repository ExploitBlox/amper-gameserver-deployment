/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.amperpowered.amper.core.service.internal.annotation.Service;
import org.pmw.tinylog.Logger;

final class VanillaServiceScanner implements ServiceScanner {

  @Override
  public void scanServices() {
    ClassGraph classGraph = new ClassGraph();

    ServiceComposer serviceComposer = ServiceComposer.vanilla();
    ServiceRegistry serviceRegistry = ServiceRegistry.vanilla();

    try (ScanResult scanResult = classGraph.enableAllInfo().scan()) {
      scanResult.getClassesWithAnnotation(Service.class.getName())
          .loadClasses()
          .forEach(serviceClass -> serviceComposer.composeService(serviceClass)
              .ifPresent(serviceModel -> {
                Logger.info("Scanned the serviceModel '" + serviceModel.name() + "'");
                serviceRegistry.register(serviceModel);
              }));
    }
  }
}
