package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Enterprise;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface EnterpriseService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Enterprise> getEnterpriseList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param enterprise
     * @return
     */
    ResultMap addEnterprise(Enterprise enterprise) throws Exception;

    /**
     * 更新数据
     * @param enterprise
     * @return
     */
    ResultMap updateEnterprise(Enterprise enterprise);

    /**
     * 获取信息
     * @param id
     * @return
     */
    Enterprise getEnterpriseById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delEnterprise(Long id);
}
