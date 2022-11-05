import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import AnalizadorSintactico.AnalizadorSintactico;

public class Main {

    public static File file; 
    static int numLinea = 0;

    static TablaSimbolos ts; 
    static AnalizadorSintactico as;

    static BufferedReader br;

    public static void main(String [] args) throws Exception {
        args[0] = "C:/Users/Diego Oloarte/Desktop/holaa.c";
        file = new File(args[0]);

        if (!abrirArchivo()) {
            System.out.println("Error al leer el archivo " + args + ".");
            return;
        }


        as = new AnalizadorSintactico(br);
        as.analizar();
    }


    public static boolean abrirArchivo() {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }


    public static void llenarTabSim() {
        String [] palabrasReservadas = {
                                        "auto", "break", "case", "char", "const", "continue", 
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