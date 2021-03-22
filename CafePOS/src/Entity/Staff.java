/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Scanner;

/**
 *
 * @author Chase Tan
 */
public class Staff extends Person {

    private static final int startingID = 1000;
    private static int count;
    private String staff_ID;
    private String roles;
    private int behavePoint;
    private double salary;    
    
    public Staff() {
        
    }
    
    //name, phoneNo, emailAddress, staff_ID, roles, behavePoint, salary
    public Staff(String name, String phoneNo, String emailAddress, String roles, int behavePoint, double salary) {
        super(name, phoneNo, emailAddress);
        this.count++;
        this.staff_ID = countStaffID(count);
        this.roles = roles;
        this.behavePoint = behavePoint;
        this.salary = salary;
    }

    public String countStaffID(int count) {
        int addedID;
        String staffID;
        
        addedID = startingID + count;
        Integer.toString(addedID);
        
        String str = new String("S");
        staffID = str.concat(Integer.toString(addedID));
        
        return staffID;
    }
    
    public String getStaff_ID() {
        return staff_ID;
    }

    public void setStaff_ID(String staff_ID) {
        this.staff_ID = staff_ID;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getBehavePoint() {
        return behavePoint;
    }

    public void setBehavePoint(int behavePoint) {
        this.behavePoint = behavePoint;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " Staff{" + "staff_ID=" + staff_ID + ", roles=" + roles + ", behavePoint=" + behavePoint + ", salary=" + salary + '}';
    }
    
    
}
