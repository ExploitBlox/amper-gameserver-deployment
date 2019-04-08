/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.stage.internal.exception;

import org.checkerframework.checker.nullness.qual.NonNull;

public class StageExecuteException extends RuntimeException {

  public StageExecuteException(@NonNull String message) {
    super(message);
  }
}