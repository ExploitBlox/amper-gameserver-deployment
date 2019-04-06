package org.amperpowered.core.bootstrap.internal;

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface BootstrapClassScanner {

  static BootstrapClassScanner vanilla() {
    return new VanillaBootstrapClassScanner();
  }

  @NonNull <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass);
}
