/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesADT;

import estruturas.*;
import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws EmptyCollectionException
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws EmptyCollection.EmptyCollectionException
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element from this list.
     *
     * @param element the element to be removed from the list
     * @return 
     * @throws EmptyCollection.EmptyCollectionException
     */
    public T remove(T element) throws EmptyCollectionException;

    /**
     * Returns a reference to the first element in this list.
     *
     * @return a reference to the first element in this list
     * @throws EmptyCollection.EmptyCollectionException
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in this list.
     *
     * @return a reference to the last element in this list
     * @throws EmptyCollection.EmptyCollectionException
     */
    public T last() throws EmptyCollectionException;

    /**
     * Returns true if this list contains the specified target element.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    public boolean contains(T target);

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    public int size();

    /**
     * Returns an iterator for the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    public Iterator<T> iterator();

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString();
}
