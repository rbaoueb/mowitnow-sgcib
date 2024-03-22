package com.sgcib.mowitnow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MowerControlEnum {

	ADVANCE("A"),
	RIGHT("D"),
	LEFT("G");

	private String code;

	public static MowerControlEnum of(final String code) {
		return Arrays.stream(MowerControlEnum.values()).filter(e->e.getCode().equalsIgnoreCase(code)).findAny().orElse(null);
	}
}
