package com.cszczotka.monads;

import java.util.function.Function;

interface IO<T> {
    T exec();

    static <T> IO<T> of(T value) {
        return () -> value;
    }

    default  <U> IO<U> map(Function<T, U> f) {
        return () -> f.apply(this.exec());
    }

    default <U> IO<U> flatMap(Function<T, IO<U>> f) {
        //IO<IO<U>>
        return () -> f.apply(this.exec()).exec();
    }
 }
