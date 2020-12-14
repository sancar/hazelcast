package com.hazelcast.internal.util.function;

public interface TriConsumer<F, S, T> {

    /**
     * Applies this consumer with the given arguments.
     *
     * @param f the first function argument
     * @param s the second function argument
     * @param t the third function argument
     */
    void consume(F f, S s, T t);

}