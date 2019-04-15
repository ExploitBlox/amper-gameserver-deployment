package org.amperpowered.amper.application.internal.service.daemon;

import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.service.internal.annotation.Runnable;
import org.amperpowered.amper.core.service.internal.annotation.Service;

@Service("daemon")
public class VanillaDaemonService implements DaemonService {

  static final DaemonService DAEMON_SERVICE = new VanillaDaemonService();

  @Override
  @Runnable(ServiceLifeCycle.ENABLING)
  public void enable() {
    System.out.println("Initialized the 'daemon' service");

  }

  @Override
  @Runnable(ServiceLifeCycle.DISABLING)
  public void disable() {

  }
}