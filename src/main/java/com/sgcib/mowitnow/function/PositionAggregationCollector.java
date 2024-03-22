package com.sgcib.mowitnow.function;


import com.sgcib.mowitnow.model.GroundLimit;
import com.sgcib.mowitnow.model.MowerControlEnum;
import com.sgcib.mowitnow.model.Position;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PositionAggregationCollector implements Collector<MowerControlEnum, AtomicReference<Position>, Position> {

    private final GroundLimit limit;
    private final Position mowerPosition;

    public PositionAggregationCollector(GroundLimit limit, Position mowerPosition) {
        this.limit = limit;
        this.mowerPosition = mowerPosition;
    }

    @Override
    public Supplier<AtomicReference<Position>> supplier() {
        return () -> new AtomicReference<>(mowerPosition);
    }

    @Override
    public BiConsumer<AtomicReference<Position>, MowerControlEnum> accumulator() {
        return (position, control) -> {
            Position tmpPos = position.get();
            switch (control) {
                case ADVANCE -> position.set(computeAdvance(tmpPos));
                case RIGHT -> position.set(Position.of(tmpPos.x(), tmpPos.y(), tmpPos.direction().turnRight()));
                case LEFT -> position.set(Position.of(tmpPos.x(), tmpPos.y(), tmpPos.direction().turnLeft()));
            }
        };
    }

    @Override
    public BinaryOperator<AtomicReference<Position>> combiner() {
        return (pos1, pos2) -> pos2;
    }

    @Override
    public Function<AtomicReference<Position>, Position> finisher() {
        return AtomicReference::get;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    private Position computeAdvance(Position position) {

        return switch (position.direction()) {
            case NORTH -> {
                if (position.y() < limit.y()) {
                    yield Position.of(position.x(), position.y() + 1, position.direction());
                }
                yield Position.of(position.x(), position.y(), position.direction());
            }
            case SOUTH -> {
                if (position.y() > 0) {
                    yield Position.of(position.x(), position.y() - 1, position.direction());
                }
                yield Position.of(position.x(), position.y(), position.direction());
            }
            case EAST -> {
                if (position.x() < limit.x()) {
                    yield Position.of(position.x() + 1, position.y(), position.direction());
                }
                yield Position.of(position.x(), position.y(), position.direction());
            }
            case WEST -> {
                if (position.x() > 0) {
                    yield Position.of(position.x() - 1, position.y(), position.direction());
                }
                yield Position.of(position.x(), position.y(), position.direction());
            }
        };

    }


}
