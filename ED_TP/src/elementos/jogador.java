/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import estruturas.ArrayUnorderedList;
import interfacesADT.ListADT;


/**
 *
 * @author David Santos
 */
public class Jogador {
    
    private int id;
    private static int proximoID=0;
    private ListADT<Bot> bots;

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

   
    
    
    
    
    
    
}
