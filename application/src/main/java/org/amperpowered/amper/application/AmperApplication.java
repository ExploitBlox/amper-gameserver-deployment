package org.amperpowered.amper.application;

import org.amperpowered.core.bootstrap.BootstrapFactory;

public class AmperApplication {

  public static void main(String[] arguments) {
    BootstrapFactory bootstrapFactory = BootstrapFactory.vanilla();

    bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class)
        .ifPresent(AmperApplicationBootstrap::initialize);
  }
}
