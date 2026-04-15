package org.dev.Helpers;

public class StringHelper {
    public static String cleanPathParam(String pathparam){
        if (pathparam == null) return "";
        return pathparam.replaceAll("[^a-zA-Z0-9_]", "");
    }
}
