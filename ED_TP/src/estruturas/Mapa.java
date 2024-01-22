/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

import elementos.Localidade;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author David Santos
 */
public class Mapa<T> extends Network<T>{

    public Mapa() {
    }

    public Mapa(int capacidade) {
        super(capacidade);
    }
    
    
    
      public T[] getVertexes() {
        T[] temp = (T[]) new Object[numVertices];
        int i = 0;
        for (i = 0; i < numVertices; i++) {
            temp[i] = vertices[i];
        }
        return temp;
     }
      

    
      
      
    public void exportToJSON(String filePath) {
        JSONObject mapaJSON = new JSONObject();
        mapaJSON.put("NumeroVertices", numVertices);
      
        JSONArray localidadesJSON = new JSONArray();
        for (int i = 0; i < numVertices; i++) {
            JSONObject localizacaoJSON = new JSONObject();
            localizacaoJSON.put("Nome", vertices[i].toString()); 
            localidadesJSON.add(localizacaoJSON);
        }
        mapaJSON.put("Localidades", localidadesJSON);

        JSONArray arestasJSON = new JSONArray();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (isAdjacent(i, j)) {
                    JSONObject arestaJSON = new JSONObject();
                    arestaJSON.put("LocalizacaoOrigem", vertices[i].toString());  
                    arestaJSON.put("LocalizacaoDestino", vertices[j].toString());  
                    arestaJSON.put("Peso", adjMatrix[i][j]);
                    arestasJSON.add(arestaJSON);
                }
            }
        }
        mapaJSON.put("Arestas", arestasJSON);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(mapaJSON.toJSONString());
            System.out.println("Mapa exportado com sucesso para: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Network importJSON(String filePath) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) jsonParser.parse(reader);
            } catch (ParseException ex) {
               
            }

            long numVertices = (Long) jsonObject.get("NumeroVertices");
            Mapa<Localidade> mapa = new Mapa<>((int)numVertices);
            
            JSONArray LocalidadesArray = (JSONArray) jsonObject.get("Localidades");
            for (int i = 0; i < LocalidadesArray.size(); i++) {
                // Adicione seus vértices ao grafo, dependendo da estrutura do JSON
                
                JSONObject localidade = (JSONObject) jsonObject.get(i);
                
            
            }
            JSONArray arestas = (JSONArray) jsonObject.get("Arestas");

            

            for (Object arestaObj : arestas) {
                JSONObject aresta = (JSONObject) arestaObj;
                long origem = (Long) aresta.get("Origem");
                long destino = (Long) aresta.get("Destino");
                double peso = (Double) aresta.get("Peso");

            }
            
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    public void showMapa() {
        System.out.println("Vertices:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }

        System.out.println("\nArestas:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (isAdjacent(i, j)) {
                    System.out.println(vertices[i] + " -- " + vertices[j] + " (Peso: " + adjMatrix[i][j] + ")");
                }
            }
        }
    }

    
    
}
