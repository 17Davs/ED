/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Utilizador
 */
public class Network<T> implements NetworkADT<T> {

    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected double[][] matrizPeso;
    protected boolean[][] adjMatrix;
    protected T[] vertices;

    /*
     * Cria um grafo vazio
     */
    public Network() {
        numVertices = 0;
        this.matrizPeso = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T vertex) {
        if (vertices.length == size()) {
            expandCapacity();
        } else {
            vertices[numVertices] = vertex;
            for (int i = 0; i <= numVertices; i++) {
                matrizPeso[numVertices][i] = 0;
                matrizPeso[i][numVertices] = 0;
            }
            numVertices++;
        }
    }

    private int findIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    private void expandCapacity() {
        T[] newVertices = (T[]) (new Object[DEFAULT_CAPACITY * 2]);
        double[][] newAdjMatrix = new double[DEFAULT_CAPACITY * 2][DEFAULT_CAPACITY * 2];

        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                newAdjMatrix[i][j] = matrizPeso[i][j];
            }
        }
        vertices = newVertices;
        matrizPeso = newAdjMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        int pos = findIndex(vertex);

        if (pos != -1) {
            if (numVertices > 1) {
                vertices[pos] = vertices[numVertices - 1];

                for (int i = 0; i < numVertices; i++) {
                    matrizPeso[i][pos] = matrizPeso[i][numVertices - 1];
                    matrizPeso[pos][i] = matrizPeso[numVertices - 1][i];
                }

                numVertices--;

                vertices[numVertices] = null;
                for (int i = 0; i < numVertices; i++) {
                    matrizPeso[numVertices][i] = 0;
                    matrizPeso[i][numVertices] = 0;
                }

            } else {
                numVertices = 0;
                vertices[0] = null;
                matrizPeso[0][0] = 0;
            }

        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    // retorna a posicao de um vertice no array de vertices
    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Vertex not found: " + vertex);
    }

    //colocar todos a private para nao conseguirmos invocar pois 
    //se estamos a usar um network nao usaremos arestas sem peso
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        remvoveEdge(getIndex(vertex1), getIndex(vertex2));
    }

    private void remvoveEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    /*
    @Override
    public Iterator iteratorBFS(T startVertex) throws Exception {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        int startIndex = getIndex(startVertex);
        if (!indexIsValid(startIndex))
            return resultList.iterator();
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++)
            visited[i] = false;

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }*/
 /*
    @Override
    public Iterator iteratorDFS(T startVertex) throws Exception {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        int startIndex = getIndex(startVertex);

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            // Encontre um vértice adjacente a x que não foi visitado e coloque-o na pilha
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }

            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }

        }

        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getIndex(startVertex);
        int targetIndex = getIndex(targetVertex);

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            throw new IllegalArgumentException("Invalid start or target vertex");
        }

        // Dijkstra's Algorithm
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> previousVertices = new HashMap<>();
        PriorityQueue<VertexDistancePair> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.distance));

        for (int i = 0; i < numVertices; i++) {
            distances.put(i, Double.POSITIVE_INFINITY);
            previousVertices.put(i, null);
        }

        distances.put(startIndex, 0.0);
        priorityQueue.offer(new VertexDistancePair(startIndex, 0.0));

        while (!priorityQueue.isEmpty()) {
            VertexDistancePair currentPair = priorityQueue.poll();
            int currentVertex = currentPair.vertex;
            double currentDistance = currentPair.distance;

            if (currentVertex == targetIndex) {
                // Construir o caminho mais curto
                List<T> shortestPath = buildShortestPath(previousVertices, startIndex, targetIndex);
                return shortestPath.iterator();
            }

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjMatrix[currentVertex][neighbor]) {
                    double weight = getEdgeWeight(currentVertex, neighbor);
                    double newDistance = currentDistance + weight;

                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previousVertices.put(neighbor, currentVertex);
                        priorityQueue.offer(new VertexDistancePair(neighbor, newDistance));
                    }
                }
            }
        }

        // Sem caminho encontrado
        return Collections.emptyIterator();
    }*/
    private double getEdgeWeight(int currentVertex, int neighbor) {
        if (indexIsValid(currentVertex) && indexIsValid(neighbor)) {
            return matrizPeso[currentVertex][neighbor];
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    private List<T> buildShortestPath(Map<Integer, Integer> previousVertices, int start, int target) {
        List<T> path = new ArrayList<>();
        int current = target;

        while (current != start) {
            path.add(vertices[current]);
            current = previousVertices.get(current);
        }

        path.add(vertices[start]);
        Collections.reverse(path);

        return path;
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        boolean[] visited = new boolean[numVertices];
        int start = findFirstNonNullVertex();

        //buscaProfundidade(start, visited);
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && vertices[i] != null) {
                return false;
            }
        }

        return true;

    }

    private int findFirstNonNullVertex() {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i] != null) {
                return i;
            }
        }
        return -1; // Nenhum vértice não nulo encontrado (o grafo está vazio)
    }

    /*
    private void buscaProfundidade(int start, boolean[] visited) {
        visited[start] = true;

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[start][i] && !visited[i]) {
                buscaProfundidade(i, visited);
            }
        }
    }*/
    @Override
    public int size() {
        return numVertices;
    }

    /*
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        } else {
            throw new IllegalArgumentException("Vertices inválidos: " + vertex1 + ", " + vertex2);
        }
    }*/
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortestPathWeight'");
    }

    private boolean indexIsValid(int index) {
        return index >= 0 && index < numVertices;
    }

    @Override
    public Iterator iteratorBFS(T startVertex) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator iteratorDFS(T startVertex) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = findIndex(vertex1);
        int index2 = findIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            matrizPeso[index1][index2] = weight;
            matrizPeso[index2][index1] = weight;
        } else {
            throw new IllegalArgumentException("Vértices inválidos: " + vertex1 + ", " + vertex2);
        }
    }

}