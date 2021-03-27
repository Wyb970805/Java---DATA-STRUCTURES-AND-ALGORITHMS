/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;
import ADT.ArrayList;
import java.util.Scanner;
import Entity.Member;
import cafepos.CafePOS;

/**
 *
 * @author Chase Tan
 */
public class MemberModule {
    public static ArrayList<Member> ListMember = new ArrayList<Member>();
    
    // This is the main menu for staff module
    public static void menuMember() {
                
        // This variable is for capture user's choice
        int option;
        boolean error;
        // Loop Validation if user enter wrong result
        do{
            error = false;
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("---------------------Welcome to Staff Menu---------------------\n");
            System.out.print("1. Add New Member   \n");
            System.out.print("2. Modify Member    \n");
            System.out.print("3. Delete Member    \n");
            System.out.print("4. Show All Member  \n");
            System.out.print("5. Back To Menu     \n");
            System.out.print("Choose (1 - 5) : ");
            option = scanner.nextInt();
            
            switch(option) {
            case 1:
                // Create a object to store in ArrayList ADT
                addMember();
                menuMember();
                
                break;
            case 2:
                modifyMember();
                break;
                
            case 3: 
                deleteMember();
                break;
                
            case 4:
                showAllMember();
                menuMember();
                break;
                
            case 5:
                CafePOS.mainMenu();
                break;
                
            default:
                //main menu;
                error = true;
            }
            
        }while (error == true);

    }
    
    // Add Staff
    public static void addMember() {
        
        //private int count = 0;
        String name, phoneNo, emailAddress;
        int pointCollected = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt Staff Name
        System.out.print("Enter Member Name: ");
        name = scanner.nextLine();
        
        // Prompt Staff Phone No.
        System.out.print("Enter Phone No: ");
        phoneNo = scanner.nextLine();
        
        // Prompt Staff Email Address
        System.out.print("Enter Email Address: ");
        emailAddress = scanner.nextLine();
        
        ListMember.add(new Member(name, phoneNo, emailAddress, pointCollected));
        
    }
    
    // Modify Staff
    public static void modifyMember() {
        
        String staff_ID;
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        // Make sure must enter valid staff id keep looping, only press cancle to go back
        System.out.print("Enter Staff ID to select which staff you would like to modify: ");
        staff_ID = scanner.nextLine();
        
        for(int i = 1; i <= ListMember.getLength(); i++) {
            
            if(ListMember.getEntry(i).getMember_ID().equals(staff_ID)) {
                do {
                    System.out.print("Is this the staff you wish two change its detail?\n");
                    System.out.print(ListMember.getEntry(i).toString() + " \n");
                    System.out.print("-------------------------------------------------\n");
                    System.out.print("1. Modify Member Name:                     \n");
                    System.out.print("2. Modify Member Phone Number:             \n");
                    System.out.print("3. Modify Member Email Address:            \n");
                    System.out.print("4. Modify Member Collected Point:          \n");
                    System.out.print("5. Cancel Modifying or Switch Member:      \n");

                    System.out.print("Enter your choice to edit (1 - 5) : ");
                    choice = scanner.nextInt();

                    Scanner scan = new Scanner(System.in);

                    if (choice == 1) {
                        System.out.print("The old staff name ( " + ListMember.getEntry(i).getName().toString() + " ).\n");

                        System.out.print("Enter the New Name: ");
                        String inputName = scan.nextLine();

                        ListMember.getEntry(i).setName(inputName);
                    }
                    else if (choice == 2) {
                        System.out.print("The old staff phone number ( " + ListMember.getEntry(i).getPhoneNo().toString() + " ).\n");
                        System.out.print("Enter the new Phone Number: ");
                        String inputPhone = scan.nextLine();

                        ListMember.getEntry(i).setPhoneNo(inputPhone);
                    }
                    else if (choice == 3) {
                        System.out.print("The old staff email address ( " + ListMember.getEntry(i).getEmailAddress().toString() + " ).\n");
                        System.out.print("Enter the new Email Adrress: ");
                        String inputEmail = scan.nextLine();

                        ListMember.getEntry(i).setEmailAddress(inputEmail);
                    }
                    else if (choice == 4) {
                        System.out.print("The old member's point collected ( " + ListMember.getEntry(i).getPointCollected()+ " ).\n");
                        System.out.print("Enter the new Point Collected of Member: ");
                        int inputPointCollected = scan.nextInt();

                        ListMember.getEntry(i).setPointCollected(inputPointCollected);
                    }
                    else if (choice == 5){
                        menuMember();
                    }
                } while(choice != 5);
            }
        }
    }
    
    public static void deleteMember() {
        
        Scanner scanID = new Scanner(System.in);
        String staff_ID;
        String choice;
        
        System.out.print("Enter Staff ID to select the member you wish to delete?\n");
        staff_ID = scanID.nextLine();
        
        for(int i = 1; i <= ListMember.getLength(); i++) {
            
            if(ListMember.getEntry(i).getMember_ID().equals(staff_ID)) {
                System.out.print("Is this the member you wish to delete ?\n");
                System.out.print(ListMember.getEntry(i).toString() + " \n");
                choice = scanID.nextLine();
                
                if (choice.equals("y") || choice.equals("Y")) {
                    ListMember.remove(i);
                    System.out.print("The Member have been deleted !\n");
                }
            }
        }
    }
    
    public static void showAllMember() {
        System.out.print(ListMember.getLength());
        
        for(int i = 1; i <= ListMember.getLength(); i++){
            System.out.println(ListMember.getEntry(i).toString());
        }
    }
}
