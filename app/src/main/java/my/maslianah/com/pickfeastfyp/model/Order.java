package my.maslianah.com.pickfeastfyp.model;

import java.sql.Time;
import java.util.Date;

public class Order {

    private String orderID;
    private String eventAddress;
    private String eventReview;
    private String eventTime;
    private String eventDate;
    private String orderPax;
    private String orderDetails;
    private String packID;
    private String addOnID;
    private String charityID;
    private String custID;
   // private static int count = 1;

    public Order() {
    }

    public String FtoString() {
        return "Order{" +
                "orderId='" + orderID + '\'' +
                ", eventAddress='" + eventAddress + '\'' +
                ", eventReview='" + eventReview + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", orderPax='" + orderPax + '\'' +
                ", orderDetails='" + orderDetails + '\'' +
                ", packId='" + packID + '\'' +
                ", addOnId='" + addOnID + '\'' +
                ", charityID='" + charityID + '\'' +
                ", custID='" + custID + '\'' +
                '}';
    }

    @Override
    public String toString() {

        return "Order" + orderID + "\n\n" +
                "Event venue at " + eventAddress +  "\n" +
                " on " + eventDate +  "\n" +
                " at " + eventTime +  "\n" +
                "No of guest" + orderPax + "\n" +
                "Description,if any,\n\t" + orderDetails + "\n" ;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderID;
    }

    public void setOrderId(String orderId) {
        this.orderID = orderId;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventReview() {
        return eventReview;
    }

    public void setEventReview(String eventReview) {
        this.eventReview = eventReview;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getOrderPax() {
        return orderPax;
    }

    public void setOrderPax(String orderPax) {
        this.orderPax = orderPax;
    }

    public String getPackId() {
        return packID;
    }

    public void setPackId(String packID) {
        this.packID = packID;
    }

    public String getAddOnId() {
        return addOnID;
    }

    public void setAddOnId(String addOnId) {
        this.addOnID = addOnId;
    }

    public String getCharityID() {
        return charityID;
    }

    public void setCharityID(String charityID) {
        this.charityID = charityID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public Order(String orderId, String eventAddress, String eventReview, String eventTime, String eventDate, String orderPax, String packID, String addOnID, String charityID, String custID) {

        this.orderID = orderID;
        this.eventAddress = eventAddress;
        this.eventReview = eventReview;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.orderPax = orderPax;
        this.packID = packID;
        this.addOnID = addOnID;
        this.charityID = charityID;
        this.custID = custID;
    }
}
