package org.amperpowered.amper.core.web.internal;

import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class RouteModelBuilder implements RouteModel {

  private String name;
  private RouteMapping routeMapping;
  private Method routeMethod;

  @NonNull
  @Override
  public String name() {
    return this.name;
  }

  @NonNull
  @Override
  public RouteModel withName(@NonNull String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public RouteMapping routeMapping() {
    return this.routeMapping;
  }

  @NonNull
  @Override
  public RouteModel withRouteMapping(@NonNull RouteMapping routeMapping) {
    this.routeMapping = Preconditions.checkNotNull(routeMapping, "routeMapping cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public Method routeMethod() {
    return this.routeMethod;
  }

  @NonNull
  @Override
  public RouteModel withRouteMethod(@NonNull Method routeMethod) {
    this.routeMethod = Preconditions.checkNotNull(routeMethod, "routeMethod cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof RouteModelBuilder)) {
      return false;
    }

    RouteModelBuilder that = (RouteModelBuilder) other;

    return this.name.equals(that.name) &&
        this.routeMapping == that.routeMapping &&
        this.routeMethod.equals(that.routeMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.routeMapping, this.routeMethod);
  }

  @Override
  public String toString() {
    return "RouteModel{" +
        "name='" + this.name + '\'' +
        ", routeMapping=" + this.routeMapping +
        ", routeMethod=" + this.routeMethod +
        '}';
  }
}
