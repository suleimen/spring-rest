package com.swat.util;

public class Util {

	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if (c != null && string != null && !string.isEmpty()) {
			try {
				return Enum.valueOf(c, string.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {

				throw new IllegalArgumentException("Status " + string + " not found");
			}
		}
		return null;
	}

}
