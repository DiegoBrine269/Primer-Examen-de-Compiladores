package AnalizadorLexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AnalizadorLexico {
    
    String codigoFuente; 
    int cursor = -1;
    int estado = 1;
    BufferedReader br;

    public AnalizadorLexico (BufferedReader br) {
        this.br = br;
    }

    public String siguienteToken() {
        String token = "";
        char c = '-';
        this.estado = 1;

        while( c != '\0' ) {
            c = sigCar();

            if(c == '\0')
                return "";

            token += c;
            
            this.estado = this.mover(this.estado, c);
         
            if (this.estado == 3){
                //Guardar en Hash Table como identificador
                break;
            }
            else if (this.estado == 4) {
                //Guardar en Hash Table como palabra reservada
                break;
            }


        }
        
        return token;
    }

    private char sigCar () {
        this.cursor++;
        return codigoFuente.charAt(cursor);
    }

    


    /*
        Verifica qué estado se obtiene a partir de un estado actual y un caracter de entrada
        Recibe: el número de estado s, un caracter de entrada c
        Devuelve: el número de estado a partir de los parámetros dados, el estado -1 es un estado de error
    */
    private int mover (int s, char c){
        return 0;
    }

    public int transicionUnCaracter (char c, char letra, int estadoResultante) {
        if (c == letra)
            return estadoResultante;
        else if((Character.isLetter(c) && c != letra) || Character.isDigit(c))
            return 2;
        else 
            return 4;
    }

    public int transicionEstadoAceptacion(char c){
        if(Character.isLetter(c) || Character.isDigit(c))
            return 2;
        else 
            return 4;
    }

    public void setCodigoFuente(String codigoFuente) {
        this.codigoFuente = codigoFuente;
    }

    public String getCodigoFuente() {
        return codigoFuente;
    }
}
