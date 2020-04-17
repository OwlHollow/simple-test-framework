package ru.liga.reflection;

import ru.liga.reflection.domain.SimpleCalc;
import ru.liga.reflection.simpletestframework.Tester;

public class App 
{
    public static void main( String[] args )
    {
        Tester tester = new Tester("ru.liga.reflection.tests");
        if(!tester.test()){
            System.out.println("****** Tests failed ******");
            System.exit(-1);
        } else {
            System.out.println("****** Tests ok ******");
        }
        SimpleCalc simpleCalc = new SimpleCalc();
        System.out.println(simpleCalc.calculate("10 - 5 + 11 - 2"));
    }
}
