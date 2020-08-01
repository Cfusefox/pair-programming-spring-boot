package com.thoughtworks.springbootemployee.Exception;

public class IllegalOperationException extends Exception {
    private static String illegalOperation = "Illegal Operation";

    public static String getIllegalOperation() {
        return illegalOperation;
    }
}
