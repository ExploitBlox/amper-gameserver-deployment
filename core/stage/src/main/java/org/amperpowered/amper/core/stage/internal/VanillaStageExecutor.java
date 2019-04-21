/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import org.amperpowered.amper.core.guice.GuiceProvider;
import org.amperpowered.amper.core.stage.internal.exception.StageExecuteException;

final class VanillaStageExecutor implements StageExecutor {

  @Override
  public void execute(SingleStage singleStage) {
    Preconditions.checkNotNull(singleStage, "singleStage cannot be null!");

    GuiceProvider guiceProvider = GuiceProvider.create();

    guiceProvider.getInstance(singleStage.method().getDeclaringClass()).ifPresent(object -> {
      try {
        singleStage.method().invoke(object);
      } catch (IllegalAccessException | InvocationTargetException cause) {
        throw new StageExecuteException("Cannot execute the stage with id '" + singleStage.priority() + "'"
            + " because " + cause.getCause());
      }
    });
  }
}
