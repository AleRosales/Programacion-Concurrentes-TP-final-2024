package datos;
import java.util.Random;
import java.util.concurrent.RecursiveTask;
 
public class QuickSortMutliThreading
    extends RecursiveTask<Integer> {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int start, end;
    int[] arr;
 
    /**
     * Finding random pivoted and partition
     * array on a pivot.
     * There are many different
     * partitioning algorithms.
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private int partition(int start, int end,
                        int[] arr)
    {
		
        int i = start, j = end;
 
        // Decide random pivot
        int pivoted = new Random()
                         .nextInt(j - i)
                     + i;
 
        // Cambia el pivoted por el ultimo elemento del array
        int t = arr[j];
        arr[j] = arr[pivoted];
        arr[pivoted] = t;
        j--;
 
        // Pone a la izquierda lo menor al pivoted y a la derecha lo mayor
        while (i <= j) {
 
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }
 
            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }
 
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }
 
        // Cambia el pivoted a su lugar correcto
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        //Devuelves j + 1, ya que los valores a la izquierda son menores que arr[j+1], y los valores a la derecha son mayores que arr[j + 1]
        return j + 1;
    }
 
    // QuickSort constructor
    public QuickSortMutliThreading(int start,
                                   int end,
                                   int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
 
    @Override
    protected Integer compute()
    {
        // Base case
        if (start >= end)
            return null;
 
        // Find partition
        int p = partition(start, end, arr);
 
        // Divide array en 2 hilos
        //hilo la mitad izquierda
        QuickSortMutliThreading left
            = new QuickSortMutliThreading(start,
                                          p - 1,
                                          arr);
        //hilo la mitad derecha
        QuickSortMutliThreading right
            = new QuickSortMutliThreading(p + 1,
                                          end,
                                          arr);
 
        left.fork();//envia a ejecutar en otro hilo
        right.compute();//incia el mismo algoritmo en la parte derecha, crea mas subHilos, recursivo
 
        left.join();//espera a que termine el hilo izquierdo
 
        // no devuelve nada por que trabajan sobre el mismo array
        return null;
    }
}