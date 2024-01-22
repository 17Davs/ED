/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

import java.time.LocalDateTime;
import org.json.simple.JSONObject;

/**
 *
 * @author David Santos
 */
public class Localidade {
    private int id;
    private static int proximoID=0;
    private String nome;
    private boolean ocupada;
    private Flag flag;

    public Localidade(String nome) {
        this.flag=null;
        this.id = ++proximoID;
        this.nome = nome;
        this.ocupada = false;
    }

    public int getId() {
        return id;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
    
    public Flag getFlag() {
        return flag;
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Localidade other = (Localidade) obj;
        return this.id == other.id;
    }
    
   
 
}
