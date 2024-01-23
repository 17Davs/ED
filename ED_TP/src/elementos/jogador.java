/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import estruturas.ArrayUnorderedList;
import estruturas.EmptyCollectionException;
import estruturas.LinkedQueue;
import interfacesADT.ListADT;
import interfacesADT.QueueADT;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santos
 */
public class Jogador {

    private int id;
    private static int proximoID = 0;
    private LinkedQueue<Bot> bots;
    private Localidade base;
    Scanner scan = new Scanner(System.in);

    public Jogador() {
        this.id = ++proximoID;
        this.bots = new LinkedQueue<>();
        this.base = null;
    }

    public int getId() {
        return id;

    }

    public Localidade getBase() {
        return base;
    }

    public void setBase(Localidade base) {
        this.base = base;
    }

    public LinkedQueue<Bot> getBots() {
        return bots;
    }
    
    public Bot getNextBot() throws EmptyCollectionException{
        return bots.dequeue();
    }
    
    public void iteratorToBot(Bot bot,Iterator<Localidade> iterator) throws EmptyCollectionException {
            bot.setIterator(iterator);
            bots.enqueue(bot);
 
    }


    public void adicionarBot(Bot bot) {
        bots.enqueue(bot);
    }
    
    public int getContBots () {
        return bots.size();
    }

}
