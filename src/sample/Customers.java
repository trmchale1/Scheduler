package sample;

public class Customers {

    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_updated_by;
    private int division_id;

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

    public String getCreate_date(){
        return create_date;
    }

    public void setCreate_date(String create_date){
        this.create_date = create_date;
    }

    public String getCreated_by(){
        return created_by;
    }

    public void setCreated_by(String created_by){
        this.created_by = created_by;
    }

    public String getLast_update(){
        return last_update;
    }

    public void setLast_update(String last_update){
        this.last_update = last_update;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public  void setLast_updated_by(String last_updated_by){
        this.last_updated_by = last_updated_by;
    }

    public int getDivision_id(){
        return division_id;
    }

    public void setDivision_id(int division_id){
        this.division_id = division_id;
    }
}
