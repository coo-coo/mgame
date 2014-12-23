package com.coo.m.game.robot;

import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

/**
 * tuling机器人，参见http://www.tuling123.com/openapi
 * 
 * @author boqing.shen
 * 
 */
public class TulingHelper {

	private static final String URL = "http://www.tuling123.com/openapi/api";

	private static final String API_KEY = "dcdf19be8305fb9b35ff49c83a73275c";

	private static final String ADDRESS = URL + "?key=" + API_KEY
			+ "&info=";
	private static String ROBOT = "suzl";

	public static final String ERR_TEXT = "很抱歉," + ROBOT + "很忙,暂未获得Ta的应答";
	public static final String WELCOME_TEXT = "亲爱的您好,我是智能机器人" + ROBOT
			+ ",很高兴为您服务，您可以输入例如‘说个笑话’的句子来与我交流，愿您满意！";

	/**
	 * TODO 网络获取
	 */
	public static String[] TOPICS = new String[] { "讲个笑话", "冷笑话", "白羊座",
			"射手座", "双子座", "天蝎座", "双鱼座", "金牛座", "处女座", "巨蟹座", "A型血",
			"B型血", "O型血", "AB型血", "双11", "双12", "消磨", "南京大屠杀",
			"说个脑筋急转弯", "说个谜语", "马云的介绍", "李连杰的介绍", "马化腾的介绍",
			"李彦宏的介绍", "周鸿伟的介绍", "雷军的介绍" };

	public static String[] TOPICS_NEWS = new String[] { "新闻", "体育新闻",
			"科技新闻", "八卦新闻" };

	/**
	 * 随便选一个话题发送吧 TODO 热门话题...
	 * 
	 * @param handler
	 */
	public static void pickTopic(Handler handler) {
		int index = new Random().nextInt(TOPICS.length);
		sendMessage(TOPICS[index], handler);
	}
	
	public static void pickTopic(Handler handler,String topic) {
		sendMessage(topic, handler);
	}

	private static int R_NEWS_COUNT = 0;

	/**
	 * 获得新闻话题
	 * 
	 * @since 1.3
	 * @param handler
	 */
	public static void pickNews(Handler handler) {
		int index = R_NEWS_COUNT % TOPICS_NEWS.length;
		sendMessage(TOPICS_NEWS[index], handler);
		R_NEWS_COUNT++;
	}

	/**
	 * 发送异步请求,交由handler来实现
	 */
	public static void sendMessage(final String msg, Handler handler) {
		// 发送请求...
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<TulingResult> future = new FutureTask<TulingResult>(
				new Callable<TulingResult>() {
					@Override
					public TulingResult call()
							throws Exception {
						return doGetTulingResult(msg);
					}
				});
		executor.execute(future);

		// 获得结果,不设置执行超时时间取得结果
		TulingResult result = null;
		try {
			result = future.get(2000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
		}
		Message m = Message.obtain();
		m.obj = result;
		handler.sendMessage(m);

		// 关闭执行器
		executor.shutdown();
	}

	/**
	 * 根据请求信息获得TulingResult
	 */
	private static TulingResult doGetTulingResult(String msg) {
		TulingResult result = null;
		try {
			HttpClient client = new HttpClient();
			// 创建GET方法的实例
			String url = ADDRESS + URLEncoder.encode(msg, "UTF-8");
			GetMethod method = new GetMethod(url);
			// 缺省支持UTF-8
			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET,
					"UTF-8");
			client.executeMethod(method);
			String text = method.getResponseBodyAsString();
			Gson gson = new Gson();
			result = gson.fromJson(text, TulingResult.class);
			// 设置请求的消息
			result.setReqMsg(msg);
		} catch (Exception e) {
		}
		return result;
	}
}
