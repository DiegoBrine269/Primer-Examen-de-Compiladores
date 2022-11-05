package AnalizadorSintactico;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import AnalizadorLexico.AnalizadorLexico;


public class AnalizadorSintactico {
    AnalizadorLexico al;

    public AnalizadorSintactico(BufferedReader br) {
        al = new AnalizadorLexico(br);
    }

    public void analizar () {

        String siguienteToken;

        do {
            siguienteToken = al.siguienteToken();
            System.out.println(siguienteToken);
        } while (siguienteToken != null);

    }
}
