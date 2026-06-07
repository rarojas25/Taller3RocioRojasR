package modelo;

import java.util.ArrayList;

public class Mago implements Puntuable {
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	
	public Mago(String nombre) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	
	public void agregarHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}
	
	public void eliminarHechizo(Hechizo hechizo) {
		hechizos.remove(hechizo);
	}
	
	@Override
	public double calcularPuntaje() {
		double total = 0;
		for(Hechizo h : hechizos) {
			total += h.calcularPuntaje();
		}
		return total;
	}
	
	public String toArchivoString() {
		if(hechizos.isEmpty()) {
			return nombre + ";";
		}
		StringBuilder sb = new StringBuilder(nombre + ";");
		for(int i = 0; i < hechizos.size(); i++) {
			sb.append(hechizos.get(i).getNombre());
			if(i < hechizos.size() - 1) {
				sb.append("|");
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return String.format("%-25s | Hechizos: %2d | Puntaje Total: %.2f", nombre, hechizos.size(), calcularPuntaje());
	}
	
}


