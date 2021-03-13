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
    private int firstIndex, lastIndex, counter;
    private static final int DEFAULT_CAPACITY = 10;
    
    public CircularLineQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public CircularLineQueue(int initialCapacity) {
    line = (E[]) new Object[initialCapacity + 1];
    firstIndex = counter = 0;
    lastIndex = initialCapacity;
    
    }
    
    public boolean addToQueue(E e){
        if(counter!=line.length){
            lastIndex=(lastIndex+1)%line.length;
            line[lastIndex]=e;
            counter+=1;
            return true;
        }
        else if(counter==line.length-1){
            resize();
            lastIndex=(lastIndex+1)%line.length;
            line[lastIndex]=e;
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
    
    
   /*   public String toString()
  {
    String result = "";
    int scan = 0;
 
    while(scan < count)
    {
     if(line[scan]!=null)
     {
       result += queue[scan].toString()+"\n";
     }
    scan++;
    }

    return result;

  }*/
    public String toString(){
        String str = "";
        if(!isEmpty()){
            if(lastIndex>firstIndex){
                for(int i=0;i<lastIndex-firstIndex+1; i++)
                    str += line[i] + "\n";
            }
            else{
                for (int i=0; i<(lastIndex+firstIndex)+1; i++){      //+iterator
                    str += line[i] + "\n";
            }
        }
        }else{
            str = "No order at all.";
        }
        
        return str;
    }

}
