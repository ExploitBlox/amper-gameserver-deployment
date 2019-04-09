/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.config;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class ApplicationConfigBuilder implements ApplicationConfig {

  private Identity identity;
  private WebConfig webConfig;
  private List<Identity> applications;

  ApplicationConfigBuilder() {
    this.identity = Identity.begin();
    this.webConfig = WebConfig.begin();
    this.applications = Collections.emptyList();
  }

  @NonNull
  @Override
  public Identity identity() {
    return this.identity;
  }

  @NonNull
  @Override
  public ApplicationConfig withIdentity(@NonNull Identity identity) {
    this.identity = Preconditions.checkNotNull(identity, "identity cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public WebConfig webConfig() {
    return this.webConfig;
  }

  @NonNull
  @Override
  public ApplicationConfig withWebConfig(@NonNull WebConfig webConfig) {
    this.webConfig = Preconditions.checkNotNull(webConfig, "webConfig cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public List<Identity> applications() {
    return this.applications;
  }

  @NonNull
  @Override
  public ApplicationConfig withApplications(@NonNull List<Identity> applications) {
    this.applications = Preconditions.checkNotNull(applications, "application cannot be null!");
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof ApplicationConfigBuilder)) {
      return false;
    }

    ApplicationConfigBuilder that = (ApplicationConfigBuilder) other;

    return this.identity.equals(that.identity) &&
        this.webConfig.equals(that.webConfig) &&
        this.applications.equals(that.applications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.identity, this.webConfig, this.applications);
  }

  @Override
  public String toString() {
    return "ApplicationConfig{" +
        "identity=" + this.identity +
        ", webConfig=" + this.webConfig +
        ", applications=" + this.applications +
        '}';
  }
}
