/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master;

import org.amperpowered.amper.core.stage.StageFactory;

public class VanillaMasterService implements MasterService {

  static final MasterService MASTER_SERVICE = new VanillaMasterService();
  private static final StageFactory STAGE_FACTORY = StageFactory.vanilla();

  @Override
  public void enable() {
    System.out.println("Initialized the 'master' service");

    STAGE_FACTORY.processingStage(MasterServiceStartStage.class);
  }

  @Override
  public void disable() {
    STAGE_FACTORY.processingStage(MasterServiceTerminateStage.class);
  }
}
