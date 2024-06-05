package datos;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Funciones {

	public static int[] generarArrayAleatorio(int n, int min, int max) {
		// Declaración del array
		int[] arr = new int[n];

		// Generación de números aleatorios
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = random.nextInt(max - min + 1) + min;
		}

		return arr;
	}

	// solo usado para testeos
	public static void mostrarArray(int[] arr) {
		System.out.println("\n-----------------------:\n");
		for (int num : arr) {
			System.out.print(num + " - ");
		}
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			if (low < pi)
				quickSort(arr, low, pi - 1);
			if (pi < high)
				quickSort(arr, pi + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {

		// ultimo item es el pivot
		int pivot = arr[high];
		int i = low - 1;

		// Pone a la izquierda lo menor al pivoted y a la derecha lo mayor
		for (int j = low; j <= high - 1; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// Cambia el pivoted a su lugar correcto
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		// Devuelves i + 1, ya que los valores a la izquierda son menores que arr[i+1],
		// y los valores a la derecha son mayores que arr[i + 1]
		return i + 1;
	}

	public static void quickSortConcurrenty(int[] arr, int low, int high) {
		ForkJoinPool pool = ForkJoinPool.commonPool();

		// Inicia el primer hilo
		// Para el rango low hasta high
		pool.invoke(new QuickSortMutliThreading(low, high, arr));
	}
}
