package cn.jy.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * LayUI默认分页封装参数
 *
 * @author zhanghongshun
 * @date 2018/10/24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LayUiPageParams<T> implements Serializable {
    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 信息提示
     */
    private String msg;
    /**
     * 总行数
     */
    private Long count;
    /**
     * 结果集
     */
    private List<T> data;

    public static LayUiPageParams defaultResult(Long count, List data) {
        LayUiPageParams layUiPageParams = new LayUiPageParams(0, "success", count, data);
        return layUiPageParams;
    }
}
