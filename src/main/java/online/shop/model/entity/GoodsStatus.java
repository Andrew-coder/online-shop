package online.shop.model.entity;

/**
 * Created by andri on 1/27/2017.
 */
public enum GoodsStatus {
    AVAILABLE("available"),
    ENDS("ends"),
    ENDED("ended");

    private String goodsStatus;

    GoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public static GoodsStatus getStatus(String goodsStatus){
        for(GoodsStatus status:values()){
            if(status.getGoodsStatus().equals(goodsStatus)){
                return status;
            }
        }
        return null;
    }
}