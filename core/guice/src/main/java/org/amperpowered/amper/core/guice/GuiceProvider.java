package org.amperpowered.amper.core.guice;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.util.Optional;
import org.amperpowered.amper.core.guice.internal.GuiceInjectController;

public final class GuiceProvider {

  public static GuiceProvider create() {
    return GUICE_PROVIDER;
  }

  private static final GuiceInjectController GUICE_INJECT_CONTROLLER = GuiceInjectController.create();
  private static final GuiceProvider GUICE_PROVIDER = new GuiceProvider();

  private Injector injector;

  public void createInjector(AbstractModule abstractModule) {
    Preconditions.checkNotNull(abstractModule, "abstractModule cannot be null!");

    this.injector = Guice.createInjector(abstractModule);
  }

  public <T> Optional<Binding<T>> getBinding(Class<T> bindingClass) {
    Preconditions.checkNotNull(bindingClass, "bindingClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getBinding(this.injector, bindingClass);
  }

  public <T> Optional<Provider<T>> getProvider(Class<T> providerClass) {
    Preconditions.checkNotNull(providerClass, "providerClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getProvider(this.injector, providerClass);
  }

  public <T> Optional<T> getInstance(Class<T> instanceClass) {
    Preconditions.checkNotNull(instanceClass, "instanceClass cannot be null!");
    Preconditions.checkNotNull(this.injector, "injector cannot be null!");

    return GUICE_INJECT_CONTROLLER.getInstance(this.injector, instanceClass);
  }

}
