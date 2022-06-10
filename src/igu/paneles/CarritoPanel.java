package igu.paneles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import igu.VentanaPrincipal;
import igu.internacionalizacion.Mensajes;
import igu.paneles.subpaneles.PanelArticuloCarrito;
import logica.Aplicacion;
import logica.Regalo;
import observer.Observer;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

/**
 * CarritoPanel representar� la clase de la interfaz gr�fica sobre la que se
 * ubicar�n todos los elementos correspondientes al panel del carrito de
 * regalos. En �l se mostrar� la lista de art�culos que el usuario ha a�adido
 * previamente desde CatalogoArticulosPanel y se podr�n editar las unidades.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class CarritoPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnNorte;
	private JPanel pnCentro;
	private JLabel lbTitulo;
	private JPanel pnBoton;
	private JButton btBack;
	private JPanel pnPuntos;
	private JLabel lbPuntos;
	private JTextField txPuntos;

	private VentanaPrincipal vP;
	private Mensajes mensajes;
	private Aplicacion aplicacion;
	private JScrollPane scrollPaneCarrito;
	private JPanel pnArticulos;

	/**
	 * Constructor de la clase del panel del carrito de regalos que incluir� una
	 * referencia a la ventana principal sobre la que se est� mostrando �ste, as�
	 * como el objeto Mensajes que se estar� usando para internacionalizar la
	 * aplicaci�n. Se registrar� la propia clase como observador del objeto de
	 * Mensajes as� como del objeto de la l�gica Clientes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usar� en la clase
	 * @param mensajes El objeto de mensajes que se est� empleando para la
	 *                 internacionalizaci�n
	 */
	public CarritoPanel(VentanaPrincipal vP, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);
		this.vP = vP;
		this.mensajes = mensajes;
		this.aplicacion = vP.getAplicacion();

		setLayout(new BorderLayout(0, 0));
		add(getPnNorte(), BorderLayout.NORTH);
		add(getPnCentro(), BorderLayout.CENTER);

		this.mensajes.registrarObservador(this);
		this.vP.getAplicacion().getClientes().registrarObservador(this);
	}

	/**
	 * Actualiza la clase con los cambios correspondientes en funci�n de si el
	 * emisor que dispara la acci�n de actualizar es el objeto 'Observable' de
	 * mensajes o el de clientes.
	 * 
	 * 
	 * En caso de ser el objeto de mensajes se actualizar� y se posibilitar�
	 * "relocalizar" los distintos elementos gr�ficos (m�s concretamente, su texto y
	 * sus mnem�nicos); y en caso de ser los clientes, se actualizar� el campo de
	 * texto con la puntuaci�n del usuario actual.
	 */
	@Override
	public void update(Object emisor) {
		if (emisor == mensajes) {
			lbPuntos.setText(mensajes.getRecursosMensajes().getString("points"));
			lbTitulo.setText(mensajes.getRecursosMensajes().getString("shoppingCart.header"));
			btBack.setText(mensajes.getRecursosMensajes().getString("back"));
			btBack.setMnemonic(mensajes.getRecursosMensajes().getString("back").charAt(0));
		} else if (emisor == vP.getAplicacion().getClientes()) {
			getTxPuntos().setText(String.valueOf(vP.getAplicacion().getClientes().getUsuarioActual().getPuntos()));
		}
	}

	/**
	 * Inicializa la clase del panel del carrito de regalos realizando acciones como
	 * generar el carrito; e indicar la puntuaci�n del cliente.
	 */
	public void inicializar() {
		generarCarrito();
		getTxPuntos().setText(String.valueOf(vP.getAplicacion().getClientes().getUsuarioActual().getPuntos()));
	}

	/**
	 * Genera el carrito de regalos a partir de los regalos que posee el cliente
	 * almacenados en la l�gica y a�adi�ndolos al panel de art�culos creando un
	 * objeto PanelArticuloCarrito.
	 */
	public void generarCarrito() {
		List<Regalo> listaRegalos = aplicacion.getClientes().getCarritoRegalos();

		getPnArticulos().removeAll();
		for (Regalo regalo : listaRegalos) {
			PanelArticuloCarrito articuloCarrito = new PanelArticuloCarrito(this, regalo, this.mensajes);
			getPnArticulos().add(articuloCarrito);
		}

		revalidate();
		repaint();
	}

	/**
	 * Elimina un regalo determinado del carrito de regalos en funci�n del que se
	 * haya pasado como par�metro. Posteriormente, se vuelve a generar la vista del
	 * carrito.
	 * 
	 * @param regalo El regalo a eliminar del carrito
	 */
	public void eliminarArticuloCarrito(Regalo regalo) {
		vP.getAplicacion().getClientes().removeFromCarrito(regalo);
		generarCarrito();
	}

	/**
	 * Getter del panel norte.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel norte para el
	 * atributo 'pnNorte'.
	 * 
	 * @return El panel norte
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBackground(SystemColor.activeCaption);
			pnNorte.setLayout(new GridLayout(0, 2, 0, 0));
			pnNorte.add(getPnBoton());
			pnNorte.add(getPnPuntos());
		}
		return pnNorte;
	}

	/**
	 * Getter del panel central.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel central para el
	 * atributo 'pnCentro'.
	 * 
	 * @return El panel central
	 */
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(SystemColor.activeCaption);
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getLbTitulo(), BorderLayout.NORTH);
			pnCentro.add(getScrollPaneCarrito(), BorderLayout.CENTER);
		}
		return pnCentro;
	}

	/**
	 * Getter del label del t�tulo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del label del t�tulo para
	 * el atributo 'lbTitulo'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "shoppingCart.header".
	 * 
	 * @return El label del t�tulo
	 */
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel(mensajes.getRecursosMensajes().getString("shoppingCart.header"));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		}
		return lbTitulo;
	}

	/**
	 * Getter del panel espec�fico del bot�n atr�s.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel espec�fico del
	 * bot�n atr�s para el atributo 'pnBoton'.
	 * 
	 * @return El panel espec�fico del bot�n atr�s
	 */
	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBackground(SystemColor.activeCaption);
			pnBoton.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnBoton.add(getBtBack());
		}
		return pnBoton;
	}

	/**
	 * Getter del bot�n atr�s.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del bot�n atr�s para el
	 * atributo 'btBack'. Como actionPerformed �ste se encargar� de regresar y
	 * mostrar el panel del cat�logo.
	 * 
	 * @return El bot�n atr�s
	 */
	private JButton getBtBack() {
		if (btBack == null) {
			btBack = new JButton(mensajes.getRecursosMensajes().getString("back"));
			btBack.setBorderPainted(false);
			btBack.setBackground(SystemColor.controlShadow);
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.mostrarPanelCatalogo();
				}
			});
			btBack.setMnemonic(mensajes.getRecursosMensajes().getString("back").charAt(0));
			btBack.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		}
		return btBack;
	}

	/**
	 * Getter del panel de puntos.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel de puntos para el
	 * atributo 'pnPuntos'.
	 * 
	 * @return El panel de puntos
	 */
	private JPanel getPnPuntos() {
		if (pnPuntos == null) {
			pnPuntos = new JPanel();
			pnPuntos.setBackground(SystemColor.activeCaption);
			pnPuntos.add(getLbPuntos());
			pnPuntos.add(getTxPuntos());
		}
		return pnPuntos;
	}

	/**
	 * Getter del label de la puntuaci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del label de la puntuaci�n
	 * para el atributo 'lbPuntos'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "points".
	 * 
	 * @return El label de la puntuaci�n
	 */
	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel(mensajes.getRecursosMensajes().getString("points"));
			lbPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		}
		return lbPuntos;
	}

	/**
	 * Getter del campo de texto para los puntos.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del campo de texto de
	 * puntos para el atributo 'txPuntos'.
	 * 
	 * @return El campo de texto para los puntos
	 */
	private JTextField getTxPuntos() {
		if (txPuntos == null) {
			txPuntos = new JTextField();
			txPuntos.setEditable(false);
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			txPuntos.setColumns(5);
		}
		return txPuntos;
	}

	/**
	 * Getter del panel de scroll del carrito.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel de scroll del
	 * carrito para el atributo 'scrollPaneCarrito'.
	 * 
	 * @return El panel de scroll del carrito
	 */
	private JScrollPane getScrollPaneCarrito() {
		if (scrollPaneCarrito == null) {
			scrollPaneCarrito = new JScrollPane();
			scrollPaneCarrito.setBackground(new Color(112, 128, 144));
			scrollPaneCarrito.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneCarrito.setViewportView(getPnArticulos());
		}
		return scrollPaneCarrito;
	}

	/**
	 * Getter del panel de art�culos.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel de art�culos para
	 * el atributo 'pnArticulos'.
	 * 
	 * @return El panel de art�culos
	 */
	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBackground(SystemColor.activeCaption);
			pnArticulos.setLayout(new GridLayout(0, 1, 0, 25));
		}
		return pnArticulos;
	}
}
