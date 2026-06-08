package modelo;
 
/*
 * Visitante concreto: muestra los propiedades especificas de cada tipo de hechizo
 */
public class VisitorDetalles implements VisitorHechizo {
	
	//Muestra la duracion de quemadura del hechizo de Fuego
	@Override
	public void visitar(HechizoDeFuego hechizo) {
		System.out.println("  [Fuego] Duracion quemadura: " + hechizo.getDuracionQuemadura() + " turno(s)");
	}
	//Muestra la mejora de defensa del hechizo de tierra
	@Override
	public void visitar(HechizoDeTierra hechizo) {
		System.out.println("  [Tierra] Mejora de defensa: " + hechizo.getMejoraDefensa());
	}
	//Muestra el stun y la cantidad de plantas del hechizo de Planta
	@Override
	public void visitar(HechizoDePlanta hechizo) {
		System.out.println("  [Planta] Stun: " + hechizo.getDuracionStun() + " turno(s) | Plantas involucradas: " + hechizo.getCantPlantas());
	}
	//Muestra el heal y la presion del agua del hechizo de agua
	@Override
	public void visitar(HechizoDeAgua hechizo) {
		System.out.println("  [Agua] Heal: " + hechizo.getCantidadHeal() + " | Presion del agua: " + hechizo.getPresionDelAgua());
	}
}
