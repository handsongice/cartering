package cn.jy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Goods {
    private Long id;

    private Long enterpriseId;

    private Long storeId;

    private Long foodId;

    private String name;

    private String code;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Integer isNew;

    private Integer isHot;

    private String pic;

    private String unit;

    private Long typeId;

    private String sign;

    private String carousels;

    private String keys;

    private String params;

    private String specs;

    private String stocks;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private Integer status;

    private Integer isDel;

    private String introduction;

    private List<GoodsCarousel> goodsCarousels;

    private List<GoodsSpec> goodsSpecs;

    private List<GoodsStock> goodsStocks;

    private List<GoodsParam> goodsParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public List<GoodsCarousel> getGoodsCarousels() {
        return goodsCarousels;
    }

    public void setGoodsCarousels(List<GoodsCarousel> goodsCarousels) {
        this.goodsCarousels = goodsCarousels;
    }

    public List<GoodsSpec> getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(List<GoodsSpec> goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public List<GoodsStock> getGoodsStocks() {
        return goodsStocks;
    }

    public void setGoodsStocks(List<GoodsStock> goodsStocks) {
        this.goodsStocks = goodsStocks;
    }

    public List<GoodsParam> getGoodsParams() {
        return goodsParams;
    }

    public void setGoodsParams(List<GoodsParam> goodsParams) {
        this.goodsParams = goodsParams;
    }

    public String getCarousels() {
        return carousels;
    }

    public void setCarousels(String carousels) {
        this.carousels = carousels;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }
}