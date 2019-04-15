package org.amperpowered.amper.application.internal.service.master;

import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;
import org.amperpowered.amper.core.service.internal.annotation.Runnable;
import org.amperpowered.amper.core.service.internal.annotation.Service;

@Service("master")
public class VanillaMasterService implements MasterService {

  static final MasterService MASTER_SERVICE = new VanillaMasterService();

  @Override
  @Runnable(ServiceLifeCycle.ENABLING)
  public void enable() {
    System.out.println("Initialized the 'master' service");
  }

  @Override
  @Runnable(ServiceLifeCycle.DISABLING)
  public void disable() {

  }
}
