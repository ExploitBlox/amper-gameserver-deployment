package org.amperpowered.amper.core.config;

import java.nio.file.Path;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;


public interface ConfigFactory {

  @NonNull
  static ConfigFactory vanilla() {
    return new VanillaConfigFactory();
  }

  <T> void save(@NonNull T requiredObject, @NonNull Path path);

  @NonNull <T> Optional<T> load(@NonNull Path path, @NonNull T type);
}
