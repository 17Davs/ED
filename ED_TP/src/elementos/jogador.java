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

    
    public void iteratorToBot(int indiceBot, int numBots, Iterator<Localidade> iterator) throws EmptyCollectionException {

        if (!bots.isEmpty() && indiceBot >= 0 && indiceBot < numBots) {
            ArrayUnorderedList<Bot> tempBots = new ArrayUnorderedList<>();

            // Update iterator for the desired bot
            for (int i = 0; i < indiceBot; i++) {
                tempBots.addToRear(bots.removeFirst());
            }

            Bot botToUpdate = bots.removeFirst();
            botToUpdate.setIterator(iterator);
            tempBots.addToRear(botToUpdate);

            while (!bots.isEmpty()) {
                tempBots.addToRear(bots.removeFirst());
            }

            bots = tempBots;

            System.out.println("Iterador configurado para o Bot " + (indiceBot + 1));
        } else {
            System.out.println("Lista de bots vazia ou índice do bot inválido. Tente novamente.");
        }
    }

    public void setIteradorBFSParaBot(int b, int numBots, Iterator iteratorBFS) {
        try {
            System.out.println("Setting BFS iterator for Bot " + b);
            iteratorToBot(b - 1, numBots, iteratorBFS);
            System.out.println("Iterador BFS configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }


    public void setIteradorDFSParaBot(int b, int numBots, Iterator iteratorDFS) {
        try {
            System.out.println("Setting DFS iterator for Bot " + b);
            iteratorToBot(b - 1, numBots, iteratorDFS);
            System.out.println("Iterador DFS configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }

    public void setIteradorShortestPathParaBot(int b, int numBots, Iterator iteratorShortestPath) {
        try {
            System.out.println("Setting Shortest Path iterator for Bot " + b);
            iteratorToBot(b - 1, numBots, iteratorShortestPath);
            System.out.println("Iterador ShortestPath configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }

    
    public void adicionarBot(Bot bot) {
        bots.enqueue(bot);
    }
    
    
    
}
