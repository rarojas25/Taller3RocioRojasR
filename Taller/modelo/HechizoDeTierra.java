package modelo;

public class HechizoDeTierra extends Hechizo {
	private double mejoraDefensa;

	public HechizoDeTierra(String nombre, double danio, double mejoraDefensa) {
		super(nombre, "Tierra", danio);
		this.mejoraDefensa = mejoraDefensa;
	}

	public double getMejoraDefensa() {
		return mejoraDefensa;
	}

	public void setMejoraDefensa(double mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}
	
	@Override
	public double calcularPuntaje() {
		return (getDanio() * mejoraDefensa) / 2;
	}
	
	@Override
	public String toArchivoString() {
		return getNombre() + ";Tierra;" + formatNum(getDanio()) + ";" + formatNum(mejoraDefensa);
	}
	
	@Override
	public void aceptar(VisitorHechizo visitor) {
		visitor.visitar(this);
	}
	
	@Override
	public String toString() {
		return super.toString() + " | MejoraDefensa: " + formatNum(mejoraDefensa);
	}
	
}
