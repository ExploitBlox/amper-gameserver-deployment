package org.amperpowered.amper.application;

import org.amperpowered.core.bootstrap.internal.Bootstrap;

public class AmperApplicationBootstrap implements Bootstrap {

  @Override
  public void initialize() {
    System.out.println("Initial Amper application...");
  }

}
