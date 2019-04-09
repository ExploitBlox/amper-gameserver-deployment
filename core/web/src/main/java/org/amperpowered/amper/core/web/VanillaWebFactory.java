package org.amperpowered.amper.core.web;

import com.google.common.base.Preconditions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import java.lang.reflect.Method;
import org.amperpowered.amper.core.web.internal.RouteMapping;
import org.amperpowered.amper.core.web.internal.RouteModel;
import org.amperpowered.amper.core.web.internal.RouteModelClassLoader;
import org.amperpowered.amper.core.web.internal.RouteModelMethodExecutor;
import org.amperpowered.amper.core.web.internal.RouteModelRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaWebFactory implements WebFactory {

  static final WebFactory WEB_FACTORY = new VanillaWebFactory();
  private static final RouteModelMethodExecutor ROUTE_MODEL_METHOD_EXECUTOR = RouteModelMethodExecutor.vanilla();
  private static final RouteModelClassLoader ROUTE_MODEL_CLASS_LOADER = RouteModelClassLoader.vanilla();
  private static final RouteModelRegistry ROUTE_MODEL_REGISTRY = RouteModelRegistry.vanilla();

  private HttpServer httpServer;
  private Router router;

  @Override
  public void listen(int port) {
    Vertx vertx = Vertx.vertx();

    this.router = Router.router(vertx);
    this.httpServer = vertx.createHttpServer()
        .requestHandler(router::accept)
        .listen(port);

    ROUTE_MODEL_REGISTRY.routeModels().forEach(this::accept);
  }

  @Override
  public void terminate() {
    Preconditions.checkNotNull(httpServer);

    this.httpServer.close();
  }

  @Override
  public void processRouteClass(@NonNull Class<?> routeClass) {
    Preconditions.checkNotNull(routeClass, "routeClass cannot be null!");

    ROUTE_MODEL_CLASS_LOADER.loadModelClass(routeClass)
        .ifPresent(ROUTE_MODEL_REGISTRY::register);
  }

  private void accept(@NonNull RouteModel routeModel) {
    Preconditions.checkNotNull(routeModel, "routeModel cannot be null!");
    Preconditions.checkNotNull(router, "router cannot be null!");

    if (routeModel.routeMapping().equals(RouteMapping.POST)) {
      this.handleRoute(router.post(routeModel.name()), routeModel.routeMethod());
    } else if (routeModel.routeMapping().equals(RouteMapping.GET)) {
      this.handleRoute(router.get(routeModel.name()), routeModel.routeMethod());
    } else if (routeModel.routeMapping().equals(RouteMapping.PATCH)) {
      this.handleRoute(router.patch(routeModel.name()), routeModel.routeMethod());
    } else if (routeModel.routeMapping().equals(RouteMapping.DELETE)) {
      this.handleRoute(router.delete(routeModel.name()), routeModel.routeMethod());
    }
  }

  private void handleRoute(@NonNull Route route, @NonNull Method method) {
    Preconditions.checkNotNull(route, "route cannot be null!");
    Preconditions.checkNotNull(method, "method cannot be null!");

    route.handler(routingContext -> ROUTE_MODEL_METHOD_EXECUTOR.execute(method, routingContext));
  }


}
