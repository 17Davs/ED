/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import interfacesADT.StackADT;
import java.util.Iterator;

/**
 *
 * @author David Santos
 */
public class Bot {
    protected Iterator<Localidade> itr;
    protected StackADT<Localidade> stack;
    protected Localidade local;
    
    public void setIterator(Iterator<Localidade> iterator) {
        this.itr = iterator;
    }

    public Bot(Iterator<Localidade> itr, StackADT<Localidade> stack) {
        this.itr = itr;
        this.stack = stack;
    }

    public Iterator<Localidade> getItr() {
        return itr;
    }

    public StackADT<Localidade> getStack() {
        return stack;
    }

    public Bot() {
        
    }
    
    
    
}
