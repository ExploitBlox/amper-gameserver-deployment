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
import org.checkerframework.checker.nullness.qual.NonNull;

@Singleton
final class VanillaConfigFactory implements ConfigFactory {

  private static final ConfigSaver CONFIG_SAVER = ConfigSaver.vanilla();
  private static final ConfigLoader CONFIG_LOADER = ConfigLoader.vanilla();

  @Override
  public <T> void save(@NonNull T requiredObject, @NonNull Path path) {
    Preconditions.checkNotNull(path, "path cannot be null!");

    try {
      CONFIG_SAVER.save(requiredObject, path);
    } catch (IOException cause) {
      cause.printStackTrace();
    }
  }

  @NonNull
  @Override
  public <T> Optional<T> load(@NonNull Path path, @NonNull T type) {
    Preconditions.checkNotNull(path, "path cannot be null!");
    Preconditions.checkNotNull(type, "type cannot be null!");

    return CONFIG_LOADER.load(path, type);
  }
}
