package org.amperpowered.amper.application.internal.service.daemon;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface DaemonService {

  @NonNull
  static DaemonService vanilla() {
    return VanillaDaemonService.DAEMON_SERVICE;
  }

  void enable();

  void disable();
}
