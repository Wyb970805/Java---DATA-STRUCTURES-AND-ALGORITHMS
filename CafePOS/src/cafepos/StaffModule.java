/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.ArrayList;
import ADT.ListInterface;
import java.util.Scanner;
import Entity.Staff;
import ADT.HeapArray;

/**
 *
 * @author Chase Tan
 */
public class StaffModule {

    //Person person = new Person();
    /**
     * @param args the command line arguments
     */

    public static ArrayList<Staff> ListStaff = new ArrayList<Staff>();
    
    public static void main(String[] args) {
        // calling the menu in staff
        menuStaff();
    }
    
    // This is the main menu for staff module
    public static void menuStaff() {
        
        // This variable is for capture user's choice
        int option;
        boolean error;
        // Loop Validation if user enter wrong result
        do{
            error = false;
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("---------------------Welcome to Staff Menu---------------------\n");
            System.out.print("1. Add New Staff   \n");
            System.out.print("2. Modify Staff    \n");
            System.out.print("3. Delete Staff    \n");
            System.out.print("4. Show All Staff  \n");
            System.out.print("5. Back To Menu    \n");
            System.out.print("Choose (1 - 5) : ");
            option = scanner.nextInt();
            
            switch(option) {
            case 1:
                // Create a object to store in ArrayList ADT
                addStaff();
                menuStaff();
                
                break;
            case 2:
                modifyClass();
                break;
                
            case 3: 
                //staffmod.deleteStaff(staff);
                break;
                
            case 4:
                showAllStaff();
                menuStaff();
                break;
                
            default:
                //main menu;
                error = true;
            }
            
        }while (error == true);

    }
    
    // Add Staff
    public static void addStaff() {
        
        //private int count = 0;
        String name, phoneNo, emailAddress, roles = null;
        String staff_ID;
        int behavePoint = 100;
        int choice;
        double salary = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt Staff Name
        System.out.print("Enter Staff Name: ");
        name = scanner.nextLine();
        
        // Prompt Staff Phone No.
        System.out.print("Enter Phone No: ");
        phoneNo = scanner.nextLine();
        
        // Prompt Staff Email Address
        System.out.print("Enter Email Address: ");
        emailAddress = scanner.nextLine();
        
        do {
            System.out.print("Enter the number (1 - 6) represent roles.");
            System.out.print("--------------\n");
            System.out.print("1. Chief      \n");
            System.out.print("2. Barista    \n");
            System.out.print("3. Cashier    \n");
            System.out.print("4. Waiter     \n");
            System.out.print("5. Manager    \n");
            System.out.print("6. Supervisor \n");
            System.out.print("Enter number (1 - 6): ");
            choice = scanner.nextInt();
            
            if (choice == 1) {
                roles = "Chief";
            }
            else if (choice == 2) {
                roles = "Barista";
                salary = 1500.00;
            }
            else if (choice == 3) {
                roles = "Cashier";
                salary = 1500.00;
            }
            else if (choice == 4) {
                roles = "Waiter";
                salary = 1500.00;
            }
            else if (choice == 5) {
                roles = "Manager";
                salary = 3500.00;
            }
            else if (choice == 6) {
                roles = "Supervisor";
                salary = 2000.00;
            }
            else if (choice == 7){
                System.out.print("Wrong Input !\n");
            }
        } while (roles == null);
        
        ListStaff.add(new Staff(name, phoneNo, emailAddress, roles, behavePoint, salary));
        
    }
    
    // Modify Staff
    public static void modifyClass() {
        
        String staff_ID;
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        // Make sure must enter valid staff id keep looping, only press cancle to go back
        System.out.print("Enter Staff ID to select which staff you would like to modify: ");
        staff_ID = scanner.nextLine();
        
        for(int i = 1; i <= ListStaff.getLength(); i++) {
            
            if(ListStaff.getEntry(i).getStaff_ID().equals(staff_ID)) {
                System.out.print("Is this the staff you wish two change its detail?\n");
                System.out.print(ListStaff.getEntry(i).toString() + " \n");
                System.out.print("-------------------------------------------------\n");
                System.out.print("1. Modify Staff Name:                     \n");
                System.out.print("2. Modify Staff Phone Number:             \n");
                System.out.print("3. Modify Staff Email Address:            \n");
                System.out.print("4. Modify Staff Staff Role:               \n");
                System.out.print("5. Modify Staff Staff Behave Point:       \n");
                System.out.print("6. Modify Staff Staff Salary:             \n");
                System.out.print("7. Cancel Modifying or Switch Staff:      \n");
                
                System.out.print("Enter your choice to edit (1 - 7) : ");
                choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.print("The old staff name ( " + ListStaff.getEntry(i).getName().toString() + " ).");
                    System.out.print("Enter the New Name: ");
                    String inputName = scanner.nextLine();
                    
                    ListStaff.getEntry(i).setName(inputName);
                }
                else if (choice == 2) {
                    System.out.print("The old staff phone number ( " + ListStaff.getEntry(i).getPhoneNo().toString() + " ).");
                    System.out.print("Enter the new Phone Number: ");
                    String inputPhone = scanner.nextLine();
                    
                    ListStaff.getEntry(i).setPhoneNo(inputPhone);
                }
                else if (choice == 3) {
                    System.out.print("The old staff email address ( " + ListStaff.getEntry(i).getEmailAddress().toString() + " ).");
                    System.out.print("Enter the new Email Adrress: ");
                    String inputEmail = scanner.nextLine();
                    
                    ListStaff.getEntry(i).setEmailAddress(inputEmail);
                }
                else if (choice == 4) {
                    System.out.print("The old staff role ( " + ListStaff.getEntry(i).getEmailAddress().toString() + " ).");
                    System.out.print("Enter the new Role of Staff: ");
                    String inputRole = scanner.nextLine();
                    
                    ListStaff.getEntry(i).setRoles(inputRole);
                }
                else if (choice == 5) {
                    System.out.print("The old staff behave point ( " + ListStaff.getEntry(i).getBehavePoint()+ " ).");
                    System.out.print("Enter the new Role of Staff: ");
                    int inputBehave = scanner.nextInt();
                    
                    ListStaff.getEntry(i).setBehavePoint(inputBehave);
                }
                else if (choice == 6) {
                    System.out.print("The old staff salary ( " + ListStaff.getEntry(i).getSalary()+ " ).");
                    System.out.print("Enter the new Salary of Staff: ");
                    double salary = scanner.nextDouble();
                    
                    ListStaff.getEntry(i).setSalary(salary);
                }
                else {
                    menuStaff();
                }

                
            }
        }
        
        /*
        while (option != "Y" || option != "N" || option != "y" || option != "n") {
            // use arrayList to capture searchID
            ListStaff.contains(staffID);
            
            //System.out.print("Is this " + searchedID + " " + name + " is the one you wish to edit ? (Y or N)");
            option = scanner.nextLine();
        }*/
    }
    
    public static void showAllStaff() {
        System.out.print(ListStaff.getLength());
        
        for(int i = 1; i <= ListStaff.getLength(); i++){
            System.out.println(ListStaff.getEntry(i).toString());
        
        }
    }
    
}
