/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

public interface ModuleModel {

  static ModuleModel begin() {
    return new ModuleModelBuilder();
  }

  String name();

  ModuleModel withName(String name);

  String version();

  ModuleModel withVersion(String version);

  String author();

  ModuleModel withAuthor(String author);

  String[] requires();

  ModuleModel withRequires(String[] requires);

  AmperModule amperModule();

  ModuleModel withAmperModule(AmperModule amperModule);

}
