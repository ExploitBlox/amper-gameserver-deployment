/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import org.amperpowered.amper.core.guice.GuiceFactory;
import org.amperpowered.amper.core.stage.StageFactory;
import org.amperpowered.core.bootstrap.internal.Bootstrap;
import org.pmw.tinylog.Logger;

public class AmperApplicationBootstrap implements Bootstrap {

  @Override
  public void initialize() {
    Logger.info("Loading Amper application, please wait a moment...");
    GuiceFactory guiceFactory = GuiceFactory.vanilla();
    guiceFactory.createInjector(new AmperApplicationGuiceModule());

    StageFactory stageFactory = StageFactory.vanilla();
    stageFactory.processingStage(AmperApplicationStartStage.class);
  }

  @Override
  public void terminate() {
    StageFactory stageFactory = StageFactory.vanilla();
    stageFactory.processingStage(AmperApplicationTerminateStage.class);
  }

}
