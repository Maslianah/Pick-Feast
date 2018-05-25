package my.maslianah.com.pickfeastfyp.model;

public class rowPack {
    private int imageId;
    private String desc;
    private String packDetails;

    public rowPack(int imageId, String desc,String packDetails) {
        this.imageId = imageId;
        this.desc = desc;
        this.packDetails = packDetails;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return  desc;
    }
    public String getPackDetails() {
        return packDetails;
    }

    public void setPackDetails(String packDetails) {
        this.packDetails = packDetails;
    }
}
