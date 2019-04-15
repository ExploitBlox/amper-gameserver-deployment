/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import java.lang.reflect.Method;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ServiceModel {

  @NonNull
  static ServiceModel begin() {
    return new ServiceModelBuilder();
  }

  @NonNull
  String name();

  @NonNull
  ServiceModel withName(@NonNull String name);

  @NonNull
  Optional<Method> enablingMethod();

  @NonNull
  ServiceModel withEnablingMethod(@NonNull Method enablingMethod);

  @NonNull
  Optional<Method> disablingMethod();

  @NonNull
  ServiceModel withDisablingMethod(@NonNull Method disablingMethod);
}
