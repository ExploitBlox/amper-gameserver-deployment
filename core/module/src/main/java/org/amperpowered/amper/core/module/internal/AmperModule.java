/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import org.amperpowered.amper.core.module.internal.context.InitialContext;
import org.amperpowered.amper.core.module.internal.context.TerminateContext;

public interface AmperModule {

  void initial(InitialContext initialContext);

  void terminate(TerminateContext terminateContext);
}
