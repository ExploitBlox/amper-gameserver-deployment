package org.amperpowered.amper.core.web.internal;

import java.util.Collection;

public interface RouteModelRegistry {

  static RouteModelRegistry vanilla() {
    return VanillaRouteModelRegistry.ROUTE_MODEL_REGISTRY;
  }

  void register(RouteModel routeModel);

  Collection<RouteModel> routeModels();
}
