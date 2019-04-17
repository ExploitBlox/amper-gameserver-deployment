package org.amperpowered.amper.application.internal.service.master.config;

import com.google.common.base.Preconditions;
import java.util.Objects;

final class WebConfigBuilder implements WebConfig {

  private String authenticateKey;
  private int port;

  WebConfigBuilder() {
    this.authenticateKey = "undefined";
    this.port = 8080;
  }


  @Override
  public String authenticateKey() {
    return this.authenticateKey;
  }


  @Override
  public WebConfig withAuthenticateKey(String authenticateKey) {
    this.authenticateKey = Preconditions.checkNotNull(authenticateKey, "authenticateKey cannot be null!");
    return this;
  }

  @Override
  public int port() {
    return this.port;
  }


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
