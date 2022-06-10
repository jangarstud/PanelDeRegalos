package logica;

import logica.util.ErrorAccesoException;

/**
 * Aplicacion será la clase principal de la lógica encargada en mayor medida de
 * mediar entre la capa de lógica y la interfaz gráfica. Instanciará el catálogo
 * de regalos así como los clientes.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class Aplicacion {

	private Catalogo catalogo = new Catalogo();
	private Clientes clientes = new Clientes();

	/**
	 * Constructor de la aplicación que cargará el catálogo y los clientes de la
	 * aplicación.
	 * 
	 * @throws ErrorAccesoException Excepción personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public Aplicacion() throws ErrorAccesoException {
		catalogo.cargar();
		clientes.cargar();
	}

	/**
	 * Getter del catálogo de la aplicación.
	 * 
	 * Este método devuelve el valor que tiene el objeto del catálogo para el
	 * atributo 'catalogo'.
	 * 
	 * @return El catálogo de la aplicación
	 */
	public Catalogo getCatalogo() {
		return catalogo;
	}

	/**
	 * Getter de los clientes de la aplicación.
	 * 
	 * Este método devuelve el valor que tiene el objeto de los clientes para el
	 * atributo 'clientes'.
	 * 
	 * @return Los clientes de la aplicación
	 */
	public Clientes getClientes() {
		return clientes;
	}

}
