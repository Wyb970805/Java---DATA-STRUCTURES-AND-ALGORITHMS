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
    private E[] line; // circular queue of counter to identify the number of element.
    private int firstIndex, lastIndex, counter;
    private static final int DEFAULT_CAPACITY = 2;
    
    public CircularLineQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public CircularLineQueue(int initialCapacity) {
    line = (E[]) new Object[initialCapacity];
    firstIndex = lastIndex = counter = 0;
    //lastIndex = initialCapacity;
    
    }
    
    public boolean addToQueue(E e){
        if(counter!=line.length){
            
            line[lastIndex]=e;
            lastIndex = (lastIndex+1)%line.length;
            counter+=1;
            return true;
        }
        else if(counter==line.length){
            resize();
            
            line[lastIndex]=e;
            lastIndex = (lastIndex+1)%line.length;
            counter+=1;
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
            counter--;
        }
        return first;
    }
    public boolean isEmpty(){
        
      //  if((lastIndex+1)%line.length==firstIndex)
        if(counter == 0)
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
    
    public boolean setFirst(E e){
        if(isEmpty()!=true){
           line[firstIndex]=e;
           return true;
        }else{
            return false;
        }
    }
    public void resize(){
        E[] temp = line;
        int length = line.length;
        line = (E[]) new Object[length*2];
        
        for(int i=0; i<length; i++){
            line[i]=temp[(firstIndex+i)%length];
        }
        
        firstIndex = 0;
        lastIndex = counter;     ///maybehere
    }
    
    public String toString(){
        String str = "";
        if(!isEmpty()){
            for(int i=0; i<counter; i++)
                str += line[firstIndex+i] + "\n";
        }else{
            str = "No order at all.";
        }
        
        return str;
    }

}
