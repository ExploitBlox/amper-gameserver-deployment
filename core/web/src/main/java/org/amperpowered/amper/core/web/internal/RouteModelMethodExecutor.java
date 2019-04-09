package org.amperpowered.amper.core.web.internal;

import io.vertx.ext.web.RoutingContext;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface RouteModelMethodExecutor {

  @NonNull
  static RouteModelMethodExecutor vanilla() {
    return new VanillaRouteModelMethodExecutor();
  }

  void execute(@NonNull Method method, @NonNull RoutingContext routingContext);
}
