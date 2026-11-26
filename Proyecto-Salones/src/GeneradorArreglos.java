import java.util.Random;

public class GeneradorArreglos {

    public static int[] aleatorio(int n) {
        int[] datos = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            datos[i] = rand.nextInt(n * 5);
        }
        return datos;
    }

    public static int[] ordenado(int n) {
        int[] datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = i + 1;
        }
        return datos;
    }

    public static int[] inverso(int n) {
        int[] datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = n - i;
        }
        return datos;
    }
}
