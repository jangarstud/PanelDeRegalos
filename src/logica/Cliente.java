package logica;

/**
 * Cliente representar� la clase de la l�gica que almacena informaci�n del
 * cliente y que determina si �ste puede participar o no. por l�nea.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Cliente {

	private String id;
	private String nombreCompleto;
	private boolean puedeJugar;
	private int puntos;

	/**
	 * Constructor por defecto de la clase Cliente que por defecto inhabilitar� la
	 * participaci�n del cliente y le asociar� 0 puntos a �ste.
	 */
	public Cliente() {
		puedeJugar = false;
		puntos = 0;
	}

	/**
	 * Constructor de la clase Cliente que recibe un vector de cadenas String que
	 * constituye el conjunto de atributos del cliente. Siguiendo el orden del
	 * formato especificado para el fichero de clientes en primer lugar se
	 * encontrar� el id del cliente; en segundo lugar el nombre del cliente; y en
	 * tercer lugar si �ste puede o no participar.
	 * 
	 * @param atributosCliente El vector de Strings del conjunto de atributos del
	 *                         cliente
	 */
	public Cliente(String[] atributosCliente) {
		id = atributosCliente[0];
		nombreCompleto = atributosCliente[1];
		puedeJugar = atributosCliente[2].equals("1") ? true : false;
		puntos = 0;
	}

	/**
	 * Decrementa los puntos de usuario tanto como el m�todo haya recibido en su
	 * par�metro 'decremento'.
	 * 
	 * @param decremento El n�mero de puntos a decrementar del cliente
	 */
	public void decrementarPuntosUsuario(int decremento) {
		puntos -= decremento;
	}

	/**
	 * Getter del ID del cliente.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del ID del cliente para el
	 * atributo 'id'.
	 * 
	 * @return El ID del cliente
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter del ID del cliente.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del ID del cliente para el
	 * atributo 'id'.
	 * 
	 * @return El ID del cliente
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Getter del booleano del cliente que determina si puede jugar.
	 * 
	 * Este m�todo devuelve el valor que tiene el booleano del cliente que determina
	 * si puede jugar para el atributo 'puedeJugar'.
	 * 
	 * @return El booleano del cliente que determina si puede jugar
	 */
	public boolean puedeJugar() {
		return puedeJugar;
	}

	/**
	 * Getter de los puntos del cliente.
	 * 
	 * Este m�todo devuelve el valor que tiene el entero de los puntos del cliente
	 * para el atributo 'puntos'.
	 * 
	 * @return Los puntos del cliente
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Setter del ID del cliente.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el ID del cliente para el
	 * atributo 'id'.
	 * 
	 * @param id El nuevo ID del cliente
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter del nombre completo del cliente.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el nombre completo del
	 * cliente para el atributo 'nombreCompleto'.
	 * 
	 * @param nombreCompleto El nuevo nombre completo del cliente
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Setter del booleano del cliente que determina si puede jugar.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el booleano del cliente que
	 * determina si puede jugar para el atributo 'puedeJugar'.
	 * 
	 * @param puedeJugar El nuevo booleano del cliente que determina si puede jugar
	 */
	public void setPuedeJugar(boolean puedeJugar) {
		this.puedeJugar = puedeJugar;
	}

	/**
	 * Setter de los puntos del cliente.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el entero de los puntos del
	 * cliente para el atributo 'puntos'.
	 * 
	 * @param puntos El nuevo valor de los puntos del cliente
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
