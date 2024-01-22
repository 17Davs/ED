/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import estruturas.ArrayUnorderedList;
import estruturas.EmptyCollectionException;
import interfacesADT.ListADT;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santos
 */
public class Jogador {

    private int id;
    private static int proximoID = 0;
    private ArrayUnorderedList<Bot> bots;

    public Jogador(int quantidadeBots) {
        this.id = ++proximoID;
        this.bots = new ArrayUnorderedList<>(quantidadeBots);
    }

    public int getId() {
        return id;
    }

    public ListADT<Bot> getBots() {
        return bots;
    }

    public void iteratorToBot(int indiceBot, Iterator<Localidade> iterator) throws EmptyCollectionException {
        if (indiceBot >= 0 && indiceBot < bots.size()) {
            Bot bot = bots.removeFirst(); // Remove o bot do início da lista
            bot.setIterator(iterator); // Configura o iterador
            bots.addToRear(bot);
        } else {
            System.out.println("Indice do bot invalido");
        }
    }

    public void setIteradorBFSParaBot(int b, Iterator iteratorBFS) {
        try {
            iteratorToBot(b, iteratorBFS);
            System.out.println("Iterador BFS configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }
    public void setIteradorDFSParaBot(int b, Iterator iteratorDFS) {
        try {
            iteratorToBot(b, iteratorDFS);
            System.out.println("Iterador BFS configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }
    public void setIteradorShortestPathParaBot(int b, Iterator iteratorShortestPath) {
        try {
            iteratorToBot(b, iteratorShortestPath);
            System.out.println("Iterador BFS configurado para o Bot " + b);
        } catch (EmptyCollectionException e) {
            System.out.println("Erro ao configurar o iterador para o Bot " + b);
            e.printStackTrace();
        }
    }


}
