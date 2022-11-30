package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import Globales.TablaSimbolos;
import Globales.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    // Posición actual de la línea
    int cursor = -1;

    //Indica qué línea se está leyendo actualmente, inicia en cero y conforme se avanza en el análisis se incrementa
    int numLinea = 0;

    //Guarda la línea que se está leyendo actualmente
    String linea;

    //Estado del autómata
    int estado = 0;

    //Leyendo comentarios o cadenas entre comillas o apóstrofes
    boolean ignorar = false;

    //Lector de archivo
    BufferedReader br;

    Token token;


    public AnalizadorLexico (BufferedReader br) throws IOException {
        this.br = br;
        siguienteLinea();
    }

    public Token siguienteToken() throws IOException {
        
        token = new Token();
        String lexema = "";

        //Si no hay línea, ya es eof
        if(this.linea == null) {
            return null;
        } 

        //La línea es un commentario
        if(this.linea.startsWith("//")) {
            try {
                siguienteLinea();
            }
            catch(NullPointerException ex) {
                //En este caso, ya no hay más líneas
                return null;
            }
        }

        //Inicio de análisis 
        char c = '-';

        while( c != '\0' ) {
            c = sigCar();

            //Fin de línea
            if(c == '\0') {

                
            }
        }

            
            //Se verifica el estado actual, para decidir si seguir analizando, detenerse o retornar el token

        return token;
    }
        

        
    

    private char sigCar () {
        this.cursor++;
        return this.linea.charAt(cursor);
    }

    private void comprobarLexema(String lexema) {
        if(compararRegEx(lexema, "_*[a-zA-Z]+[0-9]*"))
        
    }
    
    /*
        Avanza de línea en el documento
     */
    private void siguienteLinea() throws IOException {
        this.linea = this.br.readLine().trim() + '\0';
        this.numLinea++;
    }


    /*
        Verifica qué estado se obtiene a partir de un estado actual y un caracter de entrada
        Recibe: el número de estado s, un caracter de entrada c
        Devuelve: el número de estado a partir de los parámetros dados, el estado -1 es un estado de error
    */
    private int mover (int s, char c){
        int estadoResultante = -1;
        

        return estadoResultante;
    }

    private static boolean compararRegEx(String input, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();

    }
}
