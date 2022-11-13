import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import AnalizadorSintactico.AnalizadorSintactico;
import Globales.TablaSimbolos;

public class Main {

    public static File file; 

    static AnalizadorSintactico as;
    static BufferedReader br;

    public static void main(String [] args) throws Exception {
       
        // if(args.length == 0) {
        //     System.out.println("Se requiere la ruta de un archivo .c");            
        //     System.exit(0);
        // }

        String a = "C:/Users/diieg/Desktop/queue.c";

        if (!abrirArchivo(a)) {
            System.out.println("Error al leer el archivo " + args[0] + ".");
            return;
        }

        llenarTabSim();
        as = new AnalizadorSintactico(br);
        as.analizar();

        System.out.println("\n\nTABLA DE CARACTERES");
        TablaSimbolos.imprimir();
    }


    public static boolean abrirArchivo(String ruta) {
        try {
            file = new File(ruta);
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }


    public static void llenarTabSim() {
        TablaSimbolos.crear();
        String [] palabrasReservadas = {
                                        "auto", "break", "case", "char", "const", "continue", 
                                        "default", "do", "double", "else", "enum", "extern", 
                                        "float", "for", "goto", "if", "include", "int", "long",
                                        "main", "register", "return", "short", "signed", 
                                        "sizeof", "static", "struct", "switch", "typedef", 
                                        "union", "unsigned", "void", "volatile", "while",  
                                        "_Packed", 
                                    };
        for (String palabraReservada : palabrasReservadas) {
            TablaSimbolos.agregarPR(palabraReservada);
        }
    }
}