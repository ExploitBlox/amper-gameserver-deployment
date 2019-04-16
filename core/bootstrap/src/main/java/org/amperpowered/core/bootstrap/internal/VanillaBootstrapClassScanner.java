/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.core.bootstrap.internal;

import com.google.common.base.Preconditions;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.pmw.tinylog.Logger;

final class VanillaBootstrapClassScanner implements BootstrapClassScanner {

  @NonNull
  @Override
  public <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass) {
    Preconditions.checkNotNull(bootstrapClass, "bootstrapClass cannot be null!");

    Logger.info("Scanned the bootstrap class '" + bootstrapClass.getName() + "'");

    if (!Bootstrap.class.isAssignableFrom(bootstrapClass)) {
      Logger.info("Cannot scan the bootstrap class because the class is not assignable from '"
          + Bootstrap.class.getName() + "'");
      return Optional.empty();
    }

    try {
      Logger.info("Successfully scan the bootstrap class!");
      return Optional.of(bootstrapClass.newInstance());
    } catch (InstantiationException | IllegalAccessException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }
}
