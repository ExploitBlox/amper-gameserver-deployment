/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import org.amperpowered.amper.core.guice.GuiceFactory;
import org.amperpowered.amper.core.module.ModuleProvider;
import org.amperpowered.amper.core.module.internal.AmperModule;
import org.amperpowered.amper.core.module.internal.context.InitialContext;
import org.amperpowered.amper.core.service.ServiceProvider;
import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;

public class AmperApplicationStartStage {

  @Stage(0)
  public void collectService() {
    GuiceFactory guiceFactory = GuiceFactory.vanilla();

    guiceFactory.getInstance(ServiceProvider.class).ifPresent(serviceProvider -> {
      serviceProvider.scanServices();
      serviceProvider.invokeService("master", ServiceLifeCycle.ENABLING);
    });
  }

  @Stage(1)
  public void prepareModules() {
    GuiceFactory guiceFactory = GuiceFactory.vanilla();

    guiceFactory.getInstance(ModuleProvider.class).ifPresent(moduleProvider -> {
      moduleProvider.registerModules();

      for (AmperModule nestedModule : moduleProvider.nestedModules()) {
        nestedModule.initial(new InitialContext());
      }
    });
  }
}
