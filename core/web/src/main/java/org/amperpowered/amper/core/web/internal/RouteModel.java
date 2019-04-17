package org.amperpowered.amper.core.web.internal;

import java.lang.reflect.Method;

public interface RouteModel {

  static RouteModel begin() {
    return new RouteModelBuilder();
  }

  String name();

  RouteModel withName(String name);

  RouteMapping routeMapping();

  RouteModel withRouteMapping(RouteMapping routeMapping);

  Method routeMethod();

  RouteModel withRouteMethod(Method routeMethod);
}
