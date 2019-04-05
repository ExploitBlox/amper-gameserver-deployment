/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleScanner {

  @NonNull
  static ModuleScanner vanilla() {
    return new VanillaModuleScanner();
  }

  @NonNull
  List<Class<?>> scanModules(@NonNull Path modulePath) throws IOException;

  @NonNull
  Optional<Class<?>> scanModule(Path moduleFile);
}
