package com.sgcib.mowitnow.model;


import java.util.List;

public final record Mower(Position position, List<MowerControlEnum> controls) {

	public static Mower of(int x, int y, DirectionEnum direction, List<MowerControlEnum> controls) {
		return new Mower(Position.of(x, y, direction), controls);
	}

	@Override
	public String toString() {
		return position.x()+" "+ position.y()+" "+position.direction().getCode();
	}
}