package igu.paneles.subpaneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import igu.VentanaPrincipal;
import igu.escaladoimg.ImagenFactoria;
import igu.internacionalizacion.Mensajes;
import logica.Regalo;
import logica.Regalo.Categoria;
import observer.Observer;
import java.awt.SystemColor;

/**
 * MiniaturaArticulo será uno de los subpaneles de la interfaz gráfica sobre los
 * que se ubicarán todos los elementos correspondientes a la miniatura del
 * artículo de regalo. En él se mostrará información del artículo tal como los
 * puntos asociados, la imagen del artículo y un contador de unidades
 * configurable.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class MiniaturaArticulo extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnSpinner;
	private JButton btLess;
	private JButton btAdd;
	private JTextField txUnidades;

	private Regalo regalo;
	private boolean imagenInicializada = false;

	private JSeparator separator;
	private JSeparator separator_1;
	private JPanel pnInfoArticulo;
	private JPanel pnArticulo;
	private JPanel pnPuntos;
	private JLabel lbPuntos;
	private JButton btImagenArticulo;

	private VentanaPrincipal vP;
	private ProcesaSpinner pS;
	private Mensajes mensajes;

	/**
	 * Constructor de la clase del panel del carrito de regalos que incluirá una
	 * referencia a la ventana principal sobre la que se está mostrando éste, así
	 * como el regalo que se usará para generar la miniatura y el objeto Mensajes
	 * que se estará usando para internacionalizar la aplicación. Se registrará la
	 * propia clase como observador del objeto de la lógica Clientes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usará en la clase
	 * @param regalo   El regalo que se usará para generar la miniatura
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public MiniaturaArticulo(VentanaPrincipal vP, Regalo regalo, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);

		this.regalo = regalo;
		this.vP = vP;
		this.mensajes = mensajes;
		this.pS = new ProcesaSpinner();

		setLayout(new BorderLayout(0, 25));
		add(getPnSpinner(), BorderLayout.EAST);
		add(getPnInfoArticulo(), BorderLayout.CENTER);

		this.vP.getAplicacion().getClientes().registrarObservador(this);
		inicializar();
	}

	/**
	 * Actualiza la clase con los cambios correspondientes. En este caso actualizará
	 * el estado del spinner a la hora de adecuar el comportamiento con el contador
	 * de unidades.
	 */
	@Override
	public void update(Object emisor) {
		actualizarEstadoSpinner();
	}

	/**
	 * Inicializa la imagen dándole un tamaño determinado y asociándosela al botón
	 * de la imagen en caso de que previamente no haya sido inicializada.
	 */
	public void inicializarImagen() {
		if (!imagenInicializada) {
			ImageIcon imagenEscalada = ImagenFactoria.getImagen(regalo.getCodigo(), btImagenArticulo, false, 175, 175);
			btImagenArticulo.setIcon(imagenEscalada);
			btImagenArticulo.setDisabledIcon(imagenEscalada);
		}
		imagenInicializada = true;
	}

	/**
	 * Inicializa la imagen de la miniatura del artículo así como los puntos
	 * asociados y el spinner de unidades.
	 */
	public void inicializar() {
		inicializarImagen();
		getLbPuntos().setText(String.valueOf(regalo.getPuntos()) + " pts");
		actualizarEstadoSpinner();
	}

	/**
	 * Actualiza el estado del spinner de unidades cambiando en el campo de texto
	 * las unidades actuales del regalo escogido y realiza las comprobaciones
	 * oportunas para habilitar o deshabilitar las opciones de añadir y eliminar
	 * unidades.
	 */
	public void actualizarEstadoSpinner() {
		getTxUnidades().setText(String.valueOf(vP.getAplicacion().getClientes().getUnidadesRegalo(regalo)));
		getBtAdd().setEnabled(vP.getAplicacion().getClientes().puedeAdquirirRegalo(regalo));
		getBtLess().setEnabled(vP.getAplicacion().getClientes().puedeEliminarRegalo(regalo));
	}

	/**
	 * Alterna a modo de 'switch' la habilitación del botón de la imagen.
	 */
	public void alternarHabilitarImagen() {
		getBtImagenArticulo().setEnabled(!btImagenArticulo.isEnabled());
	}

	/**
	 * Getter del panel del spinner.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del spinner para
	 * el atributo 'pnSpinner'.
	 * 
	 * @return El panel del spinner
	 */
	private JPanel getPnSpinner() {
		if (pnSpinner == null) {
			pnSpinner = new JPanel();
			pnSpinner.setBackground(SystemColor.activeCaption);
			pnSpinner.setLayout(new GridLayout(0, 1, 0, 0));
			pnSpinner.add(getSeparator());
			pnSpinner.add(getBtAdd());
			pnSpinner.add(getTxUnidades());
			pnSpinner.add(getBtLess());
			pnSpinner.add(getSeparator_1());
		}
		return pnSpinner;
	}

	/**
	 * Getter del botón añadir.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón añadir para el
	 * atributo 'btAdd'. Como actionPerformed éste se encargará de realizar la
	 * acción determinada por el comportamiento de la clase evento ProcesaSpinner
	 * (atributo 'pS').
	 * 
	 * @return El botón añadir
	 */
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("");
			btAdd.setBackground(new Color(211, 211, 211));
			btAdd.setBorderPainted(false);
			ImageIcon imagenFlechaAddEscalada = ImagenFactoria.getImagen("upArrow", btAdd, false, 40, 40);
			btAdd.setIcon(imagenFlechaAddEscalada);
			btAdd.addActionListener(pS);
		}
		return btAdd;
	}

	/**
	 * Getter del botón eliminar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón eliminar para el
	 * atributo 'btLess'. Como actionPerformed éste se encargará de realizar la
	 * acción determinada por el comportamiento de la clase evento ProcesaSpinner
	 * (atributo 'pS').
	 * 
	 * @return El botón eliminar
	 */
	private JButton getBtLess() {
		if (btLess == null) {
			btLess = new JButton("");
			btLess.setBackground(new Color(211, 211, 211));
			btLess.setBorderPainted(false);
			ImageIcon imagenFlechaLessEscalada = ImagenFactoria.getImagen("downArrow", btLess, false, 20, 13);
			btLess.setIcon(imagenFlechaLessEscalada);
			btLess.addActionListener(pS);
		}
		return btLess;
	}

	/**
	 * Getter del campo de texto para las unidades.
	 * 
	 * Este método devuelve el valor que tiene el objeto del campo de texto de
	 * unidades para el atributo 'txUnidades'.
	 * 
	 * @return El campo de texto para las unidades
	 */
	private JTextField getTxUnidades() {
		if (txUnidades == null) {
			txUnidades = new JTextField();
			txUnidades.setBackground(new Color(230, 230, 250));
			txUnidades.setEditable(false);
			txUnidades.setHorizontalAlignment(SwingConstants.CENTER);
			txUnidades.setText("0");
			txUnidades.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			txUnidades.setColumns(4);
		}
		return txUnidades;
	}

	/**
	 * Getter del primer separador.
	 * 
	 * Este método devuelve el valor que tiene el objeto del primer separador para
	 * el atributo 'separator'.
	 * 
	 * @return El primer separador
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(new Color(176, 196, 222));
		}
		return separator;
	}

	/**
	 * Getter del segundo separador.
	 * 
	 * Este método devuelve el valor que tiene el objeto del segundo separador para
	 * el atributo 'separator_1'.
	 * 
	 * @return El segundo separador
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBackground(new Color(176, 196, 222));
		}
		return separator_1;
	}

	/**
	 * Getter del panel de la información del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de la información
	 * del artículo para el atributo 'pnInfoArticulo'.
	 * 
	 * @return El panel de la información del artículo
	 */
	private JPanel getPnInfoArticulo() {
		if (pnInfoArticulo == null) {
			pnInfoArticulo = new JPanel();
			pnInfoArticulo.setBackground(SystemColor.activeCaption);
			pnInfoArticulo.setLayout(new BorderLayout(0, 0));
			pnInfoArticulo.add(getPnArticulo(), BorderLayout.CENTER);
			pnInfoArticulo.add(getPnPuntos(), BorderLayout.SOUTH);
		}
		return pnInfoArticulo;
	}

	/**
	 * Getter del panel del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del artículo para
	 * el atributo 'pnArticulo'.
	 * 
	 * @return El panel del artículo
	 */
	private JPanel getPnArticulo() {
		if (pnArticulo == null) {
			pnArticulo = new JPanel();
			pnArticulo.setBackground(SystemColor.activeCaption);
			pnArticulo.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2), regalo.getDenominacion(),
					TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
			pnArticulo.setLayout(new BorderLayout(0, 0));
			pnArticulo.add(getBtImagenArticulo(), BorderLayout.CENTER);
		}
		return pnArticulo;
	}

	/**
	 * Getter del panel de puntos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de puntos para el
	 * atributo 'pnPuntos'.
	 * 
	 * @return El panel de puntos
	 */
	private JPanel getPnPuntos() {
		if (pnPuntos == null) {
			pnPuntos = new JPanel();
			pnPuntos.setBackground(SystemColor.activeCaption);
			pnPuntos.add(getLbPuntos());
		}
		return pnPuntos;
	}

	/**
	 * Getter del label de la puntuación del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de la puntuación
	 * del artículo para el atributo 'lbPuntos'.
	 * 
	 * @return El label de la puntuación del artículo
	 */
	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel("0 pts");
			lbPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		}
		return lbPuntos;
	}

	/**
	 * Getter del botón de la imagen del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón de la imagen del
	 * artículo para el atributo 'btImagenArticulo'. Como actionPerformed éste se
	 * encargará de mostrar el panel del artículo.
	 * 
	 * @return El botón de la imagen del artículo
	 */
	private JButton getBtImagenArticulo() {
		if (btImagenArticulo == null) {
			btImagenArticulo = new JButton("");
			btImagenArticulo.setBackground(Color.WHITE);
			btImagenArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.mostrarPanelArticulo(regalo, getReferenciaThis());
				}
			});
			btImagenArticulo.setBorderPainted(false);
		}
		return btImagenArticulo;
	}

	/**
	 * Getter de la referencia de la propia clase.
	 * 
	 * Este método devuelve el valor que tiene el objeto de referencia de la propia
	 * clase MiniaturaArticulo 'this'.
	 * 
	 * @return La referencia de la propia clase
	 */
	private MiniaturaArticulo getReferenciaThis() {
		return this;
	}

	/**
	 * ProcesaSpinner es una clase interna de evento en la que se establece el
	 * comportamiento del spinner dedicado al incremento o decremento del artículo
	 * de regalo sobre el que se tiene el foco.
	 */
	class ProcesaSpinner implements ActionListener {

		/**
		 * En caso de añadir el regalo y ser distinto de la categoría 'Viajes' se
		 * incrementan las unidades, de lo contrario se decrementan las unidades. Si se
		 * añadiese un regalo de la categoría 'Viaje' se abriría un diálogo específico
		 * de la clase DialogoReservaViaje para reservar el viaje.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton botonPulsado = (JButton) e.getSource();
			if (botonPulsado.equals(btAdd)) {
				if (regalo.getCategoriaRegalo() != Categoria.VTRIPS) {
					addRegalo(regalo);
				} else {
					DialogoReservaViaje dialogo = new DialogoReservaViaje(getReferenciaThis(), regalo, mensajes);
					dialogo.setVisible(true);
				}

			} else {
				vP.getAplicacion().getClientes().removeFromCarrito(regalo);
			}
		}

	}

	/**
	 * Añade el regalo pasado como parámetro al carrito de regalos.
	 * 
	 * @param regalo El regalo a añadir
	 */
	public void addRegalo(Regalo regalo) {
		vP.getAplicacion().getClientes().addToCarrito(regalo);
	}

}
