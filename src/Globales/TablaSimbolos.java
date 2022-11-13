package Globales;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;


public class TablaSimbolos {
    private static Hashtable<String, ArrayList<Integer>> ts;

    public static void crear() {
        ts = new Hashtable<String, ArrayList<Integer>>();
    }

    public static void agregarIdentificador(String lexema, int numLinea) {

        ArrayList <Integer> lineas = new ArrayList<>();
        
        if (ts.containsKey(lexema)) 
            lineas = ts.get(lexema);
        
        lineas.add(numLinea);
        ts.put(lexema, lineas);
    }

    public static void agregarPR(String lexema) {
        ts.put(lexema, new ArrayList<Integer>());
    }

    public static boolean contienePR(String lexema) {
        if (ts.containsKey(lexema))
            return (ts.get(lexema).size() == 0);
        else
            return false;
    }

    public static void imprimir() {
        Set<Map.Entry<String, ArrayList<Integer>>> entries = ts.entrySet();

        for(Map.Entry<String, ArrayList<Integer>> entry : entries ){
            System.out.println( entry.getKey() + "->" + entry.getValue() );
        }
    }
}