/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleIndex {

  @NonNull
  static ModuleIndex begin() {
    return new ModuleIndexBuilder();
  }

  @NonNull
  String name();

  @NonNull
  ModuleIndex withName(@NonNull String name);

  @NonNull
  String version();

  @NonNull
  ModuleIndex withVersion(@NonNull String version);

  @NonNull
  String author();

  @NonNull
  ModuleIndex withAuthor(@NonNull String author);

  @NonNull
  String[] requires();

  @NonNull
  ModuleIndex withRequires(@NonNull String[] requires);

  @NonNull
  AmperModule amperModule();

  @NonNull
  ModuleIndex withAmperModule(@NonNull AmperModule amperModule);

}
