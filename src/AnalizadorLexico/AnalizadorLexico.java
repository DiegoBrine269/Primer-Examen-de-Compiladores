package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.IOException;
import Globales.TablaSimbolos;

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

    BufferedReader br;

    public AnalizadorLexico (BufferedReader br) throws IOException {
        this.br = br;
        leerLinea();
    }

    public String siguienteToken() throws IOException {

        //Si no hay línea, ya es eof
        if(this.linea == null) {
            return null;
        } 

        //La línea es un commentario
        if(this.linea.startsWith("//")) {
            try {
                leerLinea();
            }
            catch(NullPointerException ex) {
                //En este caso, ya no hay más líneas
                return null;
            }
        }

        //Inicio de análisis 

        String token = "";
        char c = '-';

        //El estado 8 significa que es un comentario multilínea
        if(this.estado != 8)
            this.estado = 0;

        while( c != '\0' ) {
            c = sigCar();

            if(c == '\0') {

                //Apóstrofe no cerrada
                if(this.estado == 51){
                    System.out.println("Error en la línea " + numLinea);
                    System.exit(-1);
                }


                //Si ya llegó al fin de la línea, verificar si es token válido y retornar
                comprobarToken(token);
                try {
                    leerLinea();
                }
                catch(NullPointerException ex) {
                    if (token != ""){
                        this.linea = null;
                        return token;
                    }
                    else 
                        return null;
                }
                cursor = -1;
                
                //Se valida que existe el token
                if(token != "" && token != "\0" && token != "\n")
                    return token;
                else {
                    // Si el token está vacío, es por que en esa línea no se encontraron tokens, por lo que leemos la siguiente línea
                    return siguienteToken();
                }
            }

            
            //Se verifica el estado actual, para decidir si seguir analizando, detenerse o retornar el token

            this.estado = this.mover(this.estado, c);
            
            if(this.estado == 0) {
                token = "";
            }
            else if (this.estado == -1)  {
                System.out.println("Error en la línea " + numLinea);
                System.exit(-1);
            }
            else if(this.estado == 1 || this.estado == 61 || this.estado == 11 || this.estado == 12 || this.estado == 13 || this.estado == 6 || this.estado == 5 || this.estado == 51 || this.estado == 52 ) {
                token += c;
            }
            else if(this.estado == 2 || this.estado == 15) {
                comprobarToken(token);
                break;
            }
            else if(this.estado == 10){
                //La línea es un comentario
                try {
                    leerLinea();
                    this.estado = 0;
                    this.cursor = -1;
                }
                catch(NullPointerException ex) {
                    return null;
                }
            }
            else if (this.estado == 8)
                token = "";
            else if(this.estado == 14){
                token = token.substring(1);
                token = token.substring(0,token.length() - 2);
                comprobarToken(token);
                break;
            }
        }
        

        // Si el token está vacío, es por que en esa línea no se encontraron tokens, por lo que leemos la siguiente línea
        if(token != "" && token != "\0" && token != "\n")
            return token;
        else {
            return siguienteToken();
        }
    }

    private char sigCar () {
        this.cursor++;
        return this.linea.charAt(cursor);
    }

    private void comprobarToken(String token) {

        if(token == "" || token == "\0")
            return;

        // Comprobar si el token es PR buscando en la tabla de identificadores
        if(TablaSimbolos.contienePR(token)){
            //Existe, es palabra reservada
            System.out.print("Palabra reservada: ");
        }
        //Si no, guardarlo como identificador
        else {
            TablaSimbolos.agregarIdentificador(token, numLinea);
            System.out.print("Token: ");
        }

        //Reiniciar el estado
        this.estado = 0;
    }
    

    private void leerLinea() throws IOException {
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
        
        switch (s) {
            case 0:
                if(c == '_' || Character.isLetter(c))
                    estadoResultante = 1;
                else if (Character.isDigit(c))
                    estadoResultante = 3;
                else if (c == '\'')
                    estadoResultante = 5;
                else if (c == '\"')
                    estadoResultante = 6;
                else if (c == '/')
                    estadoResultante = 7;
                else if (c == '<')
                    estadoResultante = 11;
                else if (c == '#')
                    estadoResultante = 0;
                else if (c == '=')
                    estadoResultante = 15;
                else 
                    estadoResultante = 4;
                    break;
        
            case 1:
                if(Character.isLetter(c) || Character.isDigit(c))
                    estadoResultante = 1;
                else
                    estadoResultante = 2;
                break;      
                
            case 3:
                if(Character.isLetter(c))
                    estadoResultante = -1;
                else if (Character.isDigit(c))
                    estadoResultante = 3;
                else    
                    estadoResultante = 4;
                break;   

            case 4: 
                if(c == ' ')
                    estadoResultante = 0;
                else 
                    estadoResultante = 4;
                break;

            case 5:
                estadoResultante = 51;
                break;

            
            case 51: //estado para concatenar la última apóstrofe
                if(c == '\'')
                    estadoResultante = 52;
                else 
                    estadoResultante = -1;
                break;

            case 52: //estado para concatenar la última apóstrofe
                estadoResultante = 2;
                break;


            case 6:
                if(c == '"')
                    estadoResultante = 61;
                else    
                    estadoResultante = 6;
                break; 

            case 61: //estado para concatenar las últimas comillas dobles
                estadoResultante = 2;
                break;

            case 7:
                if(c == '*')
                    estadoResultante = 8;
                else if(c == '/')
                    estadoResultante = 10;
                else    
                    estadoResultante = 7;
                break; 

            case 8:
                if(c == '*')
                    estadoResultante = 9;
                else    
                    estadoResultante = 8;
                break; 

            case 9:
                if(c == '/')
                    estadoResultante = 0;
                else    
                    estadoResultante = 8;
                break; 

            case 11:
                if(Character.isLetter(c))
                    estadoResultante = 11;
                else if(c == '.')
                    estadoResultante = 12;
                else    
                    estadoResultante = 0;
                break;

            case 12:
                if(c == 'h')
                    estadoResultante = 13;
                else 
                    estadoResultante = 0;
                break;

            case 13:
                if(c == '>')
                    estadoResultante = 14;
                else 
                    estadoResultante = 0;
                break;

            case 14:
                estadoResultante = 0;
        }

        return estadoResultante;
    }
}
