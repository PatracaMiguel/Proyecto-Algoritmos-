public class Metricas {
    public long comparaciones = 0;
    public long intercambios = 0;
    public double tiempoMs = 0;
    public boolean desbordado = false;

    public void reiniciar() {
        comparaciones = 0;
        intercambios = 0;
        tiempoMs = 0;
        desbordado = false;
    }
}
