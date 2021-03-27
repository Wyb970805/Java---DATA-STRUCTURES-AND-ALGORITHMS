package ADT;
import java.util.Scanner;
/**
 *
 * @author Chase Tan
 */

//Wrong thing I did, top forever is top, btm forever is btm, cannot change its value
public class HeapArray<S> implements HeapInterface<S> {
    
  private S[] arrayVar; //make  a array  variable
  private int topIndexHeap; // index of top entry
  private static final int DEFAULT_CAPACITY = 50; // make the limit of stack
  private int count;
  
    public HeapArray() {
      this(DEFAULT_CAPACITY); // this line cannot understand
    }

    public HeapArray(int initialCapacity) {
      arrayVar = (S[]) new Object[initialCapacity]; 
      topIndexHeap = -1;
    }
  
    @Override
    public void pushBottom(S promptResult) {
        ++topIndexHeap;
        count++;
        
        arrayVar[topIndexHeap] = promptResult;
    }

    @Override
    public S popTop() {
        S topContent = null;
        
        if(!isEmpty()) {
            topContent = arrayVar[topIndexHeap];
            arrayVar[topIndexHeap] = null;
            topIndexHeap--;
        }
        
        return topContent;
    }

    @Override
    public S peekTop() {
        
        S topContent = null;
        
        if(!isEmpty()) {
            topContent = arrayVar[topIndexHeap];
        }
        
        return topContent;
    }

    @Override
    public S peekIndex(int enteredIndex) {
        S peekContent = null;
        
        if(!isEmpty()) {
            for (int i=0; i < count; i++) {
                if(i == enteredIndex) {
                    peekContent = arrayVar[i];
                }
            }
        }
        
        return peekContent;
    }

    @Override
    public void clearAll() {
        Scanner scanner = new Scanner(System.in);
        String ans = null;
        
        while(ans != "y" || ans != "n" || ans != "Y" || ans != "N") {
            System.out.print("are you sure you want to clear heap array (Y or N) ?");
            ans = scanner.nextLine();
            
            if(ans == "Y" || ans == "y") {
                arrayVar = null;
            }
        }
        
    }

    @Override
    public boolean isEmpty() {
        
        return topIndexHeap < 0; // It will return false or true base on this statement
    }

    @Override
    public boolean isContains(S promptResult) {
        S contains = null;
        
        if(!isEmpty()) {
            for (int i=0; i < count; i++) {
                if(arrayVar[i] == promptResult) {
                    contains = arrayVar[i];
                }
            }
        }
        
        return contains == null;
    }

    @Override
    public int heapCount() {
        return count;
    }
    
    // Retrieve all result
    
}
