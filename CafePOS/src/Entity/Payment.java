/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.Order;
import java.time.LocalDateTime;

/**
 *
 * @author OEM
 */
public class Payment {
    
    private String paymentID;
    private float paymentAmount;
    private String paymentMethod;
    private LocalDateTime paid;    //https://www.w3schools.com/java/java_date.asp
    private String paymentType;
    private Order orderList[];
}
