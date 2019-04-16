/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface ModuleModel {

  @NonNull
  static ModuleModel begin() {
    return new ModuleModelBuilder();
  }

  @NonNull
  String name();

  @NonNull
  ModuleModel withName(@NonNull String name);

  @NonNull
  String version();

  @NonNull
  ModuleModel withVersion(@NonNull String version);

  @NonNull
  String author();

  @NonNull
  ModuleModel withAuthor(@NonNull String author);

  @NonNull
  String[] requires();

  @NonNull
  ModuleModel withRequires(@NonNull String[] requires);

  @NonNull
  AmperModule amperModule();

  @NonNull
  ModuleModel withAmperModule(@NonNull AmperModule amperModule);

}
