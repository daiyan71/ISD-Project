/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSheba;

/**
 *
 * @author daiya
 */
public class List3 {
    private String carModel,carBrand,rentPrice,spName,datenTime,addrs,phone,oid;
    
    public List3(String carBrand,String carModel,String rentPrice,String spName,String datenTime){
        this.carBrand=carBrand;
        this.carModel=carModel;
        this.rentPrice=rentPrice;
        this.spName=spName;
        this.datenTime=datenTime;
    }
    public List3(String carBrand,String carModel,String rentPrice,String spName,String datenTime,String oid){
        this.carBrand=carBrand;
        this.carModel=carModel;
        this.rentPrice=rentPrice;
        this.spName=spName;
        this.datenTime=datenTime;
        this.oid=oid;
    }
    public List3(String carBrand,String carModel,String rentPrice,String spName,String datenTime,String addrs,String phone,String oid){
        this.carBrand=carBrand;
        this.carModel=carModel;
        this.rentPrice=rentPrice;
        this.spName=spName;
        this.datenTime=datenTime;
        this.addrs=addrs;
        this.phone=phone;
        this.oid=oid;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public String getSpName() {
        return spName;
    }

    public String getDatenTime() {
        return datenTime;
    }

    public String getAddrs() {
        return addrs;
    }

    public String getPhone() {
        return phone;
    }
    public String getOid() {
        return oid;
    }

    
}
