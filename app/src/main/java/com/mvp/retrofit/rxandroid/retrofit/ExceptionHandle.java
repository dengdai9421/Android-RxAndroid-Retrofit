package com.mvp.retrofit.rxandroid.retrofit;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;



public class ExceptionHandle {
	private static final int UNAUTHORIZED = 401;
	private static final int FORBIDDEN = 403;
	private static final int NOT_FOUND = 404;
	private static final int REQUEST_TIMEOUT = 408;
	private static final int INTERNAL_SERVER_ERROR = 500;
	private static final int BAD_GATEWAY = 502;
	private static final int SERVICE_UNAVAILABLE = 503;
	private static final int GATEWAY_TIMEOUT = 504;
	private static final String SOCKET_TIMEOUT_EXCEPTION = "服务器响应超时，稍后重试";
	private static final String CONNECT_EXCEPTION = "网络连接异常，稍后重试";
	private static final String UNKNOWN_HOST_EXCEPTION = "网络错误，稍后重试";
	private static final String JSON_PARSE_EXCEPTION = "数据解析出错";
	private static final String HTTP_EXCEPTION = "服务器异常,请稍后重试";
	private static final String UNKNOWN_EXCEPTION = "未知异常";
	private static final String SSL_EXCEPTION = "证书验证失败";


	public static ResponeThrowable handleException(Throwable e) {
		ResponeThrowable ex;
		if (e instanceof HttpException) {
			HttpException httpException = (HttpException) e;
			ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
			switch (httpException.code()) {
				case UNAUTHORIZED:
				case FORBIDDEN:
				case NOT_FOUND:
				case REQUEST_TIMEOUT:
				case GATEWAY_TIMEOUT:
				case INTERNAL_SERVER_ERROR:
				case BAD_GATEWAY:
				case SERVICE_UNAVAILABLE:
				default:
					ex.message = HTTP_EXCEPTION;
					break;
			}
			return ex;
		} else if (e instanceof SocketTimeoutException) {
			SocketTimeoutException socketTimeoutException = (SocketTimeoutException) e;
			ex = new ResponeThrowable(socketTimeoutException, ERROR.SOCKET_TIME_OUT_ERROR);
			ex.message = SOCKET_TIMEOUT_EXCEPTION;
			return ex;
		} else if (e instanceof ConnectException) {
			ConnectException connectException = (ConnectException) e;
			ex = new ResponeThrowable(connectException, ERROR.CONNECT_ERROR);
			ex.message = CONNECT_EXCEPTION;
			return ex;
		} else if (e instanceof UnknownHostException) {
			UnknownHostException unknownHostException = (UnknownHostException) e;
			ex = new ResponeThrowable(unknownHostException, ERROR.UNKNOW_HOST_ERROR);
			ex.message = UNKNOWN_HOST_EXCEPTION;
			return ex;
		} else if (e instanceof RuntimeException) {
			RuntimeException resultException = (RuntimeException) e;
			ex = new ResponeThrowable(resultException, ERROR.RUN_TIME_ERROR);
			ex.message = resultException.getCause().getMessage();
			return ex;
		} else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
			ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
			ex.message = JSON_PARSE_EXCEPTION;
			return ex;
		} else if (e instanceof ConnectException) {
			ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
			ex.message = CONNECT_EXCEPTION;
			return ex;
		} else if (e instanceof javax.net.ssl.SSLHandshakeException) {
			ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
			ex.message = SSL_EXCEPTION;
			return ex;
		} else {
			ex = new ResponeThrowable(e, ERROR.UNKNOWN);
			ex.message = UNKNOWN_EXCEPTION;
			return ex;
		}
	}

	/**
	 * 约定异常
	 */
	class ERROR {
		/**
		 * 未知错误
		 */
		public static final int UNKNOWN = 1000;
		/**
		 * 解析错误
		 */
		public static final int PARSE_ERROR = 1001;
		/**
		 * 网络错误
		 */
		public static final int NETWORD_ERROR = 1002;
		/**
		 * 协议出错
		 */
		public static final int HTTP_ERROR = 1003;

		/**
		 * 证书出错
		 */
		public static final int SSL_ERROR = 1005;
		/**
		 * 连接超时错误
		 */
		public static final int SOCKET_TIME_OUT_ERROR = 1006;
		/**
		 * 连接错误
		 */
		public static final int CONNECT_ERROR = 1007;
		/**
		 * 位置host错误
		 */

		public static final int UNKNOW_HOST_ERROR = 1008;
		/**
		 * 运行异常 -程序内部异常
		 */
		public static final int RUN_TIME_ERROR = 1009;

	}

	public static class ResponeThrowable extends Exception {
		public int code;
		public String message;

		public ResponeThrowable(Throwable throwable, int code) {
			super(throwable);
			this.code = code;
		}
	}


}
