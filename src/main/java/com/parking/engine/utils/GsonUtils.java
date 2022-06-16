package com.parking.engine.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author modani
 */
@Slf4j
public class GsonUtils {

    /**
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJsonToObjByFile(String path, Class<?> clazz) {
        try {
            String data = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8.name());
            return convertJsonToObj(data, clazz);
        } catch (IOException e) {
            log.error("Error when convert json to obj by file:", e);
        }
        return null;
    }

    /**
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJsonToObj(String data, Class<?> clazz) {
        Gson gson = new Gson();
        return (T) gson.fromJson(data, clazz);
    }

    /**
     * @param path
     * @param listType
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJsonToArrayByFile(String path, Type listType) {
        try {
            String data = FileUtils.readFileToString(readResource(path), StandardCharsets.UTF_8.name());
            return convertJsonToArray(data, listType);
        } catch (Exception e) {
            log.error("Error when convert json to array by file:", e);
        }
        return null;
    }

    /**
     * @param listType
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJsonToArrayByString(String data, Type listType) {
        try {
            return convertJsonToArray(data, listType);
        } catch (Exception e) {
            log.error("Error when convert json to array by file:", e);
        }
        return null;
    }

    /**
     * Read resource
     * @param path
     * @return
     * @throws IOException
     */
    public static File readResource(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        return resource.getFile();
    }

    /**
     * @param data
     * @param listType
     * @param <T>
     * @return
     */
    public static <T>List<T> convertJsonToArray(String data, Type listType) {
        Gson gson = new Gson();
        return gson.fromJson(data, listType);
    }

    //end
}
