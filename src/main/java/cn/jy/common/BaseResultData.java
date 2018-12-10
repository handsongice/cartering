package cn.jy.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseResultData implements Serializable {
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回状态 200:正常 500:错误
     */
    private String status;
    /**
     * 返回数据
     */
    private Object data;

    public static BaseResultData result(String msg, String status, Object data) {
        return new BaseResultData(msg, status, data);
    }

    public static BaseResultData resultOK(Object data) {
        return new BaseResultData("ok", "200", data);
    }

    public static BaseResultData resultError(String msg, Object data) {
        return new BaseResultData(msg, "500", data);
    }
}
