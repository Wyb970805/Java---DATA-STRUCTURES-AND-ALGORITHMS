package ADT;

/**
 *
 * @author Chase Tan
 */
public interface HeapInterface<S> {
    public void pushBottom(S promptResult); // Put the result in in such way of stack sequence (last in first out)
    public S popTop(); // Return the result of last in and pull out from the stack
    public S peekTop(); // Peek the most last put in one
    public S peekIndex(int enteredIndex); // Peek the specific index in array of stack
    public void clearAll();  // Clear whole stack
    public boolean isEmpty(); // Return true or false to tell user that array of stack empty or not
    public boolean isIndexEmpty(); // Return true or false to tell user that index empty or not
    public int heapCount(); // Count the size of the array in stack
}
