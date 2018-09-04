package com.github.framework.util.file;

import org.springframework.util.FileCopyUtils;

import java.io.*;

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
}
