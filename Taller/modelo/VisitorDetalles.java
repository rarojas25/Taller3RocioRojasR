package modelo;

public class VisitorDetalles implements VisitorHechizo {
	
	@Override
	public void visitar(HechizoDeFuego hechizo) {
		System.out.println("  [Fuego] Duracion quemadura: " + hechizo.getDuracionQuemadura() + " turno(s)");
	}
	@Override
	public void visitar(HechizoDeTierra hechizo) {
		System.out.println("  [Tierra] Mejora de defensa: " + hechizo.getMejoraDefensa());
	}
	@Override
	public void visitar(HechizoDePlanta hechizo) {
		System.out.println("  [Planta] Stun: " + hechizo.getDuracionStun() + " turno(s) | Plantas involucradas: " + hechizo.getCantPlantas());
	}
	@Override
	public void visitar(HechizoDeAgua hechizo) {
		System.out.println("  [Agua] Heal: " + hechizo.getCantidadHeal() + " | Presion del agua: " + hechizo.getPresionDelAgua());
	}
}
