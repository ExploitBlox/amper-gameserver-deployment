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

  private static final StageClassLoader STAGE_CLASS_LOADER = StageClassLoader.vanilla();
  private static final StageExecutor STAGE_EXECUTOR = StageExecutor.vanilla();

  @Override
  public void processingStage(@NonNull Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    STAGE_CLASS_LOADER.loadStageClass(stageClass).forEach(STAGE_EXECUTOR::execute);
  }
}
