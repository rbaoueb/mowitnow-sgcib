package com.sgcib.mowitnow.model;

public final record GroundLimit(int x, int y) {

    public static GroundLimit of(int x, int y) {
        return new GroundLimit(x, y);
    }

}
