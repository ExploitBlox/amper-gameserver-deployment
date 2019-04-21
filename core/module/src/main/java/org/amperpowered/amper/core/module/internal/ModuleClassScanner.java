/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.core.module.internal;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import org.amperpowered.amper.core.module.internal.annotation.Module;

@Singleton
public final class ModuleClassScanner {

  public static ModuleClassScanner create() {
    return new ModuleClassScanner();
  }

  private static final Path MODULE_PATH = Paths.get("modules/");

  public List<Class<?>> scanModules() throws IOException {
    if (!Files.exists(MODULE_PATH)) {
      Files.createDirectory(MODULE_PATH);
    }

    return Files.walk(MODULE_PATH)
        .filter(path -> path.toFile().getName().endsWith(".jar"))
        .map(this::scanModule)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  private Optional<Class<?>> scanModule(Path moduleFile) {
    if (!Files.exists(moduleFile)) {
      return Optional.empty();
    }

    try {
      JarFile jarFile = new JarFile(moduleFile.toFile());
      Enumeration<JarEntry> entries = jarFile.entries();

      while (entries.hasMoreElements()) {
        JarEntry entry = entries.nextElement();

        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
          return this.scanModuleClass(entry, moduleFile.toFile().toURI().toURL());
        }
      }
    } catch (IOException | ClassNotFoundException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }

  private Optional<Class<?>> scanModuleClass(JarEntry jarEntry, URL moduleUrl) throws ClassNotFoundException {
    Preconditions.checkNotNull(jarEntry, "jarEntry cannot be null!");

    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{moduleUrl});

    Class<?> moduleClass = urlClassLoader.loadClass(
        jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace("/", "."));

    if (moduleClass.isAnnotationPresent(Module.class)) {
      return Optional.of(moduleClass);
    }

    return Optional.empty();
  }

}
