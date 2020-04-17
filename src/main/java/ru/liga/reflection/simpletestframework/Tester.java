package ru.liga.reflection.simpletestframework;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.liga.reflection.simpletestframework.annotations.AfterTest;
import ru.liga.reflection.simpletestframework.annotations.BeforeTest;
import ru.liga.reflection.simpletestframework.annotations.Test;
import ru.liga.reflection.simpletestframework.utils.ReflectionHelper;

import java.lang.reflect.Method;
import java.sql.Ref;
import java.util.List;
import java.util.Set;

public class Tester {
    private Reflections reflections;

    private List<Method> startUpMethods;
    private List<Method> testMethods;
    private List<Method> afterTestMethods;

    private Set<Class<? extends Object>>  classes;

    private String testRootPackage;
    private boolean success;


    public Tester(String testRootPackage){
        this.testRootPackage = testRootPackage;
        init();
    }

    public int getTestCount(){
        return testMethods.size();
    }

    public boolean test(){
        success = true;

        for(Class c: classes){
            Object testClassInstance = ReflectionHelper.instantiate(c);

            startUpMethods = ReflectionHelper.findAnnotatedMethods(c, BeforeTest.class);
            testMethods = ReflectionHelper.findAnnotatedMethods(c, Test.class);
            afterTestMethods = ReflectionHelper.findAnnotatedMethods(c, AfterTest.class);

            for(Method m: startUpMethods){
                ReflectionHelper.callMethod(testClassInstance, m.getName());
            }

            for(Method m: testMethods){
                ReflectionHelper.callMethod(testClassInstance, m.getName());
            }

            for(Method m: afterTestMethods){
                ReflectionHelper.callMethod(testClassInstance, m.getName());
            }
        }

        return true;
    }

    private void init(){
        Reflections reflections =  new Reflections(testRootPackage);
        classes = reflections.getSubTypesOf(Object.class);
    }
}
