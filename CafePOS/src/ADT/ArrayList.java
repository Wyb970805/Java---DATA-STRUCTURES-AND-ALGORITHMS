/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.io.Serializable;

/**
 *
 * @author Tong Chein Leng
 * @param <T>
 */
public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] listArray;
    private int length;
    private static final int DEFAULT_CAPACITY = 2;

    //constructor
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        length = 0;
        listArray = (T[]) new Object[initialCapacity];
    }

    public boolean add(T newEntry) {
        // check if the list(array) is full or not.
        // if not full add newEntry
        // if full, double the capacity then add newEntry
        if (isArrayFull()) {
            doubleArray();
        }
        listArray[length] = newEntry;
        length++;
        return true;
    }

    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= length + 1)) {
            makeRoom(newPosition);
            listArray[newPosition - 1] = newEntry;
            length++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            result = listArray[givenPosition - 1];

            if (givenPosition < length) {
                removeGap(givenPosition);
            }
            length--;
        }
        return result;
    }

    public void clear() {
        length = 0;
    }

    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            listArray[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    //Returns the elements at the specified position in this list
    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            result = listArray[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(int givenIndex) {
        boolean found = false;
        for (int index = 0; index < length; index++) {
            if (givenIndex == index) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean containsID(int givenID) {
        for (int id = 1; id <= length; id++) {
            if (givenID == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < length); index++) {
            if (anEntry.equals(listArray[index])) {
                found = true;
            }
        }
        return found;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return false;
    }

    public String toString() {
        String outputStr = "";
        for (int i = 0; i < length; ++i) {
            outputStr += listArray[i] + "\n";
        }
        return outputStr;
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        for (int i = lastIndex; i >= newIndex; i--) {
            listArray[i + 1] = listArray[i];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            listArray[index] = listArray[index + 1];
        }
    }

    private boolean isArrayFull() {
        return length == listArray.length;
    }

    private void doubleArray() {
        T[] oldList = listArray;
        int oldSize = oldList.length;

        listArray = (T[]) new Object[oldSize * 2];

        // copy all elements from old list
        for (int i = 0; i < oldSize; i++) {
            listArray[i] = oldList[i];
        }
    }
}
