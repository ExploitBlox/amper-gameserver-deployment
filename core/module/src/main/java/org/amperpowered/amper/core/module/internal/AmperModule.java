/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import org.amperpowered.amper.core.module.internal.context.InitialContext;
import org.amperpowered.amper.core.module.internal.context.TerminateContext;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface AmperModule {

  void initial(@NonNull InitialContext initialContext);

  void terminate(@NonNull TerminateContext terminateContext);
}
