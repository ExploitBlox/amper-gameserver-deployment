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
import org.checkerframework.checker.nullness.qual.NonNull;

public interface GuiceInjectController {

  @NonNull
  static GuiceInjectController vanilla() {
    return new VanillaGuiceInjectController();
  }

  @NonNull <T> Optional<Binding<T>> getBinding(@NonNull Injector injector, @NonNull Class<T> bindingClass);

  @NonNull <T> Optional<Provider<T>> getProvider(@NonNull Injector injector, @NonNull Class<T> providerClass);

  @NonNull <T> Optional<T> getInstance(@NonNull Injector injector, @NonNull Class<T> instanceClass);
}
