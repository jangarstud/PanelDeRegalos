package logica.util;

import java.io.*;
import java.util.*;

import logica.Regalo;

/**
 * FileUtil ser� una clase de que se encargar� de cargar los datos de un fichero
 * y parsearlos.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public abstract class FileUtil {

	/**
	 * Carga los datos del fichero de entrada indicado en la lista pasada como
	 * par�metro. Los separadores del formato del fichero ser�n "@"'s.
	 * 
	 * @param nombreFicheroEntrada El nombre del fichero de entrada
	 * @param listaCatalogo        La lista sobre la que se cargar�n los regalos
	 */
	public static void loadFile(String nombreFicheroEntrada, List<Regalo> listaCatalogo) {

		String linea;
		String[] datosArticulo = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				listaCatalogo.add(new Regalo(datosArticulo));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}
	
	/*

	public static void saveToFile(String nombreFicheroSalida, Pedido pedido ){
		try {
		        BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat"));
		        String linea = pedido.toString();
		        fichero.write(linea);
		        fichero.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("El archivo no se ha podido guardar");
		    }
		catch (IOException ioe) {
		      new RuntimeException("Error de entrada/salida");
		}
	  }
	  
	  */
}
