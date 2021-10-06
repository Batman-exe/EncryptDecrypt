package app;

public class Desencriptar extends Thread{

	private Encriptar p;

	private Programa matriz;

	private String laCadena="";

	private String algoritmo;

	private String codigo;

	long tInic;
	
	private int id;
	
	public Desencriptar(int id, String codigo, String algoritmo, Programa programa) {
		this.id=id;
		tInic=System.currentTimeMillis();
		p= new Encriptar();
		matriz=programa;
		this.algoritmo=algoritmo;
		this.codigo=codigo;
	}

	public boolean identificar_entrada(String cadena)
	{
		boolean encontrada=false;

		if(p.generar_codigo(cadena, algoritmo).equals(codigo))
			encontrada=true;

		return encontrada;
	}

	public String getLaCadena(){
		return laCadena;
	}

	public void run() {
		String cadenaActual="";
		while(matriz.esFin()==false){
			if(identificar_entrada(cadenaActual)==true){
				laCadena=cadenaActual;
				matriz.cadenaEncontrada(cadenaActual); 
			}
			else
			{
				cadenaActual= matriz.getCadenaActual();
				matriz.increasePos();
			}
		}
		long tFin = System.currentTimeMillis();
		long t= tFin-tInic;
		System.out.println("Tiempo thread "+id+": "+t);
	}
}
