package modelo;

import java.util.ArrayList;

/*
 * Clase Mago
 * Representa un mago del mundo magica
 * Cada mago tiene a un nombre y una lista de hechizos que domina
 * 
 * Implementa Puntable: su puntaje en la suma de los puntajes
 * de todos los hechizos que tiene en su repertorio
 */
public class Mago implements Puntuable {
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	
	//constructor del mago
	public Mago(String nombre) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<>();
	}
	//Getter y Setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	
	/*
	 * Agrega un hechizo al repertorio del mago
	 */
	public void agregarHechizo(Hechizo hechizo) {
		hechizos.add(hechizo);
	}
	/*
	 * Elimina un hechizo del repertorio de mago
	 */
	public void eliminarHechizo(Hechizo hechizo) {
		hechizos.remove(hechizo);
	}
	 /*
	  * Calcular puntaje total del mago
	  * Se suman los puntajes de todos sus hechizos
	  */
	@Override
	public double calcularPuntaje() {
		double total = 0;
		for(Hechizo h : hechizos) {
			total += h.calcularPuntaje();
		}
		return total;
	}
	 /*
	  * Convierte el mago en formato del archivo Magos.txt para persistencia
	  * Formato: Nombre; Hechixos1|Hechizo2|Hechizo3
	  */
	
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


