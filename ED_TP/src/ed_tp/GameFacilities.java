/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed_tp;

import elementos.Localidade;
import java.util.Random;
import java.util.Scanner;
import estruturas.Network;

/**
 *
 * @author Utilizador
 */
public class GameFacilities<T> implements GameFacilitiesInterface<T> {

    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    private int numVertices;
    private int numArestas;
    private int contador = 0;
    private int opcao = 0;
    private String name;
    Network graph = new Network<>();

    @Override
    public void criarMapa() {

        int preenchimento = 0;
        int cont = 0;

        System.out.print("Introduza o numero de localizaçoes que deseja que o grafo tenha: ");
        numVertices = scan.nextInt();

        while (cont < numVertices) {
            System.out.print("Introduza o nome de uma localizaçao: ");
            name = scan.next();
            Localidade localiade = new Localidade(name);
            graph.addVertex(name);
            cont++;
        }

        System.out.print("Introduza a percentagem de o quanto deseja que o mapa seja conectado:");
        preenchimento = scan.nextInt();

        numArestas = (numVertices * (numVertices - 1)) * (preenchimento / 100);
        System.out.println("Calculo de arestas ficou igual a :" + numArestas);

        do {
            System.out.println("========   Tipo de Mapa   ========");
            System.out.println("         1. Unidirecional         ");
            System.out.println("          2. Bidirecional         ");
            System.out.println("==================================");

            System.out.print("Introduza sua opcao por favor:");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:

                    while (contador < numArestas) {

                        double peso = 0;
                        peso = 1 + (15 - 1) * random.nextDouble();

                        int i = random.nextInt(numArestas - 1); //para linhas
                        int j = random.nextInt(numArestas - 1); //para colunas

                        graph.addEdge(i, j, peso);

                    }
                    break;

                case 2:

                    do {
                        System.out.println("========  Tipo de Aresta  ========");
                        System.out.println("      1. Mesmo peso nas arestas   ");
                        System.out.println("   2. Pesos diferente nas arestas ");
                        System.out.println("==================================");

                        System.out.print("Introduza sua opcao por favor:");
                        opcao = scan.nextInt();

                        switch (opcao) {
                            case 1:
                                while (contador < numArestas) {

                                    double peso = 0;
                                    peso = 1 + (15 - 1) * random.nextDouble();

                                    int i = random.nextInt(numArestas - 1); //para linhas
                                    int j = random.nextInt(numArestas - 1); //para colunas

                                    graph.addEdge(i, j, peso);
                                    graph.addEdge(j, i, peso);

                                }
                                break;
                            case 2:
                                while (contador < numArestas) {

                                    double peso = 0;
                                    peso = 1 + (15 - 1) * random.nextDouble();

                                    double peso2 = 0;
                                    peso2 = 1 + (15 - 1) * random.nextDouble();

                                    int i = random.nextInt(numArestas - 1); //para linhas
                                    int j = random.nextInt(numArestas - 1); //para colunas

                                    graph.addEdge(i, j, peso);
                                    graph.addEdge(j, i, peso2);

                                }
                                break;
                        }
                    } while (opcao != 1 || opcao != 2);
                    break;

            }
        } while (opcao != 1 || opcao != 2);

    }

}
