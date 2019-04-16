/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.service.master.config;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class MasterConfigBuilder implements MasterConfig {

  private String service;
  private WebConfig webConfig;
  private List<Daemon> daemons;

  MasterConfigBuilder() {
    this.service = "undefined";
    this.webConfig = WebConfig.begin();
    this.daemons = Collections.emptyList();
  }

  @NonNull
  @Override
  public String service() {
    return this.service;
  }

  @NonNull
  @Override
  public MasterConfig withService(@NonNull String service) {
    this.service = Preconditions.checkNotNull(service, "service cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public WebConfig webConfig() {
    return this.webConfig;
  }

  @NonNull
  @Override
  public MasterConfig withWebConfig(@NonNull WebConfig webConfig) {
    this.webConfig = Preconditions.checkNotNull(webConfig, "webConfig cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public List<Daemon> daemons() {
    return this.daemons;
  }

  @NonNull
  @Override
  public MasterConfig withDaemons(@NonNull List<Daemon> daemons) {
    this.daemons = Preconditions.checkNotNull(daemons, "daemons cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof MasterConfigBuilder)) {
      return false;
    }

    MasterConfigBuilder that = (MasterConfigBuilder) other;

    return this.service.equals(that.service) &&
        this.webConfig.equals(that.webConfig) &&
        this.daemons.equals(that.daemons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.service, this.webConfig, this.daemons);
  }

  @Override
  public String toString() {
    return "MasterConfig{" +
        "service=" + this.service +
        ", webConfig=" + this.webConfig +
        ", daemons=" + this.daemons +
        '}';
  }
}
