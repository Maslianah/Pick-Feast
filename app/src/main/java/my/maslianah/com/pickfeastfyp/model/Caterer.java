package my.maslianah.com.pickfeastfyp.model;

public class Caterer {
    private String catID;
    private String catererName;
    private String catererMail;
    private String halalReg;
    private String companyReg;
    private String catererAddress;
    private String catererNumber;

    public Caterer(){
    }

    public Caterer(String catID, String catererName, String catererMail, String catererAddress, String catererNumber, String halalReg, String companyReg){
        this.catID = catID;
        this.catererName = catererName;
        this.catererMail = catererMail;
        this.catererNumber = catererNumber;
        this.halalReg = halalReg;
        this.companyReg = companyReg;
        this.catererAddress = catererAddress;
    }

    public String getCatID() {
        return this.catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatererName() {
        return this.catererName;
    }

    public void setCatererName(String catererName) {
        this.catererName = catererName;
    }

    public String getCatererMail() {
        return this.catererMail;
    }

    public void setCatererMail(String catererUName) {
        this.catererMail = catererUName;
    }

    public String getHalalReg() {
        return this.halalReg;
    }

    public void setHalalReg(String halalReg) {
        this.halalReg = halalReg;
    }

    public String getCompanyReg() {
        return this.companyReg;
    }

    public void setCompanyReg(String companyReg) {
        this.companyReg = companyReg;
    }

    public String getCatererAddress() {
        return this.catererAddress;
    }

    public void setCatererAddress(String catererAddress) {
        this.catererAddress = catererAddress;
    }

    public String getCatererNumber() {
        return this.catererNumber;
    }

    public void setCatererNumber(String catererNumber) {
        this.catererNumber = catererNumber;
    }

    @Override
    public String toString() {
        return "\n\nCaterer{" +
                catID + "\n" +
                "Name: " + catererName + "\n" +
                "Email: " + catererMail + "\n" +
                "Address: " + catererAddress + "\n" +
                "Number: " + catererNumber + "\n" +
                "Halal Status: " + halalReg + "\n" +
                "Company Registration Number: " + companyReg + "\n" ;
    }
}
