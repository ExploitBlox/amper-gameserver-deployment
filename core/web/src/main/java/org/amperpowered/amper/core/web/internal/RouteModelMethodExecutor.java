package org.amperpowered.amper.core.web.internal;

import io.vertx.ext.web.RoutingContext;
import java.lang.reflect.Method;

public interface RouteModelMethodExecutor {

  static RouteModelMethodExecutor vanilla() {
    return new VanillaRouteModelMethodExecutor();
  }

  void execute(Method method, RoutingContext routingContext);
}
