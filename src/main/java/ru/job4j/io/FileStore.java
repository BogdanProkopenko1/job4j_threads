package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;

public class FileStore {

    private final File file;

    public FileStore(String dir) {
        file = Path.of(dir).toFile();
    }

    public void save(String data) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data.getBytes());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
