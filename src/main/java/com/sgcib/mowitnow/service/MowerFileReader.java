package com.sgcib.mowitnow.service;

import com.sgcib.mowitnow.exception.GroundFileInvalidException;
import com.sgcib.mowitnow.exception.GroundFileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public final class MowerFileReader implements FileReader {

    private static FileReader instance;

    private MowerFileReader() {
    }

    public static FileReader getInstance() {
        if (instance == null) {
            instance = new MowerFileReader();
        }
        return instance;
    }

    @Override
    public List<String> parse(final String fileName) {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.toList();
        } catch (IOException e) {
            throw new GroundFileNotFoundException("problème d'accès au fichier!");
        }
        if (result.isEmpty() || (result.size() - 1) % 2 != 0) {
            throw new GroundFileInvalidException("le fichier doit contenir au moins une entête avec deux lignes par tondeuse!");
        }
        return result;
    }

}
