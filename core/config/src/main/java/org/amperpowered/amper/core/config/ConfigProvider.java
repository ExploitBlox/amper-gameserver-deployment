/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.amperpowered.amper.core.config.internal.ConfigLoader;
import org.amperpowered.amper.core.config.internal.ConfigSaver;

public final class ConfigProvider {

  private ConfigSaver configSaver;
  private ConfigLoader configLoader;

  @Inject
  public ConfigProvider(ConfigSaver configSaver, ConfigLoader configLoader) {
    this.configSaver = configSaver;
    this.configLoader = configLoader;
  }

  public <T> void save(T requiredObject, Path path) {
    checkNotNull(path, "path cannot be null!");
    checkNotNull(this.configSaver, "configSaver cannot be null!");

    try {
      this.configSaver.save(requiredObject, path);
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  public <T> Optional<T> load(Path path, Class<?> type) {
    checkNotNull(path, "path cannot be null!");
    checkNotNull(type, "type cannot be null!");
    checkNotNull(this.configLoader, "configLoader cannot be null!");

    return this.configLoader.load(path, type);
  }
}
