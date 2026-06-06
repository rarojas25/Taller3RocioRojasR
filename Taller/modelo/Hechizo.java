package modelo;

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
	@Override
	public abstract double calcularPuntaje();
	public abstract String toArchivoString();
	
	public abstract void aceptar(VisitorHechizo visitor);
	
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
