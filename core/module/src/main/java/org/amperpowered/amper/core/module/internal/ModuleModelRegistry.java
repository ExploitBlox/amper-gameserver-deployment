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

public interface ModuleModelRegistry {

  @NonNull
  static ModuleModelRegistry vanilla() {
    return VanillaModuleModelRegistry.MODULE_MODEL_REGISTRY;
  }

  void register(@NonNull ModuleModel moduleModel);

  boolean contains(@NonNull String name);

  @NonNull
  Optional<ModuleModel> require(@NonNull String name);

  @NonNull
  Collection<ModuleModel> moduleIndices();
}
