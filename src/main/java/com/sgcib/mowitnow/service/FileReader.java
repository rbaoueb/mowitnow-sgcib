package com.sgcib.mowitnow.service;

import com.sgcib.mowitnow.model.GardenGround;

import java.util.List;

public sealed interface FileReader permits MowerFileReader {

    List<String> parse(final String fileName);
}
