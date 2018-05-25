package my.maslianah.com.pickfeastfyp.model;

public class Customer { //fields
    private int custID;
    private String custPassword;
    private String custName;
    private String custPhone;
    private String custAddress;
    private String custEmail;

    //constructor
    public Customer (){}
    public Customer (int id, String password, String name, String phone, String address, String email ){
        this.custID = id;
        this.custEmail = email;
        this.custName = name;
        this.custAddress = address;
        this.custPhone = phone;
        this.custPassword = password;
    }

    // setter and getter

    public void setCustID(int custID) {
        this.custID = custID;
    }
    public int getCustID() {
        return this.custID;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }
    public String getCustEmail() {
        return this.custEmail;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustName() {return this.custName; }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
    public String getCustAddress() {
        return this.custAddress;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
    public String getCustPhone() {
        return this.custPhone;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }
    public String getCustPassword() {
        return this.custPassword;
    }

}
