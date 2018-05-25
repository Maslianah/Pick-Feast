package my.maslianah.com.pickfeastfyp.model;

public class AddOn {

    private String addOnID;
    private String addOnName;
    private double addOnPrice;
    private int addOnQuantity;
    private String addOnDesc;
    private String catId;
    private String custId;



    public AddOn(){}

    public AddOn(String addOnName, double addOnPrice,String addOnDesc){
        this.addOnName = addOnName;
        this.addOnPrice = addOnPrice;
        this.addOnDesc = addOnDesc;
    }
    public AddOn(String addOnName, double addOnPrice,String addOnDesc, String catId){
        this.addOnName = addOnName;
        this.addOnPrice = addOnPrice;
        this.addOnDesc = addOnDesc;
        this.catId = catId;
    }

    public AddOn(String addOnID, String custId, int addOnQuantity){
        this.addOnID = addOnID;
        this.addOnQuantity = addOnQuantity;
        this.custId = custId;
    }


    public String getAddOnID() {
        return this.addOnID;
    }

    public void setAddOnID(String addOnID) {
        this.addOnID = addOnID;
    }

    public String getAddOnName() {
        return this.addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public double getAddOnPrice() {
        return this.addOnPrice;
    }

    public void setAddOnPrice(double addOnPrice) {
        this.addOnPrice = addOnPrice;
    }

    public int getAddOnQuantity() {
        return this.addOnQuantity;
    }

    public void setAddOnQuantity(int addOnQuantity) {
        this.addOnQuantity = addOnQuantity;
    }

    public String getAddOnDesc() {
        return addOnDesc;
    }

    public void setAddOnDesc(String addOnDesc) {
        this.addOnDesc = addOnDesc;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String toCatString() {
        return "\n\nAddOn " +
                "\n" + addOnName  +
                "\nCost RM " + addOnPrice +
                "\n" + addOnDesc ;
    }

}
