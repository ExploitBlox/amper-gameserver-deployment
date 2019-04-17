/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import java.util.Collection;
import java.util.Optional;

public interface ModuleModelRegistry {

  static ModuleModelRegistry vanilla() {
    return VanillaModuleModelRegistry.MODULE_MODEL_REGISTRY;
  }

  void register(ModuleModel moduleModel);

  boolean contains(String name);

  Optional<ModuleModel> require(String name);

  Collection<ModuleModel> moduleIndices();
}
