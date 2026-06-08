package modelo;

/*
 * Hechizo de tipo Planta
 * Propiedad extra: duracionStun y cantPlant
 */
public class HechizoDePlanta extends Hechizo {
	private int duracionStun;
	private int cantPlantas;
	
	public HechizoDePlanta(String nombre, double danio, int duracionStun, int cantPlantas) {
		super(nombre, "Planta", danio);
		this.duracionStun = duracionStun;
		this.cantPlantas = cantPlantas;
	}

	public int getDuracionStun() {
		return duracionStun;
	}

	public void setDuracionStun(int duracionStun) {
		this.duracionStun = duracionStun;
	}

	public int getCantPlantas() {
		return cantPlantas;
	}

	public void setCantPlantas(int cantPlantas) {
		this.cantPlantas = cantPlantas;
	}
	@Override
	public double calcularPuntaje() {
		return getDanio() + (duracionStun * cantPlantas);
	}
	@Override
	public String toArchivoString() {
		return getNombre() + ";Planta;" + formatNum(getDanio()) + ";" + duracionStun + "," + cantPlantas;
	}
	/*
	 * Patron Visitor: delega al visitante el comportamiento especifico de Planta
	 */
	@Override
	public void aceptar(VisitorHechizo visitor) {
		visitor.visitar(this);
	}
	
	@Override
	public String toString() {
		return super.toString() + " | DurStun: " + duracionStun + " | CantPlantas: " + cantPlantas;
	}
}
