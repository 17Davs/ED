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

    public Jogador(int quantidadeBots) {
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

    public void iteratorToBot(Bot bot,int numBots, Iterator<Localidade> iterator) throws EmptyCollectionException {


          
            
            bot.setIterator(iterator);
            bots.enqueue(bot);

            

            System.out.println("Iterador configurado para o Bot " + bot.getId());
        
    }

    public void setIteradorBFSParaBot(int numBots, Iterator iteratorBFS) {
        try {
            Bot bot = bots.dequeue();
            System.out.println("Atribuindo o iterador BFS para o Bot " + bot.getId());
            iteratorToBot(bot, numBots, iteratorBFS);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot ");
            e.printStackTrace();
        }
    }

    public void setIteradorDFSParaBot(int numBots, Iterator iteratorDFS) {
        try {
            Bot bot = bots.dequeue();
            System.out.println("Atribuindo o iterador DFS para o Bot " + bot.getId());
            iteratorToBot(bot, numBots, iteratorDFS);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot ");
            e.printStackTrace();
        }
    }

    public void setIteradorShortestPathParaBot(int b, int numBots, Iterator iteratorShortestPath) {
       try {
            Bot bot = bots.dequeue();
            System.out.println("Atribuindo o iterador Shortest Path para o Bot " + bot.getId());
            iteratorToBot(bot, numBots, iteratorShortestPath);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot ");
            e.printStackTrace();
        }
    }

    public void adicionarBot(Bot bot) {
        bots.enqueue(bot);
    }
    
    public int getContBots () {
        return bots.size();
    }

}
