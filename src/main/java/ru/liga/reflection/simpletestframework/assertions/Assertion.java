package ru.liga.reflection.simpletestframework.assertions;

import ru.liga.reflection.simpletestframework.exceptions.TestException;

public class Assertion {
    public static <T> void assertEquals(T actual, T expected){
        if(actual.equals(expected)){
            return;
        }

        throw new TestException("Expected " + expected + ", but have " + actual);
    }

    public static void assertTrue(boolean actual){
        if(actual == true) {
            return;
        }

        throw new TestException("Expected true, but have " + actual);
    }

    public static void assertFalse(boolean actual){
        if(actual == false) {
            return;
        }

        throw new TestException("Expected false, but have " + actual);
    }

    public static void assertNull(Object actual){
        if(actual == null) {
            return;
        }

        throw new TestException("Expected null, but have " + actual);
    }

    public static void assertNotNull(Object actual){
        if(actual != null) {
            return;
        }

        throw new TestException("Expected not null, but have " + actual);
    }
}
