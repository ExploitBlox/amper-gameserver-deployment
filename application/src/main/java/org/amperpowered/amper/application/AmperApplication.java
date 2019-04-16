/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import org.amperpowered.core.bootstrap.BootstrapFactory;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

public class AmperApplication {

  public static void main(String[] arguments) {
    Configurator.defaultConfig()
        .writer(new FileWriter("logs/amper-log.log"))
        .addWriter(new ConsoleWriter())
        .level(Level.INFO)
        .formatPattern("{date: HH:mm:ss} {level}: {message}")
        .activate();

    BootstrapFactory bootstrapFactory = BootstrapFactory.vanilla();

    bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class)
        .ifPresent(AmperApplicationBootstrap::initialize);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(() -> bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class)
                .ifPresent(AmperApplicationBootstrap::terminate)));

  }
}
