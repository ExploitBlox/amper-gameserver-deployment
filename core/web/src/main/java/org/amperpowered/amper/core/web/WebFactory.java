package org.amperpowered.amper.core.web;

public interface WebFactory {

  static WebFactory vanilla() {
    return VanillaWebFactory.WEB_FACTORY;
  }

  void listen(int port);

  void terminate();

  void processRouteClass(Class<?> routeClass);
}
