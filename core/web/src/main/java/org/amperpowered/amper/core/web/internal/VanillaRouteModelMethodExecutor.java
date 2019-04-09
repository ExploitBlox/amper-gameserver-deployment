package org.amperpowered.amper.core.web.internal;

import com.google.common.base.Preconditions;
import io.vertx.ext.web.RoutingContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.amperpowered.amper.core.web.internal.exception.RouteModelMethodExecuteException;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaRouteModelMethodExecutor implements RouteModelMethodExecutor {

  @Override
  public void execute(@NonNull Method method, @NonNull RoutingContext routingContext) {
    Preconditions.checkNotNull(method, "method cannot be null!");
    Preconditions.checkNotNull(routingContext, "routingContext cannot be null!");

    try {
      method.invoke(method.getDeclaringClass().newInstance(), routingContext);
    } catch (IllegalAccessException | InvocationTargetException | InstantiationException cause) {
      throw new RouteModelMethodExecuteException("Cannot execute the method '" + method.getName() + "'");
    }
  }
}
