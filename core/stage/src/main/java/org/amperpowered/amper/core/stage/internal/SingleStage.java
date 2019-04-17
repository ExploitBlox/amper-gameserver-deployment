/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import java.lang.reflect.Method;

public interface SingleStage extends Comparable<SingleStage> {

  static SingleStage begin() {
    return new SingleStageBuilder();
  }

  Method method();

  SingleStage withMethod(Method method);

  int priority();

  SingleStage withPriority(int priority);
}
