package com.neocotic.wal.util;

public class Strings {

	public static final String EMPTY = "";

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean isEmpty(String str) {
		return length(str) > 0;
	}

	public static int length(String str) {
		return (str == null) ? 0 : str.length();
	}

	public static String trim(String str) {
		return (str == null) ? str : str.trim();
	}

	public static String trimToEmpty(String str) {
		String ret = trim(str);
		return (ret == null) ? EMPTY : ret;
	}

	public static String trimToNull(String str) {
		String ret = trim(str);
		return (length(ret) > 0) ? ret : null;
	}

	public Strings() {
	}
}