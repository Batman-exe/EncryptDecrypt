package app;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Clase que crea un las cadenas de todas las posibles longitudes de 1 a la 4 y las guarda en listas
 * Para conseguir las combinaciones de cadenas de longitud 7 se hace el producto cartesiano de las listas tresXcuatro
 * @author Santiago Tangarife
 *
 */
public class Programa {


	private ArrayList<String> uno; 
	private ArrayList<String> dos; 
	private ArrayList<String> tres; 
	private ArrayList<String> cuatro; 
	
	private String[] alfabeto= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	/**
	 * indice para recorrer la lista tres
	 */
	private int i=0;
	
	/**
	 * indice para recorrer la lista cuatro
	 */
	private int j=0;
	
	/**
	 * True si ya se encontró la cadena o si ya se analizó todas las cadenas
	 */
	private boolean fin;
	
	
	private String cadena;
	
	long tInic;
	
	public Programa() throws Exception {
		tInic= System.currentTimeMillis();
		medirCarga();
		fin=false;
		cadena="";
		generarCadenas();
	}

	/**
	 * Adiciona las letras del alfabeto a cada una de las cadenas de la lista anterior 
	 * @param anterior lista a la que se lvan a agregar letras
	 * @param letras las letras del alfabeto
	 * @return lista con las letras agregadas i.e. anterior=[a, ... , z] siguientes=[aa, ... , zz]
	 */
	private ArrayList<String> siguientesCadenas(ArrayList<String> anterior, String[]letras){
		ArrayList<String> actual= new ArrayList<>();

		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < anterior.size(); j++){
				String este= letras[i]+anterior.get(j);
				actual.add(este);
			}
		}

		return actual;
	}

	/**
	 * genera las cadenas de longitud 1 a 4
	 */
	private void generarCadenas(){
		uno=new ArrayList<>();
		//uno.add("");//añade la cadena vacía
		//Crea las cadenas de tamaño 1
		for (int i = 0; i < alfabeto.length; i++)
			uno.add(alfabeto[i]);

		dos = siguientesCadenas(uno, alfabeto);
		
		tres = siguientesCadenas(dos, alfabeto);
		
		cuatro = siguientesCadenas(tres, alfabeto);
	}

	/**
	 * me devuelve la cadena actual
	 * @return
	 */
	public String getCadenaActual(){
		return tres.get(i) +cuatro.get(j);
	}
	
	/**
	 * Sirve para recorrer las listas tres y cuatro como si fueran matriz, si llegan a la pos final terminan
	 */
	public void increasePos(){
		if(i<tres.size()-1 && j<cuatro.size()-1)
			j++;
		
		else if(i<tres.size()-1 && j>=cuatro.size()-1){
			i++;
			j=0;
		}
		else if(i>=tres.size()-1 && j<cuatro.size()-1)
			j++;
		else if(i>=tres.size()-1 && j>=cuatro.size()-1)
			fin=true;
	}
	
	public boolean esFin(){
		return fin;
	}
	
	public void cadenaEncontrada(String cadena)
	{
		this.cadena=cadena;
		fin=true;
	}
	
	public String getCadena(){
		return cadena;
	}
	
	public void medirCarga() throws Exception{
		while(fin){
			long tAct=System.currentTimeMillis();
			if(tAct-tInic>=300000)
			{
				System.out.println("CPU Load: "+getSystemCpuLoad());
				tInic=System.currentTimeMillis();
			}
		}
	}
	/**
	 *Codigo de la guia 
	 * @return
	 * @throws Exception
	 */
	public double getSystemCpuLoad() throws Exception {
		 MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		 ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		 AttributeList list = mbs.getAttributes(name, new String[]{ "SystemCpuLoad" });
		 if (list.isEmpty()) return Double.NaN;
		 Attribute att = (Attribute)list.get(0);
		 Double value = (Double)att.getValue();
		 // usually takes a couple of seconds before we get real values
		 if (value == -1.0) return Double.NaN;
		 // returns a percentage value with 1 decimal point precision
		 return ((int)(value * 1000) / 10.0);
		 }
}