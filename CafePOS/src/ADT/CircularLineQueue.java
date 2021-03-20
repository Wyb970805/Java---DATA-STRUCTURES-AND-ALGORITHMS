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
        else if(counter==line.length){
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
   
    public String toString()
  {
    String result = "";
    int scan = 0;
 
    while(scan < counter)
    {
     if(line[scan]!=null)
     {
       result += line[scan].toString()+"\n";
     }
    scan++;
    }
    System.out.println("-----------------"+ getFirst());
    return result;

  }
   /* public String toString(){
        String str = "";
        if(!isEmpty()){
            
            for(int i=0; i<counter && line[firstIndex+i] != null; ++i){
                System.out.println(counter);
                str += i + "." + line[firstIndex+i] + "\n";
            }
        }else{
            str = "No order at all.";
        }
        
        return str;
    }*/

}
