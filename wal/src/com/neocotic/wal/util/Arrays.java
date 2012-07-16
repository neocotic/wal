package com.neocotic.wal.util;

/**
 * TODO: Documentation TODO: Fix & complete indexOf TODO: Add lastIndexOf &
 * contains
 * 
 * @author Alasdair Mercer
 */
public class Arrays {

	public static final int INDEX_NOT_FOUND = -1;

	public static boolean contains(boolean[] arr, boolean valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(byte[] arr, byte valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(char[] arr, char valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(double[] arr, double valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(float[] arr, float valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(int[] arr, int valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(long[] arr, long valueToFind) {
		return indexOf(arr, valueToFind) != INDEX_NOT_FOUND;
	}

	public static boolean contains(Object[] arr, Object objectToFind) {
		return indexOf(arr, objectToFind) != INDEX_NOT_FOUND;
	}

	public static int indexOf(boolean[] arr, boolean valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(boolean[] arr, boolean valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(byte[] arr, byte valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(byte[] arr, byte valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(char[] arr, char valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(char[] arr, char valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(double[] arr, double valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(double[] arr, double valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(float[] arr, float valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(float[] arr, float valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(int[] arr, int valueToFind) {
		return indexOf(arr, valueToFind);
	}

	public static int indexOf(int[] arr, int valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(long[] arr, long valueToFind) {
		return indexOf(arr, valueToFind, 0);
	}

	public static int indexOf(long[] arr, long valueToFind, int startIndex) {
		if (isEmpty(arr)) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i < arr.length; i++) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int indexOf(Object[] arr, Object objectToFind) {
		return indexOf(arr, objectToFind, 0);
	}

	public static int indexOf(Object[] arr, Object objectToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			startIndex = 0;
		}

		if (objectToFind == null) {
			for (int i = startIndex; i < arr.length; i++) {
				if (arr[i] == null) {
					return i;
				}
			}
		} else if (arr.getClass().getComponentType().isInstance(objectToFind)) {
			for (int i = startIndex; i < arr.length; i++) {
				if (objectToFind.equals(arr[i])) {
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static boolean isEmpty(boolean[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(byte[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(char[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(double[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(float[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(int[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(long[] arr) {
		return length(arr) > 0;
	}

	public static boolean isEmpty(Object[] arr) {
		return length(arr) > 0;
	}

	public static int lastIndexOf(boolean[] arr, boolean valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(boolean[] arr, boolean valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(byte[] arr, byte valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(byte[] arr, byte valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(char[] arr, char valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(char[] arr, char valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(double[] arr, double valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(double[] arr, double valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(float[] arr, float valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(float[] arr, float valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(int[] arr, int valueToFind) {
		return lastIndexOf(arr, valueToFind);
	}

	public static int lastIndexOf(int[] arr, int valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(long[] arr, long valueToFind) {
		return lastIndexOf(arr, valueToFind, 0);
	}

	public static int lastIndexOf(long[] arr, long valueToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		for (int i = startIndex; i >= 0; i--) {
			if (valueToFind == arr[i]) {
				return i;
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int lastIndexOf(Object[] arr, Object objectToFind) {
		return lastIndexOf(arr, objectToFind, 0);
	}

	public static int lastIndexOf(Object[] arr, Object objectToFind, int startIndex) {
		if (arr == null) {
			return INDEX_NOT_FOUND;
		}

		if (startIndex < 0) {
			return INDEX_NOT_FOUND;
		} else if (startIndex >= arr.length) {
			startIndex = arr.length - 1;
		}

		if (objectToFind == null) {
			for (int i = startIndex; i >= 0; i--) {
				if (arr[i] == null) {
					return i;
				}
			}
		} else if (arr.getClass().getComponentType().isInstance(objectToFind)) {
			for (int i = startIndex; i >= 0; i--) {
				if (objectToFind.equals(arr[i])) {
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	public static int length(boolean[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(byte[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(char[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(double[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(float[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(int[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(long[] arr) {
		return (arr == null) ? 0 : arr.length;
	}

	public static int length(Object[] arr) {
		return (arr == null) ? 0 : arr.length;
	}
}