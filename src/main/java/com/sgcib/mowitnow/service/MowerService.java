package com.sgcib.mowitnow.service;

import com.sgcib.mowitnow.model.GardenGround;

import java.util.List;

public sealed interface MowerService permits MowerServiceImpl{

    GardenGround process(List<String> content);
}
