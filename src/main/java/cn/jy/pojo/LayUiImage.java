package cn.jy.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * LayUiImage封装参数
 *
 * @author zhanghongshun
 * @date 2018/10/24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LayUiImage<T> implements Serializable {
    /**
     * 返回编码
     */
    private String src;
    /**
     * 信息提示
     */
    private String name;

}
