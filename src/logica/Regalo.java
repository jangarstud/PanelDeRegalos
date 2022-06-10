package logica;

import java.util.Objects;

/**
 * Regalo representará la clase de la lógica que almacena información de cada
 * artículo de regalo individualmente y que redefine el método 'equals'
 * comparando los objetos a través de su código o incluso su fecha y sus
 * observaciones (en caso de tratarse de un viaje).
 * 
 * @author José Antonio García Fuentes
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

	// El primer carácter de cada categoría se usará para procesar el fichero
	// de regalos con el formato establecido para la categoría en castellano
	// (Ejemplo: AFOOD - 'A' -> Alimentación)
	public static enum Categoria {
		AFOOD, DSPORTS, EELECTRONICS, JTOYS, VTRIPS
	};

	/**
	 * Constructor de la clase Regalo que parsea la información que recibe como
	 * parámetro en forma de vector de cadenas de texto. En el caso de la categoría
	 * se recorre el enumerado para comprobar que la categoría existe en la
	 * aplicación.
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
	 * como parámetro en el regalo a crear, posibilitando así el clonar un mismo
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
	 * Getter del código de regalo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del código de regalo para
	 * el atributo 'codigo'.
	 * 
	 * @return El código de regalo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Setter del código de regalo.
	 * 
	 * Este método establece el nuevo valor que tendrá el código de regalo para el
	 * atributo 'codigo'.
	 * 
	 * @param codigo El nuevo código de regalo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Getter de la categoría de regalo.
	 * 
	 * Este método devuelve el valor que tiene el enumerado de la categoría de
	 * regalo para el atributo 'categoriaRegalo'.
	 * 
	 * @return La categoría de regalo
	 */
	public Categoria getCategoriaRegalo() {
		return categoriaRegalo;
	}

	/**
	 * Setter de la categoría de regalo.
	 * 
	 * Este método establece el nuevo valor que tendrá la categoría de regalo para
	 * el atributo 'categoriaRegalo'.
	 * 
	 * @param categoriaRegalo La nueva categoría de regalo
	 */
	public void setCategoriaRegalo(Categoria categoriaRegalo) {
		this.categoriaRegalo = categoriaRegalo;
	}

	/**
	 * Getter de la denominación del regalo.
	 * 
	 * Este método devuelve el valor que tiene el objeto de la denominación del
	 * regalo para el atributo 'denominacion'.
	 * 
	 * @return La denominación del regalo
	 */
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * Setter de la denominación del regalo.
	 * 
	 * Este método establece el nuevo valor que tendrá la denominación del regalo
	 * para el atributo 'denominacion'.
	 * 
	 * @param denominacion La nueva denominación del regalo
	 */
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	/**
	 * Getter de la descripción del regalo.
	 * 
	 * Este método devuelve el valor que tiene el objeto de la descripción del
	 * regalo para el atributo 'descripcion'.
	 * 
	 * @return La descripción del regalo
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter de la descripción del regalo.
	 * 
	 * Este método establece el nuevo valor que tendrá la descripción del regalo
	 * para el atributo 'descripcion'.
	 * 
	 * @param descripcion La nueva descripción del regalo
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Getter de los puntos del regalo.
	 * 
	 * Este método devuelve el valor que tiene el entero de los puntos del regalo
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
	 * Este método establece el nuevo valor que tendrá el entero de los puntos del
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
	 * Este método devuelve el valor que tiene el objeto de la fecha del regalo para
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
	 * Este método establece el nuevo valor que tendrá la fecha del regalo para el
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
	 * Este método devuelve el valor que tiene el objeto de las observaciones del
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
	 * Este método establece el nuevo valor que tendrá las observaciones del regalo
	 * para el atributo 'observaciones'.
	 * 
	 * @param observaciones Las nuevas observaciones del regalo
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Compara dos objetos de la clase Regalo y determina si son iguales
	 * redefiniendo el método por defecto y teniendo en cuenta las observaciones y
	 * la fecha (en caso de tratarse de un viaje); o solamente el código del regalo
	 * si es cualquier otro tipo de artículo.
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
