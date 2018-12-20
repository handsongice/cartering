package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.CookStyle;
import cn.jy.entity.Format;
import cn.jy.entity.Store;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface StoreService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Store> getStoreList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<CookStyle> getCookStyleList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Format> getFormatList(Map<String, Object> params) throws Exception;
    /**
     * 插入数据
     * @param store
     * @return
     */
    ResultMap addStore(Store store) throws Exception;

    /**
     * 更新数据
     * @param store
     * @return
     */
    ResultMap updateStore(Store store);

    /**
     * 获取信息
     * @param id
     * @return
     */
    Store getStoreById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delStore(Long id);

}
