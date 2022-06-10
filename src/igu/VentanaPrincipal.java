package igu;

import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import igu.internacionalizacion.Mensajes;
import igu.paneles.ArticuloPanel;
import igu.paneles.CarritoPanel;
import igu.paneles.CatalogoArticulosPanel;
import igu.paneles.PremiosPanel;
import igu.paneles.ValidacionPanel;
import igu.paneles.subpaneles.MiniaturaArticulo;
import logica.Aplicacion;
import logica.Regalo;

/**
 * VentanaPrincipal va a representar la clase de la interfaz gráfica sobre la
 * que ocurrirán todas las interacciones entre el usuario y la aplicación.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String VALIDACION_CL = "validacion";
	private static final String PREMIOS_CL = "premios";
	private static final String CATALOGO_CL = "catalogoArticulos";
	private static final String ARTICULO_CL = "articulo";
	private static final String CARRITO_CL = "carrito";

	private JPanel pnPrincipal;

	private ValidacionPanel pnValidacion;
	private PremiosPanel pnPremios;
	private CatalogoArticulosPanel pnCatalogo;
	private ArticuloPanel pnArticulo;
	private CarritoPanel pnCarrito;

	private Aplicacion app;
	private Mensajes mensajes;

	/**
	 * Constructor de la clase de la ventana principal.
	 * 
	 * En este constructor se inicializa el objeto VentanaPrincipal, así como los
	 * elementos de la lógica necesarios para representar visualmente la aplicación
	 * del Panel de Regalos. Los objetos involucrados en este caso serían únicamente los
	 * atributos app y mensajes, que serán instanciados en la clase Main. Posteriormente,
	 * se establece el título de la ventana así como otras propiedades básicas de la
	 * ventana. Se añadirán los correspondientes paneles, así como su layout (CardLayout),
	 * que contendrán el resto de elementos de la IGU.
	 * 
	 * @param app Objeto principal de la lógica que se instancia en la clase Main
	 * @param mensajes Objeto principal dedicado a la internacionalización de la aplicación
	 */
	public VentanaPrincipal(Aplicacion app, Mensajes mensajes) {
		this.app = app;
		this.mensajes = mensajes;

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/A04.png")));
		setResizable(false);
		setTitle("Panel de Regalos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 850);
		pnPrincipal = new JPanel();
		pnPrincipal.setBackground(SystemColor.activeCaption);
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		pnPrincipal.setLayout(new CardLayout(0, 0));
		pnPrincipal.add(getPnValidacion(), VALIDACION_CL);
		pnPrincipal.add(getPnPremios(), PREMIOS_CL);
		pnPrincipal.add(getPnCatalogoArticulos(), CATALOGO_CL);
		pnPrincipal.add(getPnArticulo(), ARTICULO_CL);
		pnPrincipal.add(getPnCarrito(), CARRITO_CL);

		setLocationRelativeTo(null);
		
		cargaAyuda();
	}
	
	/**
	 * Carga el sistema de ayuda contenido en el directorio 'help', en donde
	 * se establece el fichero Helpset junto con la tabla de contenidos (TOC),
	 * el índice y la indexación de la búsqueda. También se establece la ayuda
	 * sensible al contexto en función del panel en el que se encuentre.
	 */
	private void cargaAyuda() {

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();

		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		
		hb.enableHelp(getPnValidacion(), "validacion", hs);
		hb.enableHelp(getPnPremios(), "premios", hs);
		hb.enableHelp(getPnCatalogoArticulos(), "catalogo", hs);
		hb.enableHelp(getPnCarrito(), "carrito", hs);
		hb.enableHelp(getPnArticulo(), "articulo", hs);
	}

	/**
	 * Getter del panel de validación.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de validación para
	 * el atributo 'pnValidacion'.
	 * 
	 * @return El panel de validación de clientes
	 */
	private JPanel getPnValidacion() {
		if (pnValidacion == null) {
			pnValidacion = new ValidacionPanel(this, mensajes);
		}
		return pnValidacion;
	}

	/**
	 * Getter del panel de premios.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de premios para
	 * el atributo 'pnPremios'.
	 * 
	 * @return El panel de premios.
	 */
	private JPanel getPnPremios() {
		if (pnPremios == null) {
			pnPremios = new PremiosPanel(this, mensajes);
		}
		return pnPremios;
	}

	/**
	 * Getter del panel del catálogo de artículos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del catálogo de 
	 * artículos para el atributo 'pnCatalogo'.
	 * 
	 * @return El panel del catálogo de artículos.
	 */
	private JPanel getPnCatalogoArticulos() {
		if (pnCatalogo == null) {
			pnCatalogo = new CatalogoArticulosPanel(this, mensajes);
		}
		return pnCatalogo;
	}

	/**
	 * Getter del panel del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del artículo
	 * para el atributo 'pnArticulo'.
	 * 
	 * @return El panel del artículo.
	 */
	private JPanel getPnArticulo() {
		if (pnArticulo == null) {
			pnArticulo = new ArticuloPanel(this, mensajes);
		}
		return pnArticulo;
	}

	/**
	 * Getter del panel del carrito.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del carrito
	 * para el atributo 'pnCarrito'.
	 * 
	 * @return El panel del carrito.
	 */
	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new CarritoPanel(this, mensajes);
		}
		return pnCarrito;
	}

	/**
	 * Getter de la aplicación asociada al atributo 'app'.
	 * 
	 * Este método devuelve el valor que tiene el objeto de tipo Aplicacion
	 * para el atributo 'app'.
	 * 
	 * @return La referencia a la aplicación.
	 */
	public Aplicacion getAplicacion() {
		return this.app;
	}

	/**
	 * Muestra el panel de premios inicializándolo previamente y cambiando el título
	 * de la ventana. 
	 * 
	 */
	public void mostrarPanelPremios() {
		pnPremios.inicializar();
		((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, PREMIOS_CL);
		setTitle(mensajes.getRecursosMensajes().getString("prizes.windowTitle"));
	}

	/**
	 * Muestra el panel del catálogo de regalos inicializándolo previamente y cambiando
	 * el título de la ventana. 
	 * 
	 */
	public void mostrarPanelCatalogo() {
		pnCatalogo.inicializar();
		((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, CATALOGO_CL);
		setTitle(mensajes.getRecursosMensajes().getString("catalogue.windowTitle"));
	}

	/**
	 * Aplica el filtro a los puntos actuales del nuevo usuario en el momento de transición 
	 * del panel de premios al panel del catálogo.
	 * 
	 */
	public void transicionDesdePanelPremios() {
		pnCatalogo.asociarFiltroPorPuntos();
	}

	/**
	 * Muestra el panel del artículo inicializándolo en función del regalo y el objeto de
	 * la miniatura que se pasa como parámetro. Además se cambia el título de la ventana.
	 * 
	 * @param regalo El regalo del que se desea actualizar la información del panel
	 * @param mA La miniatura que se desea actualizar
	 */
	public void mostrarPanelArticulo(Regalo regalo, MiniaturaArticulo mA) {
		pnArticulo.inicializar(regalo, mA);
		((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, ARTICULO_CL);
		setTitle(mensajes.getRecursosMensajes().getString("article.windowTitle"));
	}

	/**
	 * Muestra el panel del carrito inicializándolo previamente.
	 */
	public void mostrarPanelCarrito() {
		pnCarrito.inicializar();
		((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, CARRITO_CL);
	}

	/**
	 * Muestra el panel de validación cambiando el título de la ventana.
	 */
	public void mostrarPanelValidacion() {
		((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, VALIDACION_CL);
		setTitle(mensajes.getRecursosMensajes().getString("validation.windowTitle"));
	}

}
