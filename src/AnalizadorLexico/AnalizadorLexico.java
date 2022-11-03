package AnalizadorLexico;

public class AnalizadorLexico {
    
    String linea; 

    int inicioBuffer = 0;
    int finBuffer = 0;

    //Significa que ya terminó de leer toda la línea, es decir, que encontró un salto de línea
    boolean finDeLinea = false;

    public String siguienteToken() {
        String token = "";

        for(int i = inicioBuffer; i < linea.length(); i++){  

            //Si el caracter es espacio en blanco o punto y coma, ya se encontró el token
            if(Character.compare(linea.charAt(i), ' ') == 0 || Character.compare(linea.charAt(i), ';') == 0){
                this.finBuffer++;
                this.inicioBuffer = this.finBuffer;
                return token;
            } else if(this.linea.charAt(i) == '\n'){
                this.inicioBuffer++;
                this.finDeLinea = true;
                this.reiniciarBuffers();
                return token;
            }
            
            token += linea.charAt(i); 
            this.finBuffer++; 
        }
        
        return token;
    }

    public void setInicioBuffer(int inicioBuffer) {
        this.inicioBuffer = inicioBuffer;
    }

    public void setFinBuffer(int finBuffer) {
        this.finBuffer = finBuffer;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setFinDeLinea(boolean finDeLinea) {
        this.finDeLinea = finDeLinea;
    }
    
    public boolean getFinDeLinea() {
        return finDeLinea;
    }

    public void reiniciarBuffers() {
        this.inicioBuffer = 0;
        this.finBuffer = 0;
    }
}
