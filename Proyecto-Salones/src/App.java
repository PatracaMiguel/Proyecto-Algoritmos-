import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner numeros = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de datos: ");
        int n = numeros.nextInt();
        int datos[] = new int[n];
        System.out.println("Ingrese los datos:");
        for (int i = 0; i < n; i++) {
            datos[i] = numeros.nextInt();
        }

        int datosOrdenados[] = bubbleSort(datos);

        for (int i = 0; i < datosOrdenados.length; i++) {
            System.out.print(datosOrdenados[i] + " ");
        }

    }

    public static int[] bubbleSort(int[] arr) {
            int n = arr.length;
            boolean intercambio;
            
            for (int i = 0; i < n - 1; i++) {
                intercambio = false;
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        intercambio = true;
                    }
                }
                if (!intercambio) {
                    break;
                }
            }
            return arr;
        }
}
