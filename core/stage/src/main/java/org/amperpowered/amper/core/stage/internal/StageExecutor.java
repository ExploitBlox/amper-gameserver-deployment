/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

@FunctionalInterface
public interface StageExecutor {

  static StageExecutor vanilla() {
    return new VanillaStageExecutor();
  }

  void execute(SingleStage singleStage);
}
