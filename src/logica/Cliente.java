package logica;

/**
 * Cliente representará la clase de la lógica que almacena información del
 * cliente y que determina si éste puede participar o no. por línea.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class Cliente {

	private String id;
	private String nombreCompleto;
	private boolean puedeJugar;
	private int puntos;

	/**
	 * Constructor por defecto de la clase Cliente que por defecto inhabilitará la
	 * participación del cliente y le asociará 0 puntos a éste.
	 */
	public Cliente() {
		puedeJugar = false;
		puntos = 0;
	}

	/**
	 * Constructor de la clase Cliente que recibe un vector de cadenas String que
	 * constituye el conjunto de atributos del cliente. Siguiendo el orden del
	 * formato especificado para el fichero de clientes en primer lugar se
	 * encontrará el id del cliente; en segundo lugar el nombre del cliente; y en
	 * tercer lugar si éste puede o no participar.
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
	 * Decrementa los puntos de usuario tanto como el método haya recibido en su
	 * parámetro 'decremento'.
	 * 
	 * @param decremento El número de puntos a decrementar del cliente
	 */
	public void decrementarPuntosUsuario(int decremento) {
		puntos -= decremento;
	}

	/**
	 * Getter del ID del cliente.
	 * 
	 * Este método devuelve el valor que tiene el objeto del ID del cliente para el
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
	 * Este método devuelve el valor que tiene el objeto del ID del cliente para el
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
	 * Este método devuelve el valor que tiene el booleano del cliente que determina
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
	 * Este método devuelve el valor que tiene el entero de los puntos del cliente
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
	 * Este método establece el nuevo valor que tendrá el ID del cliente para el
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
	 * Este método establece el nuevo valor que tendrá el nombre completo del
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
	 * Este método establece el nuevo valor que tendrá el booleano del cliente que
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
	 * Este método establece el nuevo valor que tendrá el entero de los puntos del
	 * cliente para el atributo 'puntos'.
	 * 
	 * @param puntos El nuevo valor de los puntos del cliente
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
