/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface StageFactory {

  static StageFactory vanilla() {
    return new VanillaStageFactory();
  }

  void processingStage(@NonNull Class<?> stageClass);
}
