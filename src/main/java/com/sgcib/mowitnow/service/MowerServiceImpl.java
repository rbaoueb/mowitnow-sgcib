package com.sgcib.mowitnow.service;

import com.sgcib.mowitnow.exception.CommandInvalidException;
import com.sgcib.mowitnow.exception.HeaderFileInvalidException;
import com.sgcib.mowitnow.exception.PositionInvalidException;
import com.sgcib.mowitnow.function.MowerControlResolver;
import com.sgcib.mowitnow.function.PositionAggregationCollector;
import com.sgcib.mowitnow.model.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class MowerServiceImpl implements MowerService {

    private static MowerService instance;

    private final Function<String, GroundLimit> limitResolver = (header) -> {
        if (StringUtils.isNotBlank(header) && header.matches("^[0-9] [0-9]$")) {
            String[] pos = header.split(" ");
            return GroundLimit.of(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        }
        throw new HeaderFileInvalidException("l'entête du fichier doit contenir deux numérics séparés par un espace!");
    };

    private final BiFunction<String, String, Mower> mowerResolver = (position, controls) -> {
        if (StringUtils.isBlank(position) || !position.matches("^[0-9] [0-9] (N|S|W|E)$")) {
            throw new PositionInvalidException("la ligne de la tondeuse doit contenir deux numériques et la direction spéarés par un espace!");
        }
        if (StringUtils.isBlank(controls) || !StringUtils.containsOnly(controls, MowerControlEnum.ADVANCE.getCode() + MowerControlEnum.RIGHT.getCode() + MowerControlEnum.LEFT.getCode())) {
            throw new CommandInvalidException("les commandes ne doivent contenir que les caractères A, D ou G!");
        }
        String[] pos = position.split(" ");
        return Mower.of(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), DirectionEnum.of(pos[2]), controls.chars().mapToObj(Character::toString).map(MowerControlEnum::of).collect(Collectors.toList()));
    };


    private final MowerControlResolver<Mower, GroundLimit> controlResolver = (mower, limit) -> {
        Position finalPosition = mower.controls().stream().collect(toAggregator(limit, mower.position()));
        return Mower.of(finalPosition.x(), finalPosition.y(), finalPosition.direction(), mower.controls());
    };

    private static PositionAggregationCollector toAggregator(GroundLimit limit, Position mowerPosition) {
        return new PositionAggregationCollector(limit, mowerPosition);
    }

    public static MowerService getInstance() {
        if (instance == null) {
            instance = new MowerServiceImpl();
        }
        return instance;
    }

    @Override
    public GardenGround process(List<String> content) {
        GroundLimit limit = limitResolver.apply(content.get(0));
        List<Mower> mowers = new ArrayList<>();
        for (int i = 1; i + 1 < content.size(); i += 2) {
            Mower initialMower = mowerResolver.apply(content.get(i), content.get(i + 1));
            mowers.add(controlResolver.resolve(initialMower, limit));
        }
        return GardenGround.of(limit, mowers);
    }
}
