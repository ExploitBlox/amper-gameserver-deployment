/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.guice.internal;

import com.google.common.base.Preconditions;
import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.util.Optional;

final class VanillaGuiceInjectController implements GuiceInjectController {

  @Override
  public <T> Optional<Binding<T>> getBinding(Injector injector, Class<T> bindingClass) {
    Preconditions.checkNotNull(injector, "injector cannot be null!");
    Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");

    return Optional.ofNullable(injector.getBinding(bindingClass));
  }

  @Override
  public <T> Optional<Provider<T>> getProvider(Injector injector, Class<T> providerClass) {
    Preconditions.checkNotNull(injector, "injector cannot be null!");
    Preconditions.checkNotNull(providerClass, "providerClass cannot be null!");

    return Optional.ofNullable(injector.getProvider(providerClass));
  }

  @Override
  public <T> Optional<T> getInstance(Injector injector, Class<T> instanceClass) {
    Preconditions.checkNotNull(injector, "injector cannot be null!");
    Preconditions.checkNotNull(instanceClass, "instanceClass cannot be null!");

    return Optional.ofNullable(injector.getInstance(instanceClass));
  }
}
