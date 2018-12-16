package cn.jy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FoodStock {
    private Long id;

    private Long foodId;

    private String foodSpecIds;

    private String foodSpecNames;

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

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodSpecIds() {
        return foodSpecIds;
    }

    public void setFoodSpecIds(String foodSpecIds) {
        this.foodSpecIds = foodSpecIds == null ? null : foodSpecIds.trim();
    }

    public String getFoodSpecNames() {
        return foodSpecNames;
    }

    public void setFoodSpecNames(String foodSpecNames) {
        this.foodSpecNames = foodSpecNames == null ? null : foodSpecNames.trim();
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