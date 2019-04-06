package org.amperpowered.amper.core.config.internal;

import java.nio.file.Path;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ConfigLoader {

  @NonNull
  static ConfigLoader vanilla() {
    return new VanillaConfigLoader();
  }

  @NonNull <T> Optional<T> load(@NonNull Path path, @NonNull T type);

}
