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

  private static final ServiceComposer SERVICE_COMPOSER = ServiceComposer.vanilla();
  private static final ServiceRegistry SERVICE_REGISTRY = ServiceRegistry.vanilla();
  private static final ClassGraph CLASS_GRAPH = new ClassGraph();

  @Override
  public void scanServices() {
    try (ScanResult scanResult = CLASS_GRAPH.enableAllInfo().scan()) {
      scanResult.getClassesWithAnnotation(Service.class.getName())
          .loadClasses()
          .forEach(serviceClass -> SERVICE_COMPOSER.composeService(serviceClass)
              .ifPresent(serviceModel -> {
                Logger.info("Scanned the serviceModel '" + serviceModel.name() + "'");
                SERVICE_REGISTRY.register(serviceModel);
              }));
    }
  }
}
