import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] datosActuales = null;
        int opcionPrincipal;

        do {
            System.out.println("\nProyecto: Analisis de Algoritmos");
            System.out.println("1. Ingresar datos manualmente");
            System.out.println("2. Generar casos de prueba (Aleatorio, Ordenado, Inverso)");
            System.out.println("3. Ejecutar un algoritmo de ordenamiento individual");
            System.out.println("4. Mostrar tabla comparativa con datos actuales");
            System.out.println("5. Busqueda binaria");
            System.out.println("6. Ejecutar Experimento de Complejidad (Modulo 3)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcionPrincipal = leerEntero(scanner);

            switch (opcionPrincipal) {
                case 1:
                    datosActuales = ingresarDatosManualmente(scanner);
                    break;

                case 2:
                    datosActuales = menuGenerarCasosPrueba(scanner);
                    break;

                case 3:
                    if (datosActuales == null || datosActuales.length == 0) {
                        System.out.println("\n[!] Primero debe ingresar o generar datos.");
                    } else {
                        datosActuales = menuSeleccionAlgoritmo(scanner, datosActuales);
                    }
                    break;

                case 4:
                    if (datosActuales == null || datosActuales.length == 0) {
                        System.out.println("\n[!] Primero debe ingresar o generar datos.");
                    } else {
                        ejecutarComparativaActuales(datosActuales);
                    }
                    break;

                case 5:
                    if (datosActuales == null || datosActuales.length == 0) {
                        System.out.println("\n[!] No hay datos cargados para realizar busquedas.");
                    } else {
                        if (!estaOrdenado(datosActuales)) {
                            System.out.println("\n[!] El arreglo actual no estaba ordenado. Ordenandolo con Quick Sort...");
                            int[] copia = datosActuales.clone();
                            AlgoritmosOrdenamiento.quickSort(copia);
                            datosActuales = copia;
                        }
                        ejecutarBusquedaBinaria(scanner, datosActuales);
                    }
                    break;

                case 6:
                    EvaluadorComplejidad.ejecutarPruebas();
                    break;

                case 0:
                    System.out.println("\nSaliendo del programa...");
                    break;

                default:
                    System.out.println("\nOpcion invalida. Intente de nuevo.");
            }

        } while (opcionPrincipal != 0);

        scanner.close();
    }

    public static int[] ingresarDatosManualmente(Scanner scanner) {
        System.out.print("\nIngrese la cantidad de datos: ");
        int n = leerEntero(scanner);
        if (n <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return null;
        }

        int[] datos = new int[n];
        System.out.println("Ingrese los " + n + " elementos:");
        for (int i = 0; i < n; i++) {
            datos[i] = leerEntero(scanner);
        }
        System.out.println("Arreglo cargado exitosamente.");
        mostrarVistaPrevia(datos);
        return datos;
    }

    public static int[] menuGenerarCasosPrueba(Scanner scanner) {
        System.out.print("\nIngrese el tamano del arreglo (ej. 1000, 10000, 50000): ");
        int n = leerEntero(scanner);
        if (n <= 0) {
            System.out.println("El tamano debe ser mayor a 0.");
            return null;
        }

        System.out.println("\nSeleccione el escenario:");
        System.out.println("1. Caso 1: Aleatorio (desordenado)");
        System.out.println("2. Caso 2: Ordenado ascendente (mejor caso)");
        System.out.println("3. Caso 3: Inverso descendente (peor caso)");
        System.out.print("Elija opcion: ");
        int caso = leerEntero(scanner);

        int[] datos = null;
        switch (caso) {
            case 1:
                datos = GeneradorArreglos.aleatorio(n);
                System.out.println("\n-> Caso 1 generado: " + n + " elementos aleatorios.");
                break;
            case 2:
                datos = GeneradorArreglos.ordenado(n);
                System.out.println("\n-> Caso 2 generado: " + n + " elementos en orden ascendente.");
                break;
            case 3:
                datos = GeneradorArreglos.inverso(n);
                System.out.println("\n-> Caso 3 generado: " + n + " elementos en orden inverso.");
                break;
            default:
                System.out.println("Opcion no valida.");
                return null;
        }

        mostrarVistaPrevia(datos);
        return datos;
    }

    public static int[] menuSeleccionAlgoritmo(Scanner scanner, int[] datosOriginales) {
        System.out.println("\nModulos 1 y 2: Algoritmos y Metricas");
        System.out.println("1. Bubble Sort optimizado");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort (Lomuto)");
        System.out.println("6. Heap Sort");
        System.out.print("Seleccione una opcion (1-6): ");
        int opcion = leerEntero(scanner);

        int[] copiaDatos = datosOriginales.clone();
        Metricas m = null;
        String nombre = "";

        switch (opcion) {
            case 1:
                nombre = "Bubble Sort optimizado";
                m = AlgoritmosOrdenamiento.bubbleSort(copiaDatos);
                break;
            case 2:
                nombre = "Selection Sort";
                m = AlgoritmosOrdenamiento.selectionSort(copiaDatos);
                break;
            case 3:
                nombre = "Insertion Sort";
                m = AlgoritmosOrdenamiento.insertionSort(copiaDatos);
                break;
            case 4:
                nombre = "Merge Sort";
                m = AlgoritmosOrdenamiento.mergeSort(copiaDatos);
                break;
            case 5:
                nombre = "Quick Sort (Lomuto)";
                m = AlgoritmosOrdenamiento.quickSort(copiaDatos);
                break;
            case 6:
                nombre = "Heap Sort";
                m = AlgoritmosOrdenamiento.heapSort(copiaDatos);
                break;
            default:
                System.out.println("Opcion no valida.");
                return datosOriginales;
        }

        System.out.println("\nResultados para: " + nombre);
        if (m.desbordado) {
            System.out.println(" [!] Desbordamiento de pila (StackOverflowError).");
            System.out.println(" Causa: La recursion excedio el limite en este caso.");
        } else {
            System.out.printf("  - Tiempo de ejecucion : %.4f ms%n", m.tiempoMs);
            System.out.printf("  - Comparaciones       : %,d%n", m.comparaciones);
            System.out.printf("  - Intercambios        : %,d%n", m.intercambios);
        }
        mostrarVistaPrevia(copiaDatos);

        return copiaDatos;
    }

    public static void ejecutarComparativaActuales(int[] datos) {
        System.out.println("\nTabla comparativa con datos actuales (" + datos.length + " elementos)");
        System.out.printf("%-24s | %15s | %15s | %15s%n",
                "Algoritmo", "Tiempo (ms)", "Comparaciones", "Intercambios");
        System.out.println("------------------------------------------------------------------------------------------");

        String[] nombres = {
            "Bubble Sort", "Selection Sort", "Insertion Sort",
            "Merge Sort", "Quick Sort", "Heap Sort"
        };

        for (int i = 0; i < 6; i++) {
            int[] copia = datos.clone();
            Metricas m = null;
            switch (i) {
                case 0: m = AlgoritmosOrdenamiento.bubbleSort(copia); break;
                case 1: m = AlgoritmosOrdenamiento.selectionSort(copia); break;
                case 2: m = AlgoritmosOrdenamiento.insertionSort(copia); break;
                case 3: m = AlgoritmosOrdenamiento.mergeSort(copia); break;
                case 4: m = AlgoritmosOrdenamiento.quickSort(copia); break;
                case 5: m = AlgoritmosOrdenamiento.heapSort(copia); break;
            }

            if (m.desbordado) {
                System.out.printf("%-24s | %15s | %15s | %15s%n", nombres[i], "DESBORDADO", "N/A", "N/A");
            } else {
                System.out.printf("%-24s | %15.4f | %,15d | %,15d%n",
                        nombres[i], m.tiempoMs, m.comparaciones, m.intercambios);
            }
        }
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

    public static void ejecutarBusquedaBinaria(Scanner scanner, int[] datos) {
        System.out.print("\nIngrese el valor a buscar: ");
        int objetivo = leerEntero(scanner);

        long inicio = System.nanoTime();
        int posicion = busquedaBinaria(datos, objetivo);
        long fin = System.nanoTime();

        long duracionNs = fin - inicio;
        double duracionMs = duracionNs / 1e6;

        if (posicion != -1) {
            System.out.println("Existe un elemento con valor " + objetivo + " en el indice " + posicion);
        } else {
            System.out.println("No se encontro ningun elemento con valor " + objetivo);
        }
        System.out.printf("Tiempo de busqueda: %d ns (%.4f ms)%n", duracionNs, duracionMs);
    }

    public static boolean estaOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void mostrarVistaPrevia(int[] arr) {
        if (arr.length <= 20) {
            System.out.println("Arreglo completo: " + Arrays.toString(arr));
        } else {
            System.out.println("\nVista previa (primeros 5 y ultimos 5 elementos):");
            System.out.printf("[%d, %d, %d, %d, %d, ... , %d, %d, %d, %d, %d]%n",
                    arr[0], arr[1], arr[2], arr[3], arr[4],
                    arr[arr.length - 5], arr[arr.length - 4], arr[arr.length - 3], arr[arr.length - 2],
                    arr[arr.length - 1]);
        }
    }

    public static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Ingrese un numero entero: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
