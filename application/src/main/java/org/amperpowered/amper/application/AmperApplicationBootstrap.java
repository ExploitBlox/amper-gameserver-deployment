/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import com.google.inject.Inject;
import org.amperpowered.amper.core.stage.StageFactory;
import org.amperpowered.core.bootstrap.internal.Bootstrap;

public class AmperApplicationBootstrap implements Bootstrap {

  @Inject
  private StageFactory stageFactory;

  @Override
  public void initialize() {
    stageFactory.processingStage(AmperApplicationStartStage.class);
  }

  @Override
  public void terminate() {
    stageFactory.processingStage(AmperApplicationTerminateStage.class);
  }

}
