package modelo;
 
/*
 * Interfaz del patron de diseño Visitor aplicado a los hechizos
 * Permite agregar nuevas operaciones sobre los distintos tipos de hechizos
 * sin necesidad de modificar las clases existentes
 * 
 * Cada clase concetra Visitante implementa un comportamiento especifico
 * para cada tipo de hechizo(Fuego, Tierra, Planta, Agua)
 */
public interface VisitorHechizo {
	void visitar(HechizoDeFuego hechizo);
	void visitar(HechizoDeTierra hechizo);
	void visitar(HechizoDePlanta hechizo);
	void visitar(HechizoDeAgua hechizo);
}
