package logica;

import java.util.ArrayList;
import java.util.List;

import logica.Regalo.Categoria;
import logica.util.ErrorAccesoException;
import logica.util.ReadDataFile;
import logica.util.WriteDataFile;
import observer.Observable;

/**
 * Clientes representar� la clase de la l�gica que coordinar� la carga de los
 * ficheros de clientes y el cat�logo de regalos as� como el guardado del
 * fichero de entregas. Adem�s, tambi�n se realizar�n todas las operaciones
 * correspondientes a la validaci�n de la participaci�n; y operaciones sobre las
 * unidades de regalos del carrito entre otras. Ser� una de las clases a
 * observar.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class Clientes extends Observable {
	private static final int EXPECTED_LENGTH = 3;
	private static final String FICHERO_CLIENTES = "files/clientes.dat";
	private static final String FICHERO_ENTREGAS = "files/entregas.dat";
	private List<Cliente> clientes = null;

	private Cliente usuarioActual = new Cliente();
	private List<Regalo> carritoRegalos = new ArrayList<Regalo>();

	/**
	 * Carga los clientes del fichero de clientes leyendo l�nea por l�nea toda la
	 * lista almacenada y a�adi�ndola a la lista de clientes de la aplicaci�n.
	 * 
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public void cargar() throws ErrorAccesoException {
		clientes = new ArrayList<>();
		ReadDataFile f = new ReadDataFile(FICHERO_CLIENTES);

		String[] atributosCliente;
		do {
			atributosCliente = f.read(EXPECTED_LENGTH);
			if (atributosCliente != null) {
				clientes.add(new Cliente(atributosCliente));
			}
		} while (atributosCliente != null);
	}

	/**
	 * Guarda la informaci�n asociada sobre el fichero de clientes estableciendo
	 * principalmente si tiene permiso para jugar o no (1 � 0).
	 * 
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public void guardar() throws ErrorAccesoException {
		WriteDataFile wDF = new WriteDataFile(FICHERO_CLIENTES);
		for (Cliente cliente : clientes)
			wDF.write(new String[] { cliente.getId(), cliente.getNombreCompleto(),
					String.valueOf(cliente.puedeJugar() ? 1 : 0) });

		wDF.cerrar();
	}

	/**
	 * Genera el fichero de entregas recorriendo la lista de regalos del carrito
	 * escribiendo sobre �l el id del usuario actual, el regalo a registrar junto
	 * con su c�digo, as� como su fecha y las observaciones en caso de tratarse de
	 * un viaje
	 * 
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public void generarEntregas() throws ErrorAccesoException {
		WriteDataFile wDF = new WriteDataFile(FICHERO_ENTREGAS, true); // Flag true que permite adjuntar informaci�n al
																		// archivo
		for (Regalo regaloARegistrar : carritoRegalos) {
			List<String> formatoRegaloARegistrar = new ArrayList<>();
			formatoRegaloARegistrar.add(usuarioActual.getId());
			formatoRegaloARegistrar.add(regaloARegistrar.getCodigo());

			if (regaloARegistrar.getCategoriaRegalo() == Categoria.VTRIPS) {
				formatoRegaloARegistrar.add(regaloARegistrar.getFecha());

				String observacionesRegalo = regaloARegistrar.getObservaciones();
				if (observacionesRegalo != null && !observacionesRegalo.trim().isEmpty())
					formatoRegaloARegistrar.add(observacionesRegalo);
			}

			wDF.writeEntregas(formatoRegaloARegistrar.toArray(new String[0]));
		}

		wDF.cerrar();
	}

	/**
	 * Valida la participaci�n del cliente que se recibe como par�metro. Para ello
	 * se comprueba si el cliente se encuentra entre la lista de clientes y si puede
	 * jugar (en caso de no poder jugar se recibe un 0 y un 1 en caso afirmativo).
	 * En caso de no encontrarse directamente se retorna un -1 como resultado.
	 * 
	 * @param idCliente El ID del cliente a validar
	 * @return -1 no se encuentra el cliente; 0 el cliente no puede jugar; 1 el
	 *         cliente puede jugar
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public int validarParticipacion(String idCliente) throws ErrorAccesoException {
		for (Cliente c : clientes) {
			if (c.getId().equals(idCliente)) {
				if (!c.puedeJugar())
					return 0;
				iniciarParticipacion(c);
				return 1;
			}
		}
		return -1;
	}

	/**
	 * Comienza la participaci�n del cliente recibido como par�metro impidi�ndole
	 * jugar de nuevo y asignando al cliente como usuario actual de la aplicaci�n.
	 * Se graban los cambios en el fichero de clientes y se comienza con un carrito
	 * de regalos vac�o.
	 * 
	 * @param cliente El cliente que comenzar� su participaci�n
	 * @throws ErrorAccesoException Excepci�n personalizada a lanzar en caso de
	 *                              fallo en las operaciones de IO
	 */
	public void iniciarParticipacion(Cliente cliente) throws ErrorAccesoException {
		cliente.setPuedeJugar(false);
		usuarioActual = cliente;
		guardar();
		carritoRegalos = new ArrayList<Regalo>();
	}

	/**
	 * A�ade el regalo recibido como par�metro al carrito de regalos. Adem�s, se
	 * decrementa los puntos del usuario en funci�n de los puntos que tenga el
	 * art�culo como coste. Se notificar� a los observadores cuando se realice esta
	 * operaci�n.
	 * 
	 * @param regalo El regalo a a�adir al carrito
	 */
	public void addToCarrito(Regalo regalo) {
		if (puedeAdquirirRegalo(regalo)) {
			carritoRegalos.add(regalo);
			usuarioActual.decrementarPuntosUsuario(regalo.getPuntos());
			notificarObservadores();
		}
	}

	/**
	 * Determina si puede adquirir el regalo en cuesti�n dependiendo del coste de
	 * puntos del regalo y la puntuaci�n que tenga el usuario actual.
	 * 
	 * @param regalo El regalo a adquirir
	 * @return Si puede adquirir el regalo o no
	 */
	public boolean puedeAdquirirRegalo(Regalo regalo) {
		return usuarioActual.getPuntos() >= regalo.getPuntos();
	}

	/**
	 * Determina si puede eliminar una unidad del regalo en cuesti�n dependiendo del
	 * n�mero de unidades de �ste en el carrito.
	 * 
	 * @param regalo El regalo a eliminar
	 * @return Si puede eliminar una unidad del regalo o no
	 */
	public boolean puedeEliminarRegalo(Regalo regalo) {
		return getUnidadesRegalo(regalo) > 0;
	}

	/**
	 * Elimina una unidad del carrito del regalo recibido como par�metro y revierte
	 * los puntos del usuario que hubiese gastado previamente en el art�culo en
	 * cuesti�n. Finlmente se notificar� a los observadores.
	 * 
	 * @param regalo El regalo a eliminar del carrito
	 */
	public void removeFromCarrito(Regalo regalo) {
		if (puedeEliminarRegalo(regalo))
			carritoRegalos.remove(regalo);

		usuarioActual.decrementarPuntosUsuario(-regalo.getPuntos());
		notificarObservadores();
	}

	/**
	 * Devuelve el n�mero de unidades del regalo recibido como par�metro. Para ello
	 * recorre el carrito de regalos y emplea un contador que incrementa en una
	 * unidad cada vez que encuentra una coincidencia del c�digo del regalo.
	 * 
	 * @return Las unidades del regalo en cuesti�n
	 */
	public int getUnidadesRegalo(Regalo regalo) {
		int uds = 0;
		for (Regalo r : carritoRegalos) {
			if (r.getCodigo().equals(regalo.getCodigo()))
				uds += 1;
		}
		return uds;
	}

	/**
	 * Getter de la lista de clientes.
	 * 
	 * Este m�todo devuelve el valor que tiene la lista de clientes para el atributo
	 * 'clientes'.
	 * 
	 * @return La lista de clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * Getter del usuario actual de la aplicaci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del usuario actual de la
	 * aplicaci�n para el atributo 'usuarioActual'.
	 * 
	 * @return El usuario actual de la aplicaci�n (Cliente)
	 */
	public Cliente getUsuarioActual() {
		return usuarioActual;
	}

	/**
	 * Getter de la lista del carrito de regalos.
	 * 
	 * Este m�todo devuelve el valor que tiene la lista del carrito de regalos para
	 * el atributo 'carritoRegalos'.
	 * 
	 * @return La lista del carrito de regalos
	 */
	public List<Regalo> getCarritoRegalos() {
		return carritoRegalos;
	}

}
