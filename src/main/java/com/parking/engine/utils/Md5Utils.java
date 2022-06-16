package com.parking.engine.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
    /**
     * Convert String to md5
     * @param message
     * @return
     */
    public static String convertStringToMd5(String message) {
        return DigestUtils.md5Hex(message);
    }
}
