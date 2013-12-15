package escalerapalabras;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Grafo {
    public int epoca;
    public String nombre;
    public int numeroNodos;
    public LinkedList<String> lista;
    public HashMap<String,Nodo> nodos;
    
    public Grafo(String nombre) {
        numeroNodos = 0;
        this.nombre = nombre;
        nodos = new HashMap();
    }
    
    public void agregarNodo(String nombreNodo) {
        Nodo n = new Nodo(nombreNodo);
        nodos.put(nombreNodo, n);
        numeroNodos += 1;
    }
    
    public Nodo obtenerNodo(String nombreNodo) {
	return nodos.get(nombreNodo);
    }
    
    public Collection nombresNodos() {
	return nodos.keySet();
    }

    public Collection obtenerNodos() {
	return nodos.values();
    }
    
    public void imprimir() {
        System.out.println(nombre + " tiene: " + numeroNodos + " nodos");
    }
    
    public void imprimirNodos() {
	Collection<Nodo> n = obtenerNodos();
	if(n.isEmpty())
	    System.out.println(nombre + " esta vacia");
	for(Nodo m : n)
	    m.imprimir();
    }


    public LinkedList ruta(String nf, LinkedList l) {
	if(l.isEmpty())
	    l.addFirst(nf);
	    	
	Nodo nodo = obtenerNodo(nf);
	String antecesor = nodo.pi;
	
	if(antecesor != null) {
	    l.addFirst(antecesor);
	    ruta(antecesor, l);
	}
	return l;
    }


    public LinkedList busqueda_amplitud(Nodo inicio, Nodo fin) {
        LinkedList<Nodo> Q;
        Collection<Nodo> n;
        n = obtenerNodos();
        
        for(Nodo q : n) {
            if(!q.nombre.equals(inicio.nombre)) {
                q.color = VGlobales.BLANCO;
                q.distancia = VGlobales.INFINITO;
                q.pi = null;
            }
        }
        
        inicio.pi = null;
        inicio.distancia = 0;
        inicio.color = VGlobales.GRIS;

        Q = new LinkedList();
        Q.add(inicio);
        Nodo v, z;
        Collection<String> q;
        
        while(!Q.isEmpty()) {
            z = Q.pollFirst();
            q = z.obtenerVecinos();
            for(String t : q) {
                v = obtenerNodo(t);
                if(v.color == VGlobales.BLANCO) {
                    v.color = VGlobales.GRIS;
                    v.distancia = z.distancia + 1;
                    v.pi = z.nombre;
                    Q.add(v);
                }
            }
            z.color = VGlobales.NEGRO;
        }
        LinkedList ruta = new LinkedList();
        ruta = ruta(fin.nombre, ruta);
        return ruta;
    }


    public void busqueda_profundidad(Nodo inicio) {
        Collection<Nodo> q = obtenerNodos();
        for(Nodo n : q) {
            n.color = VGlobales.BLANCO;
            n.pi = null;
        }
        visitar_dfs(inicio);
    }

    public void visitar_dfs(Nodo inicio) {
        inicio.color = VGlobales.GRIS;
        epoca += 1;
        inicio.distancia = epoca;
        Nodo v;
        Collection<String> e = inicio.obtenerVecinos();
        
        for(String nv : e) {
            v = obtenerNodo(nv);
            if(v.color == VGlobales.BLANCO) {
                v.pi = inicio.nombre;
                visitar_dfs(v);
            }
        }
        inicio.color = VGlobales.NEGRO;
        epoca += 1;
        inicio.f = epoca;
    }
    
    
    public Nodo extraer_minimo(LinkedList<Nodo> Q) {
        Nodo m = Q.getFirst();
        Iterator<Nodo> it = Q.iterator();
        Nodo ayuda;
        while(it.hasNext()) {
            ayuda = it.next();
            if(ayuda.distancia <= m.distancia)
                m = ayuda;
        }
        return m;
    }
    
    
    public void dijkstra(Nodo inicio, Nodo fin) {
        int alt;
        LinkedList <Nodo> Q;
        Collection <Nodo> n;
        n = obtenerNodos();
        
        for(Nodo nodo : n) {
            nodo.distancia = -1;
            nodo.visitado = false;
            nodo.pi = null;
        }
        
        Q = new LinkedList();
        Q.offer(inicio);

        while(!Q.isEmpty()) {
            Nodo u = Q.poll();
            
            if(u.nombre.equals(fin.nombre))
                break;
            
            u.visitado = true;
            Collection<String> nv = u.obtenerVecinos();
            
            for(String v : nv) {
                Nodo nodoVecino = obtenerNodo(v);
                alt = u.distancia + 1;
                if(alt < nodoVecino.distancia || nodoVecino.visitado == false) {
                    nodoVecino.distancia = alt;
                    nodoVecino.pi = u.nombre;
                    Q.offer(nodoVecino);
                }
            }
        }
        
        LinkedList<String> l = new LinkedList();
        Nodo u = fin;
        while(u.pi != null) {
            l.addFirst(u.nombre);
            u = obtenerNodo(u.pi);
        }
        l.addFirst(inicio.nombre);
        System.out.print("La ruta es: " + l + "\n");
    }
}
