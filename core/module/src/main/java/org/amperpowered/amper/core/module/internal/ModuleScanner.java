/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface ModuleScanner {

  static ModuleScanner vanilla() {
    return new VanillaModuleScanner();
  }

  List<Class<?>> scanModules(Path modulePath) throws IOException;

  Optional<Class<?>> scanModule(Path moduleFile);
}
