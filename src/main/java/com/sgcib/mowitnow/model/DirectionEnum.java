package com.sgcib.mowitnow.model;

import java.util.Arrays;

public enum DirectionEnum {
	NORTH("N", "W", "E"),
	SOUTH("S", "E", "W"),
	WEST("W", "S", "N"),
	EAST("E", "N", "S");

	private final String code;
	private final String left;
	private final String right;

	DirectionEnum(final String code, final String left, final String right) {
		this.code = code;
		this.left = left;
		this.right = right;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public DirectionEnum turnLeft() {
		return DirectionEnum.of(left);
	}
	
	public DirectionEnum turnRight() {
		return DirectionEnum.of(right);
	}
	
	public static DirectionEnum of(final String code) {
		return Arrays.stream(DirectionEnum.values()).filter(e->e.getCode().equalsIgnoreCase(code)).findAny().orElse(null);
	}
}