/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application;

import com.google.inject.AbstractModule;
import org.amperpowered.amper.core.config.ConfigFactory;
import org.amperpowered.amper.core.module.internal.ModuleClassParser;
import org.amperpowered.amper.core.module.internal.ModuleClassScanner;
import org.amperpowered.amper.core.module.internal.ModuleModelRegistry;
import org.amperpowered.amper.core.service.ServiceFactory;
import org.amperpowered.amper.core.stage.StageFactory;
import org.amperpowered.amper.core.web.WebFactory;
import org.amperpowered.core.bootstrap.BootstrapFactory;

final class AmperApplicationGuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    this.bind(ConfigFactory.class).toInstance(ConfigFactory.vanilla());
    this.bind(ServiceFactory.class).toInstance(ServiceFactory.vanilla());
    this.bind(StageFactory.class).toInstance(StageFactory.vanilla());
    this.bind(WebFactory.class).toInstance(WebFactory.vanilla());
    this.bind(BootstrapFactory.class).toInstance(BootstrapFactory.vanilla());

    this.bind(ModuleModelRegistry.class).toInstance(ModuleModelRegistry.create());
    this.bind(ModuleClassParser.class).toInstance(ModuleClassParser.create());
    this.bind(ModuleClassScanner.class).toInstance(ModuleClassScanner.create());
  }
}
