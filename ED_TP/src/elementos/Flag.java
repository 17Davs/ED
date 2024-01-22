/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

/**
 *
 * @author David Santos
 */
public class Flag {
    private int id;
    private String text;
    private static int proximoID=0;
    private Jogador jogador;

    public Flag(Jogador jogador) {
        this.id= ++proximoID;
        this.jogador = jogador;
        this.text = "««-- Flag do jogador " + jogador.getId() + " --»»";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

 
    
    
    
}
