package com.thoughtworks.springbootemployee.Exception;

public class NoSuchDataException extends Exception {
    private static String noSuchData= "could not find data";

    public static String getNoSuchData() {
        return noSuchData;
    }
}
