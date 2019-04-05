/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleParser {

  @NonNull
  static ModuleParser vanilla() {
    return new VanillaModuleParser();
  }

  @NonNull
  ModuleIndex parseModule(@NonNull Class<?> amperModuleClass);
}
