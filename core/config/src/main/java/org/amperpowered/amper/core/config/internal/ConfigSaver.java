package org.amperpowered.amper.core.config.internal;

import java.io.IOException;
import java.nio.file.Path;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ConfigSaver {

  @NonNull
  static ConfigSaver vanilla() {
    return new VanillaConfigSaver();
  }

  <T> void save(@NonNull T requiredObject, @NonNull Path path) throws IOException;

}
