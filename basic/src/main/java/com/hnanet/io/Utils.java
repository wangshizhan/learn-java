package com.hnanet.io;

public class Utils {
    public static String getResourcePath(String path){
        String rePath = "";
        rePath = Utils.class.getClassLoader().getResource(path).getPath();
        // rePath = this.getClass().getClassLoader().getResource(path).getPath();
        return rePath;
    }

    public static String getResourcePath(){
        String rePath = "";
        rePath = Utils.class.getClassLoader().getResource("").getPath();
        return rePath;
    }
}
