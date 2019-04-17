/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.guice;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.util.Optional;
import org.amperpowered.amper.core.guice.internal.GuiceInjectController;

final class VanillaGuiceFactory implements GuiceFactory {

  static final GuiceFactory GUICE_FACTORY = new VanillaGuiceFactory();
  private static final GuiceInjectController GUICE_INJECT_CONTROLLER = GuiceInjectController.vanilla();

  private Injector injector;

  @Override
  public void createInjector(AbstractModule abstractModule) {
    Preconditions.checkNotNull(abstractModule, "abstractModule cannot be null!");

    this.injector = Guice.createInjector(abstractModule);
  }

  @Override
  public <T> Optional<Binding<T>> getBinding(Class<T> bindingClass) {
    Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getBinding(this.injector, bindingClass);
  }

  @Override
  public <T> Optional<Provider<T>> getProvider(Class<T> providerClass) {
    Preconditions.checkNotNull(providerClass, "providerClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getProvider(this.injector, providerClass);
  }

  @Override
  public <T> Optional<T> getInstance(Class<T> instanceClass) {
    Preconditions.checkNotNull(instanceClass, "instanceClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getInstance(this.injector, instanceClass);
  }

}
