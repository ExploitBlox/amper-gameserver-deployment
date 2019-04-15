/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.amperpowered.amper.core.service.internal.ServiceLifeCycle;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Runnable {

  ServiceLifeCycle value();
}
