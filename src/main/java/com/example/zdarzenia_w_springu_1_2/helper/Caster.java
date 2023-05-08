package com.example.zdarzenia_w_springu_1_2.helper;

public class Caster {
    public static Object castStringTo(String o, Class<?> clazz){
        switch (clazz.getTypeName()){
            case "java.lang.Integer":
            case "int":
                return Integer.valueOf(o);
            case "java.lang.Double":
            case "double":
                return Double.valueOf(o);
        }
        return null;
    }
}
