/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage;

import com.google.common.base.Preconditions;
import org.amperpowered.amper.core.stage.internal.StageClassLoader;
import org.amperpowered.amper.core.stage.internal.StageExecutor;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaStageFactory implements StageFactory {

  @Override
  public void processingStage(@NonNull Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    StageClassLoader stageClassLoader = StageClassLoader.vanilla();
    StageExecutor stageExecutor = StageExecutor.vanilla();

    stageClassLoader.loadStageClass(stageClass).forEach(stageExecutor::execute);
  }
}
