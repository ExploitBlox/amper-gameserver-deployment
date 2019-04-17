/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.daemon;

import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.service.internal.annotation.BindWith;
import org.amperpowered.amper.core.service.internal.annotation.Runnable;
import org.amperpowered.amper.core.service.internal.annotation.Service;

@Service("daemon")
@BindWith(VanillaDaemonService.class)
public interface DaemonService {

  static DaemonService vanilla() {
    return VanillaDaemonService.DAEMON_SERVICE;
  }

  @Runnable(ServiceLifeCycle.ENABLING)
  void enable();

  @Runnable(ServiceLifeCycle.DISABLING)
  void disable();
}
