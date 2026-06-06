package modelo;

public class HechizoDeAgua extends Hechizo {
	private double cantidadHeal;
	private double presionDelAgua;
	
	public HechizoDeAgua(String nombre, double danio, double cantidadHeal, double presionDelAgua) {
		super(nombre, "Agua", danio);
		this.cantidadHeal = cantidadHeal;
		this.presionDelAgua = presionDelAgua;
	}

	public double getCantidadHeal() {
		return cantidadHeal;
	}

	public void setCantidadHeal(double cantidadHeal) {
		this.cantidadHeal = cantidadHeal;
	}

	public double getPresionDelAgua() {
		return presionDelAgua;
	}

	public void setPresionDelAgua(double presionDelAgua) {
		this.presionDelAgua = presionDelAgua;
	}
	@Override
	public double calcularPuntaje() {
		return (getDanio() + cantidadHeal + presionDelAgua) * 2;
	}
	@Override
	public String toArchivoString() {
		return getNombre() + ";Agua;" + formatNum(getDanio()) + ";" + formatNum(cantidadHeal) + ";" + formatNum(presionDelAgua);
	}
	@Override
	public void aceptar(VisitorHechizo visitor) {
		visitor.visitar(this);
	}
	@Override
	public String toString() {
		return super.toString() + " | Heal: " + formatNum(cantidadHeal) + " | Presion: " + formatNum(presionDelAgua); 			
	}
}
