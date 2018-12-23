package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.*;
import cn.jy.mapper.*;
import cn.jy.service.FoodService;
import cn.jy.service.GoodsService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    FoodTypeMapper foodTypeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsCarouselMapper goodsCarouselMapper;

    @Autowired
    GoodsSpecMapper goodsSpecMapper;

    @Autowired
    GoodsStockMapper goodsStockMapper;

    @Autowired
    GoodsParamMapper goodsParamMapper;

    @Override
    public List<Goods> getGoodsList(Map<String, Object> params) throws Exception {
        List<Goods> goods = goodsMapper.selectByParams(params);
        return goods;
    }

    @Override
    public List<GoodsCarousel> getCarouselList(Map<String, Object> params) throws Exception {
        List<GoodsCarousel> goodsCarousels = goodsCarouselMapper.selectByParams(params);
        return goodsCarousels;
    }

    @Override
    public List<GoodsSpec> getSpecList(Map<String, Object> params) throws Exception {
        List<GoodsSpec> goodsSpecs = goodsSpecMapper.selectByParams(params);
        return goodsSpecs;
    }

    @Override
    public List<GoodsStock> getStockList(Map<String, Object> params) throws Exception {
        List<GoodsStock> goodsStocks = goodsStockMapper.selectByParams(params);
        return goodsStocks;
    }

    @Override
    public List<GoodsParam> getParamList(Map<String, Object> params) throws Exception {
        List<GoodsParam> goodsParams = goodsParamMapper.selectByParams(params);
        return goodsParams;
    }

    @Override
    public ResultMap addGoods(Goods goods) throws Exception {
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }


    @Override
    public ResultMap updateGoods(Goods goods) throws Exception {
        try {
            goods.setUpdateTime(new Date());
            int dbResult = goodsMapper.updateByPrimaryKeySelective(goods);
            if(dbResult <=0 ){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
            //插入轮播
            goodsCarouselMapper.deleteByGoodsId(goods.getId());
            JSONArray carousels = JSONArray.parseArray(goods.getCarousels());
            if(null != carousels) {
                for (int i=0;i<carousels.size();i++) {
                    GoodsCarousel goodsCarousel = new GoodsCarousel();
                    goodsCarousel.setGoodsId(goods.getId());
                    goodsCarousel.setPic(carousels.getJSONObject(i).get("filepath").toString());
                    goodsCarousel.setIndex(carousels.getJSONObject(i).get("index").toString());
                    int dbResult1 = goodsCarouselMapper.insertSelective(goodsCarousel);
                    if(dbResult1 <=0){
                        throw new RuntimeException(Constent.ERROR_FOOD_1);
                    }
                }
            }
            //插入规格
            goodsSpecMapper.deleteByGoodsId(goods.getId());
            JSONArray specs = JSONArray.parseArray(goods.getSpecs());
            if(null != specs) {
                for (int i=0;i<specs.size();i++) {
                    GoodsSpec goodsSpec = new GoodsSpec();
                    goodsSpec.setGoodsId(goods.getId());
                    goodsSpec.setName(specs.getJSONObject(i).get("name").toString());
                    goodsSpec.setPic(specs.getJSONObject(i).get("pic").toString());
                    goodsSpec.setKey(specs.getJSONObject(i).get("key").toString());
                    goodsSpec.setCreateTime(new Date());
                    int dbResult2 = goodsSpecMapper.insertSelective(goodsSpec);
                    if(dbResult2 <=0){
                        throw new RuntimeException(Constent.ERROR_FOOD_2);
                    }
                }
            }
            //插入库存
            goodsStockMapper.deleteByGoodsId(goods.getId());
            JSONArray stocks = JSONArray.parseArray(goods.getStocks());
            if(null != stocks) {
                for (int i=0;i<stocks.size();i++) {
                    String skeystr = stocks.getJSONObject(i).get("skeys").toString();
                    JSONArray skeys = JSONArray.parseArray(skeystr);
                    List<String> specIds = new ArrayList<>();
                    List<String> skeysList = new ArrayList<>();
                    for (int j=0;j<skeys.size();j++) {
                        skeysList.add(skeys.get(j).toString());
                        String[] sa = skeys.get(j).toString().split(":");
                        Map<String, Object> params = new HashMap<>();
                        params.put("food_id",goods.getId());
                        params.put("name",sa[0]);
                        params.put("key",sa[1]);
                        List<GoodsSpec> goodsSpecs = goodsSpecMapper.selectByParams(params);
                        if(null != goodsSpecs && goodsSpecs.size() > 0) {
                            specIds.add(String.valueOf(goodsSpecs.get(0).getId()));
                        }
                    }
                    GoodsStock goodsStock = new GoodsStock();
                    goodsStock.setGoodsId(goods.getId());
                    goodsStock.setGoodsSpecIds(StringUtils.join(specIds,","));
                    goodsStock.setSkeys(StringUtils.join(skeysList,","));
                    goodsStock.setGoodsSpecNames(stocks.getJSONObject(i).get("keystr").toString());
                    goodsStock.setPrice(stocks.getJSONObject(i).getBigDecimal("price"));
                    goodsStock.setNum(stocks.getJSONObject(i).getInteger("num"));
                    goodsStock.setPic(stocks.getJSONObject(i).getString("pic"));
                    goodsStock.setCreateTime(new Date());
                    int dbResult3 = goodsStockMapper.insertSelective(goodsStock);
                    if(dbResult3 <=0){
                        throw new RuntimeException(Constent.ERROR_FOOD_3);
                    }
                }
            }
            //插入参数
            goodsParamMapper.deleteByGoodsId(goods.getId());
            JSONArray params = JSONArray.parseArray(goods.getParams());
            if(null != params) {
                for (int i=0;i<params.size();i++) {
                    GoodsParam goodsParam = new GoodsParam();
                    goodsParam.setGoodsId(goods.getId());
                    goodsParam.setName(params.getJSONObject(i).get("name").toString());
                    goodsParam.setKey(params.getJSONObject(i).get("key").toString());
                    goodsParam.setCreateTime(new Date());
                    int dbResult4 = goodsParamMapper.insertSelective(goodsParam);
                    if(dbResult4 <=0){
                        throw new RuntimeException(Constent.ERROR_FOOD_4);
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }


    @Override
    public Goods getGoodsById(Long id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }


    @Override
    public ResultMap delGoods(Long id) {
        try {
            int dbResult = goodsMapper.delGoods(id);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_DELETE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_DELETE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
}
