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

public class Jogador {

    /*
    Variavel que ira guardar o ID de um Jogador
     */
    private int id;

    /*
    Variavel static que usamos para fazer com que o ID seja atribuido de forma automatica
     */
    private static int proximoID = 0;

    /*
    Uma Queue de bots que armazenara os bots de cada jogador
     */
    private LinkedQueue<Bot> bots;

    /*
    Local onde a Flag de um Jogador sera colocada
     */
    private Localidade base;

    Scanner scan = new Scanner(System.in);

    /*
    Construtor para criar um jogador  ja com a quantidade de bots especificada
    
    @param quantidadeBots parametro que vai guardar quantos bots tera um jogador
     */
    public Jogador() {
        this.id = ++proximoID;
        this.bots = new LinkedQueue<>();
        this.base = null;
    }

    /*
    Metodo get que retorna o ID de um jogador
     */
    public int getId() {
        return id;

    }

    /*
    Metodo get do tipo Localidade que reorna a Base de um jogador
     */
    public Localidade getBase() {
        return base;
    }

    /*
    Metodo set para definir a base de um jogador
    
    @param base parametro que sera admitida como a base do jogador
     */
    public void setBase(Localidade base) {
        this.base = base;
    }

    /*
    Metodo que retorna os bots da Queue
     */
    public LinkedQueue<Bot> getBots() {
        return bots;
    }

    /*
    Metodo que faz um dequeue de um bot que estava na Queue assim retornando o bot seguinte ao que foi retirado
     */
    public Bot getNextBot() throws EmptyCollectionException {
        return bots.dequeue();
    }

    /*
    Este metodo vai ser usado na classe GameFacilities e é o responsavel por atribuir um iterador ao bot e coloca-lo na Queue de bots
    
    @param bot sera o bot que ira ser colocado na Queue
    @param iterator sera o iterador que o bot ira utilizar
     */
    public void iteratorToBot(Bot bot, Iterator<Localidade> iterator) throws EmptyCollectionException {
        bot.setIterator(iterator);

        bots.enqueue(bot);

    }

    /*
    *Adiciona um bot na Queue de bots   
    @param bot sera o bot que ira ser colocado na Queue
     */
    public void adicionarBot(Bot bot) {
        bots.enqueue(bot);
    }

    /*
    * Retorna o tamanho ou o numero de bots presentes na Queue de bots
     */
    public int getContBots() {
        return bots.size();
    }

}
