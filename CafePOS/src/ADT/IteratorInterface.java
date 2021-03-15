package ADT;

import java.util.Iterator;

/**
 *
 * @author Tong Chein Leng
 */
interface IteratorInterface<T> extends ListInterface<T>{
    public Iterator<T> getIterator();
    
}
