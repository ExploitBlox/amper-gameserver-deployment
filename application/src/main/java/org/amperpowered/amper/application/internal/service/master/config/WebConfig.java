package org.amperpowered.amper.application.internal.service.master.config;

public interface WebConfig {


  static WebConfig begin() {
    return new WebConfigBuilder();
  }


  String authenticateKey();


  WebConfig withAuthenticateKey(String authenticateKey);

  int port();


  WebConfig withPort(int port);
}
