package modelo;

/*
 * Interfaz puntuable
 * Define el comportamiento que deben tener todos los elementos
 * que pueden calcular un puntaje en el mundo magico
 * Tanto los Hechizos como los Magos la implementan
 */
public interface Puntuable {
/*
 * Calcular el puntaje del elmento segun sus propiedades.
 * Cada clase que implemente esta interfaz define su propia formula
 */
	double calcularPuntaje();
}

