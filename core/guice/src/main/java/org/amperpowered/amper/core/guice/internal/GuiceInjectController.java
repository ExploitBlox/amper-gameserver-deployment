/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.guice.internal;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.util.Optional;

public interface GuiceInjectController {

  static GuiceInjectController vanilla() {
    return new VanillaGuiceInjectController();
  }

  <T> Optional<Binding<T>> getBinding(Injector injector, Class<T> bindingClass);

  <T> Optional<Provider<T>> getProvider(Injector injector, Class<T> providerClass);

  <T> Optional<T> getInstance(Injector injector, Class<T> instanceClass);
}
