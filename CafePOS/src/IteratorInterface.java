//backup
import java.util.Iterator;

/**
 *
 * @author Tong Chein Leng
 * @param <T>
 */
public interface IteratorInterface<T> extends ListInterface<T>{
    public Iterator<T> getIterator();
    
}
