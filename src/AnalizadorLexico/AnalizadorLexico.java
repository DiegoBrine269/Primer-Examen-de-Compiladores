package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import Globales.TablaSimbolos;
import Globales.TipoToken;
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

            this.estado = mover(this.estado, c);

            comprobarLexema(lexema);
        }

            
        return token;
    }
        

        
    

    private char sigCar () {
        this.cursor++;
        return this.linea.charAt(cursor);
    }

    private void comprobarLexema(String lexema) {
        //Palabra reservada
        if(TablaSimbolos.contienePR(lexema)) {
            token.setValor(lexema);
            token.setTipo(lexema.toUpperCase()); 
        }
        //Identificador
        else if(compararRegEx(lexema, "_*[a-zA-Z]+[0-9]*")) {
            token.setValor(lexema);
            token.setTipo(TipoToken.ID);
        }
        //Número
        else if(compararRegEx(lexema, "[0-9]+.?[0-9]*")) {
            token.setValor(lexema);
            token.setTipo(TipoToken.NUM);
        }
        //Literal
        else if(compararRegEx(lexema, "\".\"")) {
            token.setValor(lexema);
            token.setTipo(TipoToken.LIT);
        }
        //Operador relacional
        else if(compararRegEx(lexema, "<|<=|>|>=")){
            token.setValor(lexema);
            token.setTipo(TipoToken.OP_R);
        }
        //Operador aritmético
        else if(compararRegEx(lexema, "+|-|*|/")){
            token.setValor(lexema);
            token.setTipo(TipoToken.OP_R);
        }
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
        
        switch(s) {
            case 0:
                if(Character.isLetter(c) || c == '_')
                    estadoResultante = 1;
                else if(Character.isDigit(c))
                    estadoResultante = 3;
                else if(c == '\"' || c == '\'')
                    estadoResultante = 6;
                else if(c == '=')
                    estadoResultante = 15;
                else if(c == '<' || c == '>')
                    estadoResultante = 9;
                else if (c == '+' || c == '-' || c == '*' || c == '/')
                    estadoResultante = 13;
                break;

            case 1:
                if(Character.isLetter(c) || Character.isDigit(c))
                    estadoResultante = 1;
                else
                    estadoResultante = 2;
                break;   

            case 3:
                if(Character.isDigit(c))
                    estadoResultante = 3;
                else if(c == '.')
                    estadoResultante = 4;
                else
                    estadoResultante = 5;
                break;   

            case 4:
                if(Character.isDigit(c))
                    estadoResultante = 4;
                else
                    estadoResultante = 5;
                break;  

            case 7:
                estadoResultante = 8;
                break;

            case 9:
                if (c == '=')
                    estadoResultante = 11;
                else 
                    estadoResultante = 10;
                break;


            case 15:
                if(c == '=')
                    estadoResultante = 11;
                else 
                    estadoResultante = 16;
                break;
        }

        return estadoResultante;
    }

    private static boolean compararRegEx(String input, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();

    }
}
