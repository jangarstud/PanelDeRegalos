package logica;

import java.util.Objects;

/**
 * Regalo representar� la clase de la l�gica que almacena informaci�n de cada
 * art�culo de regalo individualmente y que redefine el m�todo 'equals'
 * comparando los objetos a trav�s de su c�digo o incluso su fecha y sus
 * observaciones (en caso de tratarse de un viaje).
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Regalo {
	public static final int NUM_CATEGORIAS = Categoria.values().length;

	private String codigo;
	private Categoria categoriaRegalo;
	private String denominacion;
	private String descripcion;
	private int puntos;

	private String fecha;
	private String observaciones;

	// El primer car�cter de cada categor�a se usar� para procesar el fichero
	// de regalos con el formato establecido para la categor�a en castellano
	// (Ejemplo: AFOOD - 'A' -> Alimentaci�n)
	public static enum Categoria {
		AFOOD, DSPORTS, EELECTRONICS, JTOYS, VTRIPS
	};

	/**
	 * Constructor de la clase Regalo que parsea la informaci�n que recibe como
	 * par�metro en forma de vector de cadenas de texto. En el caso de la categor�a
	 * se recorre el enumerado para comprobar que la categor�a existe en la
	 * aplicaci�n.
	 * 
	 * @param datos Los atributos del objeto que se desea parsear
	 */
	public Regalo(String[] datos) {
		codigo = datos[0];
		for (Categoria c : Categoria.values()) {
			if (datos[1].equals("" + c.name().toString().charAt(0))) {
				categoriaRegalo = c;
			}
		}
		denominacion = datos[2];
		descripcion = datos[3];
		puntos = Integer.parseInt(datos[4]);
	}

	/**
	 * Constructor de copia que reproduce todas las propiedades del objeto recibido
	 * como par�metro en el regalo a crear, posibilitando as� el clonar un mismo
	 * objeto de la clase Regalo.
	 * 
	 * @param copia El regalo que se desea copiar
	 */
	public Regalo(Regalo copia) {
		this.codigo = copia.getCodigo();
		this.categoriaRegalo = copia.getCategoriaRegalo();
		this.denominacion = copia.getDenominacion();
		this.descripcion = copia.getDescripcion();
		this.puntos = copia.getPuntos();
	}

	/**
	 * Getter del c�digo de regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del c�digo de regalo para
	 * el atributo 'codigo'.
	 * 
	 * @return El c�digo de regalo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Setter del c�digo de regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el c�digo de regalo para el
	 * atributo 'codigo'.
	 * 
	 * @param codigo El nuevo c�digo de regalo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Getter de la categor�a de regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el enumerado de la categor�a de
	 * regalo para el atributo 'categoriaRegalo'.
	 * 
	 * @return La categor�a de regalo
	 */
	public Categoria getCategoriaRegalo() {
		return categoriaRegalo;
	}

	/**
	 * Setter de la categor�a de regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� la categor�a de regalo para
	 * el atributo 'categoriaRegalo'.
	 * 
	 * @param categoriaRegalo La nueva categor�a de regalo
	 */
	public void setCategoriaRegalo(Categoria categoriaRegalo) {
		this.categoriaRegalo = categoriaRegalo;
	}

	/**
	 * Getter de la denominaci�n del regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de la denominaci�n del
	 * regalo para el atributo 'denominacion'.
	 * 
	 * @return La denominaci�n del regalo
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Setter de la denominaci�n del regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� la denominaci�n del regalo
	 * para el atributo 'denominacion'.
	 * 
	 * @param denominacion La nueva denominaci�n del regalo
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Getter de la descripci�n del regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de la descripci�n del
	 * regalo para el atributo 'descripcion'.
	 * 
	 * @return La descripci�n del regalo
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter de la descripci�n del regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� la descripci�n del regalo
	 * para el atributo 'descripcion'.
	 * 
	 * @param descripcion La nueva descripci�n del regalo
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Getter de los puntos del regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el entero de los puntos del regalo
	 * para el atributo 'puntos'.
	 * 
	 * @return Los puntos del regalo
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Setter de los puntos del regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� el entero de los puntos del
	 * regalo para el atributo 'puntos'.
	 * 
	 * @param puntos El nuevo valor de los puntos del regalo
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * Getter de la fecha del regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de la fecha del regalo para
	 * el atributo 'fecha'.
	 * 
	 * @return La fecha del regalo
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Setter de la fecha del regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� la fecha del regalo para el
	 * atributo 'fecha'.
	 * 
	 * @param fecha La nueva fecha del regalo
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Getter de las observaciones del regalo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto de las observaciones del
	 * regalo para el atributo 'observaciones'.
	 * 
	 * @return Las observaciones del regalo
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Setter de las observaciones del regalo.
	 * 
	 * Este m�todo establece el nuevo valor que tendr� las observaciones del regalo
	 * para el atributo 'observaciones'.
	 * 
	 * @param observaciones Las nuevas observaciones del regalo
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Compara dos objetos de la clase Regalo y determina si son iguales
	 * redefiniendo el m�todo por defecto y teniendo en cuenta las observaciones y
	 * la fecha (en caso de tratarse de un viaje); o solamente el c�digo del regalo
	 * si es cualquier otro tipo de art�culo.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regalo other = (Regalo) obj;

		if (other.categoriaRegalo == Categoria.VTRIPS) {
			if (other.observaciones != null && !other.observaciones.trim().isEmpty())
				return Objects.equals(codigo, other.codigo) && Objects.equals(fecha, other.fecha)
						&& Objects.equals(observaciones, other.observaciones);

			return Objects.equals(codigo, other.codigo) && Objects.equals(fecha, other.fecha);
		}

		return Objects.equals(codigo, other.codigo);
	}

}
