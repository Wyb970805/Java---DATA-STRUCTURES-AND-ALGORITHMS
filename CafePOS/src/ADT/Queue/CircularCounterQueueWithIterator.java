/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT.Queue;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 *
 * @author Wen
 */
public class CircularCounterQueueWithIterator<E> implements QueueWithIteratorInterface<E>{
    private E[] line; // circular queue of counter to identify the number of element.
    private int firstIndex, lastIndex, counter;
    private static final int DEFAULT_CAPACITY = 2;
    
    public CircularCounterQueueWithIterator(){
        this(DEFAULT_CAPACITY);
    }
    
    public CircularCounterQueueWithIterator(int initialCapacity) {
    line = (E[]) new Object[initialCapacity];
    firstIndex = counter /*= lastIndex*/ = 0;
    lastIndex = -1;
    
    }
    
    public boolean addToQueue(E e){
        if(counter!=line.length){
            lastIndex = (lastIndex+1)%line.length;
            line[lastIndex]=e;
            
            ++counter;
            return true;
        }
        else if(isFull()){
            resize();
            lastIndex = (lastIndex+1)%line.length;
            line[lastIndex]=e;
            
            ++counter;
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
            --counter;
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
        lastIndex = counter-1;     ///maybehere
    }
    
    public boolean isFull(){
       return counter==line.length;
    }

    @Override
    public Iterator<E> getIterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator{
        
        private int currentIndex = firstIndex;
        
        @Override
        public boolean hasNext() {
            return currentIndex+1%line.length < counter;
        }

        @Override
        public E next() {
            E nextElement = null; 
            if (hasNext()) {
                nextElement = line[currentIndex];
                currentIndex = (currentIndex+1)%line.length;
            }
           return nextElement;
        }
        
    }
}
