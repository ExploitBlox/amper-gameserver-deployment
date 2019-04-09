package org.amperpowered.amper.core.web;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface WebFactory {

  @NonNull
  static WebFactory vanilla() {
    return VanillaWebFactory.WEB_FACTORY;
  }

  void listen(int port);

  void terminate();

  void processRouteClass(@NonNull Class<?> routeClass);
}
