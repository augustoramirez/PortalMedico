package escalerapalabras;

import java.util.Collection;
import java.util.HashMap;

public class Nodo {
    public int f;
    public int color;
    public String pi;
    public String nombre;
    public int distancia;
    public boolean visitado;
    public HashMap<String,Integer> vecinos;
    public int noVecinos;
    
    public Nodo(String nombre) {
        noVecinos = 0;
        this.nombre = nombre;
        vecinos = new HashMap();
    }
    
    public int CostoVecino(Nodo z) {
	int c = vecinos.get(z.nombre);
	return c;
    }
    
    public void agregarVecino(String nombreVecino, int costoVecino) {
        noVecinos++;
        vecinos.put(nombreVecino, costoVecino);
    }
    
    public Collection obtenerVecinos() {
        return vecinos.keySet();
    }

    public void imprimir() {
        System.out.println(nombre + " " + noVecinos);
    }
    
}