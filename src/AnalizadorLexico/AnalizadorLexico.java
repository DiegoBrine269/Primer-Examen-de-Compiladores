package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import Globales.TablaSimbolos;
import Globales.TipoToken;
import Globales.Token;


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

    boolean eof = false;
    //Lector de archivo
    BufferedReader br;


    Token token;


    public AnalizadorLexico (BufferedReader br) throws IOException {
        this.br = br;
        siguienteLinea();
    }

    public Token siguienteToken() throws IOException {

        if (this.eof)
            return null;

        String lexema = "";

        //La línea es un commentario
        while(this.linea.startsWith("//") || this.linea.equals("") || this.linea.equals(" ") || this.linea == null ) {
            siguienteLinea();
        }


        //Saltamos todos los comenttarios multilínea
        if(this.linea.startsWith("/*")) {
            do {
                siguienteLinea();
            } while(!this.linea.contains("*/"));  

            //Fin de comentarios multilínea
            while(this.linea.equals("*/\0") || this.linea.equals("") || this.linea.equals(" ") || this.linea == null ) {
                siguienteLinea();
                
            }
            
            //Fin de comentarios multilínea, pero con token adjunto
            if(this.linea.contains("*/") && !this.linea.equals("*/\0")){
                this.linea = this.linea.replace("*/", "");
            }
        }

        //Inicio de análisis 
        char c = '-';

        while( c != '\0' ) {
            c = sigCar();


            this.estado = mover(this.estado, c);



            //Estado de error
            if(this.estado == -1){
                System.out.println("Error en la línea " + this.numLinea);
                System.exit(-1);
            }
            //Concatenar
            else if(this.estado == 1 || this.estado == 3 || this.estado == 4 || this.estado == 6 || this.estado == 7 || this.estado == 9 || this.estado == 11 || this.estado == 13 || this.estado == 15 || this.estado == 17 || this.estado == 18 || this.estado == 21 || this.estado == 23 || this.estado == 25 || this.estado == 26)
                lexema += c;
            //Fin de comentario multilínea
            else if(this.estado == 29){
                lexema = "";
            }
            //Retornar
            else if(this.estado != 0){

                if(c == '\0') {   
                    this.eof = !siguienteLinea();
                }else
                    this.cursor --;

                switch (this.estado) {
                    case 2:
                        if(TablaSimbolos.contienePR(lexema))
                            return retornarToken(lexema, lexema.toUpperCase());
                        else
                            return retornarToken(lexema, TipoToken.ID);
                    case 5:
                        return retornarToken(lexema, TipoToken.NUM);

                    case 8:
                        return retornarToken(lexema, TipoToken.LIT);

                    case 10:
                        return retornarToken(lexema, TipoToken.OP_R);

                    case 12:
                        return retornarToken(lexema, TipoToken.OP_R);

                    case 14:
                        return retornarToken(lexema, TipoToken.OP_A);

                    case 16:
                        return retornarToken(lexema, TipoToken.ASIGN);

                    case 19:
                        return retornarToken(lexema, TipoToken.SE);

                    case 20:
                        return retornarToken(lexema, TipoToken.EOI);
                    
                    case 22:
                        return retornarToken(lexema, TipoToken.DIRECT);
                    
                    case 24:
                        return retornarToken(lexema, TipoToken.COMA);
                        
                    case 27:
                        return retornarToken(lexema, TipoToken.BIBLIO);
                }
            }
        }
            
        return token;
    }
        

        
    

    private char sigCar () {
        this.cursor++;
        return this.linea.charAt(cursor);
    }

    
    /*
        Avanza de línea en el documento
    */
    private boolean siguienteLinea() throws IOException {
        try {
            this.linea = this.br.readLine().trim() + '\0';

            //Línea vacía
            if(this.linea.equals("\0"))
                this.linea = "";

            //Eliminando comentarios que no estén desde el principio
            if(this.linea.contains("//") && !this.linea.startsWith("//"))
                this.linea = this.linea.substring(0, this.linea.indexOf("//")).trim() + '\0';




            this.numLinea++;
            this.cursor = -1;
            return true;
        }
        catch(NullPointerException ex) {
            //En este caso, ya no hay más líneas
            return false;
        }
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
                if(c == ' ' || c == '\0') 
                    estadoResultante = 0;
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
                else if (c == ';')
                    estadoResultante = 17;
                else if (c == '{' || c == '}' || c == '[' || c == ']' || c == '(' || c == ')')
                    estadoResultante = 18;
                else if (c == '#')
                    estadoResultante = 21;
                else if (c == ',')
                    estadoResultante = 23;
                break;

            case 1:
                if(Character.isLetter(c) || Character.isDigit(c))
                    estadoResultante = 1;
                else if (c == '.')
                    estadoResultante = 25;
                else
                    estadoResultante = 2;
                break;   

            case 3:
                if(Character.isDigit(c))
                    estadoResultante = 3;
                else if(Character.isLetter(c))
                    estadoResultante = -1;
                else if(c == '.')
                    estadoResultante = 4;
                else
                    estadoResultante = 5;
                break;   

            case 4:
                if(Character.isDigit(c))
                    estadoResultante = 4;
                else if(Character.isLetter(c))
                    estadoResultante = -1;
                else
                    estadoResultante = 5;
                break;  

            case 6: 
                if(c == '\"' || c == '\'')
                    estadoResultante = 7;
                else 
                    estadoResultante = 6;
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

            case 11:
                estadoResultante = 12;
                break;

            case 13:
                estadoResultante = 14;
                break;

            case 15:
                if(c == '=')
                    estadoResultante = 11;
                else 
                    estadoResultante = 16;
                break;

            case 17:
                estadoResultante = 20;
                break;
            
            case 18:
                estadoResultante = 19;
                break;

            case 21:
                if(Character.isLetter(c))
                    estadoResultante = 21;
                else
                    estadoResultante = 22;
                break;
            
            case 23:
                estadoResultante = 24;
                break;


            case 25:
                if(c == 'c' || c == 'h')
                    estadoResultante = 26;
                break;

            case 26:
                estadoResultante = 27;
                break;
        
        }


        return estadoResultante;
    }

    private Token retornarToken(String tipo, String valor) {
        
        this.estado = 0;
        return new Token(tipo, valor);
    }
}
