/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config.internal;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaConfigLoader implements ConfigLoader {

  private static final Gson GSON = new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  @NonNull
  @Override
  public <T> Optional<T> load(@NonNull Path path, @NonNull T type) {
    Preconditions.checkNotNull(path, "path cannot be null!");
    Preconditions.checkNotNull(type, "type cannot be null!");

    if (!Files.exists(path)) {
      return Optional.empty();
    }

    try {
      return (Optional<T>) Optional.of(GSON.fromJson(new FileReader(path.toFile().getName()), type.getClass()));
    } catch (FileNotFoundException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }
}
