/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
public class ModuleModelRegistry {

  public static ModuleModelRegistry create() {
    return new ModuleModelRegistry();
  }

  private static final List<ModuleModel> MODULE_MODELS = new ArrayList<>();

  public void register(ModuleModel moduleModel) {
    Preconditions.checkNotNull(moduleModel, "moduleModel cannot be null!");

    MODULE_MODELS.add(moduleModel);
  }

  public boolean contains(String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return this.require(name).isPresent();
  }

  public Optional<ModuleModel> require(String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return MODULE_MODELS.stream()
        .filter(moduleModel -> moduleModel.name().equalsIgnoreCase(name))
        .findFirst();
  }

  public Collection<ModuleModel> moduleModels() {
    return Collections.unmodifiableCollection(MODULE_MODELS);
  }
}
