package cn.jy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Alias("Food")
public class Food {
    private Long id;

    private Long enterpriseId;

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

    private List<FoodCarousel> foodCarousels;

    private List<FoodSpec> foodSpecs;

    private List<FoodStock> foodStocks;

    private List<FoodParam> foodParams;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private Integer status;

    private Integer isDel;

    private String introduction;

}