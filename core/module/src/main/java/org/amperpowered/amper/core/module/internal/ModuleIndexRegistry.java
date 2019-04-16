/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import java.util.Collection;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleIndexRegistry {

  @NonNull
  static ModuleIndexRegistry vanilla() {
    return VanillaModuleIndexRegistry.MODULE_INDEX_REGISTRY;
  }

  void register(@NonNull ModuleIndex moduleIndex);

  boolean contains(@NonNull String name);

  @NonNull
  Optional<ModuleIndex> require(@NonNull String name);

  @NonNull
  Collection<ModuleIndex> moduleIndices();
}
