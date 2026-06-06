package modelo;

public class HechizoDeFuego extends Hechizo {
	private int duracionQuemadura;

	public HechizoDeFuego(String nombre, double danio, int duracionQuemadura) {
		super(nombre, "Fuego", danio);
		this.duracionQuemadura = duracionQuemadura;
	}

	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}

	public void setDuracionQuemadura(int duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}
	@Override
	public double calcularPuntaje() {
		return getDanio() * duracionQuemadura;
	}
	@Override
	public String toArchivoString() {
		return getNombre() + ";Fuego;" + formatNum(getDanio()) + ";" + duracionQuemadura;
	}
	@Override
	public void aceptar(VisitorHechizo visitor) {
		visitor.visitar(this);
	}
	@Override
	public String toString() {
		return super.toString() + " | DurQuemadura: " + duracionQuemadura; 
	}
}
