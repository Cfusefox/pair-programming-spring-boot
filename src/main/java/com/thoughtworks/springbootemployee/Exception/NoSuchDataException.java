package com.thoughtworks.springbootemployee.Exception;

public class NoSuchDataException extends Exception {
    private static String noSuchData= "找不到这个数据";

    public static String getNoSuchData() {
        return noSuchData;
    }
}
