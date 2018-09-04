package com.github.framework.util;

import com.github.framework.util.json.JsonUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 龙海仁
 * @date 2015年12月4日下午10:49:22
 * @Description: http调用工具类
 */
//@Component
public class RestTemplateClient implements InitializingBean {
	/**
	 * Rest 调用
	 */
	private RestTemplate restTemplate;

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * get调用
	 * @param url url
	 * @param typeReference 返回参数泛型类型
	 * @param urlVariables url参数
	 * @return
	 * @author 龙海仁
	 * @date 2015年7月6日
	 * @update 
	 */
	public <T> T getForObject(String url, TypeReference<T> typeReference, Object... urlVariables) {
		String json = restTemplate
				.getForObject(url, String.class, urlVariables);
		return JsonUtils.toObject(json, typeReference);
	}

	/**
	 * POST 请求
	 * @param url url
	 * @param request 请求参数对象
	 * @param typeReference 返回参数泛型类型
	 * @param urlVariables url参数
	 * @return
	 * @author 龙海仁
	 * @date 2015年7月6日
	 * @update 
	 */
	public <T> T postForObject(String url, Object request, TypeReference<T> typeReference, Object... urlVariables){
		String requestJson = JsonUtils.toJson(request);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String json = restTemplate.exchange(url, HttpMethod.POST, entity,
				String.class, urlVariables).getBody();
		return JsonUtils.toObject(json, typeReference);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
}
