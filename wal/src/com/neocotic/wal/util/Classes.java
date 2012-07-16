package com.neocotic.wal.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Classes {

	public static final String CLASS_FILE_EXTENSION = ".class";

	public static final String JAR_PROTOCOL = "jar";

	public static final String PACKAGE_SEPARATOR = ".";

	public static final char PACKAGE_SEPARATOR_CHAR = '.';

	private static final String URL_SEPARATOR = "/";

	private static final char URL_SEPARATOR_CHAR = '/';

	public static List<Class<?>> getPackageClasses(Package pkg) throws IOException {
		return getPackageClasses(pkg, false, null);
	}

	public static List<Class<?>> getPackageClasses(Package pkg, boolean recursive) throws IOException {
		return getPackageClasses(pkg, recursive, null);
	}

	public static List<Class<?>> getPackageClasses(Package pkg, Filter<Class<?>> filter) throws IOException {
		return getPackageClasses(pkg, false, filter);
	}

	public static List<Class<?>> getPackageClasses(Package pkg, boolean recursive, Filter<Class<?>> filter)
			throws IOException {
		return getPackageClasses((pkg == null) ? null : pkg.getName(), recursive, filter);
	}

	public static List<Class<?>> getPackageClasses(String pkgName) throws IOException {
		return getPackageClasses(pkgName, false, null);
	}

	public static List<Class<?>> getPackageClasses(String pkgName, boolean recursive) throws IOException {
		return getPackageClasses(pkgName, recursive, null);
	}

	public static List<Class<?>> getPackageClasses(String pkgName, Filter<Class<?>> filter) throws IOException {
		return getPackageClasses(pkgName, false, filter);
	}

	public static List<Class<?>> getPackageClasses(String pkgName, boolean recursive, Filter<Class<?>> filter)
			throws IOException {
		if (pkgName == null) {
			return null;
		}

		if (filter == null) {
			filter = new Filter.Default<Class<?>>();
		}

		pkgName = pkgName.replace(PACKAGE_SEPARATOR_CHAR, URL_SEPARATOR_CHAR);

		ClassLoader cl = ClassLoader.getSystemClassLoader();
		List<Class<?>> classes = new ArrayList<Class<?>>();
		URL pkgUrl = cl.getResource(pkgName);

		if (JAR_PROTOCOL.equals(pkgUrl.getProtocol())) {
			classes = getPackageClasses(pkgName, pkgUrl, recursive, filter, cl);
		} else {
			classes = getPackageClasses(pkgName, new File(pkgUrl.getFile()), recursive, filter, cl);
		}

		return classes;
	}

	private static List<Class<?>> getPackageClasses(String pkgName, File pkgDirectory, boolean recursive,
			Filter<Class<?>> filter, ClassLoader cl) throws IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		for (File file : pkgDirectory.listFiles()) {
			if (file.isDirectory()) {
				if (recursive) {
					String path = file.getAbsolutePath();
					path = path.substring(path.lastIndexOf(pkgName.replace(URL_SEPARATOR_CHAR, File.separatorChar)));
					path = path.replace(File.separatorChar, URL_SEPARATOR_CHAR);

					classes.addAll(getPackageClasses(path, file, recursive, filter, cl));
				} else {
					continue;
				}
			}

			Class<?> cls = loadClassFromPath(pkgName, file.getName(), cl);

			if (cls != null && filter.include(cls)) {
				classes.add(cls);
			}
		}

		return classes;
	}

	private static List<Class<?>> getPackageClasses(String pkgName, URL pkgUrl, boolean recursive,
			Filter<Class<?>> filter, ClassLoader cl) throws IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		String jarName = URLDecoder.decode(pkgUrl.getFile(), "UTF-8");
		jarName = jarName.substring(5, jarName.indexOf('!'));

		JarFile jar = null;

		try {
			jar = new JarFile(jarName);
			Enumeration<JarEntry> jarEntries = jar.entries();

			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();

				// TODO: Support recursion
				if (jarEntry.isDirectory()) {
					continue;
				}

				String name = jarEntry.getName();

				// TODO: Test which separator is used in these cases
				if (name.startsWith(pkgName.replace(URL_SEPARATOR_CHAR, File.separatorChar))) {
					Class<?> cls = loadClassFromPath(pkgName, name.substring(pkgName.length()), cl);

					if (cls != null && filter.include(cls)) {
						classes.add(cls);
					}
				}
			}
		} finally {
			try {
				jar.close();
			} catch (Exception e) {
			}
		}

		return classes;
	}

	public static boolean isExtensionOf(Class<?> cls, Class<?> spr) {
		if (cls == null) {
			return false;
		}

		Class<?> superClass = cls.getSuperclass();

		if (spr == null && superClass == null) {
			return true;
		}

		return spr.equals(superClass);
	}

	public static boolean isImplementationOf(Class<?> cls, Class<?>... interfaces) {
		if (cls == null) {
			return false;
		}

		Class<?>[] actualInterfaces = cls.getInterfaces();

		if (Arrays.isEmpty(interfaces) && Arrays.isEmpty(actualInterfaces)) {
			return true;
		}

		int matches = 0;

		for (Class<?> iface : interfaces) {
			if (Arrays.contains(actualInterfaces, iface)) {
				matches++;
			}
		}

		return matches == interfaces.length;
	}

	private static Class<?> loadClassFromPath(String path, String fileName, ClassLoader cl) throws IOException {
		Class<?> cls = null;

		int index = fileName.lastIndexOf(CLASS_FILE_EXTENSION);

		if (Arrays.INDEX_NOT_FOUND != index) {
			fileName = path + URL_SEPARATOR + fileName.substring(0, index);
			fileName = fileName.replace(URL_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);

			try {
				cls = cl.loadClass(fileName);
			} catch (ClassNotFoundException e) {
				throw new IOException(e);
			}
		}

		return cls;
	}
}