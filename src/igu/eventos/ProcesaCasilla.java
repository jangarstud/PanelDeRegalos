package igu.eventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import igu.paneles.PremiosPanel;

/**
 * ProcesaCasilla es una clase de evento en la que se establece el comportamiento
 * de las casillas del panel de premios en el momento en el que se accionan.
 * 
 * @author José Antonio García Fuentes
 *
 */
public class ProcesaCasilla implements ActionListener {

	PremiosPanel pP;

	/**
	 * Constructor de la clase ProcesaCasilla en la que se obtiene el objeto del
	 * panel de premios para poder realizar los cambios oportunos en él.
	 * 
	 * @param pP Objeto del panel de premios
	 */
	public ProcesaCasilla(PremiosPanel pP) {
		this.pP = pP;
	}

	/**
	 * Actualiza la lógica del tablero y pinta la casilla en función del índice seleccionado.
	 * Este índice se obtiene a través del actionCommand del objeto fuente que lanza el evento.
	 * 
	 * Finalmente, se comprueba si la participación en el juego ha llegado a su fin.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton casillaSeleccionada = (JButton) e.getSource();
		int indiceSeleccionado = Integer.parseInt(casillaSeleccionada.getActionCommand());
		pP.actualizarLogicaTablero(indiceSeleccionado);
		pP.pintarCasilla(indiceSeleccionado, casillaSeleccionada);
		
		casillaSeleccionada.setBackground(Color.GREEN);
		casillaSeleccionada.setOpaque(true);
		casillaSeleccionada.setBorderPainted(false);

		casillaSeleccionada.setEnabled(false);
		pP.comprobarFin();

	}

}
