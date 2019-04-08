/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.config;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Identity {

  @NonNull
  static Identity begin() {
    return new IdentityBuilder();
  }

  @NonNull
  String name();

  @NonNull
  Identity withName(@NonNull String name);

  @NonNull
  String host();

  @NonNull
  Identity withHost(@NonNull String host);

  int port();

  @NonNull
  Identity withPort(int port);
}
