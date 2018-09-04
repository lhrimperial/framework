package com.github.framework.util.image;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * ImageUtils
 * 
 * @author 龙海仁
 * @create：2016年5月11日 下午10:13:07
 */
public class ImageUtils {

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imageUrl
	 * @return
	 * @author 龙海仁
	 * @date 2016年5月11日下午10:15:56
	 * @update
	 */
	@SuppressWarnings("restriction")
	public static String encodeImgageToBase64(URL imageUrl) {
		ByteArrayOutputStream outputStream = null;
		BufferedImage bufferedImage = null;
		String imageStr = null;
		try {
			bufferedImage = ImageIO.read(imageUrl);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			// 返回Base64编码过的字节数组字符串
			imageStr = encoder.encode(outputStream.toByteArray());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		return imageStr;
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @param imageFile
	 * @return
	 * @author 龙海仁
	 * @date 2016年5月11日下午10:26:10
	 * @update 
	 */
	@SuppressWarnings("restriction")
	public static String encodeImgageToBase64(File imageFile) {
		ByteArrayOutputStream outputStream = null;
		String imageStr = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			// 返回Base64编码过的字节数组字符串
			imageStr = encoder.encode(outputStream.toByteArray());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageStr;
	}
	/**
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 * @param base64
	 * @param path
	 * @param imgName
	 * @author 龙海仁
	 * @date 2016年5月11日下午10:30:04
	 * @update 
	 */
	@SuppressWarnings("restriction")
	public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {
		FileOutputStream write = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			write = new FileOutputStream(new File(path
					+ imgName));
			
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("restriction")
	public static InputStream decodeBase64ToInputStream(String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			return new ByteArrayInputStream(decoderBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 将图片转成base64字符串
     * @param image
     * @param format
     * @return
     * @author 龙海仁
     * @date 2016年6月10日上午9:24:05
     * @update 
     */
    public static String encodeToBase64(BufferedImage image, String format) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(image, format, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	 try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return imageString;
    }
}
