package org.deepin.util;

public class Result {
	private int state;//状态
	private String msg;//状态
	private Object result;//结果集
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Result(int state, Object result) {
		this.state = state;
		this.result = result;
	}
	public Result() {
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Result(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}
	public Result(int state, String msg, Object result) {
		this.state = state;
		this.msg = msg;
		this.result = result;
	}
}
