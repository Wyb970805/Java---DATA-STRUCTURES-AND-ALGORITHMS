/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.*;
import Entity.*;
import ADT.Queue.*;
import static cafepos.ItemModule.ItemSet;
import java.util.Iterator;
import java.util.Scanner;
import static cafepos.StaffModule.ListStaff;
import static cafepos.MemberModule.ListMember;

/**
 *
 * @author Wen
 */
public class OrderModule {

    char exit = 'a';

    int orderNum = 1002, tbNum = 0;
    String orderList = null;
    Scanner sc = new Scanner(System.in);
    static QueueWithIteratorInterface<Order> orderLine = new CircularCounterQueueWithIterator<>();
    static QueueWithIteratorInterface<Order> completedOrder = new CircularCounterQueueWithIterator<>();

    public void orderMenu() {

        int choice;

        do {
            //System.out.println("Hi, welcome!");
            System.out.println("----------------------------");
            System.out.println("-------=Order Module=-------");
            System.out.println("----------------------------");
            System.out.println("1. Place Order");
            System.out.println("2. Update Status");
            System.out.println("3. Modify Order");
            System.out.println("4. Remove Order");
            System.out.println("5. Show all order");
            System.out.println("0. Exit");
            System.out.printf("Please enter the index (1-5): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                sc.next(); // this is important!
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    showOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Please select between 1 - 5!");

            }
        } while (choice != 0);
    }

    // add the new order to queue
    public void placeOrder() {
        boolean error = false;
        System.out.println("New Order (Y/N)?");
        char order = sc.nextLine().charAt(0);

        while (!Character.isLetter(order)) {
            System.out.println("Please enter yes (Y) or no (n): ");
            order = sc.nextLine().charAt(0);
        }

        while (order == 'Y' || order == 'y') {

            StaffModule.showAllStaff();
            // to retrieve the valid staff 
            System.out.print("Staff ID (exp: S0000): ");
            String sid = sc.nextLine();
            Staff staffIncharge = staffIdValidation(sid);
            while (staffIncharge == null) {
                System.out.println("Invalid staff! Please enter again. \nStaff Id (S1001): ");
                sid = sc.nextLine();
                staffIncharge = staffIdValidation(sid);
            }

            // to retrieve the valid member
            System.out.print("Member ID (exp: M0000): ");
            String mid = sc.nextLine();
            Member member = memberIdValidation(mid);
            while (member == null) {
                System.out.println("Invalid member! Please enter again. \nMember Id (M1001): ");
                mid = sc.nextLine();
                member = memberIdValidation(mid);
            }

            do {
                System.out.print("Dine in - D, Take Away - T (q to exit): ");
                char orderType = sc.nextLine().charAt(0);
                orderType = Character.toUpperCase(orderType);

                if (orderType == 'D' || orderType == 'T') {

                    if (orderType == 'D') {

                        do {
                            System.out.print("Table Number (1-20): ");
                            while (!sc.hasNextInt()) {
                                System.out.println("That's not a number! \nPlease enter again: ");
                                sc.next(); // this is important!
                            }
                            tbNum = sc.nextInt();
                            sc.nextLine();
                        } while (tbNum < 1 || tbNum > 20);
                    } else {
                        tbNum = 0;
                    }
                    orderNum++;

                    ItemOrderModule itemMenu = new ItemOrderModule();

                    boolean created = itemMenu.createOrderList();
                    if (created) {

                        Order od = new Order(orderNum, orderType, tbNum, member, staffIncharge);
                        od.setOrderList(itemMenu.getItemOrderList());
                        od.setTotalPrice(itemMenu.getTotal());
                        boolean added = orderLine.addToQueue(od);
                        if (added == true) {
                            System.out.println("Order created.");
                            System.out.println(od);
                            System.out.println("----------------------------");
                        } else {
                            System.out.println("ERROR! Order haven't created.");
                        }
                    } else {
                        System.out.println("Error");
                    }
                } else if (orderType == 'Q') {
                    System.out.println("Ok. Will go back.");
                    break;
                } else {
                    System.out.println("Please enter D or T. q to exit.");
                    error = true;
                }
            } while (error == true);

            System.out.println("Next Order (Y/N)?");
            order = sc.nextLine().charAt(0);
            while (!Character.isLetter(order)) {
                System.out.println("Please enter yes (Y) or no (N): ");
                order = sc.nextLine().charAt(0);
            }
        } 
    }

    // remove the current queue first order
    public void removeOrder() {
        Order removed = null;
        System.out.println("Are you sure you want to cancel the first order in queue (Y/N)?");
        char confirm = sc.nextLine().charAt(0);
        while (!Character.isLetter(confirm)) {
            System.out.println("Please enter yes (Y) or no (N): ");
            confirm = sc.nextLine().charAt(0);
        }
        if (confirm == 'Y' || confirm == 'y') {
            if (!orderLine.isEmpty()) {
                System.out.println(orderLine.getFirst());
                System.out.println("Are you sure you want to cancel this order(Y - Yes /other to cancel)?");
                char con = sc.nextLine().charAt(0);
                if (con == 'Y' || con == 'y') {
                    removed = orderLine.removeFQueue();
                }
                System.out.println(removed + "------ Already removed.");
            } else {
                System.out.println("There is no order in queue.");
            }

        }

    }

    // remove the order from current queue and add to the completed queue which is a permanent queue.
    public void updateOrderStatus() {
        if (!orderLine.isEmpty()) {
            System.out.println("\nUpdate the Order to the completed status.");
            System.out.println("------------------------");
            Order completed = orderLine.removeFQueue();
            System.out.println(completed);
            System.out.println("------------------------");
            completedOrder.addToQueue(completed);

            System.out.println("Your order is completed.");
        } else {
            System.out.println("------------------------");
            System.out.println("There is no order in queue.");
        }

    }

    public void editOrder() {
        if (!orderLine.isEmpty()) {
            System.out.println("\nAny changes of the order on? ");
            System.out.println("----------------------------");
            System.out.println("1. Ordered Item");
            System.out.println("2. Table Number");
            System.out.println("3. Order Type");
            System.out.println("4. Member ID");
            System.out.println("5. Staff Incharge");
            System.out.println("0. Back to preious");
            System.out.printf("Please enter the index (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                sc.next(); // this is important!
            }
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    editItem();
                    break;
                case 2:
                    editTableNum();
                    break;
                case 3:
                    editOrderType();
                    break;
                case 4:
                    editMember();
                    break;
                case 5:
                    editStaff();
                case 0:
                    break;
                default:
                    System.out.println("Only 1 - 4 are offered to change.");
            }

        }
    }

    // modify the items ordered
    // 1. additional items
    // 2. change ordered items
    // 3. cancel the items
    public void editItem() {
        System.out.println("\n--------Modify Item---------");
        System.out.println("----------------------------");
        System.out.println("1. Add Order item");
        System.out.println("2. Modify Order item");
        System.out.println("3. Remove Order item");
        System.out.println("0. Exit");
        System.out.printf("Please enter the index (1-4): ");
        while (!sc.hasNextInt()) {
            System.out.println("That's not a number! \nPlease enter again: ");
            sc.next(); // this is important!
        }
        int choice = sc.nextInt();
        double oldTotal;
        double total = 0;
        double addPayment;
        sc.nextLine();
        String itemId;
        Item itemsOrder;
        int num;
        boolean found = false;
        char more = 'n';
        switch (choice) {
            case 1:
                
                //retrieve the order record to get the first order in current queue
                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("-----------Menu-------------");
                ItemModule.displayItem();
                do {
                    System.out.println("Please enter the item id (exp: x0000): ");
                    while (!sc.hasNext("[A-Za-z]\\d{4}")) {
                        System.out.println("The item id is wrong. \nPlease enter again: ");
                        sc.nextLine();
                    }
                    itemId = sc.nextLine().toUpperCase();

                    itemsOrder = null;
                    for (int i = 1; i <= ItemSet.getNum(); i++) {

                        if (ItemSet.getEntry(i).getItemId().equals(itemId)) {
                            itemsOrder = ItemSet.getEntry(i);
                            found = true;
                            break;
                        } else {
                            found = false;
                        }
                    }
                    if (found) {
                        System.out.print("Quantity: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("That's not a number! \nPlease enter again: ");
                            sc.next(); // this is important!
                        }
                        int qty = sc.nextInt();
                        sc.nextLine();
                        // retrieve the last items itemNum and increase for new item.
                        int lastIndex = orderLine.getFirst().getOrderList().getLength();
                        lastIndex++;
                        ItemOrder newEntry = new ItemOrder(lastIndex, itemsOrder, qty);
                        orderLine.getFirst().getOrderList().add(newEntry);

                        System.out.println("More item to add (Y/other)?");
                        more = sc.nextLine().charAt(0);
                        while (!Character.isLetter(more)) {
                            System.out.println("Please enter yes (Y) or no (n): ");
                            more = sc.nextLine().charAt(0);
                        }

                    } else {
                        System.out.println("Not found.");
                    }
                } while (more == 'y' || more == 'Y');
                
                // calculate the total price of the order
                for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                    total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                }
                addPayment = total - oldTotal;
                orderLine.getFirst().setTotalPrice(total);

                System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                System.out.print("\n" + orderLine.getFirst().getOrderList());
                System.out.println("----------------------------");
                System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                System.out.println("Additional payment: " + addPayment);
                break;
            case 2:
                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("Which Item you want to modify? \nPlease enter the index (1):");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                num = sc.nextInt();
                sc.nextLine();
                System.out.println(orderLine.getFirst().getOrderList().getEntry(num));
                System.out.println("-----------Menu-------------");
                ItemModule.displayItem();

                System.out.println("Please enter the item id (exp: x0000): ");
                while (!sc.hasNext("[A-Za-z]\\d{4}")) {
                    System.out.println("The item id is wrong. \nPlease enter again: ");
                    sc.nextLine();
                }
                itemId = sc.nextLine().toUpperCase();

                itemsOrder = null;
                for (int i = 1; i <= ItemSet.getNum(); i++) {

                    if (ItemSet.getEntry(i).getItemId().equals(itemId)) {
                        itemsOrder = ItemSet.getEntry(i);
                        found = true;
                        break;
                    } else {
                        found = false;
                    }
                }
                if (found) {
                    System.out.print("Quantity: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("That's not a number! \nPlease enter again: ");
                        sc.next(); // this is important!
                    }
                    int qty = sc.nextInt();
                    sc.nextLine();

                    orderLine.getFirst().getOrderList().getEntry(num).setItemOrder(itemsOrder);
                    orderLine.getFirst().getOrderList().getEntry(num).setItemQty(qty);
                    double newSub = orderLine.getFirst().getOrderList().getEntry(num).calculateSubTotal(qty);
                    orderLine.getFirst().getOrderList().getEntry(num).setSubTotal(newSub);

                    for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                        total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                    }
                    addPayment = total - oldTotal;
                    orderLine.getFirst().setTotalPrice(total);

                    System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                    System.out.print("\n" + orderLine.getFirst().getOrderList());
                    System.out.println("----------------------------");
                    System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                    System.out.println("Additional payment: " + addPayment);

                } else {
                    System.out.println("Not found.");
                }

                break;
            case 3:
                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("Which Item you want to modify? \nPlease enter the index (1):");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                num = sc.nextInt();
                sc.nextLine();
                System.out.print("Are you sure to remove (Y/other)? ");
                char conf = sc.nextLine().charAt(0);
                while (!Character.isLetter(conf)) {
                    System.out.println("Please enter an alphabel: ");
                    conf = sc.nextLine().charAt(0);
                }
                if (conf == 'Y' || conf == 'y') {
                    orderLine.getFirst().getOrderList().remove(num);
                } else {
                    System.out.println("The items is not remove yet.");
                }
                for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                    orderLine.getFirst().getOrderList().getEntry(i).setItemNum(i);
                    total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                }
                addPayment = total - oldTotal;
                orderLine.getFirst().setTotalPrice(total);

                System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                System.out.print("\n" + orderLine.getFirst().getOrderList());
                System.out.println("----------------------------");
                System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                System.out.println("Additional payment: " + addPayment);

                break;
            case 0:
                break;
            default:
                System.out.println("Error! Please select between 1 - 4!");

        }

    }

    // modify the order table number to serve
    public void editTableNum() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current table number = " + first.getTableNo());
                System.out.print("New table 1-20 (0 to cancel): ");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                int newTable = sc.nextInt();
                sc.nextLine();
                if (newTable >= 1 && newTable <= 20) {
                    orderLine.getFirst().setTableNo(newTable);
                    System.out.println("Your changes is done.");
                    System.out.println("----------------------------");
                    System.out.println(orderLine.getFirst());
                    System.out.println("----------------------------");
                } else if (newTable == 0) {
                    break;
                } else {
                    System.out.println("Please enter table number between 01-20.");
                    error = true;
                }

            } while (error == true);
        } else {
            System.out.println("No order in queue.");

        }

    }

    // modify the order type
    public void editOrderType() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current Order Type = " + first.getOrderType());
                System.out.println("Change to Dine in (D) or Take away (T) (q to cancel): ");
                char newOType = sc.nextLine().charAt(0);
                newOType = Character.toUpperCase(newOType);
                if (newOType == 'D' || newOType == 'T') {
                    orderLine.getFirst().setOrderType(newOType);
                    if (newOType == 'T') {
                        orderLine.getFirst().setTableNo(0);
                    } else {
                        editTableNum();
                    }
                    System.out.println("Your changes is done.");
                    System.out.println("----------------------------");
                    System.out.println(orderLine.getFirst());
                    System.out.println("----------------------------");
                } else if (newOType == 'q') {
                    break;
                } else {
                    System.out.println("Please enter D for Dine in or T for Take Away.");
                    error = true;
                }

            } while (error == true);

        } else {
            System.out.println("No order in queue.");

        }

    }

    // modify the member in order
    private void editMember() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current Member = " + first.getMember());
                System.out.println("New Member Id (exp: M0000): ");
                String newMid = sc.nextLine();
                Member newMember = memberIdValidation(newMid);
                while (newMember == null) {
                    System.out.println("Invalid member! Please enter again. \nMember Id (M1001): ");
                    newMid = sc.nextLine();
                    newMember = memberIdValidation(newMid);
                }
                orderLine.getFirst().setMember(newMember);
                System.out.println("Your changes is done.");
                System.out.println("----------------------------");
                System.out.println(orderLine.getFirst());
                System.out.println("----------------------------");

            } while (error == true);

        } else {
            System.out.println("No order in queue.");

        }
    }

    // modify the order incharge staff
    private void editStaff() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current staff Incharge = " + first.getStaffIncharge());
                System.out.println("New Staff Id (exp: S0000): ");
                String newSid = sc.nextLine();
                Staff newStaff = staffIdValidation(newSid);
                while (newStaff == null) {
                    System.out.println("Invalid staff! Please enter again. \nStaff Id (S1001): ");
                    newSid = sc.nextLine();
                    newStaff = staffIdValidation(newSid);
                }
                orderLine.getFirst().setStaffIncharge(newStaff);
                System.out.println("Your changes is done.");
                System.out.println("----------------------------");
                System.out.println(orderLine.getFirst());
                System.out.println("----------------------------");

            } while (error == true);

        } else {
            System.out.println("No order in queue.");

        }
    }

    // display all order
    public void showOrder() {
        int choice = 0;
        do {
            System.out.println("\n---------Show Order--------");
            System.out.println("1. Show Order Queue");
            System.out.println("2. Show Completed Order");
            System.out.println("0. Back to preious");
            System.out.println("----------------------------");
            System.out.printf("Please enter index (1-2): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!\n Please enter again: ");
                sc.next(); // this is important!
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showOrderQueue();
                    break;
                case 2:
                    showCompletedQueue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please select 1 or 2, 0 to back.");
            }
        } while (choice != 0);
    }
    
    // display current order in queue.
    private void showOrderQueue() {
        if (!orderLine.isEmpty()) {
            System.out.println("\n-------Show All Order-------");
            System.out.println("----------------------------");
            Iterator<Order> iter = orderLine.getIterator();
            while (iter.hasNext()) {
                Order o = iter.next();
                System.out.printf(o.getOrder().toString());
            }
        } else {
            System.out.println("There is no order in queue");
        }
    }

    // display the completed permanently order.
    private void showCompletedQueue() {
        if (!completedOrder.isEmpty()) {
            System.out.println("\n--Show All Completed Order--");
            System.out.println("----------------------------");
            Iterator<Order> iter = completedOrder.getIterator();
            while (iter.hasNext()) {
                Order o = iter.next();
                System.out.printf(o.getOrder().toString());
            }
        } else {
            System.out.println("There is no completed order.");
        }
    }

    // retrieve the existing member details
    public Member memberIdValidation(String mid) {
        boolean ivalidM = true;
        Member member = null;

        mid = mid.toUpperCase();
        for (int i = 1; i <= ListMember.getLength(); i++) {
            if (ListMember.getEntry(i).getMember_ID().equals(mid)) {
                member = ListMember.getEntry(i);
                ivalidM = false;
                break;
            } else {
                ivalidM = true;
            }
        }
        if (ivalidM) {
            return null;
        } else {
            System.out.println("Member: " + member);
            return member;
        }

    }
    
    // retrieve the existing staff details
    public Staff staffIdValidation(String sid) {
        boolean ivalidS = true;
        Staff staffIncharge = null;
        sid = sid.toUpperCase();
        // For Loop to search each staff in Array List
        for (int i = 1; i <= ListStaff.getLength(); i++) {
            // Use of if else statement to check whether any staff match the prompt result of user
            if (ListStaff.getEntry(i).getStaff_ID().equals(sid)) {
                staffIncharge = ListStaff.getEntry(i);
                ivalidS = false;
                break;
            } else {
                ivalidS = true;
            }
        }
        if (ivalidS) {
            return null;
        } else {
            System.out.println("Staff Incharge for order: " + staffIncharge);
            return staffIncharge;
        }

    }

}
