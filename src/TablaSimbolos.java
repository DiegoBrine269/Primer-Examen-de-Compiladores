
import java.util.ArrayList;
import java.util.Hashtable;

public class TablaSimbolos extends Hashtable<String, ArrayList<Integer>>{

    

    public void agregarIdentificador(String lexema, int numLinea) {

        ArrayList <Integer> lineas = new ArrayList<>();
        lineas.add(numLinea);
        
        if (this.containsKey(lexema)) {
            lineas = this.get(lexema);
        }
        
        lineas.add(numLinea);
        this.put(lexema, lineas);
    }

    public void agregarPR(String lexema) {
        this.put(lexema, new ArrayList<Integer>());
    }
}

// public Simbolo get(String s) {
//     for( Ent e = this; e != null; e = e.ant ) {
//     Simbolo encontro = (Simbolo)(e.tabla.get(s));
//     if( encontro != null ) return encontro;
//     }
//         return null;
//     }
// }