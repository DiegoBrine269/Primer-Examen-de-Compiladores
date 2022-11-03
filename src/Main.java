import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import AnalizadorLexico.AnalizadorLexico;

public class Main {

    static File file; 
    static int numLinea = 0;
    static BufferedReader br;

    public static void main(String [] args) throws Exception {

        if (!abrirArchivo()) 
            return;

        recorrerArchivo();
    }

    public static boolean abrirArchivo() {
        try {
            // args[0] = "C:/Users/diieg/OneDrive - Instituto Politecnico Nacional/ESCOM/1ER SEMESTRE/Fundamentos de Programación/1er Parcial/Práctica 4/Ejercicio 1/ejercicio1.c";
            file = new File("C:/Users/diieg/OneDrive - Instituto Politecnico Nacional/ESCOM/1ER SEMESTRE/Fundamentos de Programación/1er Parcial/Práctica 4/Ejercicio 1/ejercicio1.c");
            br = new BufferedReader(new FileReader(file));
            
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;

    }

    public static void recorrerArchivo() {
        String linea;
        int numLinea = 0;
        
        AnalizadorLexico al = new AnalizadorLexico();

        try {
            // Recorre todo el archivo completo
            while ((linea = br.readLine()) != null) {

                al.setLinea(linea.trim()+'\n');
                String siguienteToken;

                // Recorre la linea en cuestión 
                do {
                    siguienteToken = al.siguienteToken();
                    System.out.println(siguienteToken);

                    //if(siguienteToken matches with reserved words regex...)
                        //agregar a la tabla de símbolos como palabra reservada
                    //else if(//siguienteToken matches with identifiers regex...)
                        //agregar a la tabla de símbolos como identificador 
                }while(!al.getFinDeLinea());

                al.setFinDeLinea(false);
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}