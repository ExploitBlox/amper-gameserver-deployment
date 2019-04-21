/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Objects;

public final class ServiceModel {

  static final class Builder {

    private String name;
    private Class<?> nestedClass;
    private Method enablingMethod;
    private Method disablingMethod;

    Builder() {
      this.name = "undefined";
      this.nestedClass = null;
      this.enablingMethod = null;
      this.disablingMethod = null;
    }

    Builder withName(String name) {
      this.name = checkNotNull(name, "name cannot be null!");
      return this;
    }

    Builder withNestedClass(Class<?> nestedClass) {
      this.nestedClass = Preconditions.checkNotNull(nestedClass, "nestedClass cannot be null!");
      return this;
    }

    Builder withEnablingMethod(Method enablingMethod) {
      this.enablingMethod = checkNotNull(enablingMethod, "enablingMethod cannot be null!");
      return this;
    }

    Builder withDisablingMethod(Method disablingMethod) {
      this.disablingMethod = checkNotNull(disablingMethod, "disablingMethod cannot be null!");
      return this;
    }

    ServiceModel create() {
      return new ServiceModel(
          this.name,
          this.nestedClass,
          this.enablingMethod,
          this.disablingMethod);
    }
  }

  static Builder newBuilder() {
    return new Builder();
  }

  private String name;
  private Class<?> nestedClass;
  private Method enablingMethod;
  private Method disablingMethod;

  private ServiceModel(String name, Class<?> nestedClass, Method enablingMethod,
      Method disablingMethod) {
    this.name = checkNotNull(name, "name cannot be null!");
    this.nestedClass = checkNotNull(nestedClass, "nestedClass cannot be null!");
    this.enablingMethod = checkNotNull(enablingMethod, "enablingMethod cannot be null!");
    this.disablingMethod = checkNotNull(disablingMethod, "disablingMethod cannot be null!");
  }

  public String name() {
    return this.name;
  }

  public Class<?> nestedClass() {
    return this.nestedClass;
  }

  public Method enablingMethod() {
    return this.enablingMethod;
  }

  public Method disablingMethod() {
    return this.disablingMethod;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ServiceModel)) {
      return false;
    }

    ServiceModel that = (ServiceModel) other;

    return Objects.equals(this.name, that.name) &&
        Objects.equals(this.nestedClass, that.nestedClass) &&
        Objects.equals(this.enablingMethod, that.enablingMethod) &&
        Objects.equals(this.disablingMethod, that.disablingMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.name,
        this.nestedClass,
        this.enablingMethod,
        this.disablingMethod);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("name", this.name)
        .add("nestedClass", this.nestedClass)
        .add("enablingMethod", this.enablingMethod)
        .add("disablingMethod", this.disablingMethod)
        .toString();
  }
}
