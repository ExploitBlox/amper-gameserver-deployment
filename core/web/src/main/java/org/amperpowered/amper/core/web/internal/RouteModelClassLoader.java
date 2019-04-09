package org.amperpowered.amper.core.web.internal;

import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface RouteModelClassLoader {

  @NonNull
  static RouteModelClassLoader vanilla() {
    return new VanillaRouteModelClassLoader();
  }

  @NonNull
  Optional<RouteModel> loadModelClass(@NonNull Class<?> routeModelClass);
}
