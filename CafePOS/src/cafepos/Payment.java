/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import java.time.LocalDateTime;

/**
 *
 * @author Tong Chein Leng
 */
public class Payment {
    
    String paymentID;
    float paymentAmount;
    String paymentMethod;
    LocalDateTime paid;    //https://www.w3schools.com/java/java_date.asp
    String paymentType;
    Order orderList[];
}
