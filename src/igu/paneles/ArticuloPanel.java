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
 * ArticuloPanel representará la clase de la interfaz gráfica sobre la que se
 * ubicarán todos los elementos correspondientes a la visualización de un
 * artículo de regalos. En él se mostrarán además detalles adicionales del
 * artículo en cuestión.
 * 
 * @author José Antonio García Fuentes
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
	 * Constructor de la clase del panel del artículo de regalos que incluirá una
	 * referencia a la ventana principal sobre la que se está mostrando éste, así
	 * como el objeto Mensajes que se estará usando para internacionalizar la
	 * aplicación. Se registrará la propia clase como observador del objeto de
	 * Mensajes así como del objeto de la lógica Clientes.
	 * 
	 * @param vP       La referencia a la ventana principal que se usará en la clase
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
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
	 * Inicializa la clase del panel del artículo de regalos realizando acciones
	 * como establecer la miniatura del artículo; indicar la puntuación del cliente;
	 * y actualizar los detalles asociados para el artículo a mostrar.
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
	 * Actualiza la clase con los cambios correspondientes en función de si el
	 * emisor que dispara la acción de actualizar es el objeto 'Observable' de
	 * mensajes o el de clientes.
	 * 
	 * 
	 * En caso de ser el objeto de mensajes se actualizará y se posibilitará
	 * "relocalizar" los distintos elementos gráficos (más concretamente, su texto y
	 * sus mnemónicos); y en caso de ser los clientes, se actualizará el campo de
	 * texto con la puntuación del usuario actual.
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
	 * Este método devuelve el valor que tiene el objeto del panel norte para el
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
	 * Este método devuelve el valor que tiene el objeto del panel central para el
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
	 * Getter del panel de detalles del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de detalles del
	 * artículo para el atributo 'pnDetallesArticulo'.
	 * 
	 * @return El panel de detalles del artículo
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
	 * Getter del panel específico de la descripción del artículo.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel específico de la
	 * descripción del artículo para el atributo 'pnDescripcion'.
	 * 
	 * @return El panel específico de la descripción del artículo
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
			pnArticulo.setBackground(Color.WHITE);
			pnArticulo.setLayout(new BorderLayout(0, 0));
		}
		return pnArticulo;
	}

	/**
	 * Getter del panel específico del botón atrás.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel específico del
	 * botón atrás para el atributo 'pnBoton'.
	 * 
	 * @return El panel específico del botón atrás
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
	 * Getter del botón atrás.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón atrás para el
	 * atributo 'btBack'. Como actionPerformed éste se encargará de regresar y
	 * mostrar el panel del catálogo (y habilitará nuevamente el botón de la
	 * imagen).
	 * 
	 * @return El botón atrás
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
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setEditable(false);
			txPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			txPuntos.setColumns(5);
		}
		return txPuntos;
	}

	/**
	 * Getter del área de texto para la descripción.
	 * 
	 * Este método devuelve el valor que tiene el objeto del área de texto de la
	 * descripción para el atributo 'txtrDescripcion'.
	 * 
	 * @return El área de texto para la descripción
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
