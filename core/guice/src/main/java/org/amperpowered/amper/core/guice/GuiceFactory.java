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
import org.checkerframework.checker.nullness.qual.NonNull;

public interface GuiceFactory {

  @NonNull
  static GuiceFactory vanilla() {
    return VanillaGuiceFactory.GUICE_FACTORY;
  }

  void createInjector(@NonNull AbstractModule abstractModule);

  @NonNull <T> Optional<Binding<T>> getBinding(@NonNull Class<T> bindingClass);

  @NonNull <T> Optional<Provider<T>> getProvider(@NonNull Class<T> providerClass);

  @NonNull <T> Optional<T> getInstance(@NonNull Class<T> instanceClass);
}
