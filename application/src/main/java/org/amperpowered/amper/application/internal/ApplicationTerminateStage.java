/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal;

import org.amperpowered.amper.core.stage.internal.annotation.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationTerminateStage {

  private static final Logger LOGGER = LogManager.getLogger(ApplicationTerminateStage.class);

  @Stage(0)
  public void printTerminateMessage() {
    LOGGER.info("Terminate the amper application...");
  }
}
