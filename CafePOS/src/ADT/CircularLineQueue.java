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
    private int firstIndex;
    private int lastIndex;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CircularLineQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public CircularLineQueue(int initialCapacity) {
    line = (E[]) new Object[initialCapacity + 1];
    firstIndex = 0;
    lastIndex = initialCapacity;
    }
    
    public boolean addToQueue(E e){
        if((lastIndex+2)%line.length!=firstIndex){
            lastIndex=(lastIndex+1)%line.length;
            line[lastIndex]=e;
            return true;
        }
        else if((lastIndex+2)%line.length==firstIndex){
            resize();
            lastIndex=(lastIndex+1)%line.length;
            line[lastIndex]=e;
            return true;
        }
        else
            return false;
            
        
    }
    public E removeFQueue(){
        E first = null;
        
        if(!isEmpty()){
            first=line[firstIndex];
            line[firstIndex]=null;
            firstIndex=(firstIndex+1)%line.length;
        }
        return first;
    }
    public boolean isEmpty(){
        if((lastIndex+1)%line.length==firstIndex)
            return true;
        else
            return false;
    }
    public E getFirst(){
        E first = null;
        
        if(isEmpty()!=true){
           first = line[firstIndex]; 
           
        }
            return first;
    }
    
    public void resize(){
        E[] temp = line;
        int length = line.length;
        line = (E[]) new Object[length*2+1];
        
        for(int i=0; i<length; i++){
            line[i]=temp[(i+firstIndex)%length];
        }
        
        firstIndex =0;
        lastIndex = length;
    }
}
