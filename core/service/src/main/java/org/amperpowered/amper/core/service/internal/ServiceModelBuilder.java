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

final class ServiceModelBuilder implements ServiceModel {

  private String name;
  private Class<?> bindingClass;
  private Method enablingMethod;
  private Method disablingMethod;

  ServiceModelBuilder() {
    this.name = "undefined";
    this.bindingClass = null;
    this.enablingMethod = null;
    this.disablingMethod = null;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public ServiceModel withName(String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @Override
  public Class<?> bindingClass() {
    return this.bindingClass;
  }

  @Override
  public ServiceModel withBindingClass(Class<?> bindingClass) {
    this.bindingClass = Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");
    return this;
  }

  @Override
  public Optional<Method> enablingMethod() {
    return Optional.ofNullable(this.enablingMethod);
  }

  @Override
  public ServiceModel withEnablingMethod(Method enablingMethod) {
    this.enablingMethod = Preconditions.checkNotNull(enablingMethod, "enablingMethod cannot be null!");
    return this;
  }

  @Override
  public Optional<Method> disablingMethod() {
    return Optional.ofNullable(this.disablingMethod);
  }

  @Override
  public ServiceModel withDisablingMethod(Method disablingMethod) {
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
        this.bindingClass.equals(that.bindingClass) &&
        this.enablingMethod.equals(that.enablingMethod) &&
        this.disablingMethod.equals(that.disablingMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.name,
        this.bindingClass,
        this.enablingMethod,
        this.disablingMethod);
  }

  @Override
  public String toString() {
    return "ServiceModel{" +
        "name='" + this.name + '\'' +
        ", bindingClass=" + this.bindingClass +
        ", enablingMethod=" + this.enablingMethod +
        ", disablingMethod=" + this.disablingMethod +
        '}';
  }
}
