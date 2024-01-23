/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed_tp;

import elementos.Bot;
import elementos.Flag;
import elementos.Jogador;
import elementos.Localidade;
import estruturas.EmptyCollectionException;
import estruturas.LinkedQueue;

import estruturas.Mapa;
import interfacesADT.QueueADT;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Utilizador
 */
public class GameFacilities<T> implements GameFacilitiesInterface<T> {

    Scanner scan = new Scanner(System.in);
    Random random = new Random();
    private int numVertices;
    private int numArestas = 0;
    private Jogador jogadorAtual;
    private QueueADT<Jogador> jogadores;
    public String nomeMapa;

    private static final String currentWorkingDir = System.getProperty("user.dir");

    private String name;
    Mapa<Localidade> graph;

    public GameFacilities() {
        graph = new Mapa<>();
    }

    /*
    Este metodo vai ser usado para cada Jogador definir a sua Flag numa localidade(vertice) do mapa(grafo)
    onde vai guardar todos os vertices num array de Object e depois vai copia-los para
    um array de localidades. Apos isso apresenta todo o conteudo do array localidades e cada jogador vai escolher o indice 
    da localidade onde deseja colocar a sua flag sendo que duas flags nao podem ficar na mesma localidade
    
    @param jogador Vai armazenar qual jogador vai definir a sua flag
    */
    public void criarFlags(Jogador jogador) {

        // Array de localidedes 
        Object[] vertices = graph.getVertexes();

        // Converte o array de objetos para um array de Localidade manualmente
        Localidade[] localidades = new Localidade[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            localidades[i] = (Localidade) vertices[i];
        }

        System.out.println("======== Definir Base ========");
        System.out.println("Escolha uma base entre as seguintes localidades:");

        for (int i = 0; i < localidades.length; i++) {
            System.out.println((i + 1) + ". " + localidades[i].getNome());
        }

        int opcaoBase = 0;
        Localidade base = null;

        do {
            System.out.print("Introduza o número da localidade para a base: ");
            int opcao = scan.nextInt();

            if (opcao >= 1 && opcao <= localidades.length) {
                base = localidades[opcao - 1];

                if (base.getFlag() == null) {
                    System.out.println("Base selecionada: " + base.getNome());
                    Flag flag = new Flag(jogador);
                    base.setFlag(flag);
                } else {
                    System.out.println("Erro: A base já possui uma flag. Escolha outra base.");
                    base = null;
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

        } while (base == null);

        jogador.setBase(base);
        System.out.println("Flag do jogador " + jogador.getId() + " colocada na base " + base.getNome());
        System.out.println("==================================");

    }

    /*
    Este metodo tera como funcionalidade a criaçao de um mapa, onde
    teremos de colocar as especificaçoes do mapa a ser criado adicionando todas as as
    localidades(vertices) num array Temporario do tipo localidade. Apos especificar todas as caracteristicas do mapa 
    temos a opcao de guardar o mapa ou de apenas jogar., onde guardar vai guardar num ficheiro JSON.
    */
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

        System.out.println("numVertices:" + numVertices + "  preenchimento: " + preenchimento);
        System.out.println("Calculo de arestas ficou igual a :" + numArestas);

        do {
            System.out.println("========   Tipo de Mapa   ========");
            System.out.println("         1. Unidirecional         ");
            System.out.println("          2. Bidirecional         ");
            System.out.println("==================================");

            System.out.print("Introduza sua opcao por favor:");
            opcao = scan.nextInt();
            int contador = 0;
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
                        System.out.println();
                        System.out.println("========  Tipo de Aresta  ========");
                        System.out.println("      1. Mesmo peso nas arestas   ");
                        System.out.println("   2. Pesos diferente nas arestas ");
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
                                while (contador < numArestas) {
                                    int peso = 1 + random.nextInt(15);
                                    int peso2 = 1 + random.nextInt(15);
                                    int i = random.nextInt(numVertices);
                                    int j = random.nextInt(numVertices);

                                    graph.addEdge(tempLocalidade[i], tempLocalidade[j], peso);
                                    graph.addEdge(tempLocalidade[j], tempLocalidade[i], peso2);

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
            System.out.println();
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
                    System.out.println();
                    iniciarJogo();
                    break;
                case 2:
                    iniciarJogo();
                    break;
            }
        } while (opcao2 != 1 && opcao2 != 2);

    }

    
    /*
    Este metodo é responsavel por definir qual o iterador cada Bot de cada Jogador 
    ira seguir para encontrar a flag adversaria no mapa.
    Onde aqui vamos adicionar os dois jogadores na Queue onde foi definido uma 
    queue que so admite o tipo Jogador e definir a Flag de cada um
    no mapa. Apos isso so entra num loop para definir o iterador de cada Bot
    */
    void padronizarBots() throws EmptyCollectionException {
        int opcao = 0;
        int numBots = 0;
        int numIterators = 3;
        Bot bot = null;
        do {
            System.out.print("Introduza o numero de bots para cada jogador: ");
            numBots = scan.nextInt();
        } while (numBots < numVertices * 0.30);

        Jogador jogador1 = new Jogador(numBots);
        Jogador jogador2 = new Jogador(numBots);

        //garantir estar vazio
        while (!jogadores.isEmpty()) {
            jogadores.dequeue();
        }
        //adicionar os nossos jogadores
        jogadores.enqueue(jogador1);
        jogadores.enqueue(jogador2);

        System.out.println();
        System.out.println("Flag jogador 1");
        criarFlags(jogador1);
        System.out.println();
        System.out.println("Flag jogador 2");
        criarFlags(jogador2);

        if (numBots < numIterators) {
            //random
        } else {
            for (int vezes = 0; vezes < jogadores.size(); vezes++) {
                jogadorAtual = jogadores.dequeue();
                System.out.println();
                System.out.println("Padronização de bots para o jogador " + jogadorAtual.getId());
                for (int b = 1; b <= numBots;) {
                    bot = new Bot();
                    System.out.println("Bot numero " + bot.getId() + " para o jogador 1");
                    System.out.println("======== Iterador para o bot " + bot.getId() + " ========");
                    System.out.println("      1. Travessia por largura (BFS)       ");
                    System.out.println("    2. Travessia por profundidade (DFS)    ");
                    System.out.println("            3. Shortest Path               ");
                    System.out.println("===========================================");

                    System.out.println("Introduza sua opcao: ");
                    opcao = scan.nextInt();

                    Localidade startVertex = jogadorAtual.getBase();
                    switch (opcao) {
                        case 1:
                            System.out.println("Foi escolhido a travessia BFS para o bot " + b);

                            if (startVertex != null) {
                                Iterator<Localidade> bfsIterator = graph.iteratorBFS(startVertex);

                                try {
                                    System.out.println("Atribuindo o iterador BFS para o Bot " + bot.getId());
                                    jogadorAtual.iteratorToBot(bot, bfsIterator);
                                    System.out.println("Iterador configurado para o Bot " + bot.getId());
                                } catch (EmptyCollectionException ex) {

                                }
                            } else {
                                System.out.println("Erro para colocar  bot na base onde esta a flag do jogador");
                            }
                            b++;
                            break;

                        case 2:
                            System.out.println("Foi escolhido a travessia DFS para o bot" + b);

                            if (startVertex != null) {

                                Iterator<Localidade> dfsIterator = graph.iteratorDFS(startVertex);

                                try {
                                    System.out.println("Atribuindo o iterador DFS para o Bot " + bot.getId());
                                    jogadorAtual.iteratorToBot(bot, dfsIterator);
                                    System.out.println("Iterador configurado para o Bot " + bot.getId());
                                } catch (EmptyCollectionException ex) {

                                }
                            } else {
                                System.out.println("Erro para colocar  bot na base onde esta a flag do jogador");
                            }
                            b++;
                            break;
                        case 3:
                            System.out.println("Foi escolhido a travessia Shortest Path para o bot" + b);
                            Localidade targetVertex = null;
                            if (jogadorAtual.equals(jogador1)) {
                                targetVertex = jogador2.getBase();
                            } else {
                                targetVertex = jogador1.getBase();
                            }

                            if (startVertex != null && targetVertex != null) {

                                Iterator<Localidade> shortestPathIterator = graph.iteratorShortestPath(startVertex, targetVertex);

                                try {
                                    System.out.println("Atribuindo o iterador Shortest Path para o Bot " + bot.getId());
                                    jogadorAtual.iteratorToBot(bot, shortestPathIterator);
                                    System.out.println("Iterador configurado para o Bot " + bot.getId());
                                } catch (EmptyCollectionException ex) {

                                }
                            } else {
                                System.out.println("Erro para colocar  bot na base onde esta a flag do jogador");
                            }
                            b++;

                            break;
                    }
                }
                //meter na fila novamente
                jogadores.enqueue(jogadorAtual);
            }

        }
    }

    /*
    Este metodo tera como funcionalidade receber qual estrategia vamos adotar
    para os nossos bots num mapa especifico e tambem tem a funcionalidade de mostrar o Mapa
    */
    void iniciarJogo() {

        int opcao = 0;
        System.out.println("  ======= Loading.... ======  ");
        System.out.println("Capture the flag iniciado com sucesso!");

        do {
            System.out.println();
            System.out.println("============ Menu do Jogo =============");
            System.out.println("      1. Padronizar Bots e Jogar       ");//por retirar
            System.out.println("         2. Visualizar o mapa          ");
            System.out.println("           0. Sair do jogo             ");
            System.out.println("=======================================");

            System.out.println("Introduza sua opcao: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1: {
                    try {
                        padronizarBots();
                    } catch (EmptyCollectionException ex) {
                        Logger.getLogger(GameFacilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 2:
                    graph.showMapaFromJSON(currentWorkingDir + "/src/Files/" + nomeMapa + ".json");

                    break;
            }

        } while (opcao != 0);

    }

    /*
    Este metodo sera o responsavel pela interacao com o jogo capture the flag,
    onde tera os queues de jogador para determinar é a vez de qual jogador jogar e
    tera a queue de Bots que sera preenchida alternadamente entre os bots dos dois jogadores,
    assim que um bot de um jogador jogar este metodo faz dequeue e enqueue tanto na fila de jogadores
    quanto na fila de bots
    */
    public void oJogo() throws EmptyCollectionException {
        boolean exit = true;
        int numJogadas = 0;

        //fazer aleatorio
        if (jogadores.size() == 2) {
            whoPlays(jogadores.dequeue(), jogadores.dequeue());
        }

        while (!exit) {
            //cada jogada
            while (!jogadores.isEmpty()) {
                jogadorAtual = jogadores.dequeue();
                //fazer dequeue do proximo bot
                Bot bot = jogadorAtual.getNextBot();

                Iterator<Localidade> itr = bot.getItr();
                if (itr.hasNext()) {
                    Localidade local = itr.next();
                    if (local.isOcupada() && local.getFlag() != null) {
                        //logica se estiver ocupada

                    } else if (local.isOcupada() && local.getFlag() == null) {
                        //não pode ir para ali
                        //opcao esperar, 
                    }
                }

                jogadores.enqueue(jogadorAtual);
            }
        }

    }

    /*
    Este metodo define de forma aleatoria qual jogador
    ira jogar primeiro assim adicionando na queue os jogadores por 
    ordem de jogo.
    
    @param jogador1 Instancia da classe jogador que vai iniciar a partida
    @param jogador2 Instancia da classe jogador que vai iniciar a partida
    */
    void whoPlays(Jogador jogador1, Jogador jogador2) {

        int randomNum = random.nextInt(jogador2.getId() - jogador1.getId()) + jogador1.getId();

        if (randomNum == 1) {
            System.out.println("Jogador 1 irá jogar primeiro");
            jogadores.enqueue(jogador1);
            jogadores.enqueue(jogador2);
        } else {
            System.out.println("Jogador 2 irá jogar primeiro");
            jogadores.enqueue(jogador2);
            jogadores.enqueue(jogador1);
        }

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        GameFacilities game = new GameFacilities<>();
        Mapa map = new Mapa<>();
        String currentWorkingDir = System.getProperty("user.dir");
        String nMapa;

        do {
            System.out.println();
            System.out.println("======== Capture the flag ========");
            System.out.println("    1. Criaçao de um novo mapa    ");
            System.out.println("     2. Importação de um mapa     ");
            System.out.println("         0. Sair do jogo          ");
            System.out.println("==================================");

            System.out.print("Introduza sua opcao por favor:");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Criação de um mapa novo!");
                    game.criarMapa();
                    game.iniciarJogo();
                    break;
                case 2:
                    System.out.println("Importação de um mapa!");
                    System.out.print("Introduza o nome do mapa:");
                    nMapa = scan.next();
                    game.nomeMapa = nMapa;
                    game.graph = Mapa.importJSON(currentWorkingDir + "/src/Files/" + nMapa + ".json");

                    game.iniciarJogo();
                    break;
                case 0:
                    System.out.println("Fechando o jogo!");
                    break;
                default:
                    System.out.print("Opção inválida introduza novamente a sua opção:");
                    break;
            }
        } while (opcao != 0);
    }

}
