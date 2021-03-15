package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Tong Chein Leng
 */
public class ListIterator<T> implements IteratorInterface<T> {

    private T[] listArray;
    private int length;
    private static final int DEFAULT_CAPACITY = 50;

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

    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            result = listArray[givenPosition - 1];
        }

        return result;
    }

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

