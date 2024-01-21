/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ed_tp;

import estruturas.Network;
import java.util.Scanner;

/**
 *
 * @author David Santos
 */
public class ED_TP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        GameFacilities game = new GameFacilities<>();
        
        do {
            System.out.println();
            System.out.println("======== Capture the flag ========");
            System.out.println("    1. Criaçao de um novo mapa    ");
            System.out.println("     2. Importação de um mapa     ");
            System.out.println("         0. Sair do jogo          ");
            System.out.println("==================================");
            
            System.out.print("Introduza sua opcao por favor:");
            opcao = scan.nextInt();
            
            switch(opcao) {
                case 1:
                    System.out.println("Criação de um mapa novo!");
                    game.criarMapa();
                    
                    break;
                case 2:
                    System.out.println("Importação de um mapa!");
                    //defaults maps
                    //ou
                    //pedir localizaçao do mapa a importar
                    break;
                case 0:
                    System.out.println("Fechando o jogo!");
                    break;
                default:
                    System.out.print("Opção inválida introduza novamente a sua opção:");
                    break;
            }
        }while(opcao != 0);
    }
    
}
