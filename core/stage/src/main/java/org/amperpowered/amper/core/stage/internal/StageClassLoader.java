/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface StageClassLoader {

  @NonNull
  static StageClassLoader vanilla() {
    return new VanillaStageClassLoader();
  }

  @NonNull
  List<SingleStage> loadStageClass(@NonNull Class<?> stageClass);
}
