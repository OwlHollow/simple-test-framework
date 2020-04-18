package ru.liga.reflection.tests;

import com.sun.xml.internal.ws.policy.AssertionSet;
import ru.liga.reflection.domain.SimpleCalc;
import ru.liga.reflection.simpletestframework.annotations.AfterTest;
import ru.liga.reflection.simpletestframework.annotations.BeforeTest;
import ru.liga.reflection.simpletestframework.annotations.Test;
import ru.liga.reflection.simpletestframework.assertions.Assertion;

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
    public void whenCalculateValidStrings(){
        Assertion.assertEquals(simpleCalc.calculate("2+2"), 4);
        Assertion.assertEquals(simpleCalc.calculate("2+2-2+1"), 3);
        Assertion.assertEquals(simpleCalc.calculate("2+2+1+10-15"), 0);
    }

    @Test
    public void whenEqualsFailed(){
        Assertion.assertEquals(simpleCalc.calculate("2 - 2"), 2);
    }

    @Test
    public void whenCalculateValidStringsWithSpaces(){
        Assertion.assertEquals(simpleCalc.calculate("  2 +   2  "), 4);
        Assertion.assertEquals(simpleCalc.calculate("      2 + 2    - 2  + 1    "), 3);
        Assertion.assertEquals(simpleCalc.calculate("2 + 2 + 1  + 10 - 15"), 0);
    }

    @Test
    public void whenResultNegative(){
        Assertion.assertEquals(simpleCalc.calculate("2 - 2"), 0);
        Assertion.assertEquals(simpleCalc.calculate("      2 - 2    - 2  + 1    "), -1);
        Assertion.assertEquals(simpleCalc.calculate("2 + 2 + 1  - 10 - 15"), -20);
    }

}
