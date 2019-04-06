package org.amperpowered.core.bootstrap.internal;

import com.google.common.base.Preconditions;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaBootstrapClassScanner implements BootstrapClassScanner {

  @NonNull
  @Override
  public <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass) {
    Preconditions.checkNotNull(bootstrapClass, "bootstrapClass cannot be null!");

    if (!Bootstrap.class.isAssignableFrom(bootstrapClass)) {
      return Optional.empty();
    }

    try {
      return Optional.of(bootstrapClass.newInstance());
    } catch (InstantiationException | IllegalAccessException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }
}
