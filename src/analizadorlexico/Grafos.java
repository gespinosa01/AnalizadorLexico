/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico;

/**
 *
 * @author gerry
 */
public class Grafos {
    
    private static final String[] palabrasReservadas = {"iniciar","fin","retornar",
        "caso","sino","si","comparar","no","mientras","borrar","continuar","pausar",
        "construir","nuevo","mover","girar","rotar","girar","cuando","entero",
        "flotante","decimal","mostrar","responder","tamaño","esperar", "repetir", 
        "entonces", "dezplazar","detener","cambiar","eliminar","reiniciar", "recuperar", 
        "unir","separar","esconder","elegir","bloquear","presionar","enviar", "recibir",
        "preguntar","esperar","crear","siguiente","fijar","lista","funcion","quitar",
        "corto","vacio","importar","largo","corto","estatico","publico", "protegido",
        "privado", "implementar", "hilo","este","volatil","romper","extiende","intentar",
        "cachar","etiqueta","doble", "flotante"
    };
    private static final String[] operadores = {"+", "-", "*", "/", "%", "=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|=",
        "==", "!=", "<", ">", "<=", ">=", "&&", "||", "!", "++", "--", "<<", ">>", ">>>", "&", "|", "^", "~"};
    private static final String[] signos = {";", ",", ".", "(",")", "{","}", "[","]", "\"\"", "''", "//", "/* */"};

    
    public static boolean esIdentificador(String cadena){
        int M[][]= {{1, 1,-1,-1},
                    {1, 1, 1,-1}};
        int col, fil = 0; char c;
        for (int i = 0; i < cadena.length(); i++) {
            c = cadena.charAt(i);
            if (c>=65 && c<=90 || c>=97 && c<=122) col = 0;
            else if (c>=48 && c<=57) col = 2;
            else if (c == '_') col = 1;
            else col = 3;
            fil = M[fil][col];
            
            if (fil == -1) return false;
        }
        return true;
    }
    
    public static boolean esEntero(String cadena){
        int M[][]= {{1, 1, 1, 0},
                    {0, 0, 1, 0}};
        int ent, edo = 0; char c;
        for (int i = 0; i < cadena.length(); i++) {
            c = cadena.charAt(i);
            if (c == '+') ent = 0;
            else if (c == '-') ent = 1;
            else if (c>=48 && c<=57) ent = 2;
            else ent = 3;
            edo = M[edo][ent];
            
            if (edo == 0) return false;
        }
        return true;
    }
    
    public static boolean esDoble(String cadena){
        int M[][]= {{1, 1, 1, 2, 0},
                    {0, 0, 1, 2, 0},
                    {0, 0, 2, 0, 0}};
        int ent = 0, edo = 0; char c;
        for (int i = 0; i < cadena.length(); i++) {
            c = cadena.charAt(i);
            if (c == '+') ent = 0;
            else if (c == '-') ent = 1;
            else if (c>=48 && c<=57) ent = 2;
            else if (c == '.') ent = 3;
            else ent = 4;
            edo = M[edo][ent];
            
            if (edo == 0) return false;
        }
        return ent == 2;
    }
    
    public static int esOperador(String operador){
        for (int i = 0; i < operadores.length; i++) 
            if (operador.equals(operadores[i]))
                return i;
        
        //System.out.println("No es operador");
        return -1;
    }
    
    public static int esPalabraReservada(String cadena){
        for (int i = 0; i < palabrasReservadas.length; i++) if (cadena.equals(palabrasReservadas[i])) return i;
        return -1;
    }
    
    public static int esSigno(String signo){
        for (int i = 0; i < signos.length; i++) 
            if (signo.equals(signos[i])) {
                //System.out.println(signos[i] + " signo");
                return i;
            }
        
        //System.out.println("No es signo");
        return -1;
    }
}
