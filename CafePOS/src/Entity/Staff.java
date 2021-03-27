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

    private static final int startingID = 999;
    private static int count;
    private String staff_ID;
    private String entryKey;
    private String roles;
    private int behavePoint;
    private double salary;
    
    public Staff() {
        
    }
    
    //name, phoneNo, emailAddress, staff_ID, roles, behavePoint, salary
    public Staff(String name, String phoneNo, String emailAddress, String entryKey, String roles, int behavePoint, double salary) {
        super(name, phoneNo, emailAddress);
        this.count++;
        this.staff_ID = countStaffID(count);
        this.entryKey = entryKey;
        this.roles = roles;
        this.behavePoint = behavePoint;
        this.salary = salary;
    }

    public String getEntryKey() {
        return entryKey;
    }

    public void setEntryKey(String entryKey) {
        this.entryKey = entryKey;
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

    public String countEntry(String entryKey) {
        String encryptPass = "";
        
        for(int i = 0; i < entryKey.length(); i++) {
            encryptPass += "*";
        }
        
        return encryptPass;
    }
    
    @Override
    public String toString() {
        return String.format("Staff : %5s  | %10s  | %12s  | %4d  | %5f  |", staff_ID, countEntry(entryKey), roles, behavePoint, salary) + super.toString();
    }
    
    
}
