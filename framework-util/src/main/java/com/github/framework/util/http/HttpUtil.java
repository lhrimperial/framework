package com.github.framework.util.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: HttpUtil
 * @Description:
 * @author hairen.long@hoau.net
 * @date 2015年5月23日 下午5:50:49
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String sendPost(String path, String json) throws IOException,
            Exception {
		HttpURLConnection conn = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		StringBuilder resultStr = null;
		try {
			URL url = new URL(path);
			logger.info("request URL:" + url.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(30000);// 连接超时 单位毫秒
			conn.setReadTimeout(30000);// 读取超时 单位毫秒

			conn.setDoOutput(true);// 是否输入参数
			conn.setDoInput(true);

			conn.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			conn.setRequestProperty("accept", "*/*");

			printWriter = new PrintWriter(conn.getOutputStream());
			printWriter.write(json);
			printWriter.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				logger.info(path + " Error===" + responseCode);
			} else {
				logger.info(path + " Post Success!");
			}
			bufferedReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			resultStr = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				resultStr.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求异常");
		} finally {
			conn.disconnect();
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resultStr.toString();
	}

	public static String httpGet(String path) throws IOException {
		StringBuilder result = null;
		BufferedReader buffer = null;
		HttpURLConnection connet = null;
		try {
			URL url = new URL(path);
			connet = (HttpURLConnection) url.openConnection();
			connet.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			connet.setRequestProperty("accept", "*/*");

			connet.connect();
			if (connet.getResponseCode() > 300) {
				logger.info(connet.getResponseCode() + " "
						+ connet.getResponseMessage());
				throw new RuntimeException("请求异常");
			}
			buffer = new BufferedReader(new InputStreamReader(
					connet.getInputStream()));
			result = new StringBuilder();
			String line;

			while ((line = buffer.readLine()) != null) {
				line = new String(line.getBytes(), "utf-8");
				result.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException("请求异常");
		} finally {
			if (buffer != null) {
				buffer.close();
			}
			if (connet != null) {
				connet.disconnect();
			}
		}

		return result.toString();
	}

	public static void httpGetMedia(String url, String path, String fileName) {
		// 根据地址获取数据字节流
		HttpURLConnection conn = null;
		InputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			URL url_ = new URL(url);
			conn = (HttpURLConnection) url_.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.connect();
			// 通过输入流获取图片数据
			inStream = conn.getInputStream();
			
			// 输出的文件流
			File sf = new File(path);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			// 图片输出流
			outStream = new FileOutputStream(sf.getPath() + File.separator + fileName);

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}

			outStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (outStream != null) {
					outStream.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeStreamToFile(InputStream inStream, String path, String fileName) {
		// 根据地址获取数据字节流
		FileOutputStream outStream = null;
		try {
			// 输出的文件流
			File sf = new File(path);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			// 图片输出流
			outStream = new FileOutputStream(sf.getPath() + File.separator + fileName);

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}

			outStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
				if (outStream != null) {
					outStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static InputStream httpGetMedia(String url) {
		// 根据地址获取数据字节流
		HttpURLConnection conn = null;
		InputStream inStream = null;
		try {
			URL url_ = new URL(url);
			conn = (HttpURLConnection) url_.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.connect();
			// 通过输入流获取图片数据
			inStream = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接，流也被截断
			/*if (conn != null) {
				conn.disconnect();
			}*/
		}
		return inStream;
	}

}
