package cn.jy.common;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class LayUiResultData implements Serializable {

    // 返回信息
    private String msg;
    // 返回状态 0:正常 其他:错误
    private Integer code;
    //
    private Integer count;
    // 返回数据
    private Object data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LayUiResultData() {
    }

    public LayUiResultData(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public LayUiResultData(String msg, Integer code, Integer count, Object data) {
        this.msg = msg;
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public static LayUiResultData result(String msg, Integer code, Object data) {
        return new LayUiResultData(msg, code, data);
    }

    public static LayUiResultData resultOK(List data) {
        return new LayUiResultData("", 0,data.size(), data);
    }

    public static LayUiResultData resultError(String msg, Object data) {
        return new LayUiResultData(msg, 500, data);
    }

    }
