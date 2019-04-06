/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.core.bootstrap.internal;

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface BootstrapClassScanner {

  static BootstrapClassScanner vanilla() {
    return new VanillaBootstrapClassScanner();
  }

  @NonNull <T extends Bootstrap> Optional<T> scanBootstrapClass(@NonNull Class<T> bootstrapClass);
}
