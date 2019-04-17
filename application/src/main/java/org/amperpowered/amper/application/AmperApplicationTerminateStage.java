/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import org.amperpowered.amper.core.module.ModuleFactory;
import org.amperpowered.amper.core.module.internal.AmperModule;
import org.amperpowered.amper.core.module.internal.context.TerminateContext;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;

public class AmperApplicationTerminateStage {

  @Stage(1)
  public void prepareModules() {
    ModuleFactory moduleFactory = ModuleFactory.vanilla();

    for (AmperModule amperModule : moduleFactory.amperModules()) {
      amperModule.terminate(new TerminateContext());
    }
  }
}
