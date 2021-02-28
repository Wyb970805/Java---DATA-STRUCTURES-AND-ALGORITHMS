/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Wen
 * @param <E>
 */
public class CircularLineQueue<E> implements QueueInterface<E>{
    private E[] line; // circular queue of queue entries and leave one unused location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CircularLineQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public CircularLineQueue(int initialCapacity) {
    line = (E[]) new Object[initialCapacity + 1];
    frontIndex = 0;
    backIndex = initialCapacity;
    }
    
    public boolean addToQueue(E e){
        
        return true;
    }
    public E removeFQuque(E e){
        E queue = null;
        
        return queue;
    }
    public boolean isEmpty(){
        
        return true;
    }
    public E getFirst(){
        E first = null;
        
        return first;
    }
}
