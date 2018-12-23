package cn.jy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsStock {
    private Long id;

    private Long foodStockId;

    private Long goodsId;

    private String goodsSpecIds;

    private String goodsSpecNames;

    private String skeys;

    private String keystr;

    private BigDecimal price;

    private Integer num;

    private String pic;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodStockId() {
        return foodStockId;
    }

    public void setFoodStockId(Long foodStockId) {
        this.foodStockId = foodStockId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSpecIds() {
        return goodsSpecIds;
    }

    public void setGoodsSpecIds(String goodsSpecIds) {
        this.goodsSpecIds = goodsSpecIds == null ? null : goodsSpecIds.trim();
    }

    public String getGoodsSpecNames() {
        return goodsSpecNames;
    }

    public void setGoodsSpecNames(String goodsSpecNames) {
        this.goodsSpecNames = goodsSpecNames == null ? null : goodsSpecNames.trim();
    }

    public String getSkeys() {
        return skeys;
    }

    public void setSkeys(String skeys) {
        this.skeys = skeys == null ? null : skeys.trim();
    }

    public String getKeystr() {
        return keystr;
    }

    public void setKeystr(String keystr) {
        this.keystr = keystr == null ? null : keystr.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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
}