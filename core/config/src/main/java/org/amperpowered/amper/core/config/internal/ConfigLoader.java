/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config.internal;

import java.nio.file.Path;
import java.util.Optional;

public interface ConfigLoader {


  static ConfigLoader vanilla() {
    return new VanillaConfigLoader();
  }

  <T> Optional<T> load(Path path, T type);

}
