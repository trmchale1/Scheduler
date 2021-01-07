package sample;

public class Customers {

    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private String division;
    private String country;

    public Customers(int customer_id, String customer_name, String address, String postal_code, String phone, String division, String country){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    public int getCustomer_id(){
        return customer_id;
    }

    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

    public String getCustomer_name(){
        return customer_name;
    }

    public void setCustomer_name(String customer_name){
        this.customer_name = customer_name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPostal_code(){
        return postal_code;
    }

    public void setPostal_code(String postal_code){
        this.postal_code = postal_code;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getDivision(){return division;}

    public void setDivision(String country){this.country = country;}

    public String getCountry() {return country;}

    public void setCountry(String country) {
        this.country = country;
    }
}
