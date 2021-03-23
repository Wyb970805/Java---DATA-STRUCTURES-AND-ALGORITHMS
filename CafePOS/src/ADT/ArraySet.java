/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Wen
 * @param <T>
 */
public class ArraySet<T> implements SetInterface<T> {
    //declare array 
    private T[] setArray; 
//int [] arr = new int[5];
    private int numberOfElements;
    private static final int INITIAL_DEFAULT_CAPACITY = 5;
    
    
    // constructor
    public ArraySet(){
        this(INITIAL_DEFAULT_CAPACITY);
    }
    
    public ArraySet(int initialCapacity){
        setArray = (T[]) new Object[initialCapacity];
        numberOfElements = 0;
    }
    
    // implementation of abstract methods
    // add a new element to the set
    public boolean add(T newElement){
        // set the distinct element. check first if newElement already exist
        for(int i=0;i<numberOfElements; i++){
            if(setArray[i].equals(newElement))
                return false;
        }
        //check if the array is full or not . If full double the capacity,
        //if not full add newElement
        if (isArrayFull())
            doubleArray();
        
        //add new element
        setArray[numberOfElements]= newElement;
        numberOfElements++;
        return true;
    }
    
    public boolean remove(T anElement){//remove an element from the set
            //search for anElement
        for (int i = 0; i<numberOfElements; i++){
            if (setArray[i].equals(anElement)){
                removeGap(i);
                numberOfElements--;
                return true;
            }
        }
        return false;
    }
                
    public void removeGap(int index){
        for (int i = index; i<numberOfElements-1;i++)
                setArray[i]=setArray[i+1];
    
    }
                
    private boolean isArrayFull(){
        return numberOfElements == setArray.length;
    }
    
    private void doubleArray(){
        T[] oldSet = setArray;
        int oldSize = oldSet.length;
                
        setArray = (T[]) new Object[oldSize*2];
        
        //copy all element fro old set
        for (int i=0; i<oldSize;i++)
            setArray[i] = oldSet[i];
    }
    
    
    //check if another set is a subset of current set   //setA.checkSubset(setB)
    public boolean checkSubset(SetInterface anotherSet){
        if(anotherSet instanceof ArraySet){
            ArraySet aSet = (ArraySet) anotherSet;
        
            // check the size of anotherSet(aSet). if more than current, not a subset
            if(aSet.numberOfElements>this.numberOfElements)
              return false;
            
            //compare each elements in anotherSet(aSet) with elements in crrent set
            for(int i=0;i<aSet.numberOfElements;i++){
                boolean found = false;
                for(int j=0; j<numberOfElements && !found;j++){
                    if(aSet.setArray[i].equals(setArray[j])){
                        found = true;
                    }
                }
                if(!found)
                    return false;
            }
        }
        return true;
    }
    
    // Add another set to the current set   //setA.union(setB)
    public void union(SetInterface anotherSet){ // add another set to current set
        if(anotherSet instanceof ArraySet){
            ArraySet aSet = (ArraySet) anotherSet; //create object reference
        
            for(int i = 0; i<aSet.numberOfElements; i++)
                this.add((T) aSet.setArray[i]);
        }
    } 
        
        // returns a set with elements that are common in both the current set and the given set
        //setA.intersection(setB);
        public SetInterface intersection(SetInterface anotherSet){
            SetInterface resultSet = new ArraySet();
        
            if(anotherSet instanceof ArraySet){
                ArraySet aSet = (ArraySet) anotherSet;
        
            for(int i=0; i<aSet.numberOfElements;i++){
                boolean found = false;
                for(int j=0; j<numberOfElements && !found; j++){
                    if(aSet.setArray[i].equals(setArray[j])){
                        found = true;
                        
                    }
                }
                if(found)
                    resultSet.add((T) aSet.setArray[i]);
        }
        }
                   
            return resultSet;
        }
        
        
    public boolean isEmpty(){
        return numberOfElements == 0;
    }

    public String toString(){
        String str="";
        for (int i=0; i<numberOfElements;i++){
            str+=setArray[i]+", ";
        }
        return str;
    }

}
