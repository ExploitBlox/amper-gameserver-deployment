/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.core.bootstrap.internal;

import java.util.Optional;

public interface BootstrapClassScanner {

  static BootstrapClassScanner vanilla() {
    return new VanillaBootstrapClassScanner();
  }

  <T extends Bootstrap> Optional<T> scanBootstrapClass(Class<T> bootstrapClass);
}
