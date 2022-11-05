package AnalizadorLexico;

public class AnalizadorLexico {
    
    String codigoFuente; 
    int cursor = -1;
    int estado = 1;

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
        int estadoResultante = -1;
        switch(s) {
            case 1:
                switch (c) {
                    case 'a':
                        estadoResultante = 5; 
                        break;
                    case 'b':
                        estadoResultante = 9; 
                        break;
                    case 'c':
                        estadoResultante = 14; 
                        break;
                    case 'd':
                        estadoResultante = 30; 
                        break;
                    case 'e':
                        estadoResultante = 42; 
                        break;
                    case 'f':
                        estadoResultante = 54; 
                        break;
                    case '6':
                        estadoResultante = 61; 
                        break;
                    case 'i':
                        estadoResultante = 65; 
                        break;
                    case 'l':
                        estadoResultante = 69; 
                        break;
                    case 'r':
                        estadoResultante = 73; 
                        break;
                    case 's':
                        estadoResultante = 85; 
                        break;
                    case 't':
                        estadoResultante = 113; 
                        break;
                    case 'u':
                        estadoResultante = 120; 
                        break;
                    case 'v':
                        estadoResultante = 131; 
                        break;
                    case 'w':
                        estadoResultante = 141; 
                        break;
                    default:
                        if(Character.isLetter(c) || c == '_')
                        estadoResultante = 2;
                        break;
                    }
                
                break;

            case 2:
                if(Character.isLetter(c) || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 3;
                break;
            

            case 5:
                estadoResultante = this.transicionUnCaracter(c, 'u', 6);
                break;

            case 6:
                estadoResultante = this.transicionUnCaracter(c, 't', 7);
                break;

            case 7:
                estadoResultante = this.transicionUnCaracter(c, 'o', 8);
                break;

            case 8:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 9:
                estadoResultante = this.transicionUnCaracter(c, 'r', 10);
                break;

            case 10:
                estadoResultante = this.transicionUnCaracter(c, 'e', 11);
                break;

            case 11:
                estadoResultante = this.transicionUnCaracter(c, 'a', 12);
                break;

            case 12:
                estadoResultante = this.transicionUnCaracter(c, 'k', 13);
                break;

            case 13:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 14:
                if (c == 'a')
                    estadoResultante = 15;
                else if (c == 'h')
                    estadoResultante = 18;
                else if (c == 'o')
                    estadoResultante = 21;
                else if ((Character.isLetter(c) && c != 'a' && c != 'h' && c != 'o') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 15:
                estadoResultante = this.transicionUnCaracter(c, 's', 16);
                break;

            case 16:
                estadoResultante = this.transicionUnCaracter(c, 'e', 17);
                break;

            case 17:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 18:
                estadoResultante = this.transicionUnCaracter(c, 'a', 19);
                break;

            case 19:
                estadoResultante = this.transicionUnCaracter(c, 'r', 20);
                break;

            case 20:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 21:
                estadoResultante = this.transicionUnCaracter(c, 'n', 22);
                break;

            case 22:
                if (c == 's')
                    estadoResultante = 23;
                else if (c == 't')
                    estadoResultante = 25;
                else if((Character.isLetter(c) && c != 's' && c != 't') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;


            case 23:
                estadoResultante = this.transicionUnCaracter(c, 't', 24);
                break;

            case 24:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 25:
                estadoResultante = this.transicionUnCaracter(c, 'i', 26);
                break;

            case 26:
                estadoResultante = this.transicionUnCaracter(c, 'n', 27);
                break;

            case 27:
                estadoResultante = this.transicionUnCaracter(c, 'u', 28);
                break;

            case 28:
                estadoResultante = this.transicionUnCaracter(c, 'e', 29);
                break;

            case 29:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 30:
                if (c == 'e')
                    estadoResultante = 31;
                else if (c == 'o')
                    estadoResultante = 37;
                else if((Character.isLetter(c) && c != 'e' && c != 'o') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 31:
                estadoResultante = this.transicionUnCaracter(c, 'f', 32);
                break;

            case 32:
                estadoResultante = this.transicionUnCaracter(c, 'a', 33);
                break;

            case 33:
                estadoResultante = this.transicionUnCaracter(c, 'u', 34);
                break;

            case 34:
                estadoResultante = this.transicionUnCaracter(c, 'l', 35);
                break;

            case 35:
                estadoResultante = this.transicionUnCaracter(c, 't', 36);
                break;

            case 36:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;
            
            case 37:
                if (c == 'u')
                    estadoResultante = 38;
                else if(Character.isLetter(c) || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 38:
                estadoResultante = this.transicionUnCaracter(c, 'b', 39);
                break;

            case 39:
                estadoResultante = this.transicionUnCaracter(c, 'l', 40);
                break;
                
            case 40:
                estadoResultante = this.transicionUnCaracter(c, 'e', 41);
                break;

            case 41:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 42:
                if (c == 'l')
                    estadoResultante = 43;
                else if (c == 'n')
                    estadoResultante = 46;
                else if (c == 'x')
                    estadoResultante = 49;
                else if((Character.isLetter(c) && c != 'l' && c != 'n' && c != 'x') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 43:
                estadoResultante = this.transicionUnCaracter(c, 's', 44);
                break; 

            case 44:
                estadoResultante = this.transicionUnCaracter(c, 'e', 45);
                break;
            
            case 45:
            estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 46:
                estadoResultante = this.transicionUnCaracter(c, 'u', 47);
                break;

            case 47:
                estadoResultante = this.transicionUnCaracter(c, 'm', 48);
                break;

            case 48:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 49:
                estadoResultante = this.transicionUnCaracter(c, 't', 50);
                break;

            case 50:
                estadoResultante = this.transicionUnCaracter(c, 'e', 51);
                break;

            case 51:
                estadoResultante = this.transicionUnCaracter(c, 'r', 52);
                break;

            case 52:
                estadoResultante = this.transicionUnCaracter(c, 'n', 53);
                break;
            
            case 53:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 54:
                if (c == 'l')
                    estadoResultante = 55;
                else if (c == 'o')
                    estadoResultante = 59;
                else if((Character.isLetter(c) && c != 'l' && c != 'o') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 55:
                estadoResultante = this.transicionUnCaracter(c, 'o', 56);
                break;

            case 56:
                estadoResultante = this.transicionUnCaracter(c, 'a', 57);
                break;

            case 57:
                estadoResultante = this.transicionUnCaracter(c, 't', 58);
                break;

            case 58:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 59:
                estadoResultante = this.transicionUnCaracter(c, 'r', 60);
                break;

            case 60:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 61:
                estadoResultante = this.transicionUnCaracter(c, 'o', 62);
                break;

            case 62:
                estadoResultante = this.transicionUnCaracter(c, 't', 63);
                break;
            
            case 63:
                estadoResultante = this.transicionUnCaracter(c, 'o', 64);
                break;

            case 64:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 65:
                if (c == 'f')
                    estadoResultante = 66;
                else if (c == 'n')
                    estadoResultante = 67;
                else if((Character.isLetter(c) && c != 'f' && c != 'n') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 66:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 67:
                estadoResultante = this.transicionUnCaracter(c, 't', 68);
                break;

            case 68:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 69:
                estadoResultante = this.transicionUnCaracter(c, 'o', 70);
                break;

            case 70:
                estadoResultante = this.transicionUnCaracter(c, 'n', 71);
                break;

            case 71:
                estadoResultante = this.transicionUnCaracter(c, 'g', 72);
                break;
            
            case 72:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 73:
                estadoResultante = this.transicionUnCaracter(c, 'e', 74);
                break;

            case 74:
                if (c == 'g')
                    estadoResultante = 75;
                else if (c == 't')
                    estadoResultante = 81;
                else if((Character.isLetter(c) && c != 'g' && c != 't') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 75:
                estadoResultante = this.transicionUnCaracter(c, 'i', 76);
                break;

            case 76:
                estadoResultante = this.transicionUnCaracter(c, 's', 77);
                break;

            case 77:
                estadoResultante = this.transicionUnCaracter(c, 't', 78);
                break;

            case 78:
                estadoResultante = this.transicionUnCaracter(c, 'e', 79);
                break;

            case 79:
                estadoResultante = this.transicionUnCaracter(c, 'r', 80);
                break;

            case 80:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 81:
                estadoResultante = this.transicionUnCaracter(c, 'u', 82);
                break;

            case 82:
                estadoResultante = this.transicionUnCaracter(c, 'r', 83);
                break;

            case 83:
                estadoResultante = this.transicionUnCaracter(c, 'n', 84);
                break;

            case 84:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 85:
                if (c == 'h')
                    estadoResultante = 86;
                else if (c == 'i')
                    estadoResultante = 90;
                else if (c == 't')
                    estadoResultante = 99;
                else if (c == 'w')
                    estadoResultante = 108;
                else if((Character.isLetter(c) && c != 'h' && c != 'i' && c != 't' && c != 'g') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 86:
                estadoResultante = this.transicionUnCaracter(c, 'o', 87);
                break;

            case 87:
                estadoResultante = this.transicionUnCaracter(c, 'r', 88);
                break;

            case 88:
                estadoResultante = this.transicionUnCaracter(c, 't', 89);
                break;

            case 89:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;    

            case 90:
                if (c == 'g')
                    estadoResultante = 91;
                else if (c == 'z')
                    estadoResultante = 95;
                else if((Character.isLetter(c) && c != 'g' && c != 'z') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 91:
                estadoResultante = this.transicionUnCaracter(c, 'n', 92);
                break;

            case 92:
                estadoResultante = this.transicionUnCaracter(c, 'e', 93);
                break;

            case 93:
                estadoResultante = this.transicionUnCaracter(c, 'd', 94);
                break;

            case 94:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;  

            case 95: 
                estadoResultante = this.transicionUnCaracter(c, 'e', 96);
                break;

            case 96: 
                estadoResultante = this.transicionUnCaracter(c, 'o', 97);
                break;    

            case 97: 
                estadoResultante = this.transicionUnCaracter(c, 'f', 98);
                break;    

            case 98:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break; 
                
            case 99:
                if (c == 'a')
                    estadoResultante = 100;
                else if (c == 'r')
                    estadoResultante = 104;
                else if (c == 'w')
                    estadoResultante = 108;
                else if((Character.isLetter(c) && c != 'a' && c != 'r' && c != 'w') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 100: 
                estadoResultante = this.transicionUnCaracter(c, 't', 101);
                break;

            case 101: 
                estadoResultante = this.transicionUnCaracter(c, 'i', 102);
                break;    

            case 102: 
                estadoResultante = this.transicionUnCaracter(c, 'c', 103);
                break;    

            case 103:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;
                
            case 104: 
                estadoResultante = this.transicionUnCaracter(c, 'u', 105);
                break;

            case 105: 
                estadoResultante = this.transicionUnCaracter(c, 'c', 106);
                break;    

            case 106: 
                estadoResultante = this.transicionUnCaracter(c, 't', 107);
                break;    
        
            case 107:
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 108: 
                estadoResultante = this.transicionUnCaracter(c, 'i', 109);
                break;

            case 109: 
                estadoResultante = this.transicionUnCaracter(c, 't', 110);
                break;    

            case 110: 
                estadoResultante = this.transicionUnCaracter(c, 'c', 111);
                break;    

            case 111: 
                estadoResultante = this.transicionUnCaracter(c, 'h', 112);
                break;    

            case 112: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;    

            case 113:
                estadoResultante = this.transicionUnCaracter(c, 'y', 114);
                break;

            case 114: 
                estadoResultante = this.transicionUnCaracter(c, 'p', 115);
                break;

            case 115: 
                estadoResultante = this.transicionUnCaracter(c, 'e', 116);
                break;    

            case 116: 
                estadoResultante = this.transicionUnCaracter(c, 'd', 117);
                break;    

            case 117: 
                estadoResultante = this.transicionUnCaracter(c, 'e', 118);
                break;    

            case 118: 
                estadoResultante = this.transicionUnCaracter(c, 'f', 119);
                break; 
                
            case 119: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break; 
                
            case 120: 
                estadoResultante = this.transicionUnCaracter(c, 'n', 121);
                break;  
                
            case 121:
                if (c == 'i')
                    estadoResultante = 122;
                else if (c == 's')
                    estadoResultante = 125;
                else if((Character.isLetter(c) && c != 'i' && c != 's') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 122: 
                estadoResultante = this.transicionUnCaracter(c, 'o', 123);
                break;    

            case 123: 
                estadoResultante = this.transicionUnCaracter(c, 'n', 124);
                break; 

            case 124: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break; 

            case 125: 
                estadoResultante = this.transicionUnCaracter(c, 'i', 126);
                break; 

            case 126: 
                estadoResultante = this.transicionUnCaracter(c, 'g', 127);
                break; 

            case 127: 
                estadoResultante = this.transicionUnCaracter(c, 'n', 128);
                break; 

            case 128: 
                estadoResultante = this.transicionUnCaracter(c, 'e', 129);
                break; 

            case 129: 
                estadoResultante = this.transicionUnCaracter(c, 'd', 130);
                break; 

            case 130: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            case 131: 
                estadoResultante = this.transicionUnCaracter(c, 'o', 132);
                break; 

            case 132:
                if (c == 'i')
                    estadoResultante = 133;
                else if (c == 'l')
                    estadoResultante = 135;
                else if((Character.isLetter(c) && c != 'i' && c != 'l') || Character.isDigit(c))
                    estadoResultante = 2;
                else 
                    estadoResultante = 4;
                break;

            case 133: 
                estadoResultante = this.transicionUnCaracter(c, 'd', 134);
                break; 

            case 134: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break; 

            case 135: 
                estadoResultante = this.transicionUnCaracter(c, 'a', 136);
                break;  

            case 136: 
                estadoResultante = this.transicionUnCaracter(c, 't', 137);
                break;  

            case 137: 
                estadoResultante = this.transicionUnCaracter(c, 'i', 138);
                break;  

            case 138: 
                estadoResultante = this.transicionUnCaracter(c, 'l', 139);
                break;  

            case 139: 
                estadoResultante = this.transicionUnCaracter(c, 'e', 140);
                break; 
                
            case 140: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;
                
            case 141:
                estadoResultante = this.transicionUnCaracter(c, 'h', 142);
                break; 

            case 142:
                estadoResultante = this.transicionUnCaracter(c, 'i', 143);
                break; 

            case 143:
                estadoResultante = this.transicionUnCaracter(c, 'l', 144);
                break; 

            case 144:
                estadoResultante = this.transicionUnCaracter(c, 'e', 145);
                break; 

            case 145: 
                estadoResultante = this.transicionEstadoAceptacion(c);
                break;

            default:
                estadoResultante = -1;
                break;
        }

        return estadoResultante;
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
