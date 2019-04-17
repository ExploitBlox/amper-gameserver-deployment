/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.service.internal;

import java.util.Optional;

public interface ServiceComposer {

  static ServiceComposer vanilla() {
    return new VanillaServiceComposer();
  }

  Optional<ServiceModel> composeService(Class<?> serviceClass);
}
