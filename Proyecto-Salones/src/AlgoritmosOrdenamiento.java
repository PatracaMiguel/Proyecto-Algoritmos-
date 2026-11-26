public class AlgoritmosOrdenamiento {
    public static Metricas bubbleSort(int[] arr) {
        Metricas m = new Metricas();
        int n = arr.length;
        boolean intercambio;

        long inicio = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            intercambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                m.comparaciones++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    m.intercambios++;
                    intercambio = true;
                }
            }
            if (!intercambio) {
                break;
            }
        }
        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    public static Metricas selectionSort(int[] arr) {
        Metricas m = new Metricas();
        int n = arr.length;

        long inicio = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                m.comparaciones++;
                if (arr[j] < arr[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            if (indiceMinimo != i) {
                int temp = arr[i];
                arr[i] = arr[indiceMinimo];
                arr[indiceMinimo] = temp;
                m.intercambios++;
            }
        }
        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    public static Metricas insertionSort(int[] arr) {
        Metricas m = new Metricas();
        int n = arr.length;

        long inicio = System.nanoTime();
        for (int i = 1; i < n; i++) {
            int clave = arr[i];
            int j = i - 1;

            while (j >= 0) {
                m.comparaciones++;
                if (arr[j] > clave) {
                    arr[j + 1] = arr[j];
                    m.intercambios++;
                    j = j - 1;
                } else {
                    break;
                }
            }
            arr[j + 1] = clave;
        }
        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    public static Metricas mergeSort(int[] arr) {
        Metricas m = new Metricas();
        long inicio = System.nanoTime();
        mergeSortRecursivo(arr, 0, arr.length - 1, m);
        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    private static void mergeSortRecursivo(int[] arr, int inicio, int fin, Metricas m) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            mergeSortRecursivo(arr, inicio, medio, m);
            mergeSortRecursivo(arr, medio + 1, fin, m);
            merge(arr, inicio, medio, fin, m);
        }
    }

    private static void merge(int[] arr, int inicio, int medio, int fin, Metricas m) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;

        int[] izquierda = new int[n1];
        int[] derecha = new int[n2];

        for (int i = 0; i < n1; i++)
            izquierda[i] = arr[inicio + i];
        for (int j = 0; j < n2; j++)
            derecha[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = inicio;

        while (i < n1 && j < n2) {
            m.comparaciones++;
            if (izquierda[i] <= derecha[j]) {
                arr[k] = izquierda[i];
                i++;
            } else {
                arr[k] = derecha[j];
                j++;
            }
            m.intercambios++;
            k++;
        }

        while (i < n1) {
            arr[k] = izquierda[i];
            m.intercambios++;
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = derecha[j];
            m.intercambios++;
            j++;
            k++;
        }
    }

    public static Metricas quickSort(int[] arr) {
        Metricas m = new Metricas();
        long inicio = System.nanoTime();
        try {
            quickSortRecursivo(arr, 0, arr.length - 1, m);
        } catch (StackOverflowError e) {
            m.desbordado = true;
        }
        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    private static void quickSortRecursivo(int[] arr, int bajo, int alto, Metricas m) {
        if (bajo < alto) {
            int pi = particionLomuto(arr, bajo, alto, m);
            quickSortRecursivo(arr, bajo, pi - 1, m);
            quickSortRecursivo(arr, pi + 1, alto, m);
        }
    }

    private static int particionLomuto(int[] arr, int bajo, int alto, Metricas m) {
        int pivote = arr[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            m.comparaciones++;
            if (arr[j] <= pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                m.intercambios++;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        m.intercambios++;

        return i + 1;
    }

    public static Metricas heapSort(int[] arr) {
        Metricas m = new Metricas();
        int n = arr.length;

        long inicio = System.nanoTime();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, m);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            m.intercambios++;

            heapify(arr, i, 0, m);
        }

        long fin = System.nanoTime();
        m.tiempoMs = (fin - inicio) / 1e6;
        return m;
    }

    private static void heapify(int[] arr, int n, int i, Metricas m) {
        int mayor = i;
        int izq = 2 * i + 1;
        int der = 2 * i + 2;

        if (izq < n) {
            m.comparaciones++;
            if (arr[izq] > arr[mayor]) {
                mayor = izq;
            }
        }

        if (der < n) {
            m.comparaciones++;
            if (arr[der] > arr[mayor]) {
                mayor = der;
            }
        }

        if (mayor != i) {
            int swap = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = swap;
            m.intercambios++;

            heapify(arr, n, mayor, m);
        }
    }
}
