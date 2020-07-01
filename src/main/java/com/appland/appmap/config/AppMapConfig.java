package com.appland.appmap.config;

import com.appland.appmap.util.Logger;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AppMapConfig {
  public String name;
  public AppMapPackage[] packages = new AppMapPackage[0];
  private static AppMapConfig singleton = new AppMapConfig();

  /**
   * Populate the configuration from a file.
   * @param configFile The file to be loaded
   * @return The AppMapConfig singleton
   */
  public static AppMapConfig load(File configFile) {
    InputStream inputStream = null;

    try {
      inputStream = new FileInputStream(configFile);
    } catch (FileNotFoundException e) {
      Logger.println(String.format("error: file not found -> %s", configFile.getPath()));
      return null;
    }

    Yaml yaml = new Yaml();

    try {
      singleton = yaml.loadAs(inputStream, AppMapConfig.class);
    } catch (YAMLException e) {
      System.err.println("AppMap: encountered syntax error in appmap.yml " + e.getMessage());
      System.exit(1);
    }
    
    return singleton;
  }

  /**
   * Get the AppMapConfig singleton.
   * @return The singleton instance
   */
  public static AppMapConfig get() {
    return singleton;
  }

  /**
   * Check if a class/method is included in the configuration.
   * @param className The class name to be checked
   * @param methodName The method name to be checked
   * @param isStatic {@code true} if the method is static
   * @return {@code true} if the class/method is included in the configuration. {@code false} if it
   *         is not included or otherwise explicitly excluded.
   */
  public Boolean includes(String className, String methodName, boolean isStatic) {
    for (AppMapPackage pkg : this.packages) {
      if (pkg.includes(className, methodName, isStatic)) {
        return true;
      }
    }

    return false;
  }
}
