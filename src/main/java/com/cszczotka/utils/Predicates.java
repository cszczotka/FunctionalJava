package com.cszczotka.utils;

import java.util.Arrays;
import java.util.function.Predicate;

public class Predicates {

    public static <T> Predicate<T> andPredicates(Predicate<T>... predicates) {
        Predicate<T> p = x -> true; // Identity

        /*
        for (int i = 0; i < predicates.length; i++) {
            p = p.and(predicates[i]);
        };
        return p;
        */
        return Arrays.stream(predicates).reduce(p, Predicate::and);
    }

    public static <T> Predicate<T> orPredicates(Predicate<T>... predicates) {
        Predicate<T> p = x -> false; // Identity

        /*
        for (int i = 0; i < predicates.length; i++) {
            p = p.or(predicates[i]);
        };
        return p;
        */
        return Arrays.stream(predicates).reduce(p, Predicate::or);
    }
}
