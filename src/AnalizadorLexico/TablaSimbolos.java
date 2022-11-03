package AnalizadorLexico;
import java.util.ArrayList;
import java.util.Hashtable;

public class TablaSimbolos extends Hashtable<String, ArrayList<Integer>>{

    public void agregarNuevo(String lexema, int numLinea) {

        ArrayList <Integer> lineas = new ArrayList<>();
        lineas.add(numLinea);
        
        if (this.containsKey(lexema)) {
            lineas = this.get(lexema);
        }
        
        lineas.add(numLinea);
        this.put(lexema, lineas);
    }
}
