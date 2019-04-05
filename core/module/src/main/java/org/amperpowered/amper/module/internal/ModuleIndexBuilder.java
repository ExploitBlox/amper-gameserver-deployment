/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class ModuleIndexBuilder implements ModuleIndex {

  private String name;
  private String version;
  private String author;
  private String[] requires;
  private AmperModule amperModule;

  ModuleIndexBuilder() {
    this.name = "undefined";
    this.version = "1.0.0";
    this.author = "undefined";
    this.requires = new String[]{};
  }

  @NonNull
  @Override
  public String name() {
    return this.name;
  }

  @NonNull
  @Override
  public ModuleIndex withName(@NonNull String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public String version() {
    return this.version;
  }

  @NonNull
  @Override
  public ModuleIndex withVersion(@NonNull String version) {
    this.version = Preconditions.checkNotNull(version, "version cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public String author() {
    return this.author;
  }

  @NonNull
  @Override
  public ModuleIndex withAuthor(@NonNull String author) {
    this.author = author;
    return this;
  }

  @NonNull
  @Override
  public String[] requires() {
    return this.requires;
  }

  @NonNull
  @Override
  public ModuleIndex withRequires(@NonNull String[] requires) {
    this.requires = Preconditions.checkNotNull(requires, "requires cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public AmperModule amperModule() {
    return this.amperModule;
  }

  @NonNull
  @Override
  public ModuleIndex withAmperModule(@NonNull AmperModule amperModule) {
    this.amperModule = Preconditions.checkNotNull(amperModule, "amperModule cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ModuleIndexBuilder)) {
      return false;
    }

    ModuleIndexBuilder that = (ModuleIndexBuilder) other;

    return this.name.equals(that.name) &&
        this.version.equals(that.version) &&
        this.author.equals(that.author) &&
        Arrays.equals(this.requires, that.requires) &&
        this.amperModule.equals(that.amperModule);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(this.name, this.version, this.author, this.amperModule);
    result = 31 * result + Arrays.hashCode(this.requires);
    return result;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", this.name)
        .add("version", this.version)
        .add("author", this.version)
        .add("requires", this.requires)
        .add("amperModule", this.amperModule)
        .toString();
  }
}
