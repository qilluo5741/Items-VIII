package org.deepin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.deepin.Alipay.AlipayNotify;
import org.deepin.config.WeixinConfig;
import org.deepin.util.Log;
import org.deepin.weixin.MD5Util;
import org.deepin.weixin.WeixinResult;
import org.deepin.weixin.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("pay")
public class PayController {
	@Autowired
	private HttpServletRequest request;
	/**
	 * 微信支付
	 * @return
	 */
	@RequestMapping(value="weixinNotice",method=RequestMethod.POST)
	public @ResponseBody String wxNotice(){
		//接受传过来的信息 并转换成键值对的集合
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
		//验证签名
		if(weixinSign.equals(mySign)){//验证成功
			////////////////////////////////////可能需要的参数///////////////////////////////////////////
			//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
			//错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
			//总金额	total_fee	是	Int	100	订单总金额，单位为分
			//现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
			//微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
			//商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
			//商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
			//支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
			if(map.get("result_code").equals("SUCCESS")){
				//////////////////////////////////处理业务/////////////////////////////////////
				String out_trade_no=map.get("out_trade_no");//商户订单号
				String total_fee=map.get("total_fee");//订单金额
				  /////////////////////////////////////////////////////////////////////////////
				System.out.println(total_fee);
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no);
				outRes=new WeixinResult("SUCCESS","签名验证成功！");
			}
			else{
				Log.getInstance().debug("微信支付通知：错误代码："+map.get("err_code")+"错误代码描述："+map.get("err_code_des"));
				outRes=new WeixinResult("FAIL","业务结果验证为失败！");
			}
		}
		else{
			//验证签名失败！
			Log.getInstance().error("签名验证失败！");
			outRes=new WeixinResult("FAIL","签名验证失败！");
		}	
		//相应微信是否接受成功
		return WeixinUtil.toXml(outRes);
	}
	/**
	 * 支付宝支付
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="alipayNotice",method=RequestMethod.POST)
	public @ResponseBody String alipayNotice(ModelMap map) throws UnsupportedEncodingException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap(); 
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]:valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//订单金额
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数
		if(AlipayNotify.verify(params)){
			//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				System.out.println(out_trade_no+"测试订单号是否得到------------------------------------");
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				System.out.println(total_fee);
				//如果有做过处理，不执行商户的业务程序
				//注意：
				System.out.println(out_trade_no+"订单系统中查到该笔订单的详细----------------------------------");
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	@SuppressWarnings("unchecked")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//所有参与传参的参数按照accsii排序（升序） 		
		Iterator it = es.iterator(); 	
		while(it.hasNext()) { 		
			Map.Entry entry = (Map.Entry)it.next(); 	
			String k = (String)entry.getKey(); 		
			Object v = entry.getValue(); 		
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) { 
				sb.append(k + "=" + v + "&"); 	
				} 	
		} 		
		sb.append("key=" + WeixinConfig.APP_KEY); 	
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase(); 
		return sign; 	
	}
}