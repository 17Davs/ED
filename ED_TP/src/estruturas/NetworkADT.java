/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package estruturas;

import elementos.Localidade;

/**
 *
 * @author Utilizador
 */
public interface NetworkADT<T> extends GraphADT<T> {
    
    void addEdge(T vertex1, T vertex2, double weight);
    double shortestPathWeight(T vertex1, T vertex2);
    
}
