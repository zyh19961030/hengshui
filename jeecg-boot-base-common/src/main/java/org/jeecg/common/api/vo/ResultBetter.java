package org.jeecg.common.api.vo;

import java.io.Serializable;

import org.jeecg.common.constant.CommonConstant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *   接口返回数据格式
 */
@Data
@ApiModel(value="ResultBetter接口返回对象", description="ResultBetter接口返回对象")
public class ResultBetter<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	@ApiModelProperty(value = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@ApiModelProperty(value = "返回处理消息")
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	@ApiModelProperty(value = "返回代码")
	private Integer code = 0;

	/**
	 * 返回数据对象 data
	 */
	@ApiModelProperty(value = "返回数据对象")
	private T result;

	public ResultBetter() {

	}

    public ResultBetter(Integer ret, String retmsg) {
        this.code = ret;
        this.message = retmsg;
    }

    public ResultBetter(Integer ret, String retmsg, T data, boolean success) {
        this.code = ret;
        this.message = retmsg;
        this.result = data;
        this.success=success;
    }

    /**
	 * 时间戳
	 */
	@ApiModelProperty(value = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public void error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void success(String message) {
		this.message = message;
		//this.code = CommonConstant.SC_OK_200;
		this.success = true;
	}

	public static ResultBetter<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static ResultBetter<Object> error(int code, String msg) {
		ResultBetter<Object> r = new ResultBetter<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	public static ResultBetter<Object> ok() {
		ResultBetter<Object> r = new ResultBetter<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage("成功");
		return r;
	}
	
	public static ResultBetter<Object> ok(String msg) {
		ResultBetter<Object> r = new ResultBetter<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static ResultBetter<Object> ok(Object data) {
		ResultBetter<Object> r = new ResultBetter<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}

}