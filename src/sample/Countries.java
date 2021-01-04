package sample;

public class Countries {

    private int country_id;
    private String country;


    public Countries(int country_id, String country){
        this.country_id = country_id;
        this.country = country;
    }

    public int getCountry_id(){
        return country_id;
    }

    public void setCountry_id(int country_id){
        this.country_id = country_id;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

}
