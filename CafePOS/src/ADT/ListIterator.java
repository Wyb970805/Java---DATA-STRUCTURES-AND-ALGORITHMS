/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author Tong Chein Leng
 * @param <T>
 */
public class ListIterator<T> implements IteratorInterface<T> {

    private T[] listArray;
    private int length;
    private static final int DEFAULT_CAPACITY = 2;

    public ListIterator() {
        this(DEFAULT_CAPACITY);
    }

    public ListIterator(int initialCapacity) {
        length = 0;
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity];
        listArray = tempList;
    }

    public Iterator<T> getIterator() {
        return new ArrayListIterator();
    }

    public boolean add(T newEntry) {
        // check if the list(array) is full or not.
        // if not full add newID
        // if full, double the capacity then add new ID
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
            if (!isArrayFull()) {
                makeRoom(newPosition);
                listArray[newPosition - 1] = newEntry;
                length++;
            }
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
        boolean found = false;
        for (int id = 1; id <= length; id++) {
            if (givenID == id) {
                found = true;
            }
        }
        return found;
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
        for (int index = 0; index < length; ++index) {
            outputStr += listArray[index] + "\n";
        }

        return outputStr;
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

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            listArray[index + 1] = listArray[index];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            listArray[index] = listArray[index + 1];
        }
    }

    private class ArrayListIterator implements Iterator<T> {

        private int nextIndex;
        private boolean wasNextCalled; // needed by remove

        private ArrayListIterator() {
            nextIndex = 0;
            wasNextCalled = false;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < length;
        }

        @Override
        public T next() {
            if (hasNext()) {
                wasNextCalled = true;
                T nextEntry = listArray[nextIndex];
                nextIndex++; // advance iterator

                return nextEntry;
            } else {
                throw new NoSuchElementException("Illegal call to next();"
                        + "iterator is after end of list.");
            }
        }

        public void remove() {
            if (wasNextCalled) {
                // nextIndex was incremented by the call to next, so it 
                // is the position number of the entry to be removed
                ListIterator.this.remove(nextIndex);
                nextIndex--;           // index of next entry in iteration
                wasNextCalled = false; // reset flag
            } else {
                throw new IllegalStateException("Illegal call to remove(); "
                        + "next() was not called.");
            }
        }
    }
}

