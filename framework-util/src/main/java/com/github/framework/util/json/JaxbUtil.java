package com.github.framework.util.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:46:14
 * @Description: xml与java对象转换工具类
 */
public class JaxbUtil {
	private static ConcurrentHashMap<Class<?>, JAXBContext> jaxbContextMap = new ConcurrentHashMap<Class<?>,JAXBContext>();
    
    /** 
     * 将JAXB实现对象转换成XML格式的字符串 
     * @param <T> 这里的类是具体类，不能是接口 
     * @param tclz 转换对象实例 
     * @return 
     */  
    public static <T> String marshToXmlBinding(Class<T> tclz, T t) throws JAXBException {
            JAXBContext jc = null;
            if(jaxbContextMap.get(tclz)==null) {  
                Map<String, String> properties = new HashMap<String, String>();
                jc = JAXBContext.newInstance(new Class<?>[]{tclz},properties);
                jaxbContextMap.put(tclz, jc);  
            }else{  
                jc = jaxbContextMap.get(tclz);  
            }  
              
            Marshaller u = jc.createMarshaller();
            // XML内容格式化  
            u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            u.marshal(t, sw);  
            return sw.toString();  
    }  
  
    /** 
     * 将XML格式的字符串转换成JAXB实现对象 
     * @param <T> 这里的类是具体类，不能是接口 
     * @param tclz 
     * @param xmlstr 
     * @return 
     */  
    @SuppressWarnings("unchecked")
    public static <T> T unmarshToObjBinding(Class<T> tclz, String xmlstr) {
        try {  
            JAXBContext jc = null;
            if(jaxbContextMap.get(tclz)==null) {  
                Map<String, String> properties = new HashMap<String, String>();
                jc = JAXBContext.newInstance(new Class<?>[]{tclz},properties);
                jaxbContextMap.put(tclz, jc);  
            }else{  
                jc = jaxbContextMap.get(tclz);  
            }  
              
            Unmarshaller un = jc.createUnmarshaller();
            return (T) un.unmarshal(new ByteArrayInputStream(xmlstr.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
        	
        } catch (JAXBException e) {
		}  
        return null;  
    }  
    
}  

