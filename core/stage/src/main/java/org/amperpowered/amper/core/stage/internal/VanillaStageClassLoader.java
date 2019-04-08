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
import java.util.Optional;
import java.util.stream.Collectors;
import org.amperpowered.amper.core.guice.GuiceFactory;
import org.amperpowered.amper.core.stage.internal.annotation.Stage;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaStageClassLoader implements StageClassLoader {

  @NonNull
  @Override
  public List<SingleStage> loadStageClass(@NonNull Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    this.printInformation(stageClass);

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

  private void printInformation(@NonNull Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    GuiceFactory guiceFactory = GuiceFactory.vanilla();

    Optional<?> instance = guiceFactory.getInstance(stageClass);

    if (instance.isPresent()) {
      Object object = instance.get();

      System.out.println("Running stage at class " + object.getClass());
    }
  }

  @NonNull
  private List<Method> collectStageMethods(@NonNull Class<?> stageClass) {
    Preconditions.checkNotNull(stageClass, "stageClass cannot be null!");

    return Arrays.stream(stageClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(Stage.class))
        .collect(Collectors.toList());
  }
}
