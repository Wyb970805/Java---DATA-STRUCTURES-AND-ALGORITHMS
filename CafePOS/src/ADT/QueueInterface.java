/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import Entity.Order;

/**
 *
 * @author Wen
 * @param <E>
 */
public interface QueueInterface<E> {
    
    boolean addToQueue(E e); //add new element into queue; return true if added successful
    E removeFQueue();    //delete last element;
    boolean isEmpty();  // identify whether the queue is empty; return true if the queue is empty
    E getFirst();   // to get first element in queue; return first element if queue is not empty
    boolean setFirst(E e); // to replace the first element; return true if replace successful;
    String toString();  // display all element in adt
    
}
