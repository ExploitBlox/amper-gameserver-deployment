/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import org.amperpowered.core.bootstrap.BootstrapFactory;

public class AmperApplication {

  public static void main(String[] arguments) {
    BootstrapFactory bootstrapFactory = BootstrapFactory.vanilla();

    bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class)
        .ifPresent(AmperApplicationBootstrap::initialize);

    Runtime.getRuntime()
        .addShutdownHook(new Thread(() -> bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class)
            .ifPresent(AmperApplicationBootstrap::terminate)));

  }
}
