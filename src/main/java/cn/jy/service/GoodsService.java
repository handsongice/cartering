package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 11:48
 */
public interface GoodsService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Goods> getGoodsList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<GoodsCarousel> getCarouselList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<GoodsSpec> getSpecList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<GoodsStock> getStockList(Map<String, Object> params) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<GoodsParam> getParamList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param goods
     * @return
     */
    ResultMap addGoods(Goods goods) throws Exception;


    /**
     * 更新数据
     * @param goods
     * @return
     */
    ResultMap updateGoods(Goods goods) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    Goods getGoodsById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delGoods(Long id);
}
