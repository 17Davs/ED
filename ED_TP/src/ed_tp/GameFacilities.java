/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed_tp;

import elementos.Jogador;
import elementos.Localidade;
import estruturas.ArrayUnorderedList;
import estruturas.Mapa;
import java.util.Random;
import java.util.Scanner;
import estruturas.Network;
import estruturas.NetworkADT;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.api.tree.NewTree;

/**
 *
 * @author Utilizador
 */
public class GameFacilities<T> implements GameFacilitiesInterface<T> {

    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    private int numVertices;
    private int numArestas = 0;
    private int contador = 0;
    private static final String currentWorkingDir = System.getProperty("user.dir");

    private String name;
    Mapa<Localidade> graph;

    public GameFacilities() {
        graph = new Mapa<>();
    }

    public void criarFlags() {
        Localidade[] localidades = graph.getVertexes();

        System.out.println("========   Criação das Flags   ========");
        System.out.println("              Jogador 1                ");
        int contador = 0;
        for (Localidade l : localidades) {
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

        do {
            System.out.print("Introduza o numero de localizaçoes que deseja que o grafo tenha: ");
            numVertices = scan.nextInt();
        } while (numVertices < 10);

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

        numArestas = (int) ((numVertices * (numVertices - 1)) * ((double) preenchimento / 100));
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
                    } while (opcao != 1 && opcao != 2);
                    break;

            }
        } while (opcao != 1 && opcao != 2);
        int opcao2 = 0;
        do {
            System.out.println("============  Export  ============");
            System.out.println("     1. Exportar o mapa e jogar   ");
            System.out.println("2. Apenas jogar sem guardar o mapa");
            System.out.println("==================================");

            System.out.print("Introduza sua opcao por favor:");
            opcao2 = scan.nextInt();

            switch (opcao2) {
                case 1:
                    System.out.print("Indica o nome do mapa: ");
                    String nomeMapa = scan.next();
                    graph.exportToJSON(currentWorkingDir + "/src/Files/" + nomeMapa + ".json");
                    iniciarJogo();
                    break;
                case 2:
                    iniciarJogo();
                    break;
            }
        } while (opcao2 != 1 && opcao2 != 2);

    }

    void iniciarJogo() {

        int opcao = 0;
        int numBots = 0;
        int numIterators = 3;

        System.out.println("  ======= Loading.... ======  ");
        System.out.println("Capture the flag iniciado com sucesso!");

        //introduzir numero de bots
        do {
            System.out.print("Introduza o numero de bots para cada jogador: ");
            numBots = scan.nextInt();
        } while (numBots < numVertices * 0.30);

        Jogador jogador1 = new Jogador(numBots);
        Jogador jogador2 = new Jogador(numBots);

        if (numBots < numIterators) {
            //random
        } else {
            System.out.println("Padronização de bots para o jogador 1");
            for (int b = 1; b <= numBots; b++) {
                System.out.println("Bot numero " + b + " para o jogador 1");
                do {
                    System.out.println();
                    System.out.println("======== Iterador para o bot " + b + " ========");
                    System.out.println("      1. Travessia por largura (BFS)       ");//por retirar
                    System.out.println("    2. Travessia por profundidade (DFS)    ");
                    System.out.println("            3. Shortest Path               ");
                    System.out.println("===========================================");

                    System.out.println("Introduza sua opcao: ");
                    opcao = scan.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Foi escolhido a travessia BFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            Object[] vertexArray = graph.getVertexes();
                            for (Object obj : vertexArray) {
                                try {
                                    Localidade localidade = (Localidade) obj;
                                    System.out.println(localidade);
                                } catch (ClassCastException e) {
                                    System.out.println("Erro: O objeto não é do tipo Localidade");
                                    e.printStackTrace(); // print the stack trace for debugging
                                }
                            }

                            Localidade startVertex = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                for (Object obj : vertexArray) {
                                    try {
                                        Localidade localidade = (Localidade) obj;
                                        if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                            startVertex = localidade;
                                            break;
                                        }
                                    } catch (ClassCastException e) {
                                        System.out.println("Erro: O objeto não é do tipo Localidade");
                                        e.printStackTrace(); // print the stack trace for debugging
                                    }
                                }
                                if (startVertex == null) {
                                    System.out.println("Nome do vertice nao existe no grafo");
                                }
                            } while (startVertex == null);

                             {
                                Iterator<Localidade> bfsIterator = graph.iteratorBFS(startVertex);
                                jogador1.setIteradorBFSParaBot(b, bfsIterator);
                            }
                            break;

                        case 2:
                            System.out.println("Foi escolhido a travessia DFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            for (Localidade localidade : graph.getVertexes()) {
                                System.out.println(localidade);
                            }

                            Localidade startVertex2 = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                for (Localidade localidade : graph.getVertexes()) {
                                    if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                        startVertex = localidade;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }
                            } while (startVertex2 != null);

                            jogador1.setIteradorDFSParaBot(b, graph.iteratorDFS(startVertex2));
                            break;
                        case 3:
                            System.out.println("Foi escolhido a travessia Shortest Path para o bot" + b);

                            System.out.println("Foi escolhido a travessia DFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            for (Localidade localidade : graph.getVertexes()) {
                                System.out.println(localidade);
                            }

                            Localidade startVertex3 = null;
                            Localidade targetVertex3 = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                System.out.println("Introduza o nome da sua localizacao final: ");
                                String localidadeFinal = scan.next();

                                for (Localidade localidade : graph.getVertexes()) {
                                    if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                        startVertex3 = localidade;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }

                                for (Localidade localidade2 : graph.getVertexes()) {
                                    if (localidade2.getNome().equalsIgnoreCase(localidadeFinal)) {
                                        targetVertex3 = localidade2;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }
                            } while (startVertex3 != null && targetVertex3 != null);

                            jogador1.setIteradorShortestPathParaBot(b, graph.iteratorShortestPath(startVertex3, targetVertex3));
                            break;
                    }

                } while (b != numBots);

            }

            System.out.println("Padronização de bots para o jogador 2");
            for (int b = 0; b <= numBots; b++) {
                System.out.println("Bot numero " + b + " para o jogador 2");
                do {
                    System.out.println();
                    System.out.println("======== Iterador para o bot " + b + " ========");
                    System.out.println("      1. Travessia por largura (BFS)       ");//por retirar
                    System.out.println("    2. Travessia por profundidade (DFS)    ");
                    System.out.println("            3. Shortest Path               ");
                    System.out.println("===========================================");

                    System.out.println("Introduza sua opcao: ");
                    opcao = scan.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("Foi escolhido a travessia BFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            for (Localidade localidade : graph.getVertexes()) {
                                System.out.println(localidade);
                            }

                            Localidade startVertex = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                for (Localidade localidade : graph.getVertexes()) {
                                    if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                        startVertex = localidade;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }
                            } while (startVertex != null);

                            jogador1.setIteradorBFSParaBot(b, graph.iteratorBFS(startVertex));
                            break;

                        case 2:
                            System.out.println("Foi escolhido a travessia DFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            for (Localidade localidade : graph.getVertexes()) {
                                System.out.println(localidade);
                            }

                            Localidade startVertex2 = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                for (Localidade localidade : graph.getVertexes()) {
                                    if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                        startVertex = localidade;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }
                            } while (startVertex2 != null);

                            jogador1.setIteradorDFSParaBot(b, graph.iteratorDFS(startVertex2));
                            break;
                        case 3:
                            System.out.println("Foi escolhido a travessia Shortest Path para o bot" + b);

                            System.out.println("Foi escolhido a travessia DFS para o bot" + b);

                            System.out.println("Lista de Localidades");
                            for (Localidade localidade : graph.getVertexes()) {
                                System.out.println(localidade);
                            }

                            Localidade startVertex3 = null;
                            Localidade targetVertex3 = null;
                            do {
                                System.out.println("Introduza o nome da sua localizacao inicial: ");
                                String localidadeInicial = scan.next();
                                System.out.println("Introduza o nome da sua localizacao final: ");
                                String localidadeFinal = scan.next();

                                for (Localidade localidade : graph.getVertexes()) {
                                    if (localidade.getNome().equalsIgnoreCase(localidadeInicial)) {
                                        startVertex3 = localidade;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }

                                for (Localidade localidade2 : graph.getVertexes()) {
                                    if (localidade2.getNome().equalsIgnoreCase(localidadeFinal)) {
                                        targetVertex3 = localidade2;
                                        break;
                                    } else {
                                        System.out.println("Nome do vertice nao existe no grafo");
                                    }
                                }
                            } while (startVertex3 != null && targetVertex3 != null);

                            jogador1.setIteradorShortestPathParaBot(b, graph.iteratorShortestPath(startVertex3, targetVertex3));
                            break;
                    }

                } while (b != numBots);
            }
        }

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

                    graph.showMapa();

                    break;
            }

        } while (opcao != 0);

    }

}
