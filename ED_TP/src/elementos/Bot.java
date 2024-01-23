/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import estruturas.LinkedStack;
import interfacesADT.StackADT;
import java.util.Iterator;

/**
 *
 * @author David Santos
 */
public class Bot {

    private Iterator<Localidade> itr;
    private LinkedStack<Localidade> stack;

    private int id;
    private static int proximoID = 0;

    public Bot() {
        id = ++proximoID;
        this.itr = null;
        this.stack = new LinkedStack<>();

    }

    public void setIterator(Iterator<Localidade> iterator) {
        this.itr = iterator;
    }

    public Bot(Iterator<Localidade> itr, LinkedStack<Localidade> stack) {
        this.itr = itr;
        this.stack = stack;
    }

    public Iterator<Localidade> getItr() {
        return itr;
    }

    public LinkedStack<Localidade> getStack() {
        return stack;
    }

    public int getId() {
        return id;
    }

}
