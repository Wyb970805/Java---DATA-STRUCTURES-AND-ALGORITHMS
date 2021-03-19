package ADT;

/**
 *
 * @author Chase Tan
 */
public class HeapArray<S> implements HeapInterface<S> {
    
  private S[] array; //make  a array  variable
  private int topIndex; // index of top entry
  private static final int DEFAULT_CAPACITY = 50; // make the limit of stack

  public HeapArray() {
    this(DEFAULT_CAPACITY); // this line cannot understand
  }

  public HeapArray(int initialCapacity) {
    array = (S[]) new Object[initialCapacity]; 
    topIndex = -1;
  }
  
    @Override
    public void pushBottom(S promptResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public S popIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public S popTop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public S peekTop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public S peekIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIndexEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
