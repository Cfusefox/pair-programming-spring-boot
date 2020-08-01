package com.thoughtworks.springbootemployee.Exception;

public class IllegalOperationException extends Exception {
    private static String illegalOperation = "非法操作";

    public static String getIllegalOperation() {
        return illegalOperation;
    }
}
