/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal;

import org.amperpowered.amper.core.guice.GuiceFactory;
import org.amperpowered.core.bootstrap.internal.Bootstrap;

public class ApplicationBootstrap implements Bootstrap {

  @Override
  public void initialize() {
    System.out.println("Initial Amper application...");

    GuiceFactory guiceFactory = GuiceFactory.vanilla();
    guiceFactory.createInjector(new ApplicationGuiceModule());
  }

  @Override
  public void terminate() {

  }

}
