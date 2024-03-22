package com.sgcib.mowitnow.function;

@FunctionalInterface
public interface MowerControlResolver<T, U> {

    /**
     *
     * @return new {@link T GardenGround} after the ground initialized
     */
    T resolve(T mower, U limit);
}