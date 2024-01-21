/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elementos;

/**
 *
 * @author David Santos
 */
public class Localidade {
    private int id;
    private static int proximoID=0;
    private String nome;
    private boolean ocupada;
    private int flag;

    public Localidade(String nome) {
        this.flag=0;
        this.id = ++proximoID;
        this.nome = nome;
        this.ocupada = false;
    }

    public int getId() {
        return id;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
