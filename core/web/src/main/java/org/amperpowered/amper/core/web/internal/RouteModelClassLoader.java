package org.amperpowered.amper.core.web.internal;

import java.util.Optional;

public interface RouteModelClassLoader {

  static RouteModelClassLoader vanilla() {
    return new VanillaRouteModelClassLoader();
  }

  Optional<RouteModel> loadModelClass(Class<?> routeModelClass);
}
