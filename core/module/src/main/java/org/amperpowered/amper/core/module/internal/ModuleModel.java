/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ModuleModel {

  static final class Builder {

    private String name;
    private String version;
    private String author;
    private List<String> requiredModules;
    private AmperModule nestedModule;

    Builder() {
      this.name = "undefined";
      this.version = "undefined";
      this.author = "undefined";
      this.requiredModules = Collections.emptyList();
      this.nestedModule = null;
    }

    Builder withName(String name) {
      this.name = checkNotNull(name, "name cannot be null!");
      return this;
    }

    Builder withVersion(String version) {
      this.version = checkNotNull(version, "version cannot be null!");
      return this;
    }

    Builder withAuthor(String author) {
      this.author = checkNotNull(author, "author cannot be null!");
      return this;
    }

    Builder withRequiredModules(List<String> requiredModules) {
      this.requiredModules = checkNotNull(requiredModules, "requiredModules cannot be null!");
      return this;
    }

    Builder withNestedModule(AmperModule nestedModule) {
      this.nestedModule = checkNotNull(nestedModule, "nestedModule cannot be null!");
      return this;
    }

    ModuleModel create() {
      return new ModuleModel(
          this.name,
          this.version,
          this.author,
          this.requiredModules,
          this.nestedModule);
    }
  }

  static Builder newBuilder() {
    return new Builder();
  }

  private String name;
  private String version;
  private String author;
  private List<String> requiredModules;
  private AmperModule nestedModule;

  private ModuleModel(String name, String version, String author,
      List<String> requiredModules,
      AmperModule nestedModule) {
    this.name = checkNotNull(name, "name cannot be null!");
    this.version = checkNotNull(version, "version cannot be null!");
    this.author = checkNotNull(author, "author cannot be null!");
    this.requiredModules = checkNotNull(requiredModules, "requiredModules cannot be null!");
    this.nestedModule = checkNotNull(nestedModule, "nestedModule cannot be null!");
  }

  public String name() {
    return this.name;
  }

  public String version() {
    return this.version;
  }

  public String author() {
    return this.author;
  }

  public List<String> requiredModules() {
    return this.requiredModules;
  }

  public AmperModule nestedModule() {
    return this.nestedModule;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ModuleModel)) {
      return false;
    }

    ModuleModel that = (ModuleModel) other;

    return Objects.equals(this.name, that.name) &&
        Objects.equals(this.version, that.version) &&
        Objects.equals(this.author, that.author) &&
        Objects.equals(this.requiredModules, that.requiredModules) &&
        Objects.equals(this.nestedModule, that.nestedModule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.name,
        this.version,
        this.author,
        this.requiredModules,
        this.nestedModule);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", this.name)
        .add("version", this.version)
        .add("author", this.author)
        .add("requiredModules", this.requiredModules)
        .add("nestedModule", this.nestedModule)
        .toString();
  }
}
