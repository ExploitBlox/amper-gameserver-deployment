package org.amperpowered.amper.core.web.internal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.amperpowered.amper.core.web.internal.RouteMapping;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {

  String name();

  RouteMapping routeMapping();
}
