package logica;

import logica.util.ErrorAccesoException;

/**
 * Aplicacion ser� la clase principal de la l�gica encargada en mayor medida de
 * mediar entre la capa de l�gica y la interfaz gr�fica. Instanciar� el cat�logo
 * de regalos as� como los clientes.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Aplicacion {

	private Catalogo catalogo = new Catalogo();
	private Clientes clientes = new Clientes();

	/**
	 * Constructor de la aplicaci�n que cargar� el cat�logo y los clientes de la
	 * aplicaci�n.
	 * 
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public Aplicacion() throws ErrorAccesoException {
		catalogo.cargar();
		clientes.cargar();
	}

	/**
	 * Getter del cat�logo de la aplicaci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del cat�logo para el
	 * atributo 'catalogo'.
	 * 
	 * @return El cat�logo de la aplicaci�n
	 */
	public Catalogo getCatalogo() {
		return catalogo;
	}

	/**
	 * Getter de los clientes de la aplicaci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de los clientes para el
	 * atributo 'clientes'.
	 * 
	 * @return Los clientes de la aplicaci�n
	 */
	public Clientes getClientes() {
		return clientes;
	}

}
