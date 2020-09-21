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
public class ListValues {
    
    private String name,price,modelName;
    
    public ListValues(String name,String price,String ModelName){
        this.name=name;
        this.price=price;
        this.modelName=modelName;
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getModelName() {
        return modelName;
    }
    
    
}
