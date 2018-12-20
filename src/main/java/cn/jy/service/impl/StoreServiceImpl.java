package cn.jy.service.impl;

import cn.jy.dto.ResultMap;
import cn.jy.entity.CookStyle;
import cn.jy.entity.Format;
import cn.jy.entity.Store;
import cn.jy.mapper.CookStyleMapper;
import cn.jy.mapper.FormatMapper;
import cn.jy.mapper.StoreMapper;
import cn.jy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/12/20 13:28
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    CookStyleMapper cookStyleMapper;

    @Autowired
    FormatMapper formatMapper;

    @Override
    public List<Store> getStoreList(Map<String, Object> params) throws Exception {
        List<Store> stores = storeMapper.selectByParams(params);
        return stores;
    }

    @Override
    public List<CookStyle> getCookStyleList(Map<String, Object> params) throws Exception {
        List<CookStyle> cookStyles = cookStyleMapper.selectByParams(params);
        return cookStyles;
    }

    @Override
    public List<Format> getFormatList(Map<String, Object> params) throws Exception {
        List<Format> formats = formatMapper.selectByParams(params);
        return formats;
    }

    @Override
    public ResultMap addStore(Store store) throws Exception {
        return null;
    }

    @Override
    public ResultMap updateStore(Store store) {
        return null;
    }

    @Override
    public Store getStoreById(Long id) {
        return null;
    }

    @Override
    public ResultMap delStore(Long id) {
        return null;
    }
}
