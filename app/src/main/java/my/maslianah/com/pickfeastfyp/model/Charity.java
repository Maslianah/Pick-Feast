package my.maslianah.com.pickfeastfyp.model;

public class Charity {
    private String charityID;
    private String charityName;
    private String charityAddress;
    private String charityOwner;
    private String charityNumber;
    private String charityDescription;
    private String charityLocation;

    public Charity (){}

    public Charity(String charityID, String charityName, String charityOwner, String charityNumber, String charityAddress, String charityLocation, String charityDescription) {
        this.charityID = charityID;
        this.charityName = charityName;
        this.charityAddress = charityAddress;
        this.charityOwner = charityOwner;
        this.charityNumber = charityNumber;
        this.charityDescription = charityDescription;
        this.charityLocation = charityLocation;

    }

    public String getCharityLocation() {
        return charityLocation;
    }

    public void setCharityLocation(String charityLocation) {
        this.charityLocation = charityLocation;
    }

    public String getCharityID() {
        return this.charityID;
    }

    public void setCharityID(String charityID) {
        this.charityID = charityID;
    }

    public String getCharityName() {
        return this.charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public String getCharityAddress() {
        return this.charityAddress;
    }

    public void setCharityAddress(String charityAddress) {
        this.charityAddress = charityAddress;
    }

    public String getCharityOwner() {
        return this.charityOwner;
    }

    public void setCharityOwner(String charityOwner) {
        this.charityOwner = charityOwner;
    }

    public String getCharityNumber() {
        return this.charityNumber;
    }

    public void setCharityNumber(String charityNumber) {
        this.charityNumber = charityNumber;
    }

    public String getCharityDescription() {
        return this.charityDescription;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }

    @Override
    public String toString() {
        return "\n" + charityName +
                "\n\nOwned by " + charityOwner +
                "\n\nLocated at " + charityAddress +
                "\n\nContact at " + charityNumber +
                "\n\n" + charityDescription + "\n";
    }


    public String orderDetailstoString() {
        return "\n" + charityName +
                "\n\nOwned by " + charityOwner +
                "\n\nContact at " + charityNumber + "\n";
    }
}
