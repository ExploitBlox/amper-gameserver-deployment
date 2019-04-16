/*
 * Copyright (c) 2019, Amper and its contributors
 *
 * This code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package org.amperpowered.amper.module.internal;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import org.amperpowered.amper.module.internal.annotation.Module;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.pmw.tinylog.Logger;

final class VanillaModuleScanner implements ModuleScanner {

  @NonNull
  @Override
  public List<Class<?>> scanModules(@NonNull Path modulePath) throws IOException {
    Preconditions.checkNotNull(modulePath, "modulePath cannot be null!");

    if (!Files.exists(modulePath)) {
      Files.createDirectory(modulePath);
    }

    return Files.walk(modulePath)
        .filter(path -> path.toFile().getName().endsWith(".jar"))
        .map(this::scanModule)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  @NonNull
  @Override
  public Optional<Class<?>> scanModule(@NonNull Path moduleFile) {
    Preconditions.checkNotNull(moduleFile, "moduleFile cannot be null!");

    if (!Files.exists(moduleFile)) {
      return Optional.empty();
    }

    try {
      JarFile jarFile = new JarFile(moduleFile.toFile());
      Enumeration<JarEntry> entries = jarFile.entries();

      while (entries.hasMoreElements()) {
        JarEntry entry = entries.nextElement();

        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
          URLClassLoader urlClassLoader = new URLClassLoader(
              new URL[]{moduleFile.toFile().toURI().toURL()});

          String entryName = entry.getName();

          String className = entryName.substring(0, entryName.length() - 6)
              .replace("/", ".");

          Class<?> moduleClass = urlClassLoader.loadClass(className);

          if (moduleClass.isAnnotationPresent(Module.class)) {
            Module module = moduleClass.getDeclaredAnnotation(Module.class);

            Logger.info("Found the module " + module.name() + " version: " + module.version()
                + " author: " + module.author());
            return Optional.of(moduleClass);
          }
        }
      }
    } catch (IOException | ClassNotFoundException cause) {
      cause.printStackTrace();
    }

    return Optional.empty();
  }
}
