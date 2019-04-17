/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master.config;

public interface Daemon {


  static Daemon begin() {
    return new DaemonBuilder();
  }


  String name();


  Daemon withName(String name);


  String host();


  Daemon withHost(String host);

  int port();


  Daemon withPort(int port);
}
