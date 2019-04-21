/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Singleton
public final class ConfigLoader {

  public static ConfigLoader create() {
    return new ConfigLoader();
  }

  private static final Gson GSON = new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  public <T> Optional<T> load(Path path, Class<?> type) {
    checkNotNull(path, "path cannot be null!");
    checkNotNull(type, "type cannot be null!");

    if (!Files.exists(path)) {
      return Optional.empty();
    }

    try {
      return Optional.of((T) GSON.fromJson(new FileReader(path.toFile().getName()), type));
    } catch (FileNotFoundException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }

}
