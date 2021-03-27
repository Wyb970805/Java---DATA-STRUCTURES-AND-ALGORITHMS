/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;
import ADT.ArrayList;
import Entity.Member;
import java.util.Scanner;
import cafepos.CafePOS;

/**
 *
 * @author Chase Tan
 */
public class MemberModule {
    public static ArrayList<Member> ListMember = new ArrayList<Member>();
    
    // This is the main menu for member module
    public static void menuMember() {
                     
        // This variable is for capture user's choice
        int option;
        boolean error;
        // Loop Validation if user enter wrong result
        do{
            error = false;
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n---------------------Welcome to Member Menu---------------------\n");
            System.out.print("|\t\t1. Add New Member   \n");
            System.out.print("|\t\t2. Modify Member    \n");
            System.out.print("|\t\t3. Delete Member    \n");
            System.out.print("|\t\t4. Show All Member  \n");
            System.out.print("|\t\t5. Back To Menu     \n");
            System.out.print("-----------------------------------------------------------------\n");
            System.out.print("\t\t    Choose (1 - 5) : ");
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
    
    // Add Member
    public static void addMember() {
        
        //private int count = 0;
        String name, phoneNo, emailAddress;
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt Member Name
        System.out.print("Enter Member Name: ");
        name = scanner.nextLine();
        
        // Prompt Member Phone No.
        System.out.print("Enter Phone No: ");
        phoneNo = scanner.nextLine();
        
        // Prompt Member Email Address
        System.out.print("Enter Email Address: ");
        emailAddress = scanner.nextLine();
        
        ListMember.add(new Member(name, phoneNo, emailAddress));
        
    }
    
    // Modify Member
    public static void modifyMember() {
        
        String member_ID;
        int choice;
        Scanner scanner = new Scanner(System.in);
        
        // Make sure must enter valid member id keep looping, only press cancle to go back
        System.out.print("Enter Member ID to select which member you would like to modify: ");
        member_ID = scanner.nextLine();
        
        for(int i = 1; i <= ListMember.getLength(); i++) {
            
            if(ListMember.getEntry(i).getMember_ID().equals(member_ID)) {
                do {
                    System.out.print("Is this the member you wish two change its detail?\n");
                    System.out.print(ListMember.getEntry(i).toString() + " \n");
                    System.out.print("------------------Modify Menu-----------------\n");
                    System.out.print("|\t1. Modify Member Name:                     \n");
                    System.out.print("|\t2. Modify Member Phone Number:             \n");
                    System.out.print("|\t3. Modify Member Email Address:            \n");
                    System.out.print("|\t4. Cancel Modifying or Switch Member:      \n");
                    System.out.print("----------------------------------------------\n");

                    System.out.print("Enter your choice to edit (1 - 5) : ");
                    choice = scanner.nextInt();

                    Scanner scan = new Scanner(System.in);

                    if (choice == 1) {
                        System.out.print("The old member name ( " + ListMember.getEntry(i).getName().toString() + " ).\n");

                        System.out.print("Enter the New Name: ");
                        String inputName = scan.nextLine();

                        ListMember.getEntry(i).setName(inputName);
                    }
                    else if (choice == 2) {
                        System.out.print("The old member phone number ( " + ListMember.getEntry(i).getPhoneNo().toString() + " ).\n");
                        System.out.print("Enter the new Phone Number: ");
                        String inputPhone = scan.nextLine();

                        ListMember.getEntry(i).setPhoneNo(inputPhone);
                    }
                    else if (choice == 3) {
                        System.out.print("The old member email address ( " + ListMember.getEntry(i).getEmailAddress().toString() + " ).\n");
                        System.out.print("Enter the new Email Adrress: ");
                        String inputEmail = scan.nextLine();

                        ListMember.getEntry(i).setEmailAddress(inputEmail);
                    }
                    else if (choice == 4){
                        menuMember();
                    }
                } while(choice != 4);
            }
        }
    }
    
    public static void deleteMember() {
        
        Scanner scanID = new Scanner(System.in);
        String member_ID;
        String choice;
        
        System.out.print("\nEnter Member ID to select the member you wish to delete?\n");
        member_ID = scanID.nextLine();
        
        for(int i = 1; i <= ListMember.getLength(); i++) {
            
            if(ListMember.getEntry(i).getMember_ID().equals(member_ID)) {
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
