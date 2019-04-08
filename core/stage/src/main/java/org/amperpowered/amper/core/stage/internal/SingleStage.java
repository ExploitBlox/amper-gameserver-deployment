/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface SingleStage extends Comparable<SingleStage> {

  @NonNull
  static SingleStage begin() {
    return new SingleStageBuilder();
  }

  @NonNull
  Method method();

  @NonNull
  SingleStage withMethod(@NonNull Method method);

  int priority();

  @NonNull
  SingleStage withPriority(int priority);
}
