/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master.config;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Daemon {

  @NonNull
  static Daemon begin() {
    return new DaemonBuilder();
  }

  @NonNull
  String name();

  @NonNull
  Daemon withName(@NonNull String name);

  @NonNull
  String host();

  @NonNull
  Daemon withHost(@NonNull String host);

  int port();

  @NonNull
  Daemon withPort(int port);
}
