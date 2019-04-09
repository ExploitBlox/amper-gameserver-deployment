package org.amperpowered.amper.core.web.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;

public class VanillaRouteModelRegistry implements RouteModelRegistry {

  static final RouteModelRegistry ROUTE_MODEL_REGISTRY = new VanillaRouteModelRegistry();

  private static final List<RouteModel> ROUTE_MODELS = new ArrayList<>();

  @Override
  public void register(@NonNull RouteModel routeModel) {
    Preconditions.checkNotNull(routeModel, "routeModel cannot be null!");

    ROUTE_MODELS.add(routeModel);
  }

  @NonNull
  @Override
  public Collection<RouteModel> routeModels() {
    return Collections.unmodifiableCollection(ROUTE_MODELS);
  }
}
