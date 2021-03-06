/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import com.google.inject.Inject;
import org.amperpowered.amper.core.guice.GuiceProvider;
import org.amperpowered.amper.core.stage.StageFactory;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;
import org.amperpowered.core.bootstrap.BootstrapFactory;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

public class AmperApplication {

  public static void main(String[] arguments) {
    GuiceProvider guiceProvider = GuiceProvider.create();
    guiceProvider.createInjector(new AmperApplicationGuiceModule());

    Logger.info("Loading Amper application, please wait a moment...");

    StageFactory stageFactory = StageFactory.vanilla();
    stageFactory.processingStage(AmperApplication.class);
  }

  @Inject
  private BootstrapFactory bootstrapFactory;

  @Stage(0)
  public void initializeTinyLogConfiguration() {
    Configurator.defaultConfig()
        .writer(new FileWriter("logs/amper-log.log"))
        .addWriter(new ConsoleWriter())
        .level(Level.INFO)
        .formatPattern("{date: HH:mm:ss} {level}: {message}")
        .activate();
  }

  @Stage(1)
  public void scanBootstrapClass() {
    bootstrapFactory.scanBootstrapClass(AmperApplicationBootstrap.class).ifPresent(applicationBootstrap -> {
      applicationBootstrap.initialize();

      Runtime.getRuntime().addShutdownHook(new Thread(applicationBootstrap::terminate));
    });
  }
}
