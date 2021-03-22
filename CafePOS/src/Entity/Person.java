/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Chase Tan
 */
public class Person {

   private String name;
   private String phoneNo;
   private String emailAddress;
   
   public Person() {
       
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
   public Person(String name, String phoneNo, String emailAddress) {
       this.name = name;
       this.phoneNo = phoneNo;
       this.emailAddress = emailAddress;
   }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", phoneNo=" + phoneNo + ", emailAddress=" + emailAddress + '}';
    }
   
   
    
}
