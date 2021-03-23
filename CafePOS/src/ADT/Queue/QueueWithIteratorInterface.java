/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT.Queue;

import java.util.Iterator;

/**
 *
 * @author Wen
 */
public interface QueueWithIteratorInterface<E> extends QueueInterface<E> {
    public Iterator<E> getIterator();
}
