public class EvaluadorComplejidad {

    public static void ejecutarPruebas() {
        int[] tamanos = {1000, 10000, 50000};
        String[] nombresCasos = {"Aleatorio", "Ordenado", "Inverso"};
        String[] nombresAlgoritmos = {
            "Bubble Sort",
            "Selection Sort",
            "Insertion Sort",
            "Merge Sort",
            "Quick Sort",
            "Heap Sort"
        };

        System.out.println("\nModulo 3: Experimento de Complejidad");
        System.out.printf("%-7s | %-10s | %-16s | %14s | %14s | %14s%n",
                "Tamano", "Caso", "Algoritmo", "Tiempo (ms)", "Comparaciones", "Intercambios");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (int n : tamanos) {
            for (int c = 1; c <= 3; c++) {
                String casoNombre = nombresCasos[c - 1];

                int[] base;
                if (c == 1) {
                    base = GeneradorArreglos.aleatorio(n);
                } else if (c == 2) {
                    base = GeneradorArreglos.ordenado(n);
                } else {
                    base = GeneradorArreglos.inverso(n);
                }

                for (int a = 0; a < 6; a++) {
                    int[] copia = base.clone();
                    Metricas m = null;

                    switch (a) {
                        case 0: m = AlgoritmosOrdenamiento.bubbleSort(copia); break;
                        case 1: m = AlgoritmosOrdenamiento.selectionSort(copia); break;
                        case 2: m = AlgoritmosOrdenamiento.insertionSort(copia); break;
                        case 3: m = AlgoritmosOrdenamiento.mergeSort(copia); break;
                        case 4: m = AlgoritmosOrdenamiento.quickSort(copia); break;
                        case 5: m = AlgoritmosOrdenamiento.heapSort(copia); break;
                    }

                    if (m.desbordado) {
                        System.out.printf("%-7d | %-10s | %-16s | %14s | %14s | %14s%n",
                                n, casoNombre, nombresAlgoritmos[a], "DESBORDADO*", "N/A", "N/A");
                    } else {
                        System.out.printf("%-7d | %-10s | %-16s | %14.4f | %14d | %14d%n",
                                n, casoNombre, nombresAlgoritmos[a], m.tiempoMs, m.comparaciones, m.intercambios);
                    }
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
            }
        }

        System.out.println("\n* Nota sobre Quick Sort (StackOverflowError):");
        System.out.println("  Con particion de Lomuto y ultimo pivote, los casos ordenado e inverso");
        System.out.println("  generan la maxima profundidad de recursion O(n), desbordando la pila de llamadas.");
    }
}
