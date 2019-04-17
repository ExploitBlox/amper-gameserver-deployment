/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master.config;

import java.util.List;

public interface MasterConfig {


  static MasterConfig begin() {
    return new MasterConfigBuilder();
  }


  String service();


  MasterConfig withService(String service);


  WebConfig webConfig();


  MasterConfig withWebConfig(WebConfig webConfig);


  List<Daemon> daemons();


  MasterConfig withDaemons(List<Daemon> daemons);

}
