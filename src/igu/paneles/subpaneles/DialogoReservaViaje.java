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
 * DialogoReservaViaje ser� uno de los subpaneles de la interfaz gr�fica sobre
 * los que se ubicar�n todos los elementos correspondientes al di�logo para la
 * reserva de un viaje. En �l se dar� opci�n a confirmar una fecha as� como
 * alguna observaci�n a tener en cuenta para la reserva.
 * 
 * @author Jos� Antonio Garc�a Fuentes
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
	 * Constructor de la clase del di�logo de reserva del viaje que incluir� una
	 * referencia a la miniatura del art�culo, as� como el regalo que se usar� para
	 * generar la reserva y el objeto Mensajes que se estar� usando para
	 * internacionalizar la aplicaci�n.
	 * 
	 * @param mA       La referencia a miniatura del art�culo
	 * @param regalo   El regalo que se usar� para generar la reserva
	 * @param mensajes El objeto de mensajes que se est� empleando para la
	 *                 internacionalizaci�n
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
	 * Este m�todo devuelve el valor que tiene el objeto del panel del calendario
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
	 * Este m�todo devuelve el valor que tiene el objeto del panel de observaciones
	 * para el atributo 'pnObservaciones'. Como texto del t�tulo del borde se carga
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
	 * Este m�todo devuelve el valor que tiene el objeto del del selector de fecha
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
	 * Configura la fecha m�nima que podr� escogerse en el selector de fecha
	 * dateChooser. El criterio ser� una fecha m�nima superior a 5 d�as de la fecha
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
	 * Este m�todo devuelve el valor que tiene el objeto del label de la fecha para
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
	 * Este m�todo devuelve el valor que tiene el objeto del panel de scroll de
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
	 * Getter del �rea de texto para observaciones.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del �rea de texto de
	 * observaciones para el atributo 'textAreaObservaciones'.
	 * 
	 * @return El �rea de texto para observaciones
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
	 * Este m�todo devuelve el valor que tiene el objeto del panel de opciones para
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
	 * Getter del bot�n ok.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del bot�n ok para el
	 * atributo 'btOk'. Como actionPerformed �ste se encargar� de reservar el viaje.
	 * 
	 * @return El bot�n ok
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
	 * Reserva el viaje en cuesti�n y establece la fecha y las observaciones (en
	 * caso de haberse indicado). En caso de que la fecha a seleccionar se encuentre
	 * vac�a se mostrar� el mensaje del fichero de recursos identificado con la
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
	 * Getter del bot�n de cancelar.
	 * 
	 * Este m�todo devuelve el valor que tiene el objeto del bot�n de cancelar para
	 * el atributo 'btCancel'. Como actionPerformed �ste se encargar� de cerrar el
	 * di�logo.
	 * 
	 * @return El bot�n de cancelar
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
	 * Cierra el di�logo actual.
	 */
	private void cerrarDialogo() {
		dispose();
	}
}
