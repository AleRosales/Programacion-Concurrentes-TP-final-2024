package test;

import java.util.Arrays;

import datos.Funciones;

public class Test{
	

		private static void IniciarCarrera(int n) {
			double tiempoInicial, tiempoFinal;
			
			
			int[] array = Funciones.generarArrayAleatorio(n, 0, 1000);
			int[] arrayCopy =Arrays.copyOf(array,array.length);
			
			tiempoInicial = System.nanoTime();										
			System.out.print("\nSecuencial con "+n+"\n");
			Funciones.quickSort(array,0, n-1);
			
			tiempoFinal = System.nanoTime()-tiempoInicial;
			//Funciones.mostrarArray(array);
			System.out.print("\n Demoró: "+ tiempoFinal/100 +" nanoSegundos \n");
						
			tiempoInicial = System.nanoTime();										
			System.out.print("\nConcurrente con "+n+"\n");
			Funciones.quickSortConcurrenty(arrayCopy,0,n-1);
			
			tiempoFinal = System.nanoTime()-tiempoInicial;
			//Funciones.mostrarArray(arrayCopy);
			System.out.print("\n Demoró: "+ tiempoFinal/100 +" nanoSegundos \n");
			

		}
		public static void main(String[] args) {
		
			//testo 1
			IniciarCarrera(10000);
			//testeo 2
			IniciarCarrera(500000);
			//testeo 3
			IniciarCarrera(800000);

		}
	

}