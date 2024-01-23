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

    /*
    Variavel iterador do tipo localidade
    */
    private Iterator<Localidade> itr;
    
    /*
    Variavel de uma Stack que é uma Stack do tipo Localidade
    */
    private LinkedStack<Localidade> stack;

    /*
    Variavel que guarda 
    */
    private int id;
    
    /*
    Variavel que vai funcionar para todos os bots terem um ID automatico
    */
    private static int proximoID = 0;

    /*
    Construtor da classe Bot
    */
    public Bot() {
        id = ++proximoID;
        this.itr = null;
        this.stack = new LinkedStack<>();

    }

    /*
    Cria o iterador para um bot
    */
    public void setIterator(Iterator<Localidade> iterator) {
        this.itr = iterator;
    }

    /*
    Construtor da classe Bot
    */
    public Bot(Iterator<Localidade> itr, LinkedStack<Localidade> stack) {
        this.itr = itr;
        this.stack = stack;
    }

    /*
    Retorna o terador do bot
    */
    public Iterator<Localidade> getItr() {
        return itr;
    }

    /*
    Retorna a Stack do tipo localidade
    */
    public LinkedStack<Localidade> getStack() {
        return stack;
    }

    /*
    Retorna o ID de um bot
    */
    public int getId() {
        return id;
    }

}
