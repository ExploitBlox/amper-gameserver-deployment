/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.amperpowered.amper.core.config.internal.ConfigLoader;
import org.amperpowered.amper.core.config.internal.ConfigSaver;

@Singleton
final class VanillaConfigFactory implements ConfigFactory {

  @Override
  public <T> void save(T requiredObject, Path path) {
    Preconditions.checkNotNull(path, "path cannot be null!");

    try {
      ConfigSaver configSaver = ConfigSaver.vanilla();
      configSaver.save(requiredObject, path);
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  @Override
  public <T> Optional<T> load(Path path, T type) {
    Preconditions.checkNotNull(path, "path cannot be null!");
    Preconditions.checkNotNull(type, "type cannot be null!");

    ConfigLoader configLoader = ConfigLoader.vanilla();
    return configLoader.load(path, type);
  }
}
