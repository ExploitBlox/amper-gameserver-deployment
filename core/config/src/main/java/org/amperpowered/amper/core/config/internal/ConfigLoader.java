/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
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