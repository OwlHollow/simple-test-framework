package ru.liga.reflection.domain;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimpleCalc {
    /**
     * Метод вычисляющий  алгебраическое выражение;
     * @param expression Строка с числами и операциями сложения и вычитания;
     * @return Результат выражения.
     */
    public Integer calculate(String expression){
        int result = 0;

        //appending string while parsing digits
        StringBuilder strValue = new StringBuilder();

        //lists for digits and operators
        LinkedList<Integer> values = new LinkedList<>();
        ArrayList<String> operators = new ArrayList<>();

        final char p = '+', m = '-';
        char chrValue;

        //remove all spaces
        expression = expression.replace(" ", "");
        try {
            for (int i = 0; i < expression.length(); i++) {
                chrValue = expression.charAt(i);
                switch (chrValue) {
                    case p:
                    case m:
                        operators.add(String.valueOf(chrValue));
                        values.add(Integer.parseInt(strValue.toString()));

                        strValue.delete(0, strValue.length());
                        break;
                    default:
                        strValue.append(chrValue);
                }
            }
        } catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        //add last digit
        values.add(Integer.parseInt(strValue.toString()));

        result = values.pop();
        for(String s: operators){
            switch (s){
                case "+":
                    result += values.pop();
                    break;
                case "-":
                    result -= values.pop();
                    break;
            }
        }

        return result;
    }
}
