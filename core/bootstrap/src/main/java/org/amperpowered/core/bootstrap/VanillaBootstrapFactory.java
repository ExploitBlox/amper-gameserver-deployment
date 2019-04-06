package org.amperpowered.core.bootstrap;

import com.google.common.base.Preconditions;
import java.util.Optional;
import org.amperpowered.core.bootstrap.internal.Bootstrap;
import org.amperpowered.core.bootstrap.internal.BootstrapClassScanner;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaBootstrapFactory implements BootstrapFactory {

  private static final BootstrapClassScanner BOOTSTRAP_CLASS_SCANNER = BootstrapClassScanner.vanilla();

  @NonNull
  @Override
  public <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass) {
    Preconditions.checkNotNull(bootstrapClass, "bootstrapClass cannot be null!");

    return BOOTSTRAP_CLASS_SCANNER.scanBootstrapClass(bootstrapClass);
  }
}
