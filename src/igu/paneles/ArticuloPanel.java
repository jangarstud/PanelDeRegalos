package igu.paneles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import igu.VentanaPrincipal;
import igu.internacionalizacion.Mensajes;
import igu.paneles.subpaneles.MiniaturaArticulo;
import logica.Aplicacion;
import logica.Regalo;
import observer.Observer;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.Color;

/**
 * ArticuloPanel representar� la clase de la interfaz gr�fica sobre la que se
 * ubicar�n todos los elementos correspondientes a la visualizaci�n de un
 * art�culo de regalos. En �l se mostrar�n adem�s detalles adicionales del
 * art�culo en cuesti�n.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class ArticuloPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnNorte;
	private JPanel pnCenter;
	private JPanel pnDetallesArticulo;
	private JPanel pnDescripcion;
	private JPanel pnArticulo;
	private JPanel pnBoton;
	private JButton btBack;
	private JPanel pnPuntos;
	private JLabel lbPuntos;
	private JTextField txPuntos;

	private VentanaPrincipal vP;
	private Mensajes mensajes;
	private Aplicacion aplicacion;
	private MiniaturaArticulo mA;

	private JTextArea txtrDescripcion;

	/**
	 * Constructor de la clase del panel del art�culo de regalos que incluir� una
	 * referencia a la ventana principal sobre la que se est� mostrando �ste, as�
	 * como el objeto Mensajes que se estar� usando para internacionalizar la
	 * aplicaci�n. Se registrar� la propia clase como observador del objeto de
	 * Mensajes as� como del objeto de la l�gica Clientes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usar� en la clase
	 * @param mensajes El objeto de mensajes que se est� empleando para la
	 *                 internacionalizaci�n
	 */
	public ArticuloPanel(VentanaPrincipal vP, Mensajes mensajes) {
		setBackground(SystemColor.activeCaption);
		this.vP = vP;
		this.mensajes = mensajes;
		this.aplicacion = vP.getAplicacion();

		setLayout(new BorderLayout(0, 0));
		add(getPnNorte(), BorderLayout.NORTH);
		add(getPnCenter(), BorderLayout.CENTER);

		this.mensajes.registrarObservador(this);
		this.vP.getAplicacion().getClientes().registrarObservador(this);
	}

	/**
	 * Inicializa la clase del panel del art�culo de regalos realizando acciones
	 * como establecer la miniatura del art�culo; indicar la puntuaci�n del cliente;
	 * y actualizar los detalles asociados para el art�culo a mostrar.
	 */
	public void inicializar(Regalo regalo, MiniaturaArticulo mA) {
		this.mA = mA;

		getTxPuntos().setText(String.valueOf(vP.getAplicacion().getClientes().getUsuarioActual().getPuntos()));
		getPnArticulo().removeAll();
		getPnArticulo().add(mA, BorderLayout.CENTER);
		mA.alternarHabilitarImagen();
		getTxtrDescripcion().setText(regalo.getDescripcion());

		validate();
		repaint();
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
			getLbPuntos().setText(mensajes.getRecursosMensajes().getString("points"));
			getBtBack().setText(mensajes.getRecursosMensajes().getString("back"));
			getBtBack().setMnemonic(mensajes.getRecursosMensajes().getString("back").charAt(0));

			String tituloDescripcion = mensajes.getRecursosMensajes().getString("article.description");
			pnDescripcion.setBorder(
					new TitledBorder(null, tituloDescripcion, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		} else if (emisor == vP.getAplicacion().getClientes()) {
			getTxPuntos().setText(String.valueOf(aplicacion.getClientes().getUsuarioActual().getPuntos()));
		}
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
	 * atributo 'pnCenter'.
	 * 
	 * @return El panel central
	 */
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(SystemColor.activeCaption);
			pnCenter.setLayout(new GridLayout(0, 1, 0, 0));
			pnCenter.add(getPnArticulo());
			pnCenter.add(getPnDetallesArticulo());
		}
		return pnCenter;
	}

	/**
	 * Getter del panel de detalles del art�culo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel de detalles del
	 * art�culo para el atributo 'pnDetallesArticulo'.
	 * 
	 * @return El panel de detalles del art�culo
	 */
	private JPanel getPnDetallesArticulo() {
		if (pnDetallesArticulo == null) {
			pnDetallesArticulo = new JPanel();
			pnDetallesArticulo.setBackground(SystemColor.activeCaption);
			pnDetallesArticulo.setLayout(new BorderLayout(0, 0));
			pnDetallesArticulo.add(getPnDescripcion(), BorderLayout.CENTER);
		}
		return pnDetallesArticulo;
	}

	/**
	 * Getter del panel espec�fico de la descripci�n del art�culo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel espec�fico de la
	 * descripci�n del art�culo para el atributo 'pnDescripcion'.
	 * 
	 * @return El panel espec�fico de la descripci�n del art�culo
	 */
	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setBackground(SystemColor.activeCaption);
			pnDescripcion.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnDescripcion.setLayout(new BorderLayout(0, 0));
			pnDescripcion.add(getTxtrDescripcion());
		}
		return pnDescripcion;
	}

	/**
	 * Getter del panel del art�culo.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del panel del art�culo para
	 * el atributo 'pnArticulo'.
	 * 
	 * @return El panel del art�culo
	 */
	private JPanel getPnArticulo() {
		if (pnArticulo == null) {
			pnArticulo = new JPanel();
			pnArticulo.setBackground(Color.WHITE);
			pnArticulo.setLayout(new BorderLayout(0, 0));
		}
		return pnArticulo;
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
			FlowLayout flowLayout = (FlowLayout) pnBoton.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnBoton.add(getBtBack());
		}
		return pnBoton;
	}

	/**
	 * Getter del bot�n atr�s.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del bot�n atr�s para el
	 * atributo 'btBack'. Como actionPerformed �ste se encargar� de regresar y
	 * mostrar el panel del cat�logo (y habilitar� nuevamente el bot�n de la
	 * imagen).
	 * 
	 * @return El bot�n atr�s
	 */
	private JButton getBtBack() {
		if (btBack == null) {
			btBack = new JButton(mensajes.getRecursosMensajes().getString("back"));
			btBack.setBorderPainted(false);
			btBack.setBackground(SystemColor.activeCaptionBorder);
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mA.alternarHabilitarImagen();
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
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setEditable(false);
			txPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			txPuntos.setColumns(5);
		}
		return txPuntos;
	}

	/**
	 * Getter del �rea de texto para la descripci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del �rea de texto de la
	 * descripci�n para el atributo 'txtrDescripcion'.
	 * 
	 * @return El �rea de texto para la descripci�n
	 */
	private JTextArea getTxtrDescripcion() {
		if (txtrDescripcion == null) {
			txtrDescripcion = new JTextArea();
			txtrDescripcion.setEditable(false);
			txtrDescripcion.setWrapStyleWord(true);
			txtrDescripcion.setLineWrap(true);
			txtrDescripcion.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		}
		return txtrDescripcion;
	}
}
