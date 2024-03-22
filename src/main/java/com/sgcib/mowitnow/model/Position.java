package com.sgcib.mowitnow.model;

public final record Position(int x, int y, DirectionEnum direction) {

	public static Position of(int x, int y, DirectionEnum direction) {
		return new Position(x, y, direction);
	}

}