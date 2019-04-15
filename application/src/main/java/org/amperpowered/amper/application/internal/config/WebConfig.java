package org.amperpowered.amper.application.internal.config;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface WebConfig {

  @NonNull
  static WebConfig begin() {
    return new WebConfigBuilder();
  }

  @NonNull
  String authenticateKey();

  @NonNull
  WebConfig withAuthenticateKey(@NonNull String authenticateKey);

  int port();

  @NonNull
  WebConfig withPort(int port);
}
