package org.amperpowered.amper.application.config;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

final class WebConfigBuilder implements WebConfig {

  private String authenticateKey;
  private int port;

  WebConfigBuilder() {
    this.authenticateKey = "undefined";
    this.port = 8080;
  }

  @NonNull
  @Override
  public String authenticateKey() {
    return this.authenticateKey;
  }

  @NonNull
  @Override
  public WebConfig withAuthenticateKey(@NonNull String authenticateKey) {
    this.authenticateKey = Preconditions.checkNotNull(authenticateKey, "authenticateKey cannot be null!");
    return this;
  }

  @Override
  public int port() {
    return this.port;
  }

  @NonNull
  @Override
  public WebConfig withPort(int port) {
    this.port = port;
    return this;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof WebConfigBuilder)) {
      return false;
    }

    WebConfigBuilder that = (WebConfigBuilder) other;

    return this.port == that.port &&
        this.authenticateKey.equals(that.authenticateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.authenticateKey, this.port);
  }

  @Override
  public String toString() {
    return "WebConfig{" +
        "authenticateKey='" + this.authenticateKey + '\'' +
        ", port=" + this.port +
        '}';
  }
}
