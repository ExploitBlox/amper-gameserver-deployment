/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Objects;

final class ModuleModelBuilder implements ModuleModel {

  private String name;
  private String version;
  private String author;
  private String[] requires;
  private AmperModule amperModule;

  ModuleModelBuilder() {
    this.name = "undefined";
    this.version = "1.0.0";
    this.author = "undefined";
    this.requires = new String[]{};
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public ModuleModel withName(String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @Override
  public String version() {
    return this.version;
  }

  @Override
  public ModuleModel withVersion(String version) {
    this.version = Preconditions.checkNotNull(version, "version cannot be null!");
    return this;
  }

  @Override
  public String author() {
    return this.author;
  }

  @Override
  public ModuleModel withAuthor(String author) {
    this.author = author;
    return this;
  }

  @Override
  public String[] requires() {
    return this.requires;
  }

  @Override
  public ModuleModel withRequires(String[] requires) {
    this.requires = Preconditions.checkNotNull(requires, "requires cannot be null!");
    return this;
  }

  @Override
  public AmperModule amperModule() {
    return this.amperModule;
  }

  @Override
  public ModuleModel withAmperModule(AmperModule amperModule) {
    this.amperModule = Preconditions.checkNotNull(amperModule, "amperModule cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ModuleModelBuilder)) {
      return false;
    }

    ModuleModelBuilder that = (ModuleModelBuilder) other;

    return this.name.equals(that.name) &&
        this.version.equals(that.version) &&
        this.author.equals(that.author) &&
        Arrays.equals(this.requires, that.requires) &&
        this.amperModule.equals(that.amperModule);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(
        this.name,
        this.version,
        this.author,
        this.amperModule);

    result = 31 * result + Arrays.hashCode(this.requires);

    return result;
  }

  @Override
  public String toString() {
    return "ModuleModel{" +
        "name='" + this.name + '\'' +
        ", version='" + this.version + '\'' +
        ", author='" + this.author + '\'' +
        ", requires=" + Arrays.toString(this.requires) +
        ", amperModule=" + this.amperModule +
        '}';
  }
}
