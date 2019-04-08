/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.config;

import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ApplicationConfig {

  @NonNull
  static ApplicationConfig begin() {
    return new ApplicationConfigBuilder();
  }

  @NonNull
  Identity identity();

  @NonNull
  ApplicationConfig withIdentity(@NonNull Identity identity);

  @NonNull
  List<Identity> applications();

  @NonNull
  ApplicationConfig withApplications(@NonNull List<Identity> applications);

}
