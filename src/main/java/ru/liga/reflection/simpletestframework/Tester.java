package ru.liga.reflection.simpletestframework;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.liga.reflection.simpletestframework.annotations.AfterTest;
import ru.liga.reflection.simpletestframework.annotations.BeforeTest;
import ru.liga.reflection.simpletestframework.annotations.Test;
import ru.liga.reflection.simpletestframework.exceptions.TestException;
import ru.liga.reflection.simpletestframework.utils.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class Tester {
    private Set<Class<? extends Object>> classes;

    public Tester(String testRootPackage) {
        init(testRootPackage);
    }

    /**
     * Метод запускающий тесты; Если хотя бы один тест упадёт (выбросит TestException) - программа завершается;
     */
    public void test() {
        Method currentMethod = null;

        for (Class c : classes) {
            Object testClassInstance = ReflectionHelper.instantiate(c);

            System.out.println("*** Run " + c.getName() + " tests ***");

            List<Method> startUpMethods = ReflectionHelper.findAnnotatedMethods(c, BeforeTest.class);
            List<Method> testMethods = ReflectionHelper.findAnnotatedMethods(c, Test.class);
            List<Method> afterTestMethods = ReflectionHelper.findAnnotatedMethods(c, AfterTest.class);

            try {
                for (Method m : startUpMethods) {
                    ReflectionHelper.callMethod(testClassInstance, m.getName());
                }

                for (Method m : testMethods) {
                    currentMethod = m;
                    System.out.print("Running " + m.getName() + " test: ");
                    ReflectionHelper.callMethod(testClassInstance, m.getName());
                    System.out.println("Ok");
                }

                for (Method m : afterTestMethods) {
                    ReflectionHelper.callMethod(testClassInstance, m.getName());
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                Throwable t =  e.getCause();
                if(t instanceof TestException){
                    System.out.println("Failed");
                    System.out.println("Failed while execute " + currentMethod.getName() + " in class " + c.getName());
                    e.printStackTrace();
                    System.exit(-1);
                }
                e.printStackTrace();
            }
        }

        System.out.println("*** All tests passed ***");
        System.out.println();
    }

    private void init(String testRootPackage) {
        Reflections reflections = new Reflections(testRootPackage, new SubTypesScanner(false));
        classes = reflections.getSubTypesOf(Object.class);
    }
}
