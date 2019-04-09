package org.amperpowered.amper.core.web.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Optional;
import org.amperpowered.amper.core.web.internal.annotation.Route;
import org.amperpowered.amper.core.web.internal.annotation.RouteHandler;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaRouteModelClassLoader implements RouteModelClassLoader {

  @NonNull
  @Override
  public Optional<RouteModel> loadModelClass(@NonNull Class<?> routeModelClass) {
    Preconditions.checkNotNull(routeModelClass, "routeModelClass cannot be null!");

    if (!routeModelClass.isAnnotationPresent(Route.class)) {
      return Optional.empty();
    }

    Route route = routeModelClass.getDeclaredAnnotation(Route.class);

    RouteModel routeModel = RouteModel.begin()
        .withName(route.name())
        .withRouteMapping(route.routeMapping());

    for (Method method : routeModelClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(RouteHandler.class)) {
        routeModel.withRouteMethod(method);
      }
    }

    return Optional.of(routeModel);
  }
}
