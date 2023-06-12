
package analizadorlexico;

import java.util.ArrayList;

/**
 *
 * @author gerry
 */
public class AnalizadorLexico {

    private final ArrayList<String[]> componentesLexicos = new ArrayList<>();
    private int yyline;
    
    private void analisis(String codigo){
        String renglones[] = codigo.split("[;\n]");
        yyline = 1;
        for (String renglon : renglones) {
            System.out.println("Linea " + yyline + ": " + renglon);
            getToken(renglon);
            yyline++;
        }
    }
    
    private void analisis2(String codigo){
        String renglones[] = codigo.split("[;\n]");
        yyline = 1;
        for (String renglon : renglones) {
            String palabra="";
            char caract = ' ',carsig =' ';
            for (int i = 0; i < renglon.length(); i++) {
                caract=renglon.charAt(i);   //System.out.println(caract +" - "+ i);
                palabra=palabra+caract;
                
                if(i+1 == renglon.length()){
                    getToken(palabra);
                } else {
                    carsig=renglon.charAt(i+1);
                    if(carsig != ' '){/*palabra=palabra+caract;*/}
                    else {
                        getToken(palabra);
                        palabra="";
                        i++;
                    }
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnalizadorLexico AL = new AnalizadorLexico();
        
        String texto = "x = 10; a = 10;";
        
        AL.analisis2(texto);
        AL.imprimirTokens();
    }
    
}
