/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master.config;

import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface MasterConfig {

  @NonNull
  static MasterConfig begin() {
    return new MasterConfigBuilder();
  }

  @NonNull
  String service();

  @NonNull
  MasterConfig withService(@NonNull String service);

  @NonNull
  WebConfig webConfig();

  @NonNull
  MasterConfig withWebConfig(@NonNull WebConfig webConfig);

  @NonNull
  List<Daemon> daemons();

  @NonNull
  MasterConfig withDaemons(@NonNull List<Daemon> daemons);

}
