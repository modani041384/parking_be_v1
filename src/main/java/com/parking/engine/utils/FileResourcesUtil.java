package com.parking.engine.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class FileResourcesUtil {
    /**
     * get a file from the resources of folder works everywhere, IDEA, unit test and JAR file.
     * @param fileName
     * @return
     */
    public InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        return inputStream;
    }

    /**
     * Convert InputStream to String
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String convertInputStreamToStr(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            log.error("Error when convert inputStream to string:", e);
        }
        return null;
    }

    //end
}
