/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.daemon;

import org.amperpowered.amper.core.stage.StageFactory;
import org.pmw.tinylog.Logger;

public class VanillaDaemonService implements DaemonService {

  static final DaemonService DAEMON_SERVICE = new VanillaDaemonService();
  private static final StageFactory STAGE_FACTORY = StageFactory.vanilla();

  @Override
  public void enable() {
    Logger.info("Initialized the 'daemon' service");

    STAGE_FACTORY.processingStage(DaemonServiceStartStage.class);
  }

  @Override
  public void disable() {
    STAGE_FACTORY.processingStage(DaemonServiceTerminateStage.class);
  }
}