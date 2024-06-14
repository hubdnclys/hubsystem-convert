package com.practice.spring_java.global.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadHtmlContentFromFile {

    public static String readHtmlContentFromFile(String filePath) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
        return new String(encodedBytes);
    }
}
