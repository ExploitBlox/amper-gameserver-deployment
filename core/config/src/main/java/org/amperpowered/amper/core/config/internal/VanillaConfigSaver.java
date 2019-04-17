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
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class VanillaConfigSaver implements ConfigSaver {

  private static final Gson GSON = new GsonBuilder()
      .disableHtmlEscaping()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  @Override
  public <T> void save(T requiredObject, Path path) throws IOException {
    Preconditions.checkNotNull(requiredObject, "requiredObject cannot be null!");
    Preconditions.checkNotNull(path, "path cannot be null!");

    if (!Files.exists(path)) {
      Files.createFile(path);
    }

    try (FileWriter fileWriter = new FileWriter(path.toFile().getName())) {
      GSON.toJson(requiredObject, fileWriter);
    }
  }
}
