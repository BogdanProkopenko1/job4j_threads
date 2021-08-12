package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(String dir) {
        file = Path.of(dir).toFile();
    }

    public String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append(data);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return output.toString();
    }
}
