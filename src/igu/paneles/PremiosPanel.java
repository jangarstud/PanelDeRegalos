package igu.paneles;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import igu.VentanaPrincipal;
import igu.escaladoimg.ImagenFactoria;
import igu.eventos.ProcesaCasilla;
import igu.internacionalizacion.Mensajes;
import logica.tablero.Casilla;
import logica.tablero.TableroJuego;
import observer.Observer;
import java.awt.SystemColor;
import java.awt.Color;

/**
 * PremiosPanel va a representar la clase de la interfaz gráfica sobre la que se
 * ubicarán todos los elementos correspondientes al panel de premios. Se trata
 * del segundo panel con el que el usuario interactuará al haber validado su
 * participación. Se mostrará un número de casillas determinado (25) en el que
 * se ubicarán aleatoriamente distintos premios reflejados en puntos o intentos
 * adicionales.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class PremiosPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int NUMERO_PANELES = 25;

	private JPanel pnNorte;
	private JPanel pnIntentos;
	private JPanel pnPuntos;
	private JLabel lbIntentos;
	private JTextField txIntentos;
	private JLabel lbPuntos;
	private JTextField txPuntos;
	private JPanel pnPremios;
	private JPanel pnSur;
	private JButton btContinuar;

	private VentanaPrincipal vP;
	private Mensajes mensajes;
	private TableroJuego tablero;
	private ProcesaCasilla pC;

	/**
	 * Constructor de la clase del panel de premios que incluirá una referencia a la
	 * ventana principal sobre la que se está mostrando éste, así como el objeto
	 * Mensajes que se estará usando para internacionalizar la aplicación. Se
	 * registrará la propia clase como observador del objeto de Mensajes, así como
	 * del objeto Tablero.
	 * 
	 * @param vP       La referencia a la ventana principal que se usará en la clase
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public PremiosPanel(VentanaPrincipal vP, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);
		this.mensajes = mensajes;
		this.vP = vP;
		this.tablero = new TableroJuego();
		this.pC = new ProcesaCasilla(this);

		setLayout(new BorderLayout(0, 0));
		add(getPnNorte(), BorderLayout.NORTH);
		add(getPnPremios(), BorderLayout.CENTER);
		add(getPnSur(), BorderLayout.SOUTH);

		this.mensajes.registrarObservador(this);
		this.tablero.registrarObservador(this);
	}

	/**
	 * Actualiza la clase con los cambios correspondientes en función de si el
	 * emisor que dispara la acción de actualizar es el objeto 'Observable' de
	 * mensajes o el del tablero.
	 * 
	 * En caso de ser el objeto de mensajes se actualizarán los distintos elementos
	 * gráficos (más concretamente, su texto); y en caso de ser el tablero, se
	 * actualizarán los campos de texto con la puntuación y el número de intentos.
	 */
	@Override
	public void update(Object emisor) {
		if (emisor == mensajes) {
			lbIntentos.setText(mensajes.getRecursosMensajes().getString("prizes.tries"));
			lbPuntos.setText(mensajes.getRecursosMensajes().getString("points") + ": ");
			btContinuar.setText(mensajes.getRecursosMensajes().getString("continue"));
			btContinuar.setMnemonic(btContinuar.getText().charAt(0));
		} else if (emisor == tablero) {
			getTxPuntos().setText(String.valueOf(tablero.getPuntuacion()));
			getTxIntentos().setText(String.valueOf(tablero.getIntentos()));
		}
	}

	/**
	 * Inicializa la clase del panel de premios realizando acciones como inicializar
	 * la lógica del tablero; actualizar los puntos y los intentos mostrados en la
	 * interfaz; generar otro conjunto de casillas distinto; y repintar todos los
	 * elementos visuales presentes en el panel.
	 */
	public void inicializar() {
		tablero.inicializar();
		getTxIntentos().setText(String.valueOf(tablero.getIntentos()));
		getTxPuntos().setText(String.valueOf(tablero.getPuntuacion()));
		getBtContinuar().setEnabled(false);
		generarCasillas(NUMERO_PANELES);

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
			pnNorte.setLayout(new BorderLayout(0, 0));
			pnNorte.add(getPnIntentos(), BorderLayout.WEST);
			pnNorte.add(getPnPuntos(), BorderLayout.EAST);
		}
		return pnNorte;
	}

	/**
	 * Getter del panel de intentos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de intentos para
	 * el atributo 'pnIntentos'.
	 * 
	 * @return El panel de intentos
	 */
	private JPanel getPnIntentos() {
		if (pnIntentos == null) {
			pnIntentos = new JPanel();
			pnIntentos.setBackground(SystemColor.activeCaption);
			pnIntentos.add(getLbIntentos());
			pnIntentos.add(getTxIntentos());
		}
		return pnIntentos;
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
			pnPuntos.add(getTxPuntos());
		}
		return pnPuntos;
	}

	/**
	 * Getter del label de intentos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de intentos para
	 * el atributo 'lbIntentos'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "prizes.tries".
	 * 
	 * @return El label de intentos
	 */
	private JLabel getLbIntentos() {
		if (lbIntentos == null) {
			lbIntentos = new JLabel(mensajes.getRecursosMensajes().getString("prizes.tries"));
			lbIntentos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		}
		return lbIntentos;
	}

	/**
	 * Getter del campo de texto de intentos.
	 * 
	 * Este método devuelve el valor que tiene el objeto del campo de texto de
	 * intentos para el atributo 'txIntentos'.
	 * 
	 * @return El campo de texto de intentos
	 */
	private JTextField getTxIntentos() {
		if (txIntentos == null) {
			txIntentos = new JTextField();
			txIntentos.setEditable(false);
			txIntentos.setHorizontalAlignment(SwingConstants.CENTER);
			txIntentos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
			txIntentos.setColumns(3);
		}
		return txIntentos;
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
			lbPuntos = new JLabel(mensajes.getRecursosMensajes().getString("points") + ": ");
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
			txPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setColumns(10);
		}
		return txPuntos;
	}

	/**
	 * Getter del panel central de premios.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel central de
	 * premios para el atributo 'pnPremios'.
	 * 
	 * @return El panel central de premios
	 */
	private JPanel getPnPremios() {
		if (pnPremios == null) {
			pnPremios = new JPanel();
			pnPremios.setBackground(SystemColor.activeCaption);
			pnPremios.setLayout(new GridLayout(5, 5, 2, 4));
		}
		return pnPremios;
	}

	/**
	 * Genera tantas casillas a nivel visual como el número que se le indique como
	 * parámetro.
	 * 
	 * @param numeroPaneles El número de casillas que se desea crear
	 * @return Los objetos JButton de cada una de las casillas
	 */
	private JButton[] generarCasillas(int numeroPaneles) {
		getPnPremios().removeAll();
		JButton casillas[] = new JButton[numeroPaneles];
		for (int i = 0; i < numeroPaneles; i++) {
			casillas[i] = creaCasilla(i);
			getPnPremios().add(casillas[i]);
		}
		return casillas;
	}

	/**
	 * Crea la casilla con el índice indicado asociándole éste a su actionCommand
	 * así como el atributo receptor del evento 'pC'.
	 * 
	 * @param indice El índice de la casilla que se desea crear
	 * @return El botón creado con las propiedades correspondientes
	 */
	private JButton creaCasilla(int indice) {
		JButton casilla = new JButton("");
		casilla.setActionCommand(String.valueOf(indice));
		casilla.addActionListener(pC);
		return casilla;
	}

	/**
	 * Actualiza la lógica del tablero en función de la posición seleccionada.
	 * 
	 * @param posicion La posición que se desea descubrir en el tablero
	 */
	public void actualizarLogicaTablero(int posicion) {
		tablero.descubrirCasilla(posicion);
	}

	/**
	 * Pinta la casilla indicada en función del tipo de ésta.
	 * 
	 * @param posicion     La posición que ocupa la casilla seleccionada
	 * @param botonCasilla El objeto visual de la casilla que se desea manipular
	 */
	public void pintarCasilla(int posicion, JButton botonCasilla) {
		Casilla casilla = tablero.getCasilla(posicion);
		ImageIcon imagen = ImagenFactoria.getImagen(casilla.getNombre(), botonCasilla, true, 0, 0);
		botonCasilla.setIcon(imagen);
		botonCasilla.setDisabledIcon(imagen);
	}

	/**
	 * Comprueba si la participación en el panel de premios ha llegado a su fin por
	 * medio del número de intentos disponible. En caso de llegar a su fin, se
	 * muestra un mensaje informativo y a continuación se pinta el tablero entero,
	 * para que el usuario pueda observar la ubicación de cada casilla.
	 */
	public void comprobarFin() {
		if (tablero.getIntentos() == 0) {
			JOptionPane.showMessageDialog(null, mensajes.getRecursosMensajes().getString("prizes.endGame"),
					mensajes.getRecursosMensajes().getString("prizes.endTitle"), JOptionPane.INFORMATION_MESSAGE);
			descubrirTablero();
			getBtContinuar().setEnabled(true);
			vP.getAplicacion().getClientes().getUsuarioActual().setPuntos(tablero.getPuntuacion());
		}
	}

	/**
	 * Descubre el tablero completo recorriendo todas las casillas y pintándolas.
	 */
	private void descubrirTablero() {
		for (int i = 0; i < getPnPremios().getComponents().length; i++) {
			JButton casillaAPintar = (JButton) getPnPremios().getComponent(i);
			pintarCasilla(i, casillaAPintar);
			casillaAPintar.setEnabled(false);
		}
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
			pnSur.add(getBtContinuar());
		}
		return pnSur;
	}

	/**
	 * Getter del botón de continuar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón de continuar para
	 * el atributo 'btContinuar'.
	 * 
	 * @return El botón de continuar
	 */
	private JButton getBtContinuar() {
		if (btContinuar == null) {
			btContinuar = new JButton(mensajes.getRecursosMensajes().getString("continue"));
			btContinuar.setForeground(Color.DARK_GRAY);
			btContinuar.setBackground(SystemColor.inactiveCaptionBorder);
			btContinuar.setBorderPainted(false);
			btContinuar.setMnemonic(btContinuar.getText().charAt(0));
			btContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarCatalogo();
				}
			});
			btContinuar.setHorizontalAlignment(SwingConstants.RIGHT);
			btContinuar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
			btContinuar.setEnabled(false);
		}
		return btContinuar;
	}

	/**
	 * Muestra el panel del catálogo de regalos.
	 */
	private void mostrarCatalogo() {
		vP.setBounds(100, 100, 660, 850);
		vP.setResizable(false);
		vP.setLocationRelativeTo(null);

		vP.transicionDesdePanelPremios(); // Ajusta el filtro de puntos únicamente cuando se cambia desde la vista de
											// premios al catálogo
		vP.mostrarPanelCatalogo();
	}
}
