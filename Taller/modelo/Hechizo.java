package modelo;

/*
 * Clase abstracta Hechizo
 * Es la base de todos los tipos de hechizos del mundo magico
 * Contiene los atributos comunes: nombre, tipo y danio
 * 
 * Implementa puntuable: obliga a que cada subclase calcule su propio puntaje
 * Implementa el patron visitor: cada subclase acpeta un visitante
 * que ejecuta la operacion corrrecta segun el tipo
 */
public abstract class Hechizo implements Puntuable {
	private String nombre;
	private String tipo;
	private double danio;
	
	public Hechizo(String nombre, String tipo, double danio) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.danio = danio;
	}
	// Getters y Setters
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getDanio() {
		return danio;
	}
	public void setDanio(double danio) {
		this.danio = danio;
	}
	//Calcula el puntaje segun la formula del tipo de elemento
	
	@Override
	public abstract double calcularPuntaje();
	
	//Genera la linea que se guarda en el archivo Hechizos.txt
	public abstract String toArchivoString();
	
	/*
	 * Patron visitor: acepta un visitante y llama al metodo correcto
	 * Cada subclase llama a visitor.visitar(this) con su propio tipo
	 */
	public abstract void aceptar(VisitorHechizo visitor);
	
	/*
	 * formatea un numero double sin mostrar el ".0" innecesario
	 */
	protected static String formatNum(double valor) {
		if(valor == (long) valor) {
			return String.valueOf((long)valor);
		}
		return String.valueOf(valor);
	}
	@Override
	public String toString() {
		return String.format("% -25s | %-6s | Daño: %6.1f | Puntaje: %8.2f", nombre, tipo, danio, calcularPuntaje());
	}
}
