/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal;

import com.google.inject.AbstractModule;
import org.amperpowered.amper.core.config.ConfigFactory;

final class ApplicationGuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    this.bind(ConfigFactory.class).toInstance(ConfigFactory.vanilla());
  }
}