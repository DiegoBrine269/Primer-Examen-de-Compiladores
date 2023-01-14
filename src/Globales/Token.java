package Globales;
public class Token {
    String tipo; //pr, oprel, oparit, número, literal, char
    String valor;

    public Token() {
        super();
    }

    public Token (String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }
}
