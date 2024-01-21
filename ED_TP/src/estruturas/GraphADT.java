/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package estruturas;

import java.util.Iterator;

/**
 *
 * @author Utilizador
 */
public interface GraphADT<T> {

    void addVertex(T vertex);
    void removeVertex(T vertex);
    void addEdge(T vertex1, T vertex2);
    void removeEdge(T vertex1, T vertex2);
    Iterator iteratorBFS(T startVertex) throws Exception;
    Iterator iteratorDFS(T startVertex) throws Exception;
    Iterator iteratorShortestPath(T startVertex, T targetVertex);
    boolean isEmpty();
    boolean isConnected();
    int size();
    String toString();   


}
