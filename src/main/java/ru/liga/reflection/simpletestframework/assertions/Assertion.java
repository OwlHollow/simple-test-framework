package ru.liga.reflection.simpletestframework.assertions;

import ru.liga.reflection.simpletestframework.exceptions.TestException;

public class Assertion {
    public static <T> boolean assertEquals(T actual, T expected){
        return actual.equals(expected);
    }

    public static void assertTrue(boolean actual){
        if(actual == true) {
            return;
        }

        throw new TestException();
    }

    public static void assertFalse(boolean actual){
        if(actual == false) {
            return;
        }

        throw new TestException();
    }

    public static void assertNull(Object actual){
        if(actual == null) {
            return;
        }

        throw new TestException();
    }

    public static void assertNotNull(Object actual){
        if(actual != null) {
            return;
        }

        throw new TestException();
    }
}
