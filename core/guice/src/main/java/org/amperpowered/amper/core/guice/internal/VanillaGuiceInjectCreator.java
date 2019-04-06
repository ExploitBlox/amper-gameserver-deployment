/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.guice.internal;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.checkerframework.checker.nullness.qual.NonNull;

final class VanillaGuiceInjectCreator implements GuiceInjectCreator {

  @NonNull
  @Override
  public Injector createInjector(@NonNull AbstractModule abstractModule) {
    Preconditions.checkNotNull(abstractModule, "abstractModule cannot be null!");

    return Guice.createInjector(abstractModule);
  }
}
