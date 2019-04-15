package org.amperpowered.amper.application.internal.service.master;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface MasterService {

  @NonNull
  static MasterService vanilla() {
    return VanillaMasterService.MASTER_SERVICE;
  }

  void enable();

  void disable();
}
