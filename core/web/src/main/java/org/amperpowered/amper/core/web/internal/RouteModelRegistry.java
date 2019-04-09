package org.amperpowered.amper.core.web.internal;

import java.util.Collection;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface RouteModelRegistry {

  @NonNull
  static RouteModelRegistry vanilla() {
    return VanillaRouteModelRegistry.ROUTE_MODEL_REGISTRY;
  }

  void register(@NonNull RouteModel routeModel);

  @NonNull
  Collection<RouteModel> routeModels();
}
