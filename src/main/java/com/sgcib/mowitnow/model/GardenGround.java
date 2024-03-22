package com.sgcib.mowitnow.model;

import java.util.List;

public final record GardenGround(GroundLimit limit, List<Mower> mowers) {

    public static GardenGround of(GroundLimit limit, List<Mower> mowers) {
        return new GardenGround(limit, mowers);
    }

}
