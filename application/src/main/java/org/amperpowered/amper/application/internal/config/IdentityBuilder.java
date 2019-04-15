/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.application.internal.config;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class IdentityBuilder implements Identity {

  private String name;
  private String host;
  private int port;

  IdentityBuilder() {
    this.name = "application-01";
    this.host = "127.0.0.1";
    this.port = 25572;
  }

  @NonNull
  @Override
  public String name() {
    return this.name;
  }

  @NonNull
  @Override
  public Identity withName(@NonNull String name) {
    this.name = Preconditions.checkNotNull(name, "name cannot be null!");
    return this;
  }

  @NonNull
  @Override
  public String host() {
    return this.host;
  }

  @NonNull
  @Override
  public Identity withHost(@NonNull String host) {
    this.host = Preconditions.checkNotNull(host, "host cannot be null!");
    return this;
  }

  @Override
  public int port() {
    return this.port;
  }

  @NonNull
  @Override
  public Identity withPort(int port) {
    this.port = port;
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof IdentityBuilder)) {
      return false;
    }

    IdentityBuilder that = (IdentityBuilder) other;

    return this.port == that.port &&
        this.name.equals(that.name) &&
        this.host.equals(that.host);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.host, this.port);
  }

  @Override
  public String toString() {
    return "Identity{" +
        "name='" + this.name + '\'' +
        ", host='" + this.host + '\'' +
        ", port=" + this.port +
        '}';
  }
}