package igu.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import igu.VentanaPrincipal;
import igu.escaladoimg.ImagenFactoria;
import igu.internacionalizacion.Mensajes;
import logica.util.ErrorAccesoException;
import observer.Observer;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

/**
 * ValidacionPanel va a representar la clase de la interfaz gráfica sobre la que
 * se ubicarán todos los elementos correspondientes al panel de validación. Se
 * trata del primer panel con el que el usuario interactuará. Se mostrará un
 * teclado virtual en el que usuario tendrá que introducir un código de cliente
 * que estará previamente registrado en la lista de clientes (en el fichero
 * 'clientes.dat'). Si su entrada es válida podrá continuar al panel de premios
 * y participar.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class ValidacionPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Component verticalGlue;
	private JLabel lbTitulo;
	private JLabel lbIndicacion;
	private Component verticalGlue1;
	private JPanel pnCodigo;
	private JTextField txCodigo;
	private JButton btOk;
	private JLabel lbAclaracion;
	private Component verticalGlue2;
	private JPanel pnIdioma;
	private JButton btIdioma;

	private JPanel pnTecladoNumeros;
	private JPanel pnTecladoFila1;
	private JPanel pnTecladoFila2;
	private JPanel pnTecladoFila3;

	private VentanaPrincipal vP;
	private ProcesaTeclaVirtual pTV;
	private Mensajes mensajes;

	/**
	 * Constructor de la clase del panel de validación que incluirá una referencia a
	 * la ventana principal sobre la que se está mostrando éste, así como el objeto
	 * Mensajes que se estará usando para internacionalizar la aplicación. Se
	 * registrará la propia clase como observador del objeto de Mensajes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usará en la clase
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public ValidacionPanel(VentanaPrincipal vP, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);
		this.mensajes = mensajes;
		this.mensajes.registrarObservador(this);
		this.vP = vP;
		pTV = new ProcesaTeclaVirtual();

		vP.setTitle(mensajes.getRecursosMensajes().getString("validation.windowTitle"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getPnIdioma());
		add(getVerticalGlue());
		add(getLbTitulo());
		add(getLbIndicacion());
		add(getVerticalGlue1());
		add(getPnCodigo());
		add(getLbAclaracion());
		add(getVerticalGlue2());
		add(getPnTecladoNumeros());
		add(getPnTecladoFila1());
		add(getPnTecladoFila2());
		add(getPnTecladoFila3());
	}

	/**
	 * Actualiza la clase con los cambios correspondientes. Con el objeto de
	 * mensajes se actualizarán y se posibilitará "relocalizar" los distintos
	 * elementos gráficos (más concretamente, su texto).
	 */
	@Override
	public void update(Object emisor) {
		vP.setTitle(mensajes.getRecursosMensajes().getString("validation.windowTitle"));
		lbTitulo.setText(mensajes.getRecursosMensajes().getString("validation.title"));
		lbIndicacion.setText(mensajes.getRecursosMensajes().getString("validation.indication"));
		lbAclaracion.setText(mensajes.getRecursosMensajes().getString("validation.clarification"));
	}

	/**
	 * Getter del componente VerticalGlue del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'verticalGlue'.
	 * 
	 * @return El componente VerticalGlue del BoxLayout
	 */
	private Component getVerticalGlue() {
		if (verticalGlue == null) {
			verticalGlue = Box.createVerticalGlue();
		}
		return verticalGlue;
	}

	/**
	 * Getter del label del título.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label del título para
	 * el atributo 'lbTitulo'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "validation.title".
	 * 
	 * @return El label del título
	 */
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel(mensajes.getRecursosMensajes().getString("validation.title"));
			lbTitulo.setForeground(Color.WHITE);
			lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbTitulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		}
		return lbTitulo;
	}

	/**
	 * Getter del label de indicación.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label del título para
	 * el atributo 'lbIndicacion'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "validation.indication".
	 * 
	 * @return El label del indicación
	 */
	private JLabel getLbIndicacion() {
		if (lbIndicacion == null) {
			lbIndicacion = new JLabel(mensajes.getRecursosMensajes().getString("validation.indication"));
			lbIndicacion.setForeground(Color.WHITE);
			lbIndicacion.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbIndicacion.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		}
		return lbIndicacion;
	}

	/**
	 * Getter del componente VerticalGlue del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'verticalGlue1'.
	 * 
	 * @return El componente VerticalGlue del BoxLayout
	 */
	private Component getVerticalGlue1() {
		if (verticalGlue1 == null) {
			verticalGlue1 = Box.createVerticalGlue();
		}
		return verticalGlue1;
	}

	/**
	 * Getter del panel del código.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del código para
	 * el atributo 'pnCodigo'.
	 * 
	 * @return El panel del código
	 */
	private JPanel getPnCodigo() {
		if (pnCodigo == null) {
			pnCodigo = new JPanel();
			pnCodigo.setBackground(SystemColor.activeCaption);
			pnCodigo.setPreferredSize(new Dimension(500, 50));
			pnCodigo.setMinimumSize(new Dimension(10000, 100));
			pnCodigo.setMaximumSize(new Dimension(32767, 100));
			pnCodigo.add(getTxCodigo());
			pnCodigo.add(getBtOk());
		}
		return pnCodigo;
	}

	/**
	 * Getter del campo de texto del código.
	 * 
	 * Este método devuelve el valor que tiene el objeto del campo de texto del
	 * código para el atributo 'txCodigo'.
	 * 
	 * @return El campo de texto del código
	 */
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			txCodigo.setMinimumSize(new Dimension(7, 40));
			txCodigo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			txCodigo.setColumns(25);
		}
		return txCodigo;
	}

	/**
	 * Getter del botón ok.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón ok para el
	 * atributo 'btOk'. Como actionPerformed éste se encargará de comprobar si la
	 * entrada ha sido válida o por el contrario informar de un error en caso de
	 * obtener una excepción del tipo 'ErrorAccesoException'.
	 * 
	 * @return El botón ok
	 */
	private JButton getBtOk() {
		if (btOk == null) {
			btOk = new JButton("Ok");
			btOk.setMnemonic(btOk.getText().charAt(0));
			btOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						validarEntrada();
					} catch (ErrorAccesoException e1) {
						JOptionPane.showMessageDialog(null,
								mensajes.getRecursosMensajes().getString("validation.exceptionError"),
								mensajes.getRecursosMensajes().getString("validation.exceptionTitle"),
								JOptionPane.INFORMATION_MESSAGE);

						e1.printStackTrace();
					}
				}
			});
			btOk.setFont(new Font("Verdana", Font.PLAIN, 14));
		}
		return btOk;
	}

	/**
	 * Valida la entrada introducida en el campo de texto del código. Para ello se
	 * realiza la validación a través de la lógica y en caso de ser correcto se
	 * continúa al panel de premios. En otro caso se mostrarán los mensajes de error
	 * correspondientes en función de si el usuario introducido es válido pero ya ha
	 * gastado su participación previamente o bien si el usuario directamente no es
	 * válido.
	 * 
	 * @throws ErrorAccesoException
	 */
	private void validarEntrada() throws ErrorAccesoException {
		String input = getTxCodigo().getText();
		int validacionCodigo = vP.getAplicacion().getClientes().validarParticipacion(input);
		if (validacionCodigo == 1) {
			mostrarPremios();
			getTxCodigo().setText("");
		} else {
			String razonError = "";
			razonError = validacionCodigo == 0 ? mensajes.getRecursosMensajes().getString("validation.error1")
					: mensajes.getRecursosMensajes().getString("validation.error2");

			JOptionPane.showMessageDialog(null, razonError,
					mensajes.getRecursosMensajes().getString("validation.errorTitle"), JOptionPane.INFORMATION_MESSAGE);
			getTxCodigo().setText("");
		}
	}

	/**
	 * Muestra el panel de premios al usuario.
	 */
	private void mostrarPremios() {
		vP.setResizable(true);
		vP.mostrarPanelPremios();
	}

	/**
	 * Getter del label de aclaración.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de aclaración
	 * para el atributo 'lbAclaracion'. Como texto se carga del fichero de recursos
	 * la etiqueta con identificador "validation.clarification".
	 * 
	 * @return El label de aclaración
	 */
	private JLabel getLbAclaracion() {
		if (lbAclaracion == null) {
			lbAclaracion = new JLabel(mensajes.getRecursosMensajes().getString("validation.clarification"));
			lbAclaracion.setForeground(Color.WHITE);
			lbAclaracion.setAlignmentX(Component.CENTER_ALIGNMENT);
			lbAclaracion.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		}
		return lbAclaracion;
	}

	/**
	 * Getter del componente VerticalGlue del BoxLayout.
	 * 
	 * Este método devuelve el valor que tiene el objeto del componente para el
	 * atributo 'verticalGlue2'.
	 * 
	 * @return El componente VerticalGlue del BoxLayout
	 */
	private Component getVerticalGlue2() {
		if (verticalGlue2 == null) {
			verticalGlue2 = Box.createVerticalGlue();
		}
		return verticalGlue2;
	}

	/**
	 * Getter del panel del teclado numérico.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del teclado
	 * numérico para el atributo 'pnTecladoNumeros'. Para ello incluye las distintas
	 * 'teclas' por medio del método generaFilaTeclado() pasándole como parámetro el
	 * texto que se carga del fichero de recursos sobre la etiqueta con
	 * identificador "validation.virtualKeyboardNumbers".
	 * 
	 * @return El panel del teclado numérico
	 */
	private JPanel getPnTecladoNumeros() {
		if (pnTecladoNumeros == null) {
			pnTecladoNumeros = new JPanel();
			pnTecladoNumeros.setBackground(SystemColor.activeCaption);
			pnTecladoNumeros.setMaximumSize(new Dimension(32767, 0));
			generaFilaTeclado(mensajes.getRecursosMensajes().getString("validation.virtualKeyboardNumbers"),
					pnTecladoNumeros);
		}
		return pnTecladoNumeros;
	}

	/**
	 * Getter del panel de la primera fila del teclado virtual.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de la primera
	 * fila del teclado virtual para el atributo 'pnTecladoFila1'. Para ello incluye
	 * las distintas 'teclas' por medio del método generaFilaTeclado() pasándole
	 * como parámetro el texto que se carga del fichero de recursos sobre la
	 * etiqueta con identificador "validation.virtualKeyboardRow1".
	 * 
	 * @return El panel de la primera fila del teclado virtual
	 */
	private JPanel getPnTecladoFila1() {
		if (pnTecladoFila1 == null) {
			pnTecladoFila1 = new JPanel();
			pnTecladoFila1.setBackground(SystemColor.activeCaption);
			pnTecladoFila1.setMaximumSize(new Dimension(32767, 100));
			generaFilaTeclado(mensajes.getRecursosMensajes().getString("validation.virtualKeyboardRow1"),
					pnTecladoFila1);

		}
		return pnTecladoFila1;
	}

	/**
	 * Genera la fila del teclado en función de la cadena de texto recibida,
	 * recorriéndola carácter a carácter y la añade al objeto JPanel pasado como
	 * parámetro.
	 * 
	 * @param teclas     Los distintos caracteres que tendrá la fila de teclas
	 *                   dentro de una cadena de texto
	 * @param filaTeclas El panel de la fila de teclas sobre el que se añadirán
	 *                   éstas
	 */
	private void generaFilaTeclado(String teclas, JPanel filaTeclas) {
		for (char tecla : teclas.toCharArray()) {
			filaTeclas.add(creaTeclaVirtual(tecla));
		}
	}

	/**
	 * Crea la tecla virtual correspondiente en función del carácter recibido,
	 * devolviendo un botón ya configurado.
	 * 
	 * @param caracter El caracter que tendrá como texto la tecla
	 * @return El botón ya configurado
	 */
	private JButton creaTeclaVirtual(char caracter) {
		JButton tecla = new JButton(String.valueOf(caracter));
		tecla.setPreferredSize(new Dimension(46, 40));
		tecla.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		tecla.addActionListener(pTV);

		return tecla;
	}

	/**
	 * ProcesaTeclaVirtual es una clase interna de evento en la que se establece el
	 * comportamiento de los botones (o teclas) pertenecientes al teclado virtual en
	 * el momento en el que se accionan.
	 */
	class ProcesaTeclaVirtual implements ActionListener {
		/**
		 * Actualiza la entrada sobre el campo de texto del código en función de la
		 * tecla pulsada.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton teclaPulsada = (JButton) e.getSource();
			getTxCodigo().setText(getTxCodigo().getText() + teclaPulsada.getText());
		}

	}

	/**
	 * Getter del panel de la segunda fila del teclado virtual.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de la segunda
	 * fila del teclado virtual para el atributo 'pnTecladoFila2'. Para ello incluye
	 * las distintas 'teclas' por medio del método generaFilaTeclado() pasándole
	 * como parámetro el texto que se carga del fichero de recursos sobre la
	 * etiqueta con identificador "validation.virtualKeyboardRow2".
	 * 
	 * @return El panel de la segunda fila del teclado virtual
	 */
	private JPanel getPnTecladoFila2() {
		if (pnTecladoFila2 == null) {
			pnTecladoFila2 = new JPanel();
			pnTecladoFila2.setBackground(SystemColor.activeCaption);
			pnTecladoFila2.setMaximumSize(new Dimension(32767, 100));
			generaFilaTeclado(mensajes.getRecursosMensajes().getString("validation.virtualKeyboardRow2"),
					pnTecladoFila2);
		}
		return pnTecladoFila2;
	}

	/**
	 * Getter del panel de la tercera fila del teclado virtual.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de la tercera
	 * fila del teclado virtual para el atributo 'pnTecladoFila3'. Para ello incluye
	 * las distintas 'teclas' por medio del método generaFilaTeclado() pasándole
	 * como parámetro el texto que se carga del fichero de recursos sobre la
	 * etiqueta con identificador "validation.virtualKeyboardRow3".
	 * 
	 * @return El panel de la tercera fila del teclado virtual
	 */
	private JPanel getPnTecladoFila3() {
		if (pnTecladoFila3 == null) {
			pnTecladoFila3 = new JPanel();
			pnTecladoFila3.setBackground(SystemColor.activeCaption);
			pnTecladoFila3.setMaximumSize(new Dimension(32767, 100));
			generaFilaTeclado(mensajes.getRecursosMensajes().getString("validation.virtualKeyboardRow3"),
					pnTecladoFila3);
		}
		return pnTecladoFila3;
	}

	/**
	 * Getter del panel del idioma.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del idioma para
	 * el atributo 'pnIdioma'.
	 * 
	 * @return El panel del idioma
	 */
	private JPanel getPnIdioma() {
		if (pnIdioma == null) {
			pnIdioma = new JPanel();
			pnIdioma.setBackground(SystemColor.activeCaption);
			pnIdioma.setMaximumSize(new Dimension(32767, 1000));
			FlowLayout flowLayout = (FlowLayout) pnIdioma.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnIdioma.add(getBtIdioma());
		}
		return pnIdioma;
	}

	/**
	 * Getter del botón de idioma.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón de idioma para el
	 * atributo 'btIdioma'. Como actionCommand del botón se cargará 'es' o 'en' en
	 * función de lo que se haya pulsado. Esto implicará relocalizar los textos
	 * asociados a todos los observadores.
	 * 
	 * @return El botón de idioma
	 */
	private JButton getBtIdioma() {
		if (btIdioma == null) {
			btIdioma = new JButton("");
			btIdioma.setBorder(new EmptyBorder(0, 0, 0, 0));
			btIdioma.setBackground(SystemColor.activeCaption);
			btIdioma.setOpaque(true);
			btIdioma.setBorderPainted(false);
			ImageIcon imagenBoton = ImagenFactoria.getImagen("translationIcon", btIdioma, false, 130, 120);
			btIdioma.setIcon(imagenBoton);
			btIdioma.setActionCommand(Locale.getDefault().getLanguage());
			btIdioma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btIdioma.getActionCommand().equals("es")) {
						btIdioma.setActionCommand("en");
					} else {
						btIdioma.setActionCommand("es");
					}
					mensajes.localizar(new Locale(btIdioma.getActionCommand()));
				}
			});
			btIdioma.setHorizontalAlignment(SwingConstants.RIGHT);
			btIdioma.setFont(new Font("Verdana", Font.PLAIN, 14));
		}
		return btIdioma;
	}

}
