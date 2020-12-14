package com.hazelcast.internal.util.function;

public interface TriFunction<F, S, T, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param f the first function argument
     * @param s the second function argument
     * @param t the third function argument
     * @return the function result
     */
    R apply(F f, S s, T t);

}