package org.amperpowered.amper.core.web;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
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
import org.pmw.tinylog.Logger;

@Singleton
final class VanillaWebFactory implements WebFactory {

  static final WebFactory WEB_FACTORY = new VanillaWebFactory();

  private HttpServer httpServer;
  private Router router;

  @Override
  public void listen(int port) {
    Logger.info("Running web server on port: " + port);
    Vertx vertx = Vertx.vertx();

    this.router = Router.router(vertx);
    this.httpServer = vertx.createHttpServer()
        .requestHandler(router::accept)
        .listen(port);

    RouteModelRegistry routeModelRegistry = RouteModelRegistry.vanilla();

    routeModelRegistry.routeModels().forEach(this::accept);
  }

  @Override
  public void terminate() {
    Preconditions.checkNotNull(httpServer);

    this.httpServer.close();
  }

  @Override
  public void processRouteClass(Class<?> routeClass) {
    Preconditions.checkNotNull(routeClass, "routeClass cannot be null!");

    RouteModelClassLoader routeModelClassLoader = RouteModelClassLoader.vanilla();
    RouteModelRegistry routeModelRegistry = RouteModelRegistry.vanilla();

    routeModelClassLoader.loadModelClass(routeClass).ifPresent(routeModelRegistry::register);
  }

  private void accept(RouteModel routeModel) {
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

  private void handleRoute(Route route, Method method) {
    Preconditions.checkNotNull(route, "route cannot be null!");
    Preconditions.checkNotNull(method, "method cannot be null!");

    RouteModelMethodExecutor routeModelMethodExecutor = RouteModelMethodExecutor.vanilla();
    route.handler(routingContext -> routeModelMethodExecutor.execute(method, routingContext));
  }
}
