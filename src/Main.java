import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.TablaSimbolos;

public class Main {

    static File file; 
    static int numLinea = 0;
    static BufferedReader br;
    static TablaSimbolos ts; 

    public static void main(String [] args) throws Exception {

        llenarTabSim();

        if (!abrirArchivo()) 
            return;

        recorrerArchivo();
    }

    public static boolean abrirArchivo() {
        try {
            // args[0] = "C:/Users/diieg/OneDrive - Instituto Politecnico Nacional/ESCOM/1ER SEMESTRE/Fundamentos de Programación/1er Parcial/Práctica 4/Ejercicio 1/ejercicio1.c";
            file = new File("C:/Users/Diego Oloarte/Desktop/hola.c");
            br = new BufferedReader(new FileReader(file));
            
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;

    }

    public static void recorrerArchivo() {

        AnalizadorLexico al = new AnalizadorLexico();

        try {
            String siguienteToken;

            // Le pasamos el código fuente al analizador léxico
            al.setCodigoFuente(br.lines().collect(Collectors.joining()) + '\0');

            do {
                siguienteToken = al.siguienteToken();
                System.out.println(siguienteToken);
            } while (siguienteToken != "");


            
            
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }       
    }

    public static void llenarTabSim() {
        String [] palabrasReservadas = {"auto", "break", "case", "char", "const", "continue", 
                                        "default", "do", "double", "else", "enum", "extern", 
                                        "float", "for", "goto", "if", "int", "long", "main", 
                                        "register", "return", "short", "signed", "sizeof", 
                                        "static", "struct", "switch", "typedef", "union", 
                                        "unsigned", "void", "volatile", "while",  "_Packed", 
                                    };
        for (String palabraReservada : palabrasReservadas) {
            ts.agregarPR(palabraReservada);
        }
    }
}