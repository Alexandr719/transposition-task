package com.task.utils;

import com.task.exeptions.FileException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Class for writing/reading files
 */
public class FileHelperUtils {

    private FileHelperUtils() {
    }

    public static String readFromResourcesByName(String filename) {
        ClassLoader classLoader = FileHelperUtils.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new FileException("Got error while reading file from resource " + filename);
        }
    }

    public static String readFileBySysPath(String path) {
        try {
            return FileUtils.readFileToString(new File(path), "UTF-8");
        } catch (IOException e) {
            throw new FileException("Got error while reading file by path " + path);
        }
    }

    public static void writeFile(String path, String data) {
           File file = new File(path);
        try {
            FileUtils.writeStringToFile(file, data, "UTF-8");
        } catch (IOException e) {
            throw new FileException("Got error while writing to file " + path);
        }
    }
}
