/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;

final class VanillaStageClassLoader implements StageClassLoader {

  @Override
  public List<SingleStage> loadStageClass(Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    List<SingleStage> stages = new ArrayList<>();

    this.collectStageMethods(stageClass).forEach(method -> {
      Stage stage = method.getDeclaredAnnotation(Stage.class);

      stages.add(SingleStage.begin()
          .withMethod(method)
          .withPriority(stage.value()));
    });

    Collections.sort(stages);

    return stages;
  }

  private List<Method> collectStageMethods(Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    return Arrays.stream(stageClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(Stage.class))
        .collect(Collectors.toList());
  }
}
