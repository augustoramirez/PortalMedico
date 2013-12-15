package escalerapalabras;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class Diccionario {
    public LinkedList <String> lista;
    public Grafo diccionario;

    public void setDiccionario(Grafo diccionario) {
        this.diccionario = diccionario;
    }

    public Grafo getDiccionario() {
        return diccionario;
    }

    public Diccionario() {
        Cronometro dic = new Cronometro();
        lista = new LinkedList();
        diccionario = new Grafo("Escalera de palabras");
        System.out.println("Espere, cargando el diccionario .... ");
        File fichero;
        BufferedReader entrada;
        fichero = new File("/home/hector9317/NetBeansProjects/EscaleraPalabras/src/escalerapalabras/fichero.txt");
        
        try {
            entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            while(entrada.ready()) {
                linea = entrada.readLine();
                lista.add(linea);
            }
            setDiccionario(construir_diccionario());
        } catch (IOException e) {}
        System.out.println("Se cargo correctamente el diccionario");
        System.out.println("Tiempo requerido para cargar el diccionario = " + dic.tiempoTranscurrido() + " s");
    }
        
    public Grafo construir_diccionario() {
        int i, contador;
        Grafo grafo = new Grafo("Escalera de palabras");
        for(String q : lista)
            grafo.agregarNodo(q);
        
        Nodo nodo;
        Collection<String> nombre = grafo.nombresNodos();
        
        for(String t : nombre) {
            for(String s : lista) {
                if(!s.equals(t)) {
                    contador = 0;
                    for(i = 0; i < 5; i++) {
                        if(s.charAt(i) != t.charAt(i)) {
                            contador++;
                            if(contador > 1)
                                break;
                        }
                    }

                    if(contador == 1) {
                        nodo = grafo.obtenerNodo(t);
                        nodo.agregarVecino(s,1);
                    }
                }
            }
        }        
        return grafo;
    }
}
    