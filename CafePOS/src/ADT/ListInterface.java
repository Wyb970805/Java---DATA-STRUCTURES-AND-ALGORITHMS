/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Tong Chein Leng
 */
public interface ListInterface<T> {

    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public T remove(int givenPosition);
    public void clear();
    public boolean replace(int givenPosition, T newEntry);
    public T getEntry(int givenPosition);
    public boolean contains(int givenIndex);
    public boolean containsID(int givenID);
    public boolean contains(T anEntry);
    public int getLength();
    public boolean isEmpty();
    public boolean isFull();
    public String toString();
}

