package org.amperpowered.amper.core.web.internal.exception;

import org.checkerframework.checker.nullness.qual.NonNull;

public class RouteModelMethodExecuteException extends RuntimeException {

  public RouteModelMethodExecuteException(@NonNull String message) {
    super(message);
  }
}
