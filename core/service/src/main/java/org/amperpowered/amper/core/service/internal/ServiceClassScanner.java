/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Singleton;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.amperpowered.amper.core.service.internal.annotation.Service;

@Singleton
public final class ServiceClassScanner {

  public static ServiceClassScanner create() {
    return new ServiceClassScanner();
  }

  public void scanServices(
      ServiceClassParser serviceClassParser,
      ServiceModelRegistry serviceModelRegistry) {
    checkNotNull(serviceClassParser, "serviceClassParser cannot be null!");
    checkNotNull(serviceModelRegistry, "serviceModelRegistry cannot be null!");

    ClassGraph classGraph = new ClassGraph();

    try (ScanResult scanResult = classGraph.enableAllInfo().scan()) {
      scanResult.getClassesWithAnnotation(Service.class.getName())
          .loadClasses()
          .forEach(serviceClass -> serviceClassParser.parseServiceClassToModel(serviceClass)
              .ifPresent(serviceModelRegistry::register));
    }
  }
}
