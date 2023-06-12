/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class AnalizadorLex {
    private final ArrayList<String[]> componentesLexicos = new ArrayList<>();
    private int yyline;
    
    private void analisis(){
        ArrayList<String> renglones = readFile();
        yyline = 1;
        for (String renglon : renglones) {
            String palabra = "";
            char caract;
            char carsig;
            for (int i = 0; i < renglon.length(); i++) {
                caract=renglon.charAt(i);   //System.out.println(caract +" - "+ i);
                palabra=palabra+caract;
                
                if(i+1 < renglon.length()){
                    carsig=renglon.charAt(i+1);
                    if(carsig == ' ' || Grafos.esSigno(carsig+"") == 0){
                        getToken(palabra);
                        palabra="";
                        i++;}
                } else {
                    getToken(palabra);
                }
                
            }
            yyline++;
        }
    }
    
    public void getToken(String linea){
        if(Grafos.esOperador(linea) != -1) componentesLexicos.add(new String[]{linea,"OPERADOR"});
        else if(Grafos.esPalabraReservada(linea)!=-1) componentesLexicos.add(new String[]{linea,"PALABRA RESERVADA"});
        else if(Grafos.esIdentificador(linea)) componentesLexicos.add(new String[]{linea,"IDENTIFICADOR"});
        else if(Grafos.esEntero(linea)) componentesLexicos.add(new String[]{linea,"ENTERO"});
        else if(Grafos.esDoble(linea)) componentesLexicos.add(new String[]{linea,"DOBLE"});
        else if(Grafos.esSigno(linea) != -1) componentesLexicos.add(new String[]{linea,"SIGNO"});
        
    }
    
    public void imprimirTokens(){
        System.out.println("IMPRIMIENDO TOKENS");
        for (String[] componenteLexico : componentesLexicos) {
            System.out.println(componenteLexico[0]+" - "+componenteLexico[1]);
        }
    }
    
    
    
    
    public static ArrayList<String> readFile() {
        ArrayList<String> dataFile = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("codigo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int commentIndex = line.indexOf('#');
                if (commentIndex != -1) {
                    line = line.substring(0, commentIndex).trim();
                }
                line = line.trim(); // Eliminar espacios en blanco al inicio y final de la línea
                if (!line.isEmpty()) {
                    dataFile.add(line);
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading codigo.txt file: " + e.getMessage());
        }

        return dataFile;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnalizadorLex AL = new AnalizadorLex();
        
        AL.analisis();
        AL.imprimirTokens();
    }
   

}
