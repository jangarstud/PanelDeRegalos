package logica.tablero;

import java.util.Random;

import observer.Observable;

/**
 * TableroJuego representar� la clase del tablero de juego principal sobre la
 * que el usuario comenzar� su participaci�n. La dimensi�n por defecto del
 * tablero ser� de 25 casillas; el n�mero de intentos ser� de 3; existir� 1
 * casilla de 1000 puntos; 5 de 250 puntos; 8 de 50 puntos; 1 de multiplica x2;
 * 2 casillas especiales; y 8 casillas vac�as. Adem�s almacenar� la puntuaci�n
 * que se ir� adquiriendo a medida que se gastan los turnos. Ser� una de las
 * clases a observar.
 * 
 * @author Jos� Antonio Garc�a Fuentes
 *
 */
public class TableroJuego extends Observable {
	public static final int DIMENSION_TABLERO = 25;
	public static final int NUM_INTENTOS = 3;

	public static final int NUM_1000PTS = 1;
	public static final int NUM_250PTS = 5;
	public static final int NUM_50PTS = 8;
	public static final int NUM_MULTIPLICA_POR_2 = 1;
	public static final int NUM_ESPECIAL = 2;
	public static final int NUM_VACIA = 8;

	public static final Random random = new Random();

	private int intentos;
	private Casilla[] casillas;
	private int puntuacion;

	/**
	 * Constructor de la clase TableroJuego que comenzar� estableciendo la
	 * puntuaci�n del usuario en 0; los intentos en 3; y el array de casillas estar�
	 * formado por 25 objetos Casilla.
	 */
	public TableroJuego() {
		puntuacion = 0;
		intentos = NUM_INTENTOS;
		casillas = new Casilla[DIMENSION_TABLERO];
	}

	/**
	 * Inicializa el tablero de juego con los mismos criterios que en el constructor
	 * pero adem�s generar� las casillas y se encargar� de imprimirlas por consola.
	 */
	public void inicializar() {
		puntuacion = 0;
		intentos = NUM_INTENTOS;
		casillas = new Casilla[DIMENSION_TABLERO];
		generarCasillas();
		imprimirCasillas();
	}

	/**
	 * Imprime las casillas por consola de forma que se pueda facilitar la ubicaci�n
	 * de cada tipo de casilla y no se dificulte el uso de la aplicaci�n de cara a
	 * las pruebas.
	 */
	private void imprimirCasillas() {
		int indiceCasilla = 0;
		for (int i = 0; i < casillas.length; i++) {
			indiceCasilla = i + 1;
			System.out.println("Casilla " + indiceCasilla + ": " + casillas[i].getNombre());
		}
		System.out.println("\n*******************************************************************\n");
	}

	/**
	 * Genera las casillas con los criterios establecidos inicialmente para el
	 * n�mero de casillas de cada tipo (seteado en las constantes).
	 */
	private void generarCasillas() {
		generaPuntos(1000, NUM_1000PTS);
		generaPuntos(250, NUM_250PTS);
		generaPuntos(50, NUM_50PTS);
		generaMultiplicador(NUM_MULTIPLICA_POR_2);
		generaEspeciales(NUM_ESPECIAL);
		generaVacias();
	}

	/**
	 * Genera las casillas de puntuaci�n de manera aleatoria indic�ndole los puntos
	 * que se desean al igual que el n�mero de casillas.
	 * 
	 * @param puntos   Los puntos que tendr�n las casillas a generar
	 * @param aGenerar El n�mero de casillas de puntuaci�n a generar
	 */
	private void generaPuntos(int puntos, int aGenerar) {
		while (aGenerar > 0) {
			int posicion = random.nextInt(DIMENSION_TABLERO);
			if (casillas[posicion] == null) {
				casillas[posicion] = new PuntuacionCasilla(puntos);
				aGenerar--;
			}
		}
	}

	/**
	 * Genera las casillas de multiplicador x2 indic�ndole el n�mero de �stas.
	 * 
	 * @param aGenerar El n�mero de casillas de multiplicador x2 a generar
	 */
	private void generaMultiplicador(int aGenerar) {
		while (aGenerar > 0) {
			int posicion = random.nextInt(DIMENSION_TABLERO);
			if (casillas[posicion] == null) {
				casillas[posicion] = new Multiplica2();
				aGenerar--;
			}
		}
	}

	/**
	 * Genera las casillas especiales indic�ndole el n�mero de �stas.
	 * 
	 * @param aGenerar El n�mero de casillas especiales a generar
	 */
	private void generaEspeciales(int aGenerar) {
		while (aGenerar > 0) {
			int posicion = random.nextInt(DIMENSION_TABLERO);
			if (casillas[posicion] == null) {
				casillas[posicion] = new EspecialCasilla();
				aGenerar--;
			}
		}
	}

	/**
	 * Genera las casillas vac�as en funci�n de los huecos en el tablero restantes
	 * sin generar.
	 */
	private void generaVacias() {
		for (int i = 0; i < DIMENSION_TABLERO; i++) {
			if (casillas[i] == null) {
				casillas[i] = new VaciaCasilla();
			}
		}
	}

	/**
	 * Getter de los intentos.
	 * 
	 * Este m�todo devuelve el valor que tiene el entero para el atributo
	 * 'intentos'.
	 * 
	 * @return Los intentos
	 */
	public int getIntentos() {
		return this.intentos;
	}

	/**
	 * Incrementa los puntos del jugador a�adi�ndole los indicados como par�metro.
	 * Adem�s, se notificar� a todos los observadores registrados sobre esta clase
	 * Observable.
	 * 
	 * @param puntuacion2 La puntuaci�n a incrementar
	 */
	public void incPuntos(int puntuacion2) {
		this.puntuacion += puntuacion2;
		notificarObservadores();
	}

	/**
	 * Resta un intento durante el juego. Adem�s, se notificar� a todos los
	 * observadores registrados sobre esta clase Observable.
	 */
	public void restarIntento() {
		if (intentos - 1 >= 0) {
			this.intentos -= 1;
			notificarObservadores();
		}
	}

	/**
	 * Getter de la puntuaci�n.
	 * 
	 * Este m�todo devuelve el valor que tiene el entero para el atributo
	 * 'puntuacion'.
	 * 
	 * @return La puntuacion
	 */
	public int getPuntuacion() {
		return this.puntuacion;
	}

	/**
	 * Getter del vector de casillas.
	 * 
	 * Este m�todo devuelve el valor que tiene el vector para el atributo
	 * 'casillas'.
	 * 
	 * @return El vector de casillas
	 */
	public Casilla getCasilla(int posicion) {
		return casillas[posicion];
	}

	/**
	 * Descubre la casilla indicada actualizando el estado de juego.
	 * 
	 * @param posicion La posici�n de la casilla a descubrir
	 */
	public void descubrirCasilla(int posicion) {
		casillas[posicion].actualizar(this);
	}
}
