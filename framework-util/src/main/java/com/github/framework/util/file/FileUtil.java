package com.github.framework.util.file;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* @ClassName: FileUtil
* @Description:
* @author hairen.long@hoau.net
* @date 2015年6月19日 上午8:34:55
*/
public class FileUtil {
	public static void createFileIfAbsent(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			File dir = file.getParentFile();
			if(dir != null) {
				createDirIfAbsent(dir.getPath());
			}

			try {
				file.createNewFile();
			} catch (IOException var4) {
				throw new IllegalStateException(String.format("创建文件[%s]出错：%s", new Object[]{filePath, var4.getMessage()}), var4);
			}

			if(!file.exists()) {
				throw new IllegalStateException(String.format("创建文件[%s]失败，可能对该目录无写权限", new Object[]{dir.getPath()}));
			}
		}

		if(file.isDirectory()) {
			throw new IllegalArgumentException(String.format("已存在目录[%s]，无法创建相同名称的文件", new Object[]{filePath}));
		}
	}

	public static void createDirIfAbsent(String dirPath) {
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
			if(!dir.exists()) {
				throw new IllegalStateException(String.format("创建目录[%s]失败，可能对该目录无写权限", new Object[]{dirPath}));
			}
		}

		if(dir.isFile()) {
			throw new IllegalArgumentException(String.format("已存在文件[%s]，无法创建相同名称的目录", new Object[]{dirPath}));
		}
	}
	/**
	 * @Title: deleteAllFile
	 * @Description:删除文件路径下所有文件和子目录下的文件
	 * @param folderFullPath
	 * @return
	 */
	public static boolean deleteAllFile(String folderFullPath) {
		boolean ret = false;
		File file = new File(folderFullPath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					String filePath = fileList[i].getPath();
					deleteAllFile(filePath);
				}
			}
			if (file.isFile()) {
				file.delete();
			}
		}
		return ret;
	}
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dirPath 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(String dirPath) {
    	File dir = new File(dirPath);
    	if(!dir.exists()){
    		throw new RuntimeException("file is not exists");
    	}
        if (dir.isDirectory()) {
        	File[] fileList = dir.listFiles();
//　　　　　 递归删除目录中的子目录下
            for (int i=0; i<fileList.length; i++) {
                boolean success = deleteDir(fileList[i].getPath());
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

	/**
	 * @Title: getBytes
	 * @Description:根据文件路径获取byte数组
	 * @param filePath
	 * @return
	 */
	public static byte[] getBytes(String filePath) {
		byte[] bytes = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			bytes = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	
	/**
	 * @Title: saveByteArrayToFile
	 * @Description:将byte数组写入文件
	 * @param bfile
	 * @param filePath
	 * @param fileName
	 */
	public static void saveByteArrayToFile(byte[] bfile, String filePath,
			String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			// 判断文件目录是否存在
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 写文件
	 * @param in 写入文件流
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(InputStream in, String filePath, String fileName){
		File file = createFile(filePath,fileName);
		try {
			FileOutputStream out = new FileOutputStream(file);
			FileCopyUtils.copy(in, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件复制
	 * @param in
	 * @param out
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(File in, File out){
		try {
			FileCopyUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件复制
	 * @param in
	 * @param filePath
	 * @param fileName
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(File in, String filePath, String fileName){
		File out = createFile(filePath,fileName);
		copyFile(in,out);
	}
	/**
	 * 创建文件路径
	 * @param filePath 文件目录
	 * @param fileName 文件名
	 * @return
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static File createFile(String filePath, String fileName){
		File dir = new File(filePath);
		// 判断文件目录是否存在
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if(!filePath.endsWith(File.separator)){
			filePath = filePath + File.separator;
		}
		File file = new File(filePath + fileName);
		return file;
	}
	
	public static void createFolder(String folderPath){
		File dir = new File(folderPath);
		// 判断文件目录是否存在
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * 如果目录不存在创建目录
	 * @param path
	 * @update 
	 */
	public static void existDri(String path) {
		File dir = new File(path);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * 将输入流对象转成文件对象
	 * @param in
	 * @return
	 * @throws IOException
	 * @author 龙海仁
	 * @date 2016年6月10日上午11:48:09
	 * @update 
	 */
	public static File stream2file (InputStream in) throws IOException {

	    final File tempFile = File.createTempFile("stream2file", ".tmp");
	    tempFile.deleteOnExit();

	    try {
	    	FileOutputStream out = new FileOutputStream(tempFile);
	        IOUtils.copy(in, out);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }

	    return tempFile;            
	}


	public static void main(String[] args) {
		InputStreamReader ir = null;
		BufferedReader br = null;
		File file = new File("E:/上海资信/明文-2022-10-20/X36070100002942022091531000010.txt");
		try {
			ir = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
			br = new BufferedReader(ir);
			String name = file.getName();
			if (name.substring(22, 25).equals("310")) {
				bassgmt(br);
			}
			if (name.substring(22, 25).equals("410")) {
				acctsgmt(br);
			}
			if (name.substring(22, 25).equals("610") ) {
				fn(br);
			}
			if (name.substring(22, 25).equals("510")) {
				mot(br);
			}
			if (name.substring(22, 25).equals("420")) {
				ctrt(br);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void bassgmt(BufferedReader br) {
		String line;
		int count = 1;

		Map<String, String> map = new HashMap<>();
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();
		Map<String, String> map3 = new HashMap<>();
		Map<String, String> map4 = new HashMap<>();
		Map<String, String> map5 = new HashMap<>();
		Map<String, String> map6 = new HashMap<>();
		Map<String, String> map7 = new HashMap<>();
		Map<String, String> map8 = new HashMap<>();
		Map<String, String> map9 = new HashMap<>();
		Map<String, String> map10 = new HashMap<>();
		Map<String, String> map11= new HashMap<>();
		Map<String, String> map12= new HashMap<>();
		try {
			while ((line = br.readLine()) != null) {


				if (count>1) {
					//310  企业名称 大型
					if ("2".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  企业名称 中型
					if ("3".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map1.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  企业名称 小型
					if ("4".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map2.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  企业名称 微型
					if ("5".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map3.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  企业名称 无需
					if ("9".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map4.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  企业名称 未知
					if ("X".equals(line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()))) {
						map12.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EntScale>"),line.indexOf("</EntScale>")).substring("<EntScale>".length()));
					}
					//310  经济类型不为其他900
					if ("900".equals(line.substring(line.indexOf("<EcoType>"),line.indexOf("</EcoType>")).substring("<EcoType>".length()))) {
						map5.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EcoType>"),line.indexOf("</EcoType>")).substring("<EcoType>".length()));
					}
					//310  小类
					if (line.substring(line.indexOf("<EcoIndusCate>"),line.indexOf("</EcoIndusCate>")).substring("<EcoIndusCate>".length()).length() == 4) {
						map6.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<EcoIndusCate>"),line.indexOf("</EcoIndusCate>")).substring("<EcoIndusCate>".length()));
					}
					//310  客户资料
					if ("X".equals(line.substring(line.indexOf("<CustomerType>"),line.indexOf("</CustomerType>")).substring("<CustomerType>".length()))) {
						map7.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<CustomerType>"),line.indexOf("</CustomerType>")).substring("<CustomerType>".length()));
					}
//					//310  主要组成人员
					if ("X".equals(line.substring(line.indexOf("<MmbIDType>"),line.indexOf("</MmbIDType>")).substring("<MmbIDType>".length()))) {
						map8.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<MmbIDType>"),line.indexOf("</MmbIDType>")).substring("<MmbIDType>".length()));
					}
					//310  出资人
//					if ("X".equals(line.substring(line.indexOf("<SharHodIDType>"),line.indexOf("</SharHodIDType>")).substring("<SharHodIDType>".length()))) {
//						map9.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
//								line.substring(line.indexOf("<SharHodIDType>"),line.indexOf("</SharHodIDType>")).substring("<SharHodIDType>".length()));
//					}
					//310  实际控制人
//					String substring = line.substring(line.indexOf("<ActuCotrlIDType>"), line.indexOf("</ActuCotrlIDType>")).substring("<ActuCotrlIDType>".length());
//					if ("x".equals(line.substring(line.indexOf("<ActuCotrlIDType>"),line.indexOf("</ActuCotrlIDType>")).substring("<ActuCotrlIDType>".length()))) {
//						map10.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
//								line.substring(line.indexOf("<ActuCotrlIDType>"),line.indexOf("</ActuCotrlIDType>")).substring("<ActuCotrlIDType>".length()));
//					}
					//310  有效新政区划
					if (!"".equals(line.substring(line.indexOf("<AdmDivOfReg>"),line.indexOf("</AdmDivOfReg>")).substring("<AdmDivOfReg>".length()))) {
						map11.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<AdmDivOfReg>"),line.indexOf("</AdmDivOfReg>")).substring("<AdmDivOfReg>".length()));
					}
					count++;
				}

				count++;

			}
			System.out.println(map.size());
			System.out.println(map1.size());
			System.out.println(map2.size());
			System.out.println(map3.size());
			System.out.println(map4.size());
			System.out.println(map5.size());
			System.out.println(map6.size());
			System.out.println(map7.size());
			System.out.println(map8.size());
			System.out.println(map9.size());
			System.out.println(map10.size());
			System.out.println(map11.size());
			System.out.println(map12.size());
		} catch (Exception e) {

		}
	}

	public static void fn(BufferedReader br) {
		String line;
		int count = 1;

		Map<String, String> map = new HashMap<>();
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();

		try {
			while ((line = br.readLine()) != null) {


				if (count>1) {
					//610  资产负债
					if ("610".equals(line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()))) {
						map.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()));
					}
					//620  企业名称 中型
					if ("620".equals(line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()))) {
						map1.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()));
					}
					//630  企业名称 小型
					if ("630".equals(line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()))) {
						map2.put(line.substring(line.indexOf("<EntName>"),line.indexOf("</EntName>")).substring("<EntName>".length()),
								line.substring(line.indexOf("<InfRecType>"),line.indexOf("</InfRecType>")).substring("<InfRecType>".length()));
					}

					count++;
				}

				count++;

			}
			System.out.println(map.size());
			System.out.println(map1.size());
			System.out.println(map2.size());

		} catch (Exception e) {

		}
	}

	public static void acctsgmt(BufferedReader br) {
		String line;
		int count = 1;

		Map<String, String> map = new HashMap<>();
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();

		try {
			while ((line = br.readLine()) != null) {


				if (count>1) {
					//410 未结清
					map2.put(line.substring(line.indexOf("<AcctCode>"),line.indexOf("</AcctCode>")).substring("<AcctCode>".length()),
							line.substring(line.indexOf("<AcctBal>"),line.indexOf("</AcctBal>")).substring("<AcctBal>".length()));
					if ("10".equals(line.substring(line.indexOf("<AcctStatus>"),line.indexOf("</AcctStatus>")).substring("<AcctStatus>".length()))) {
						map.put(line.substring(line.indexOf("<AcctCode>"),line.indexOf("</AcctCode>")).substring("<AcctCode>".length()),
								line.substring(line.indexOf("<AcctBal>"),line.indexOf("</AcctBal>")).substring("<AcctBal>".length()));
					} else {
						map1.put(line.substring(line.indexOf("<AcctCode>"),line.indexOf("</AcctCode>")).substring("<AcctCode>".length()),
								line.substring(line.indexOf("<AcctBal>"),line.indexOf("</AcctBal>")).substring("<AcctBal>".length()));
					}
					count++;
				}

				count++;

			}
			long bal = 0;
			for (Map.Entry<String, String> entry:map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Integer integer = Integer.valueOf(value);
				if (!map1.containsKey(key)) {
					bal+=integer;
					System.out.println(bal);
				}

			}
			System.out.println(bal);
			System.out.println(map.size());
			System.out.println(map1.size());
			System.out.println(map2.size());

		} catch (Exception e) {

		}
	}

	public static void mot(BufferedReader br) {
		String line;
		int count = 1;

		Map<String, String> map = new HashMap<>();
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();

		try {
			while ((line = br.readLine()) != null) {


				if (count>1) {
					//510 有效抵押合同
					if ("1".equals(line.substring(line.indexOf("<GuarType>"),line.indexOf("</GuarType>")).substring("<GuarType>".length()))
					&& "10".equals(line.substring(line.indexOf("<RptDateCode>"),line.indexOf("</RptDateCode>")).substring("<RptDateCode>".length()))) {
						map.put(line.substring(line.indexOf("<CcCode>"),line.indexOf("</CcCode>")).substring("<CcCode>".length()),
								line.substring(line.indexOf("<CcAmt>"),line.indexOf("</CcAmt>")).substring("<CcAmt>".length()));
					} else if ("2".equals(line.substring(line.indexOf("<GuarType>"),line.indexOf("</GuarType>")).substring("<GuarType>".length()))
							&& "10".equals(line.substring(line.indexOf("<RptDateCode>"),line.indexOf("</RptDateCode>")).substring("<RptDateCode>".length()))) {
						map1.put(line.substring(line.indexOf("<CcCode>"),line.indexOf("</CcCode>")).substring("<CcCode>".length()),
								line.substring(line.indexOf("<CcAmt>"),line.indexOf("</CcAmt>")).substring("<CcAmt>".length()));
					}
					count++;
				}

				count++;

			}
			int diyaAmt = 0;
			int zyaAmt = 0;
//			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Map.Entry<String, String> entry:map.entrySet()) {
				String value = entry.getValue();
				Integer integer = Integer.valueOf(value);
				diyaAmt+=integer;
			}
			for (Map.Entry<String, String> entry:map1.entrySet()) {
				String value = entry.getValue();
				Integer integer = Integer.valueOf(value);
				zyaAmt+=integer;
			}
			System.out.println(map.size());
			System.out.println(map1.size());
			System.out.println(diyaAmt);
			System.out.println(zyaAmt);

		} catch (Exception e) {

		}
	}

	public static void ctrt(BufferedReader br) {
		String line;
		int count = 1;

		Map<String, String> map = new HashMap<>();
		Map<String, String> map1 = new HashMap<>();
		Map<String, String> map2 = new HashMap<>();

		try {
			while ((line = br.readLine()) != null) {


				if (count>1) {
					//510 有效抵押合同
					if ("1".equals(line.substring(line.indexOf("<GuarType>"),line.indexOf("</GuarType>")).substring("<GuarType>".length()))
							&& "10".equals(line.substring(line.indexOf("<RptDateCode>"),line.indexOf("</RptDateCode>")).substring("<RptDateCode>".length()))) {
						map.put(line.substring(line.indexOf("<CcCode>"),line.indexOf("</CcCode>")).substring("<CcCode>".length()),
								line.substring(line.indexOf("<CcAmt>"),line.indexOf("</CcAmt>")).substring("<CcAmt>".length()));
					} else if ("2".equals(line.substring(line.indexOf("<GuarType>"),line.indexOf("</GuarType>")).substring("<GuarType>".length()))
							&& "10".equals(line.substring(line.indexOf("<RptDateCode>"),line.indexOf("</RptDateCode>")).substring("<RptDateCode>".length()))) {
						map1.put(line.substring(line.indexOf("<CcCode>"),line.indexOf("</CcCode>")).substring("<CcCode>".length()),
								line.substring(line.indexOf("<CcAmt>"),line.indexOf("</CcAmt>")).substring("<CcAmt>".length()));
					}
					count++;
				}

				count++;

			}
			int diyaAmt = 0;
			int zyaAmt = 0;
//			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Map.Entry<String, String> entry:map.entrySet()) {
				String value = entry.getValue();
				Integer integer = Integer.valueOf(value);
				diyaAmt+=integer;
			}
			for (Map.Entry<String, String> entry:map1.entrySet()) {
				String value = entry.getValue();
				Integer integer = Integer.valueOf(value);
				zyaAmt+=integer;
			}
			System.out.println(map.size());
			System.out.println(map1.size());
			System.out.println(diyaAmt);
			System.out.println(zyaAmt);

		} catch (Exception e) {

		}
	}
}

