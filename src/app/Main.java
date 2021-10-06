/**
 * 
 */
package app;

/**
 * @author Santiago Tangarife
 *
 */
public class Main {

	private static Desencriptar[] hilos;
	
	private static Programa matriz ;

	public static void main(String[] args){

		try {
			matriz= new Programa();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Encriptar p= new Encriptar();
		String cadena = "aaazydc";
		String algoritmo = "MD5";

		String codigo = p.generar_codigo(cadena, algoritmo);

		System.out.println("Cadena ingresada: "+ cadena);
		System.out.println("Codigo generado con "+algoritmo+": "+codigo);

		long tInic=System.currentTimeMillis();
		int numThreads= 8;
		hilos= new Desencriptar[numThreads];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i]= new Desencriptar(i,codigo,algoritmo, matriz);
			hilos[i].run();
		}
		long tfin=System.currentTimeMillis();
		long t=tfin-tInic;
		
		System.out.println("Cadena encontrada con el algoritmo "+ algoritmo+" "+ matriz.getCadena());
		System.out.println("Tiempo (ms): "+t);

	}

}
