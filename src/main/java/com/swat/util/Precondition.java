package com.swat.util;

public class Precondition {

	public static <T> T checkNotNull(T reference, Object errorMessage) {
		if (reference == null) {
			throw new NullPointerException(String.valueOf(errorMessage));
		} else {
			return reference;
		}
	}

}
