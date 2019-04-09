package org.amperpowered.amper.core.web.internal;

import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface RouteModel {

  @NonNull
  static RouteModel begin() {
    return new RouteModelBuilder();
  }

  @NonNull
  String name();

  @NonNull
  RouteModel withName(@NonNull String name);

  @NonNull
  RouteMapping routeMapping();

  @NonNull
  RouteModel withRouteMapping(@NonNull RouteMapping routeMapping);

  @NonNull
  Method routeMethod();

  @NonNull
  RouteModel withRouteMethod(@NonNull Method routeMethod);
}
