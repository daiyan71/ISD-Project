/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectMSSQL;

/**
 *
 * @author daiya
 */
public class List {
   
    private String cId,cBrand,cModel,cType,cPrice,av;
    
    public List(String cId,String cBrand,String cModel,String cType,String cPrice){
        this.cId=cId;
        this.cBrand=cBrand;
        this.cModel=cModel;
        this.cType=cType;
        this.cPrice=cPrice;

    }
    public List(String cId,String cBrand,String cModel,String cType,String cPrice,String av){
        this.cId=cId;
        this.cBrand=cBrand;
        this.cModel=cModel;
        this.cType=cType;
        this.cPrice=cPrice;
        this.av=av;

    }
    
    public String getId(){
        return cId;
    }
    public String getBrand(){
        return cBrand;
    }
    public String getModel(){
        return cModel;
    }
    public String getType(){
        return cType;
    }public String getPrice(){
        
            return cPrice;
        
        
    }

    public String getAv() {
        return av;
    }
    
}
