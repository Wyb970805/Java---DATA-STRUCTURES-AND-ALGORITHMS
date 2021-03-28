/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.ArrayList;
import ADT.HeapArray;
import java.util.Scanner;
import Entity.Staff;
import cafepos.CafePOS;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors

/**
 *
 * @author Chase Tan
 */
public class StaffModule {

    //Person person = new Person();
    /**
     * @param args the command line arguments
     */

    // Include the ADT in object inside here
    
    public static ArrayList<Staff> ListStaff = new ArrayList<Staff>();
    
    // This is the main menu  for staff module
    public static void menuStaff() {
        
        // This variable is for capture user's choice
        int option;
        
        do{
            // Scanner object insert
            Scanner scanner = new Scanner(System.in);
            
            // Main menu in staff module
            System.out.print("\n---------------------Welcome to Staff Menu---------------------\n");
            System.out.print("|\t\t    1. Add New Staff   \n");
            System.out.print("|\t\t    2. Modify Staff    \n");
            System.out.print("|\t\t    3. Delete Staff    \n");
            System.out.print("|\t\t    4. Show All Staff  \n");
            System.out.print("|\t\t    5. Tick Staff Attendance \n");
            System.out.print("|\t\t    6. Check Date Of Staff Attendance \n");
            System.out.print("|\t\t    0. Back To Menu    \n");
            System.out.print("---------------------------------------------------------------\n");
            
            System.out.print("\t\t     Choose (1 - 6)(0 to exit) : ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("\nPlease Enter numeric number only, number 1-6: ");
                scanner.next(); // this is important!
            }
            option = scanner.nextInt();
            
            switch(option) {
                case 1:
                    addStaff(); // Add Staff method calling
                    break;
                    
                case 2:
                    modifyClass(); // Modify Staff method calling
                    break;

                case 3: 
                    deleteStaff(); // Delete Staff method calling
                    break;

                case 4:
                    showAllStaff(); // Select All Staff
                    break;

                case 5:
                    attendanceTick(); // Extra Features of attendance ticking
                    break;
                    
                case 6:
                    dateAttendanceChecking(); 
                    break;

                case 0:
                    break; // Go back to the largest main menu of the system
                    
                default:
                    System.out.print("You entered wrong input, please enter only number 1-6 or 0!\n");
            }
            
        }while (option != 0);

    }
    
    // Add Staff
    public static void addStaff() {
        // Local Variable Set
        String name, phoneNo, emailAddress, entryKey, roles = null;
        int choice, behavePoint = 1000;
        double salary = 0;
        
        // Scanner Object 
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
        
        // Prompt the Password from Users
        System.out.print("Enter Entry Key Password: ");
        entryKey = scanner.nextLine();
        
        do {
            System.out.print("Enter the number (1 - 6) represent roles: \n");
            System.out.print("\n----------------------------------------\n");
            System.out.print("|\t1. Chief      \n");
            System.out.print("|\t2. Barista    \n");
            System.out.print("|\t3. Cashier    \n");
            System.out.print("|\t4. Waiter     \n");
            System.out.print("|\t5. Manager    \n");
            System.out.print("|\t6. Supervisor \n");
            System.out.print("----------------------------------------\n");
            System.out.print("\t Enter number (1 - 6): ");
            choice = scanner.nextInt();
            
            if (choice == 1) {
                roles = "Chief";
                salary = 1500.00;
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
            else {
                System.out.print("Wrong Input, please enter only number 1-6 or 0 !\n");
            }
        } while (roles == null);
        
        ListStaff.add(new Staff(name, phoneNo, emailAddress, entryKey, roles, behavePoint, salary));
        
    }
    
    // Modify Staff
    public static void modifyClass() {
        
        // Local Variable Define 
        String staff_ID;
        int choice;
        
        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for the Staff ID in order to select which to modify
        System.out.print("\nEnter Staff ID to select which staff you would like to modify: ");
        staff_ID = scanner.nextLine();
        
        // For Loop to search each staff in Array List
        for(int i = 1; i <= ListStaff.getLength(); i++) {
            
            // Use of if else statement to check whether any staff match the prompt result of user
            if(ListStaff.getEntry(i).getStaff_ID().equals(staff_ID)) {
                
                // Keep Loop until users done and wish to exit with pressing option '7'
                do {
                    System.out.print("\t Is this the staff you wish two change its detail?\n");
                    System.out.print(ListStaff.getEntry(i).toString() + " \n");
                    System.out.print("-------------------------------------------------\n");
                    System.out.print("|\t1. Modify Staff Name:                     \n");
                    System.out.print("|\t2. Modify Staff Phone Number:             \n");
                    System.out.print("|\t3. Modify Staff Email Address:            \n");
                    System.out.print("|\t4. Modify Staff Entry Key:                \n");
                    System.out.print("|\t5. Modify Staff Role:                     \n");
                    System.out.print("|\t6. Modify Staff Behave Point:             \n");
                    System.out.print("|\t7. Modify Staff Salary:                   \n");
                    System.out.print("|\t0. Cancel Modifying or Switch Staff:      \n");
                    System.out.print("-------------------------------------------------\n");
                    System.out.print("\t Enter your choice to edit (1 - 7)(0 to exit) : ");
                    choice = scanner.nextInt();

                    // New Scanner object to prevent violation
                    Scanner scan = new Scanner(System.in);

                    if (choice == 1) {
                        System.out.print("\nThe old staff name ( " + ListStaff.getEntry(i).getName().toString() + " ).\n");
                        System.out.print("Enter the New Name: ");
                        String inputName = scan.nextLine();

                        ListStaff.getEntry(i).setName(inputName);
                    }
                    else if (choice == 2) {
                        System.out.print("\nThe old staff phone number ( " + ListStaff.getEntry(i).getPhoneNo().toString() + " ).\n");
                        System.out.print("Enter the new Phone Number: ");
                        String inputPhone = scan.nextLine();

                        ListStaff.getEntry(i).setPhoneNo(inputPhone);
                    }
                    else if (choice == 3) {
                        System.out.print("\nThe old staff email address ( " + ListStaff.getEntry(i).getEmailAddress().toString() + " ).\n");
                        System.out.print("Enter the new Email Adrress: ");
                        String inputEmail = scan.nextLine();

                        ListStaff.getEntry(i).setEmailAddress(inputEmail);
                    }
                    else if (choice == 4) {
                        System.out.print("\nEnter Staff Entry Key matched to modify : ");
                        String inputEntry = scan.nextLine();
                        
                        if (ListStaff.getEntry(i).getEntryKey().toString().equals(inputEntry)) {
                            ListStaff.getEntry(i).setEntryKey(inputEntry);
                            System.out.print("Change Successfully.");
                        }
                    }
                    else if (choice == 5) {
                        System.out.print("\nThe old staff role ( " + ListStaff.getEntry(i).getRoles().toString() + " ).\n");
                        System.out.print("Enter the new Role of Staff: ");
                        String inputRole = scan.nextLine();

                        ListStaff.getEntry(i).setRoles(inputRole);
                    }
                    else if (choice == 6) {
                        System.out.print("\nThe old staff behave point ( " + ListStaff.getEntry(i).getBehavePoint()+ " ).\n");
                        System.out.print("Enter the new Behave Point of Staff: ");
                        int inputBehave = scan.nextInt();

                        ListStaff.getEntry(i).setBehavePoint(inputBehave);
                    }
                    else if (choice == 7) {
                        System.out.print("\nThe old staff salary ( " + ListStaff.getEntry(i).getSalary()+ " ).\n");
                        System.out.print("Enter the new Salary of Staff: ");
                        double salary = scan.nextDouble();

                        ListStaff.getEntry(i).setSalary(salary);
                    }
                    else if (choice == 0){
                        break;
                    }
                } while(choice != 0);     
            } 
            else {
                System.out.print("\nStaff ID not found !\n"); // Error message print if Staff ID not found
            }
        }
    }
    
    public static void deleteStaff() {
        
        // Scanner Object
        Scanner scanID = new Scanner(System.in);
        
        // Local Variable
        String staff_ID;
        String choice;
        
        // Prompt User for the Staff ID to Delete Which Staff
        System.out.print("\nEnter Staff ID to select the staff you wish to delete?\n");
        staff_ID = scanID.nextLine();
        
        // Search Each Staff in Array List
        for(int i = 1; i <= ListStaff.getLength(); i++) {
            
            // Found the Staff 
            if(ListStaff.getEntry(i).getStaff_ID().equals(staff_ID)) {
                
                // Prompt Confirmation Message to Delete
                System.out.print(ListStaff.getEntry(i).toString() + " \n");
                System.out.print("\nIs this the staff you wish to delete: (Y or y)");
                choice = scanID.nextLine();
                
                // Confirm to delete the selected Staff in Array List
                if (choice.equals("y") || choice.equals("Y")) {
                    ListStaff.remove(i);
                    System.out.print("The Staff have been deleted !\n");
                }
                else {
                    System.out.print("You can now enter again correct Staff ID to delete.");
                    deleteStaff();
                }
            }
        }
    }
    
    // Display All Staff Method
    public static void showAllStaff() {
        // Simple Brief for total number of Staff Registered
        System.out.print("All Staff Number = " + ListStaff.getLength() + ".\n");
        
        // Print All
        for(int i = 1; i <= ListStaff.getLength(); i++){
            System.out.println(ListStaff.getEntry(i).toString());
        }
    }
    
    // Able to Check Yesterday Attendance
    public static void attendanceTick() {
        
        // if time is done and attendance ticked, doesnt allow come in 
        boolean error = false;

        // Scanner Object
        Scanner scan = new Scanner(System.in);

        // Create the object for local time
        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss"); // 24 hour format

        // Take out the time that might be use and seperate it
        String formattedDate = dateTimeNow.format(formatDate);
        String formattedTime = dateTimeNow.format(formatTime);

        // Calculate for today time allowed
        String hourInString = formattedTime.substring(0, 2);
        String dateInString = formattedDate.substring(0, 2);
        int hour = Integer.parseInt(hourInString);
        
        File fileobj = new File(dateInString);
        if (fileobj.exists()) {
            error = true;
        }

        if(hour <= 8 && hour >= 7 && error != true) {
            
            HeapArray<Staff> HeapAttendDaily = new HeapArray<>();
            HeapArray<Staff> HeapStaff = new HeapArray<>();
    
            // From List Array put to Stack
            for(int i = 1; i <= ListStaff.getLength(); i++) {
                HeapStaff.pushBottom(ListStaff.getEntry(i));
            }

            // Now make the attendance of staff sort and prepare data
            for(int i = 0; i < HeapStaff.heapCount(); i++) {

                System.out.println("\n" + HeapStaff.peekTop());
                System.out.print("\nDoes this staff present today: ");

                String choice = scan.nextLine();
                Staff staffAttend = HeapStaff.popTop();

                int j = i + 1;

                if (choice.equals("Y") || choice.equals("y")) {
                    HeapAttendDaily.pushBottom(staffAttend);
                    ListStaff.getEntry(j).setBehavePoint(ListStaff.getEntry(j).getBehavePoint() + 5);

                    if (ListStaff.getEntry(j).getBehavePoint() >= 2500) {
                        ListStaff.getEntry(j).setSalary(ListStaff.getEntry(j).getSalary() + 700);
                        ListStaff.getEntry(j).setBehavePoint(ListStaff.getEntry(j).getBehavePoint() - 1500);
                    }

                }
                else if (choice.equals("N") || choice.equals("n")) {
                    System.out.print("\nAbsent status entered for current staff: " + staffAttend.getStaff_ID() + " " + staffAttend.getName() + "\n");
                    ListStaff.getEntry(j).setBehavePoint(ListStaff.getEntry(j).getBehavePoint() - 5);
                }
                
            }

            try {
              FileWriter myWriter = new FileWriter(dateInString);
              
              myWriter.write("\n-------------------------------------------------\n");
              myWriter.write("|\tDate: " + formattedDate + "\n");
              myWriter.write("|\tTime: " + formattedTime + "\n");
              myWriter.write("|\tStaff Working Today\n");
              myWriter.write("-------------------------------------------------\n");
              
              // Print out the report 
                System.out.print("\n-------------------------------------------------\n");
                System.out.print("|\tDate: " + formattedDate + "\n");
                System.out.print("|\tTime: " + formattedTime + "\n");
                System.out.print("|\tStaff Working Today\n");
                System.out.print("-------------------------------------------------\n");

                for (int i = 0; i < HeapAttendDaily.heapCount(); i++) {
                    myWriter.write(HeapAttendDaily.peekTop() + "\n");
                    System.out.print(HeapAttendDaily.popTop() + "\n");
                }
                System.out.print("-------------------------------------------------\n");
            
                myWriter.write("-------------------------------------------------\n");
                myWriter.close();
                
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
        }
        else {
            System.out.print("\nEither Time Passed 9 AM or You have Ticked Today, Check Attendance at Staff Menu!\n\n");
        }
    }
    
    public static void dateAttendanceChecking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the date of attendance of staff you wish to navigate: ");
        String result = scanner.nextLine();
        
        try {
            File myObj = new File(result);
            
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            
            myReader.close();
            
            } catch (FileNotFoundException e) {
              System.out.println("\nThe Date you entered does not have record");
              e.printStackTrace();
            }
        
    }
    
}
