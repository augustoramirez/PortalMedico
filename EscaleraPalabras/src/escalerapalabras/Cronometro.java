package escalerapalabras;
public class Cronometro {
    private double tiempo;
    
    public Cronometro() {
        ponerCero();
    }
    
    public void ponerCero() {
        tiempo = System.currentTimeMillis();
    }
    
    public double tiempoTranscurrido() {
        double t;
        t = (System.currentTimeMillis() - tiempo) / 1000;
        return t;
    }
}
