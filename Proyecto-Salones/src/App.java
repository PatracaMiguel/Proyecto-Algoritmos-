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
        System.out.println();

        System.out.print("Ingrese la capacidad a buscar: ");
        int objetivo = numeros.nextInt();
        int posicion = busquedaBinaria(datosOrdenados, objetivo);
 
        if (posicion != -1) {
            System.out.println("Existe un salón con capacidad " + objetivo + " en la posición " + posicion);
        } else {
            System.out.println("No hay ningún salón con capacidad de " + objetivo);
        }
 
        numeros.close();
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
        public static int busquedaBinaria(int[] arr, int objetivo) {
        int inicio = 0;
        int fin = arr.length - 1;
 
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
 
            if (arr[medio] == objetivo) {
                return medio;     
            } else if (arr[medio] < objetivo) {
                inicio = medio + 1;   
            } else {
                fin = medio - 1;      
            }
        }
        return -1;
    }
}
