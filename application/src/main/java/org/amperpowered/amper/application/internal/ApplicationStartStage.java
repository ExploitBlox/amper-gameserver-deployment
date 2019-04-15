/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal;

import java.nio.file.Paths;
import org.amperpowered.amper.core.service.ServiceFactory;
import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;
import org.amperpowered.amper.module.ModuleFactory;
import org.amperpowered.amper.module.internal.AmperModule;
import org.amperpowered.amper.module.internal.context.InitialContext;

public class ApplicationStartStage {

  @Stage(0)
  public void collectService() {
    ServiceFactory serviceFactory = ServiceFactory.vanilla();
    serviceFactory.scanServices();

    //TODO: SELECTING THE SERVICE NAME FROM CONFIGURATION
    serviceFactory.invokeService("master", ServiceLifeCycle.ENABLING);
  }

  @Stage(1)
  public void prepareModules() {
    ModuleFactory moduleFactory = ModuleFactory.vanilla();
    moduleFactory.registerModulesRecurvesly(Paths.get("modules/"));

    for (AmperModule amperModule : moduleFactory.amperModules()) {
      amperModule.initial(new InitialContext());
    }
  }
}
