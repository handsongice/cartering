package cn.jy.entity;

public class GoodsCarousel {
    private Long id;

    private Long foodCarouselId;

    private Long goodsId;

    private String index;

    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodCarouselId() {
        return foodCarouselId;
    }

    public void setFoodCarouselId(Long foodCarouselId) {
        this.foodCarouselId = foodCarouselId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index == null ? null : index.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}