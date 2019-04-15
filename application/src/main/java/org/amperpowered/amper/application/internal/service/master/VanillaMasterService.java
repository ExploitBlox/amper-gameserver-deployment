/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master;

public class VanillaMasterService implements MasterService {

  static final MasterService MASTER_SERVICE = new VanillaMasterService();

  @Override
  public void enable() {
    System.out.println("Initialized the 'master' service");
  }

  @Override
  public void disable() {
  }
}
