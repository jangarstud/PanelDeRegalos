package igu.paneles;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import igu.VentanaPrincipal;
import igu.internacionalizacion.Mensajes;
import igu.internacionalizacion.filtros.I18nFiltroAll;
import igu.internacionalizacion.filtros.I18nFiltroCategoria;
import igu.internacionalizacion.filtros.I18nFiltroPuntos;
import igu.paneles.subpaneles.MiniaturaArticulo;
import logica.Aplicacion;
import logica.Clientes;
import logica.Regalo;
import logica.Regalo.Categoria;
import logica.util.ErrorAccesoException;
import observer.Observer;
import java.awt.SystemColor;

/**
 * CatalogoArticulosPanel va a representar la clase de la interfaz gráfica sobre
 * la que se ubicarán todos los elementos correspondientes al panel del catálogo
 * de regalos. Se trata del tercer panel con el que el usuario interactuará. Se
 * mostrará la lista de artículos que el usuario podrá añadir o eliminar en
 * función de la puntuación que haya obtenido previamente en su participación
 * del panel de premios.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class CatalogoArticulosPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnNorte;
	private JPanel pnCentro;
	private JPanel pnFiltroyCesta;
	private JPanel pnSur;
	private JButton btConfirmar;
	private JComboBox<Predicate<Regalo>> cBoxCategoria;
	private JLabel lbPuntos;
	private JTextField txPuntos;
	private JLabel lbCategoria;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component rigidArea;
	private JPanel pnFiltro;
	private JPanel pnCesta;
	private JButton btCarrito;
	private JLabel lbFiltro;
	private JComboBox<Predicate<Regalo>> cBoxFiltro;

	private VentanaPrincipal vP;
	private Mensajes mensajes;
	private Aplicacion aplicacion;

	private JScrollPane scrollPaneCatalogo;
	private JPanel pnCatalogo;

	private DefaultComboBoxModel<Predicate<Regalo>> modelCategoria;
	private DefaultComboBoxModel<Predicate<Regalo>> modelFiltro;

	private ProcesaFiltro pF;

	/**
	 * Constructor de la clase del panel del catálogo de artículos que incluirá una
	 * referencia a la ventana principal sobre la que se está mostrando éste, así
	 * como el objeto Mensajes que se estará usando para internacionalizar la
	 * aplicación. Se registrará la propia clase como observador del objeto de
	 * Mensajes así como del objeto de la lógica Clientes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usará en la clase
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public CatalogoArticulosPanel(VentanaPrincipal vP, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);
		this.vP = vP;
		this.mensajes = mensajes;
		this.aplicacion = vP.getAplicacion();
		pF = new ProcesaFiltro();

		setLayout(new BorderLayout(0, 0));
		add(getPnNorte(), BorderLayout.NORTH);
		add(getPnCentro(), BorderLayout.CENTER);
		add(getPnSur(), BorderLayout.SOUTH);

		this.mensajes.registrarObservador(this);
		this.vP.getAplicacion().getClientes().registrarObservador(this);
	}

	/**
	 * Actualiza la clase con los cambios correspondientes en función de si el
	 * emisor que dispara la acción de actualizar es el objeto 'Observable' de
	 * mensajes o el de clientes.
	 * 
	 * 
	 * En caso de ser el objeto de mensajes se actualizará y se posibilitará
	 * "relocalizar" los distintos elementos gráficos (más concretamente, su texto);
	 * y en caso de ser los clientes, se actualizarán los campos de texto con la
	 * puntuación del usuario actual al igual que se habilitará la opción de
	 * 'Confirmar' en caso de que el carrito no esté vacío.
	 */
	@Override
	public void update(Object emisor) {
		if (emisor == mensajes) {
			lbPuntos.setText(mensajes.getRecursosMensajes().getString("points"));
			lbCategoria.setText(mensajes.getRecursosMensajes().getString("catalogue.categoryTitle"));
			lbFiltro.setText(mensajes.getRecursosMensajes().getString("catalogue.filterBy"));
			btConfirmar.setText(mensajes.getRecursosMensajes().getString("confirm"));
			btConfirmar.setMnemonic(btConfirmar.getText().charAt(0));
			asociarCategorias();
		} else if (emisor == vP.getAplicacion().getClientes()) {
			getTxPuntos().setText(String.valueOf(vP.getAplicacion().getClientes().getUsuarioActual().getPuntos()));

			getBtConfirmar().setEnabled(!aplicacion.getClientes().getCarritoRegalos().isEmpty());
		}

	}

	/**
	 * Inicializa la clase del panel del catálogo de regalos realizando acciones
	 * como generar el catálogo; indicar la puntuación del cliente; y habilitar o
	 * deshabilitar el botón de 'Confirmar' en función de si el carrito está o no
	 * vacío.
	 */
	public void inicializar() {
		generarCatalogoRegalos(aplicacion.getCatalogo().getPremiosCatalogo());
		getTxPuntos().setText(String.valueOf(vP.getAplicacion().getClientes().getUsuarioActual().getPuntos()));
		getBtConfirmar().setEnabled(!aplicacion.getClientes().getCarritoRegalos().isEmpty());
	}

	/**
	 * Genera el catálogo de regalos en función de los que se hayan recibido en una
	 * lista eliminando los que estuviesen previamente y añadiendo los nuevos
	 * paneles al panel principal del catálogo. Cada regalo será un JPanel distinto
	 * de la clase MiniaturaArticulo.
	 * 
	 * @param listaRegalos
	 */
	private void generarCatalogoRegalos(List<Regalo> listaRegalos) {
		getPnCatalogo().removeAll();
		for (Regalo regalo : listaRegalos) {
			MiniaturaArticulo miniaturaRegalo = new MiniaturaArticulo(this.vP, regalo, mensajes);
			getPnCatalogo().add(miniaturaRegalo);
		}

		validate();
		repaint();
	}

	/**
	 * Getter del panel norte.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel norte para el
	 * atributo 'pnNorte'.
	 * 
	 * @return El panel norte
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBackground(SystemColor.activeCaption);
			pnNorte.setLayout(new BoxLayout(pnNorte, BoxLayout.X_AXIS));
			pnNorte.add(getHorizontalGlue());
			pnNorte.add(getLbCategoria());
			pnNorte.add(getCBoxCategoria());
			pnNorte.add(getRigidArea());
			pnNorte.add(getLbPuntos());
			pnNorte.add(getTxPuntos());
			pnNorte.add(getHorizontalGlue_1());
		}
		return pnNorte;
	}

	/**
	 * Getter del panel central.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel central para el
	 * atributo 'pnCentro'.
	 * 
	 * @return El panel central
	 */
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(SystemColor.activeCaption);
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getPnFiltroyCesta(), BorderLayout.NORTH);
			pnCentro.add(getScrollPaneCatalogo(), BorderLayout.CENTER);
		}
		return pnCentro;
	}

	/**
	 * Getter del panel del filtro y la cesta.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del filtro y la
	 * cesta para el atributo 'pnFiltroyCesta'.
	 * 
	 * @return El panel del filtro y la cesta
	 */
	private JPanel getPnFiltroyCesta() {
		if (pnFiltroyCesta == null) {
			pnFiltroyCesta = new JPanel();
			pnFiltroyCesta.setBackground(SystemColor.activeCaption);
			pnFiltroyCesta.setLayout(new BorderLayout(0, 0));
			pnFiltroyCesta.add(getPnFiltro(), BorderLayout.WEST);
			pnFiltroyCesta.add(getPnCesta(), BorderLayout.EAST);
		}
		return pnFiltroyCesta;
	}

	/**
	 * Getter del panel sur.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel sur para el
	 * atributo 'pnSur'.
	 * 
	 * @return El panel sur
	 */
	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBackground(SystemColor.activeCaption);
			FlowLayout flowLayout = (FlowLayout) pnSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnSur.add(getBtConfirmar());
		}
		return pnSur;
	}

	/**
	 * Getter del botón de confirmar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón de confirmar para
	 * el atributo 'btConfirmar'. Como actionPerformed éste se encargará de
	 * comprobar la confirmación en función del contenido del carrito.
	 * 
	 * @return El botón de confirmar
	 */
	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton(mensajes.getRecursosMensajes().getString("confirm"));
			btConfirmar.setBorderPainted(false);
			btConfirmar.setBackground(SystemColor.controlHighlight);
			btConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarConfirmacionCarrito();
				}
			});
			btConfirmar.setMnemonic(btConfirmar.getText().charAt(0));
			btConfirmar.setFont(new Font("Verdana", Font.PLAIN, 12));
		}
		return btConfirmar;
	}

	/**
	 * Comprueba la fase de confirmación sobre los regalos añadidos al carrito por
	 * parte del usuario en caso de que aún existan puntos sin gastar o completa la
	 * participación si ya se han gastado todos los puntos.
	 */
	private void comprobarConfirmacionCarrito() {
		List<Regalo> carritoRegalos = aplicacion.getClientes().getCarritoRegalos();
		int puntosRestantes = aplicacion.getClientes().getUsuarioActual().getPuntos();

		if (!carritoRegalos.isEmpty() && puntosRestantes != 0) {
			dialogoOpciones(puntosRestantes);
		} else {
			completarParticipacion(puntosRestantes);
		}
	}

	/**
	 * Muestra un diálogo cuyo mensaje es obtenido mediante el texto que se carga
	 * del fichero de recursos con la etiqueta con identificador
	 * "catalogue.pointsLeft", del mismo modo que se utiliza la etiqueta
	 * "catalogue.pointsLeftTitle" para el título del diálogo, y posteriormente se
	 * completa la participación.
	 * 
	 * @param puntosSinGastar Los puntos restantes del usuario
	 */
	private void dialogoOpciones(int puntosSinGastar) {
		String mensajePuntosRestantes = String.format(mensajes.getRecursosMensajes().getString("catalogue.pointsLeft"),
				puntosSinGastar);

		if (JOptionPane.showOptionDialog(vP, mensajePuntosRestantes,
				mensajes.getRecursosMensajes().getString("catalogue.pointsLeftTitle"), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) {

			completarParticipacion(puntosSinGastar);
		}
	}

	/**
	 * Completa la participación del usuario mostrando al usuario el mensaje
	 * informativo mediante el texto que se carga del fichero de recursos con la
	 * etiqueta con identificador "catalogue.confirmation" y el título
	 * "catalogue.confirmationTitle", y trata de generar la entrega a través de la
	 * lógica.
	 * 
	 * Finalmente retorna a la primera pantalla del panel de validación, quedando
	 * preparada para el siguiente usuario.
	 * 
	 * @param puntosSinGastar Los puntos restantes del usuario
	 */
	private void completarParticipacion(int puntosSinGastar) {
		Clientes clientes = aplicacion.getClientes();

		JOptionPane.showMessageDialog(null,
				String.format(mensajes.getRecursosMensajes().getString("catalogue.confirmation"), puntosSinGastar),
				mensajes.getRecursosMensajes().getString("catalogue.confirmationTitle"),
				JOptionPane.INFORMATION_MESSAGE);

		try {
			clientes.generarEntregas();
		} catch (ErrorAccesoException e) {
			JOptionPane.showMessageDialog(null, mensajes.getRecursosMensajes().getString("validation.exceptionError"),
					mensajes.getRecursosMensajes().getString("validation.exceptionTitle"),
					JOptionPane.INFORMATION_MESSAGE);

			e.printStackTrace();
		}

		vP.mostrarPanelValidacion();

	}

	/**
	 * Getter del combobox parametrizado como predicado de regalos para la
	 * categoría.
	 * 
	 * Este método devuelve el valor que tiene el objeto del combobox parametrizado
	 * como predicado de regalos para la categoría para el atributo 'cBoxCategoria'.
	 * 
	 * @return El combobox parametrizado como predicado de regalos para la categoría
	 */
	private JComboBox<Predicate<Regalo>> getCBoxCategoria() {
		if (cBoxCategoria == null) {
			cBoxCategoria = new JComboBox<Predicate<Regalo>>();
			modelCategoria = new DefaultComboBoxModel<Predicate<Regalo>>();
			asociarCategorias();
			cBoxCategoria.setModel(modelCategoria);
			cBoxCategoria.addActionListener(pF);
			cBoxCategoria.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		}
		return cBoxCategoria;
	}

	/**
	 * Asocia todas las categorías existentes del enumerado 'Categoria' para cada
	 * objeto Regalo empleando las clase intermediaria I18nFiltroCategoria para
	 * permitir internacionalizar los textos asociados al atributo 'mensajes'.
	 */
	private void asociarCategorias() {
		modelCategoria.removeAllElements();

		modelCategoria.addElement(new I18nFiltroCategoria(Categoria.AFOOD, mensajes));
		modelCategoria.addElement(new I18nFiltroCategoria(Categoria.DSPORTS, mensajes));
		modelCategoria.addElement(new I18nFiltroCategoria(Categoria.EELECTRONICS, mensajes));
		modelCategoria.addElement(new I18nFiltroCategoria(Categoria.JTOYS, mensajes));
		modelCategoria.addElement(new I18nFiltroCategoria(Categoria.VTRIPS, mensajes));
	}

	/**
	 * Getter del label de la puntuación.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de la puntuación
	 * para el atributo 'lbPuntos'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "points".
	 * 
	 * @return El label de la puntuación
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
	 * Este método devuelve el valor que tiene el objeto del campo de texto de
	 * puntos para el atributo 'txPuntos'.
	 * 
	 * @return El campo de texto para los puntos
	 */
	private JTextField getTxPuntos() {
		if (txPuntos == null) {
			txPuntos = new JTextField();
			txPuntos.setEditable(false);
			txPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setMaximumSize(new Dimension(250, 2147483647));
			txPuntos.setColumns(10);
		}
		return txPuntos;
	}

	/**
	 * Getter del label de la categoría.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de la categoría
	 * para el atributo 'lbCategoria'. Como texto se carga del fichero de recursos
	 * la etiqueta con identificador "catalogue.categoryTitle".
	 * 
	 * @return El label de la categoría
	 */
	private JLabel getLbCategoria() {
		if (lbCategoria == null) {
			lbCategoria = new JLabel(mensajes.getRecursosMensajes().getString("catalogue.categoryTitle"));
			lbCategoria.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		}
		return lbCategoria;
	}

	/**
	 * Getter del componente HorizontalGlue del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'horizontalGlue'.
	 * 
	 * @return El componente HorizontalGlue del BoxLayout
	 */
	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
		}
		return horizontalGlue;
	}

	/**
	 * Getter del componente HorizontalGlue del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'horizontalGlue_1'.
	 * 
	 * @return El componente HorizontalGlue del BoxLayout
	 */
	private Component getHorizontalGlue_1() {
		if (horizontalGlue_1 == null) {
			horizontalGlue_1 = Box.createHorizontalGlue();
		}
		return horizontalGlue_1;
	}

	/**
	 * Getter del componente RigidArea del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'rigidArea'.
	 * 
	 * @return El componente RigidArea del BoxLayout
	 */
	private Component getRigidArea() {
		if (rigidArea == null) {
			rigidArea = Box.createRigidArea(new Dimension(20, 20));
			rigidArea.setPreferredSize(new Dimension(60, 20));
		}
		return rigidArea;
	}

	/**
	 * Getter del panel específico del filtro.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel específico del
	 * filtro para el atributo 'pnFiltro'.
	 * 
	 * @return El panel específico del filtro
	 */
	private JPanel getPnFiltro() {
		if (pnFiltro == null) {
			pnFiltro = new JPanel();
			pnFiltro.setBackground(SystemColor.activeCaption);
			FlowLayout flowLayout = (FlowLayout) pnFiltro.getLayout();
			flowLayout.setHgap(10);
			pnFiltro.add(getLbFiltro());
			pnFiltro.add(getCBoxFiltro());
		}
		return pnFiltro;
	}

	/**
	 * Getter del panel específico de la cesta.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel específico de la
	 * cesta para el atributo 'pnCesta'.
	 * 
	 * @return El panel específico de la cesta
	 */
	private JPanel getPnCesta() {
		if (pnCesta == null) {
			pnCesta = new JPanel();
			pnCesta.setBackground(SystemColor.activeCaption);
			pnCesta.setLayout(new BorderLayout(20, 0));
			pnCesta.add(getBtCarrito(), BorderLayout.CENTER);
		}
		return pnCesta;
	}

	/**
	 * Getter del botón del carrito.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón del carrito para
	 * el atributo 'btContinuar'. Como actionPerformed éste se encargará de mostrar
	 * el panel del carrito.
	 * 
	 * @return El botón del carrito
	 */
	private JButton getBtCarrito() {
		if (btCarrito == null) {
			btCarrito = new JButton("");
			btCarrito.setBackground(SystemColor.inactiveCaption);
			btCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.mostrarPanelCarrito();
				}
			});
			btCarrito.setIcon(new ImageIcon(CatalogoArticulosPanel.class.getResource("/img/shoppingCart.png")));
			btCarrito.setBorderPainted(false);
		}
		return btCarrito;
	}

	/**
	 * Getter del label de filtro.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de filtro para el
	 * atributo 'lbFiltro'. Como texto se carga del fichero de recursos la etiqueta
	 * con identificador "catalogue.filterBy".
	 * 
	 * @return El label de filtro
	 */
	private JLabel getLbFiltro() {
		if (lbFiltro == null) {
			lbFiltro = new JLabel(mensajes.getRecursosMensajes().getString("catalogue.filterBy"));
			lbFiltro.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		}
		return lbFiltro;
	}

	/**
	 * Getter del combobox parametrizado como predicado de regalos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del combobox parametrizado
	 * como predicado de regalos para el atributo 'cBoxCategoria'. Como
	 * actionListener tiene asociado el atributo receptor del evento 'pF'.
	 * 
	 * @return El combobox parametrizado como predicado de regalos
	 */
	private JComboBox<Predicate<Regalo>> getCBoxFiltro() {
		if (cBoxFiltro == null) {
			cBoxFiltro = new JComboBox<Predicate<Regalo>>();
			cBoxFiltro.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
			cBoxFiltro.addActionListener(pF);
			modelFiltro = new DefaultComboBoxModel<>();
		}
		return cBoxFiltro;
	}

	/**
	 * Asocia el filtrado por puntos al modelo del atributo 'modelFiltro' en función
	 * de los puntos del usuario para posibilitar la selección del catálogo de
	 * regalos en el comboBox desde una puntuación similar a la del usuario o menor.
	 */
	public void asociarFiltroPorPuntos() {
		modelFiltro.removeAllElements();

		modelFiltro.addElement(new I18nFiltroAll(mensajes));
		int i = aplicacion.getClientes().getUsuarioActual().getPuntos();
		while (i > 0) {
			modelFiltro.addElement(new I18nFiltroPuntos(i, mensajes));
			i -= 100;
		}

		cBoxFiltro.setModel(modelFiltro);
	}

	/**
	 * Getter del panel de scroll del catálogo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de scroll del
	 * catálogo para el atributo 'scrollPaneCatalogo'.
	 * 
	 * @return El panel de scroll del catálogo
	 */
	private JScrollPane getScrollPaneCatalogo() {
		if (scrollPaneCatalogo == null) {
			scrollPaneCatalogo = new JScrollPane();
			scrollPaneCatalogo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneCatalogo.setViewportView(getPnCatalogo());
		}
		return scrollPaneCatalogo;
	}

	/**
	 * Getter del panel del catálogo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del catálogo para
	 * el atributo 'pnCatalogo'.
	 * 
	 * @return El panel del catálogo
	 */
	private JPanel getPnCatalogo() {
		if (pnCatalogo == null) {
			pnCatalogo = new JPanel();
			pnCatalogo.setBackground(SystemColor.activeCaption);
			pnCatalogo.setLayout(new GridLayout(0, 2, 25, 15));
			pnCatalogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 75));
		}
		return pnCatalogo;
	}

	/**
	 * ProcesaFiltro es una clase interna de evento en la que se establece el
	 * comportamiento de los comboboxes dedicados al filtrado del catálogo de
	 * regalos en función del tipo de filtro escogido.
	 */
	class ProcesaFiltro implements ActionListener {
		/**
		 * Refresca el catálogo de regalos mostrado en función de la opción que se ha
		 * cambiado en alguno de los comboboxes dedicados al filtrado.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<Predicate<Regalo>> combo = (JComboBox<Predicate<Regalo>>) e.getSource();

			List<Regalo> regalosFiltrados = aplicacion.getCatalogo()
					.verFiltrados((Predicate<Regalo>) combo.getSelectedItem());

			generarCatalogoRegalos(regalosFiltrados);
		}

	}
}
