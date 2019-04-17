/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.config.internal;

import java.io.IOException;
import java.nio.file.Path;

public interface ConfigSaver {


  static ConfigSaver vanilla() {
    return new VanillaConfigSaver();
  }

  <T> void save(T requiredObject, Path path) throws IOException;

}
