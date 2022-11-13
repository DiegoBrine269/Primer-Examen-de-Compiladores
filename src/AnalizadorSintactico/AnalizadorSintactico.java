package AnalizadorSintactico;
import java.io.BufferedReader;
import java.io.IOException;

import AnalizadorLexico.AnalizadorLexico;


public class AnalizadorSintactico {
    AnalizadorLexico al;

    public AnalizadorSintactico(BufferedReader br) throws IOException {
        al = new AnalizadorLexico(br);
    }

    public void analizar () throws IOException {

        String siguienteToken;

        while ((siguienteToken = al.siguienteToken()) != null) {
            System.out.println(siguienteToken); 
        } 

    }
}
