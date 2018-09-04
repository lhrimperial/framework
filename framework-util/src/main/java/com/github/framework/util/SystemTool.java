package com.github.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * 与系统相关的一些常用工具方法. 目前实现的有：获取MAC地址、IP地址、主机名
 * SystemTool
 * @author 龙海仁
 * @date 2016年5月18日上午10:31:16 
 */
public final class SystemTool {

	private static final Logger LOG = LoggerFactory.getLogger(SystemTool.class);
	
	private SystemTool() {
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 * getOSName
	 * @return String
	 * @since JDK1.6
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
	 * getUnixMACAddress
	 * @return mac地址
	 * @return String
	 * @since JDK1.6
	 */
	public static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡
																	// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
				if (index >= 0) {// 找到了
					mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
					break;
				}
			}
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				LOG.debug(e1.getMessage());
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	/**
	 * 获取widnows网卡的mac地址.
	 * getWindowsMACAddress
	 * @return mac地址
	 * @return String
	 * @since JDK1.6
	 */
	public static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical
																		// address]
				if (index >= 0) {// 找到了
					index = line.indexOf(':');// 寻找":"的位置
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// 取出mac地址并去除2边空格
					}
					break;
				}
			}
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				LOG.debug(e1.getMessage());
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}
	
	/** 
     * windows 7 专用 获取MAC地址 
     *  
     * @return 
     * @throws Exception
     */  
    public static String getWin7MACAddress() throws Exception {
          
        // 获取本地IP对象  
        InetAddress ia = InetAddress.getLocalHost();
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
  
        // 下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();
  
        for (int i = 0; i < mac.length; i++) {  
            if (i != 0) {  
                sb.append("-");  
            }  
            // mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);  
        }  
  
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();  
    } 

	/**
	 * 获得本机的主机名
	 * getHostName
	 * @return 本机的主机名
	 * @return String
	 * @since JDK1.6
	 */
	public static String getHostName() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LOG.debug(e.getMessage());
		}
		if (ia == null) {
			return "some error..";
		} else {
			return ia.getHostName();
		}
	}

	/**
	 * 获得本机的IP地址
	 * getIPAddress
	 * @return ip地址
	 * @return String
	 * @since JDK1.6
	 */
	public static String getIPAddress() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LOG.debug(e.getMessage());
		}
		if (ia == null) {
			return "some error..";
		} else {
			return ia.getHostAddress();
		}
	}

	/**
	 * 获取window或者unix的mac地址
	 * getMac
	 * @return Mac地址
	 * @return String
	 * @throws Exception
	 * @since JDK1.6
	 */
	public static String getMac() throws Exception {
		String os = getOSName();
		if (os.startsWith("windows 7")) {
		    return getWin7MACAddress();
		} else if (os.startsWith("windows")) {
			// 本地是windows
			return getWindowsMACAddress();
		} else {
			// 本地是非windows系统 一般就是unix
			return getUnixMACAddress();
		}
	}

	public static void main(String[] args) throws Exception {
	    getMac();
	}
}
