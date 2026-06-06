package modelo;

public interface VisitorHechizo {
	void visitar(HechizoDeFuego hechizo);
	void visitar(HechizoDeTierra hechizo);
	void visitar(HechizoDePlanta hechizo);
	void visitar(HechizoDeAgua hechizo);
}
