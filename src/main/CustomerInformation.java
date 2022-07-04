package main;
public class CustomerInformation {
    private String custID;
    private String custIC;
    private String custName;
    private String counterPaid;
    
    //RELATION WITH CLASS ITEMINFORMATION
    private ItemInformation itemInformation;
    
    public CustomerInformation(){
        this.itemInformation = null;
        this.custID = null;
        this.custIC= null;
        this.custName = null;
        this.counterPaid = null;
    }
    
    public CustomerInformation(String custID, String custIC, String custName, String counterPaid, String itemID, String itemName, double itemPrice, String datePurchase){
        this.itemInformation = new ItemInformation(itemID, itemName, itemPrice, datePurchase, custID);
        this.custID = custID;
        this.custIC = custIC;
        this.custName = custName;
        this.counterPaid = counterPaid;
    }
    
    public String getCustID(){
        return custID;
    }
    
    public String getCustIC(){
        return custIC;
    }
    
    public String getCustName(){
        return custName;
    }
    
    public String getCounterPaid(){
        return counterPaid;
    }
    
    //ITEM METHOD
    public String getItemID(){
        return itemInformation.getItemID();
    }
    
    public String getItemName(){
        return itemInformation.getItemName();
    }
    
    public double getitemPrice(){
        return itemInformation.getitemPrice();
    }
    
    public String getDatePurchase(){
        return itemInformation.getDatePurchase();
        
    }
    public String itemGetCustID(){
        return itemInformation.getCustID();
    }
    
    public void setCustID(String custID){
        this.custID = custID;
    }
    
    public void setCustIC(String custIC){
        this.custIC = custIC;
    }
    
    public void setCustName(String custName){
        this.custName = custName;
    }
    
    public void setCounterPaid(String counterPaid){
        this.counterPaid = counterPaid;
    }
    
    public String toString(){
        return "Customer ID: " + custID + "\nCustomer name: " + custName + "\nCustomer IC: " + custIC + "\nCounter Paid: " 
                + counterPaid + "\nItem ID: " + getItemID() + "\nItem name: " + getItemName() + "\nPrice: " + getitemPrice() 
                + "\nDate purchased: " + getDatePurchase() + "\nCustomer item ID: " + itemGetCustID();
    }
    
}