/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaModuleIndexRegistry implements ModuleIndexRegistry {

  static final ModuleIndexRegistry MODULE_INDEX_REGISTRY = new VanillaModuleIndexRegistry();
  private static final List<ModuleIndex> MODULE_INDICES = new ArrayList<>();

  @Override
  public void register(@NonNull ModuleIndex moduleIndex) {
    Preconditions.checkNotNull(moduleIndex, "moduleIndex cannot be null!");

    MODULE_INDICES.add(moduleIndex);
  }

  @Override
  public boolean contains(@NonNull String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return this.require(name).isPresent();
  }

  @NonNull
  @Override
  public Optional<ModuleIndex> require(@NonNull String name) {
    Preconditions.checkNotNull(name, "name cannot be null!");

    return MODULE_INDICES.stream()
        .filter(moduleIndex -> moduleIndex.name().equalsIgnoreCase(name))
        .findFirst();
  }

  @NonNull
  @Override
  public Collection<ModuleIndex> moduleIndices() {
    return Collections.unmodifiableCollection(MODULE_INDICES);
  }
}
