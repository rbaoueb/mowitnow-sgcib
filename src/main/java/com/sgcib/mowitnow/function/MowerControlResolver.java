package com.sgcib.mowitnow.function;

import com.sgcib.mowitnow.model.GroundLimit;
import com.sgcib.mowitnow.model.Mower;

@FunctionalInterface
public interface MowerControlResolver<T extends Mower, U extends GroundLimit> {

    /**
     *
     * @return new {@link T GardenGround} after the ground initialized
     */
    T resolve(T mower, U limit);
}