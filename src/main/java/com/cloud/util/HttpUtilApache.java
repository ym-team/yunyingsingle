//package com.cloud.util;
//
//import java.io.IOException;
//
//import com.alibaba.fastjson.JSONObject;
//
//public class HttpUtilApache {
//
//	
//
///*
// *  POST请求
// *  参数是：URL，jsonObject(请求参数封装成json对象)
// * 
// * */
//public static String post(String url,JSONObject jsonObject) {
//	//创建HttpClients对象
//	CloseableHttpClient httpClient = HttpClients.createDefault();
//	//创建post请求对象
//	HttpPost httpPost = new HttpPost(url);
//	//创建封装请求参数对象，设置post请求参数
//	StringEntity myEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
//	httpPost.setEntity(myEntity);
//	try {
//		//执行POST请求，获取请求结果
//		HttpResponse httpResponse = httpClient.execute(httpPost);
//		if (httpResponse.getStatusLine().getStatusCode() == 200) {
//			// 发送成功
//			String resutlEntity = EntityUtils.toString(httpResponse.getEntity());
//			return resutlEntity;
//		} else {
//			// 发送失败
//			return null;
//		}
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}finally {
//		try {
//			if (httpClient != null) {
//				// 释放资源
//				httpClient.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	return null;
//}
//
//}
