package com.github.framework.util.file;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 龙海仁
 * @create：2016年1月7日 下午4:38:52
 * @description：
 */
public class IOUtils {

	public static void copy(InputStream input, OutputStream output) throws IOException {
		copy(input, output, false);
	}

	public static void copy(InputStream input, OutputStream output, boolean isClose) throws IOException {
		int len = 0;
		byte[] buffer = new byte[8192];
		try {
			while ((len = input.read(buffer, 0, 8192)) != -1) {
				output.write(buffer, 0, len);
			}
			output.flush();
		} finally {
			if (isClose) {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// e.printStackTrace();
						// log.warn(e);
					}
				}
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						// e.printStackTrace();
						// log.warn(e);
					}
				}
			}
		}
	}

	public static void close(Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

}
