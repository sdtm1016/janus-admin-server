//package com.clsaa.janus.admin.security;
//
//import com.google.common.base.Strings;
//import com.lianjia.confucius.baymax.web.result.JAssert;
//import com.lianjia.confucius.baymax.web.result.OnAssertFailed;
//import com.lianjia.confucius.janus.bizcode.GatewayBizCode;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.TreeMap;
//import java.util.concurrent.TimeUnit;
//
///**
// * @summary 接口签名的一些校验逻辑
// * @Copyright (c) 2016, Lianjia Group All Rights Reserved.
// */
//public final class SignatureVerifier {
//	private SignatureVerifier() {
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 * 时间戳的有效期
//	 */
//	private static final long timestampInvalidSpan = TimeUnit.MINUTES.toMillis(10);
//
//	/**
//	 * Token类型
//	 */
//	public static final String PARAM_GRANT_TYPE = "grant_type";
//
//	/**
//	 * 客户端
//	 */
//	public static final String PARAM_CLIENT_ID = "client_id";
//
//	/**
//	 * 客户端密钥
//	 */
//	public static final String PARAM_CLIENT_SECRET = "client_secret";
//
//	/**
//	 * 刷新token
//	 */
//	public static final String PARAM_REFRESH_TOKEN = "refresh_token";
//
//	/**
//	 * 随机码
//	 */
//	public static final String PARAM_NOUCE = "nouce";
//
//	/**
//	 * 时间戳参数
//	 */
//	public static final String PARAM_TIMESTAMP = "timestamp";
//
//	/**
//	 * 签名参数
//	 */
//	public static final String PARAM_SIGNATURE = "signature";
//
//	/**
//	 * 签名验证，验证不通过会抛出异常
//	 *
//	 * @param clientSecret
//	 * @param request
//	 */
//	public static void verify(String clientSecret, HttpServletRequest request, OnAssertFailed onAssertFailed) {
//
//		String requestURI = request.getRequestURI();
//
//		String requestMethod = request.getMethod().toUpperCase();
//
//		String paramSignature = request.getParameter(SignatureVerifier.PARAM_SIGNATURE);
//
//		long timestamp = Long.parseLong(request.getParameter(SignatureVerifier.PARAM_TIMESTAMP));
//
//		long dif = System.currentTimeMillis() - timestamp;
//
//		// 验证请求是否在时间允许范围内
//		if (dif < TimeUnit.MINUTES.toMillis(-5)) {
//			JAssert.justInvalidParam(GatewayBizCode.INVALID_TIMESTAMP.getCode(), "客户端比服务端时钟快，超过5MIN，请先同步时钟");
//		} else if (dif > timestampInvalidSpan) {
//			JAssert.justInvalidParam(GatewayBizCode.INVALID_TIMESTAMP.getCode(), "请求已过时或客户端时钟过慢，超过10MIN，请先同步时钟");
//		}
//
//		// 参数按字典顺序排序
//		Map<String, String[]> sortedMap = new TreeMap<>(request.getParameterMap());
//		// 参数字符串
//		StringBuilder paramStr = new StringBuilder(sortedMap.size() * 20);
//		paramStr.append(requestURI);
//		paramStr.append(requestMethod);
//		for (Entry<String, String[]> pair : sortedMap.entrySet()) {
//			// 签名参数不参与计算
//			if (PARAM_SIGNATURE.equals(pair.getKey())) {
//				continue;
//			}
//			String[] values = pair.getValue();
//			// 跳过空字符串（null or empty)
//			paramStr.append(pair.getKey()).append('=');
//			if (values == null || values.length == 0) {
//				continue;
//			}
//			// 如果参数为null，则设置为""
//			for (int i = 0; i < values.length; i++) {
//				paramStr.append(Strings.nullToEmpty(values[i]));
//				if ((i + 1) != (values.length)) {
//					paramStr.append(",");
//				}
//			}
//			paramStr.append("&");
//		}
//		// 删除掉最后一个&字符
//		String data = paramStr.substring(0, paramStr.length() - 1);
//
//		// 根据secret生成签名sign = BASE64URL(HmacSHA256(client_secret,(URI+METHOD+DIC_SORT(params)))
//		CryptoResult hmacResult = HMAC.SHA256(clientSecret, data);
//		// 判断hmac摘要是否成功
//		JAssert.allowed(hmacResult.isOK(), GatewayBizCode.INVALID_SIGNATURE.getCode(), "验证签名-HMAC摘要生成失败");
//		// 判断签名是否一致
//		JAssert.validParam(paramSignature.equals(hmacResult.getContent()), GatewayBizCode.INVALID_SIGNATURE.getCode(),
//			"签名不一致", onAssertFailed);
//
//	}
//}
