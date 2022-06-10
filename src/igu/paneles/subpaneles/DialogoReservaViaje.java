package igu.paneles.subpaneles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import igu.internacionalizacion.Mensajes;
import logica.Regalo;

/**
 * DialogoReservaViaje será uno de los subpaneles de la interfaz gráfica sobre
 * los que se ubicarán todos los elementos correspondientes al diálogo para la
 * reserva de un viaje. En él se dará opción a confirmar una fecha así como
 * alguna observación a tener en cuenta para la reserva.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class DialogoReservaViaje extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnPrincipal;
	private JPanel pnCalendar;
	private JPanel pnObservaciones;
	private JPanel pnOpciones;
	private JButton btOk;
	private JButton btCancel;
	private JDateChooser dateChooser;
	private JLabel lbFecha;
	private JScrollPane scrollPaneObservaciones;
	private JTextArea textAreaObservaciones;

	private MiniaturaArticulo mA;
	private Regalo regalo;
	private Mensajes mensajes;

	/**
	 * Constructor de la clase del diálogo de reserva del viaje que incluirá una
	 * referencia a la miniatura del artículo, así como el regalo que se usará para
	 * generar la reserva y el objeto Mensajes que se estará usando para
	 * internacionalizar la aplicación.
	 * 
	 * @param mA       La referencia a miniatura del artículo
	 * @param regalo   El regalo que se usará para generar la reserva
	 * @param mensajes El objeto de mensajes que se está empleando para la
	 *                 internacionalización
	 */
	public DialogoReservaViaje(MiniaturaArticulo mA, Regalo regalo, Mensajes mensajes) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					;
				}
			}
		});
		this.mA = mA;
		this.regalo = regalo;
		this.mensajes = mensajes;

		setTitle(mensajes.getRecursosMensajes().getString("reservation.reservationTitle"));
		setModal(true);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(DialogoReservaViaje.class.getResource("/img/calendarIcon.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pnPrincipal);
		pnPrincipal.add(getPnCalendar(), BorderLayout.NORTH);
		pnPrincipal.add(getPnObservaciones(), BorderLayout.CENTER);
		pnPrincipal.add(getPnOpciones(), BorderLayout.SOUTH);

		getRootPane().setDefaultButton(getBtOk());

		setLocationRelativeTo(null);
	}

	/**
	 * Getter del panel del calendario.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel del calendario
	 * para el atributo 'pnCalendar'.
	 * 
	 * @return El panel del calendario
	 */
	private JPanel getPnCalendar() {
		if (pnCalendar == null) {
			pnCalendar = new JPanel();
			pnCalendar.setLayout(new BorderLayout(0, 0));
			pnCalendar.add(getDateChooser());
			pnCalendar.add(getLbFecha(), BorderLayout.WEST);
		}
		return pnCalendar;
	}

	/**
	 * Getter del panel de observaciones.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de observaciones
	 * para el atributo 'pnObservaciones'. Como texto del título del borde se carga
	 * del fichero de recursos la etiqueta con identificador "observations".
	 * 
	 * @return El panel de observaciones
	 */
	private JPanel getPnObservaciones() {
		if (pnObservaciones == null) {
			pnObservaciones = new JPanel();
			pnObservaciones.setBorder(new TitledBorder(null, mensajes.getRecursosMensajes().getString("observations"),
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnObservaciones.setLayout(new BorderLayout(0, 0));
			pnObservaciones.add(getScrollPaneObservaciones(), BorderLayout.CENTER);
		}
		return pnObservaciones;
	}

	/**
	 * Getter del selector de fecha.
	 * 
	 * Este método devuelve el valor que tiene el objeto del del selector de fecha
	 * para el atributo 'dateChooser'.
	 * 
	 * @return El selector de fecha
	 */
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();

			JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
			editor.setEditable(false);

			configurarFechaMinima();
		}
		return dateChooser;
	}

	/**
	 * Configura la fecha mínima que podrá escogerse en el selector de fecha
	 * dateChooser. El criterio será una fecha mínima superior a 5 días de la fecha
	 * actual.
	 */
	private void configurarFechaMinima() {
		LocalDate localDateFechaMinima = LocalDate.parse(LocalDate.now().toString()).plusDays(5);

		Date fechaMinima = convertToDate(localDateFechaMinima);
		dateChooser.setMinSelectableDate(fechaMinima);
	}

	/**
	 * Convierte la fecha del formato aplicado para el objeto LocalDate y lo
	 * transforma al del objeto Date.
	 * 
	 * @param dateToConvert La fecha a convertir
	 * @return El objeto Date con su formato de fecha asociado
	 */
	public Date convertToDate(LocalDate dateToConvert) {
		return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Getter del label de la fecha.
	 * 
	 * Este método devuelve el valor que tiene el objeto del label de la fecha para
	 * el atributo 'lbFecha'. Como texto se carga del fichero de recursos la
	 * etiqueta con identificador "reservation.chooseDate".
	 * 
	 * @return El label de la fecha
	 */
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel(mensajes.getRecursosMensajes().getString("reservation.chooseDate"));
		}
		return lbFecha;
	}

	/**
	 * Getter del panel de scroll de observaciones.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de scroll de
	 * observaciones para el atributo 'scrollPaneObservaciones'.
	 * 
	 * @return El panel de scroll de observaciones
	 */
	private JScrollPane getScrollPaneObservaciones() {
		if (scrollPaneObservaciones == null) {
			scrollPaneObservaciones = new JScrollPane();
			scrollPaneObservaciones.setViewportView(getTextAreaObservaciones());
		}
		return scrollPaneObservaciones;
	}

	/**
	 * Getter del área de texto para observaciones.
	 * 
	 * Este método devuelve el valor que tiene el objeto del área de texto de
	 * observaciones para el atributo 'textAreaObservaciones'.
	 * 
	 * @return El área de texto para observaciones
	 */
	private JTextArea getTextAreaObservaciones() {
		if (textAreaObservaciones == null) {
			textAreaObservaciones = new JTextArea();
			textAreaObservaciones.setWrapStyleWord(true);
			textAreaObservaciones.setLineWrap(true);
		}
		return textAreaObservaciones;
	}

	/**
	 * Getter del panel de opciones.
	 * 
	 * Este método devuelve el valor que tiene el objeto del panel de opciones para
	 * el atributo 'pnOpciones'.
	 * 
	 * @return El panel de opciones
	 */
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnOpciones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnOpciones.add(getBtOk());
			pnOpciones.add(getBtCancel());
		}
		return pnOpciones;
	}

	/**
	 * Getter del botón ok.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón ok para el
	 * atributo 'btOk'. Como actionPerformed éste se encargará de reservar el viaje.
	 * 
	 * @return El botón ok
	 */
	private JButton getBtOk() {
		if (btOk == null) {
			btOk = new JButton("OK");
			btOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reservar();
				}
			});
			btOk.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		}
		return btOk;
	}

	/**
	 * Reserva el viaje en cuestión y establece la fecha y las observaciones (en
	 * caso de haberse indicado). En caso de que la fecha a seleccionar se encuentre
	 * vacía se mostrará el mensaje del fichero de recursos identificado con la
	 * etiqueta "reservation.emptyDateMessage".
	 */
	private void reservar() {
		Date fechaSeleccionada = getDateChooser().getDate();
		if (fechaSeleccionada != null) {
			Regalo regaloViajeAReservar = new Regalo(regalo);

			regaloViajeAReservar.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(fechaSeleccionada));
			regaloViajeAReservar.setObservaciones(getTextAreaObservaciones().getText());

			mA.addRegalo(regaloViajeAReservar);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					mensajes.getRecursosMensajes().getString("reservation.emptyDateMessage"),
					mensajes.getRecursosMensajes().getString("reservation.emptyDateTitle"),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Getter del botón de cancelar.
	 * 
	 * Este método devuelve el valor que tiene el objeto del botón de cancelar para
	 * el atributo 'btCancel'. Como actionPerformed éste se encargará de cerrar el
	 * diálogo.
	 * 
	 * @return El botón de cancelar
	 */
	private JButton getBtCancel() {
		if (btCancel == null) {
			btCancel = new JButton(mensajes.getRecursosMensajes().getString("cancel"));
			btCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarDialogo();
				}
			});
			btCancel.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		}
		return btCancel;
	}

	/**
	 * Cierra el diálogo actual.
	 */
	private void cerrarDialogo() {
		dispose();
	}
}
