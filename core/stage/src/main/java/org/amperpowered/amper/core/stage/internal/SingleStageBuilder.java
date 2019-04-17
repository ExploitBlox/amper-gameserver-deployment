/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Objects;

final class SingleStageBuilder implements SingleStage {

  private Method method;
  private int priority;

  SingleStageBuilder() {
    this.method = null;
    this.priority = 0;
  }

  @Override
  public Method method() {
    return this.method;
  }

  @Override
  public SingleStage withMethod(Method method) {
    this.method = Preconditions.checkNotNull(method, "method cannot be null!");
    return this;
  }

  @Override
  public int priority() {
    return this.priority;
  }

  @Override
  public SingleStage withPriority(int priority) {
    this.priority = priority;
    return this;
  }

  @Override
  public int compareTo(SingleStage other) {
    Preconditions.checkNotNull(other, "singleStage cannot be null!");

    return this.priority - other.priority();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof SingleStageBuilder)) {
      return false;
    }

    SingleStageBuilder that = (SingleStageBuilder) other;

    return this.priority == that.priority &&
        this.method.equals(that.method);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.method, this.priority);
  }

  @Override
  public String toString() {
    return "SingleStage{" +
        "method=" + this.method +
        ", priority=" + this.priority +
        '}';
  }
}
