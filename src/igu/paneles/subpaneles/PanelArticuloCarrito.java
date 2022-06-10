package igu.paneles.subpaneles;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import igu.escaladoimg.ImagenFactoria;
import igu.internacionalizacion.Mensajes;
import igu.paneles.CarritoPanel;
import logica.Regalo;
import logica.Regalo.Categoria;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * PanelArticuloCarrito será uno de los subpaneles de la interfaz gráfica sobre
 * los que se ubicarán todos los elementos correspondientes al panel del
 * artículo de regalo del carrito. En él se mostrará la imagen y fecha en caso
 * de ser un viaje, al igual que sus puntos asociados y la opción de eliminar
 * unidades de éste.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class PanelArticuloCarrito extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnImagenYFecha;
	private JLabel lbImagen;
	private JLabel lbPuntos;
	private JPanel pnBoton;
	private JButton btEliminar;

	private CarritoPanel cP;
	private Regalo regalo;
	private Mensajes mensajes;

	/**
	 * Constructor de la clase del panel del artículo del carrito de regalos que
	 * incluirá una referencia al panel del carrito sobre el que se está mostrando
	 * éste, así como el regalo que se usará para generar el panel del artículo y el
	 * objeto Mensajes que se estará usando para internacionalizar la aplicación.
	 * 
	 * @param cP       La referencia al panel del carrito que se usará en la clase
	 * @param regalo   El regalo que se usará para generar el panel del artículo
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public PanelArticuloCarrito(CarritoPanel cP, Regalo regalo, Mensajes mensajes) {
		setBackground(new Color(176, 196, 222));
		this.cP = cP;
		this.regalo = regalo;
		this.mensajes = mensajes;

		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), regalo.getDenominacion(), TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(255, 255, 255)));
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getPnImagenYFecha());
		add(getLbPuntos());
		add(getPnBoton());

	}

	/**
	 * Getter del panel de la imagen y la fecha.
	 * 
	 * Este método devuelve el valor que tiene el objeto de la imagen y la fecha
	 * para el atributo 'pnImagenYFecha'.
	 * 
	 * @return El panel de la imagen y la fecha
	 */
	private JPanel getPnImagenYFecha() {
		if (pnImagenYFecha == null) {
			pnImagenYFecha = new JPanel();
			pnImagenYFecha.setBackground(new Color(176, 196, 222));

			if (regalo.getCategoriaRegalo() == Categoria.VTRIPS) {
				pnImagenYFecha.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
						regalo.getFecha(), TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM, null, new Color(0, 0, 0)));
			}

			pnImagenYFecha.setLayout(new BorderLayout(0, 0));
			pnImagenYFecha.add(getLbImagen(), BorderLayout.CENTER);
		}
		return pnImagenYFecha;
	}

	/**
	 * Getter del label de la imagen.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de la imagen del
	 * artículo para el atributo 'lbImagen'.
	 * 
	 * @return El label de la imagen
	 */
	private JLabel getLbImagen() {
		if (lbImagen == null) {
			lbImagen = new JLabel("");
			lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon imagenEscalada = ImagenFactoria.getImagen(regalo.getCodigo(), lbImagen, false, 110, 110);
			lbImagen.setIcon(imagenEscalada);
		}
		return lbImagen;
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
			lbPuntos = new JLabel(String.valueOf(regalo.getPuntos()) + " pts");
			lbPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			lbPuntos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		}
		return lbPuntos;
	}

	/**
	 * Getter del panel específico del botón eliminar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel específico del
	 * botón eliminar para el atributo 'pnBoton'.
	 * 
	 * @return El panel específico del botón eliminar
	 */
	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBackground(new Color(176, 196, 222));
			pnBoton.setLayout(new BoxLayout(pnBoton, BoxLayout.X_AXIS));
			pnBoton.add(getBtEliminar());
		}
		return pnBoton;
	}

	/**
	 * Getter del botón eliminar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón eliminar para el
	 * atributo 'btEliminar'. Como actionPerformed éste se encargará de decrementar
	 * una unidad del artículo.
	 * 
	 * @return El botón eliminar
	 */
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton(mensajes.getRecursosMensajes().getString("delete"));
			btEliminar.setBorderPainted(false);
			btEliminar.setBackground(new Color(230, 230, 250));
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cP.eliminarArticuloCarrito(regalo);
				}
			});
			btEliminar.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		}
		return btEliminar;
	}

}
