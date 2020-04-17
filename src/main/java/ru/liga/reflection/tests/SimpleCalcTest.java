package ru.liga.reflection.tests;

import ru.liga.reflection.domain.SimpleCalc;
import ru.liga.reflection.simpletestframework.annotations.AfterTest;
import ru.liga.reflection.simpletestframework.annotations.BeforeTest;
import ru.liga.reflection.simpletestframework.annotations.Test;

public class SimpleCalcTest {
    private SimpleCalc simpleCalc;

    @BeforeTest
    public void setUp(){
        System.out.println("Setting up test");
        simpleCalc = new SimpleCalc();
    }

    @AfterTest
    public void conclusion(){
        System.out.println("End tests");
    }

    @Test
    public void validStrings(){
        System.out.println("Testing class");
    }
}
