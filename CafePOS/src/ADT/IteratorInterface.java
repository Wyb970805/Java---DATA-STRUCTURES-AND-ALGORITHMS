/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Iterator;

/**
 *
 * @author Tong Chein Leng
 */
public interface IteratorInterface<T> extends ListInterface<T>{
    public Iterator<T> getIterator();
    
}
