/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.core.bootstrap;

import com.google.common.base.Preconditions;
import java.util.Optional;
import org.amperpowered.core.bootstrap.internal.Bootstrap;
import org.amperpowered.core.bootstrap.internal.BootstrapClassScanner;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaBootstrapFactory implements BootstrapFactory {

  @NonNull
  @Override
  public <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass) {
    Preconditions.checkNotNull(bootstrapClass, "bootstrapClass cannot be null!");

    BootstrapClassScanner bootstrapClassScanner = BootstrapClassScanner.vanilla();

    return bootstrapClassScanner.scanBootstrapClass(bootstrapClass);
  }
}
