/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed_tp;

import elementos.Localidade;
import estruturas.ArrayUnorderedList;
import java.util.Random;
import java.util.Scanner;
import estruturas.Network;
import estruturas.NetworkADT;
import java.util.Iterator;
import jdk.nashorn.api.tree.NewTree;

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

    private String name;
    NetworkADT<Localidade> graph;

    public GameFacilities() {
        graph = new Network<>();
    }

    
    public void criarFlags(){
        Localidade[] localidades = ((Network)graph).getVertexes();
        
        
        System.out.println("========   Criação das Flags   ========");
        System.out.println("              Jogador 1                ");
        int contador=0; 
        for(Localidade l: localidades){
            System.out.println(++contador);
        }
        
        
        System.out.print("Introduza sua opcao por favor:");
        int op = scan.nextInt();
        System.out.println("           2. Bidirecional             ");
        System.out.println("==================================");

        //listar as lovalidades 
        
        System.out.print("Introduza sua opcao por favor:");
        op = scan.nextInt();
    
}
    


    @Override
    public void criarMapa() {

        int opcao = 0;


        int preenchimento = 0;
        int cont = 0;

        System.out.print("Introduza o numero de localizaçoes que deseja que o grafo tenha: ");
        numVertices = scan.nextInt();

        Localidade[] tempLocalidade = new Localidade[numVertices];

        while (cont < numVertices) {
            System.out.print("Introduza o nome de uma localizaçao: ");
            name = scan.next();
            Localidade localidade = new Localidade(name);
            graph.addVertex(localidade);
            tempLocalidade[cont] = localidade;
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

                        int peso = 1 + (15 - 1) * random.nextInt();
                        int i = random.nextInt(numVertices);
                        int j = random.nextInt(numVertices);

                        graph.addEdge(tempLocalidade[i], tempLocalidade[j], peso);
                        contador++;

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
                                //certificar se fica bem dentro do ciclo isConnected()
                                do {
                                    while (contador < numArestas) {

                                        int peso = 1 + (15 - 1) * random.nextInt();
                                        int i = random.nextInt(numVertices);
                                        int j = random.nextInt(numVertices);

                                        graph.addEdge(tempLocalidade[i], tempLocalidade[j], peso);
                                        contador++;

                                    }
                                } while (graph.isConnected());
                                break;
                            case 2:
                                while (contador < numArestas) {

                                    int peso = 0;
                                    peso = 1 + (15 - 1) * random.nextInt();

                                    int peso2 = 0;
                                    peso2 = 1 + (15 - 1) * random.nextInt();

                                    int i = random.nextInt(numArestas - 1); //para linhas
                                    int j = random.nextInt(numArestas - 1); //para colunas

                                    graph.addEdge(tempLocalidade[i], tempLocalidade[j], peso);
                                    graph.addEdge(tempLocalidade[j], tempLocalidade[i], peso);
                                    contador++;

                                }
                                break;
                        }
                    } while (opcao != 1 || opcao != 2);
                    break;

            }
        } while (opcao != 1 || opcao != 2);
        iniciarJogo();
    }

    void iniciarJogo() {

        int opcao = 0;

        System.out.println("  ======= Loading.... ======  ");
        System.out.println("Capture the flag iniciado com sucesso!");

        //introduzir numero de bots
        do {
            System.out.println();
            System.out.println("======== Menu do Jogo ========");
            System.out.println("     1. Padronizar Bots       ");//por retirar
            System.out.println("    2. Visualizar o mapa      ");
            System.out.println("    0. Sair do jogo           ");
            System.out.println("==============================");

            System.out.println("Introduza sua opcao: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    if (graph instanceof Network) {
                        ((Network) graph).showMapa();
                    } else {
                        System.out.println("O objeto graph não é uma instância de Network.");
                    }
                    break;
            }

        } while (opcao != 0);

    }

    
    
}
