/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Provider;
import java.util.Optional;

public interface GuiceFactory {

  static GuiceFactory vanilla() {
    return VanillaGuiceFactory.GUICE_FACTORY;
  }

  void createInjector(AbstractModule abstractModule);

  <T> Optional<Binding<T>> getBinding(Class<T> bindingClass);

  <T> Optional<Provider<T>> getProvider(Class<T> providerClass);

  <T> Optional<T> getInstance(Class<T> instanceClass);
}
