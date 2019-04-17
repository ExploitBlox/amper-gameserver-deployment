/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.core.bootstrap;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import java.util.Optional;
import org.amperpowered.core.bootstrap.internal.Bootstrap;
import org.amperpowered.core.bootstrap.internal.BootstrapClassScanner;

@Singleton
final class VanillaBootstrapFactory implements BootstrapFactory {

  @Override
  public <T extends Bootstrap> Optional<T> scanBootstrapClass(Class<T> bootstrapClass) {
    Preconditions.checkNotNull(bootstrapClass, "bootstrapClass cannot be null!");

    BootstrapClassScanner bootstrapClassScanner = BootstrapClassScanner.vanilla();
    return bootstrapClassScanner.scanBootstrapClass(bootstrapClass);
  }
}
