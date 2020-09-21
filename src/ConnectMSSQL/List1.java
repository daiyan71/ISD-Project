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
public class List1 {
     private String sId,sName,sRate,av;
     
     public List1(String sId,String sName,String sRate){
        this.sId=sId;
        this.sName=sName;
        this.sRate=sRate;
    }
    public List1(String sId,String sName,String sRate,String av){
        this.sId=sId;
        this.sName=sName;
        this.sRate=sRate;
        this.av=av;
    }
    
    
    public String getId(){
        return sId;
    }
    public String getServiceName(){
        return sName;
    }
    public String getServiceRate(){
        return sRate;
    }

    public String getAv() {
        return av;
    }
    
  
}
