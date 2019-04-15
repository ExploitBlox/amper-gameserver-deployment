/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

final class ServiceModelBuilder implements ServiceModel {

  private String name;
  private Method enablingMethod;
  private Method disablingMethod;

  ServiceModelBuilder() {
    this.name = "undefined";
    this.enablingMethod = null;
    this.disablingMethod = null;
  }

  @NonNull
  @Override
  public String name() {
    return this.name;
  }

  @NonNull
  @Override
  public ServiceModel withName(@NonNull String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public Optional<Method> enablingMethod() {
    return Optional.ofNullable(this.enablingMethod);
  }

  @NonNull
  @Override
  public ServiceModel withEnablingMethod(@NonNull Method enablingMethod) {
    this.enablingMethod = Preconditions.checkNotNull(enablingMethod, "enablingMethod cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public Optional<Method> disablingMethod() {
    return Optional.ofNullable(this.disablingMethod);
  }

  @NonNull
  @Override
  public ServiceModel withDisablingMethod(@NonNull Method disablingMethod) {
    this.disablingMethod = Preconditions.checkNotNull(disablingMethod, "disablingMethod cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ServiceModelBuilder)) {
      return false;
    }

    ServiceModelBuilder that = (ServiceModelBuilder) other;

    return this.name.equals(that.name) &&
        this.enablingMethod.equals(that.enablingMethod) &&
        this.disablingMethod.equals(that.disablingMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.enablingMethod, this.disablingMethod);
  }

  @Override
  public String toString() {
    return "ServiceModel{" +
        "name='" + this.name + '\'' +
        ", enablingMethod=" + this.enablingMethod +
        ", disablingMethod=" + this.disablingMethod +
        '}';
  }
}
