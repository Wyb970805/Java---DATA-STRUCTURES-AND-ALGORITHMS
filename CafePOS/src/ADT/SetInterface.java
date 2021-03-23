/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Wen
 */
public interface SetInterface<T> {
    boolean add(T newElement);
    boolean remove(T anElement);
    boolean checkSubset(SetInterface anotherSet);   //check if another set is a subset of current set
    void union(SetInterface anotherSet); // add another set to current set
    SetInterface intersection(SetInterface anotherSet);
    boolean isEmpty();
}
