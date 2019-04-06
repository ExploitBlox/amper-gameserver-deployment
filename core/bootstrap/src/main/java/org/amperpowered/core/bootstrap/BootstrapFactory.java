package org.amperpowered.core.bootstrap;

import java.util.Optional;
import org.amperpowered.core.bootstrap.internal.Bootstrap;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface BootstrapFactory {

  @NonNull
  static BootstrapFactory vanilla() {
    return new VanillaBootstrapFactory();
  }

  @NonNull <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass);
}
