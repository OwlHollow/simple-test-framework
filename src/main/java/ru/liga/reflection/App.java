package ru.liga.reflection;

import ru.liga.reflection.domain.SimpleCalc;
import ru.liga.reflection.simpletestframework.Tester;
import ru.liga.reflection.tests.SimpleCalcTest;

import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
        Tester tester = new Tester("ru.liga.reflection.tests");
        tester.test();
    }
}
