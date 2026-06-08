///Integrante 1: Rocío Azucena Rojas Robledo - 21.694.049-0 - ICCI - rarojas25
//https://github.com/rarojas25/Taller3RocioRojasR 



package main;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.*;
import sistema.Sistema;

/*
 * Clase Main - Punto de entrada del programa
 * Contiene el menu principal y los dos paneles_
 * - Administrador: CRUD de magos y hechizos
 * - Analista: reportes y ranking
 * 
 * Usa el programa visitor (VisitorDetalles) para mostrar las propiedades
 * especificas de cada tipo de hechizo
 */
public class Main{

		static Scanner sc = new Scanner(System.in);
		static Sistema sistema = new Sistema();
		
		public static void main(String[] args) {
			try {
				System.setOut(new PrintStream(System.out, true, "UTF-8"));
				
			}catch (Exception e) {
				
			}
			System.out.println("======================================");
			System.out.println("   MUNDO MAGICO - CARGANDO...");
			System.out.println("======================================");
			sistema.cargaDatos();
			
			boolean salir = false;
			while(!salir) {
				System.out.println("======================================");
				System.out.println("  MUNDO MAGICO - MENU PRINCIPAL");
				System.out.println("======================================");
				System.out.println("1. Panel Administrador");
				System.out.println("2. Panel Analista");
				System.out.println("0. Salir");
				System.out.println("Seleccione: ");
				
				int opcion = leerEntero();
				switch(opcion) {
				case 1: 
					menuAdministrador();
					break;
				case 2:
					menuAnalista();
					break;
				case 0:
					salir = true;
					System.out.println("Hasta luego!");
					break;
				default: 
					System.out.println("Opcion invalida.");
				}
			}
			sc.close();
		}
		//Panel Administrador
		
		static void menuAdministrador() {
			boolean volver = false;
			while(!volver) {
				System.out.println("-------------------------------");
				System.out.println("    PANEL ADMINISTRADOR");
				System.out.println("--------------------------------");
				System.out.println("1. Agregar Mago");
				System.out.println("2. Modidicar Mago");
				System.out.println("3. Eliminar Mago");
				System.out.println("4. Agregar Hechizo");
				System.out.println("5. Modifificar Hechizo");
				System.out.println("6. Eliminar Hechizo"); 
				System.out.println("0. Volver");
				System.out.println("Seleccione: ");
				
				int op = leerEntero();
				switch(op) {
				case 1:
					agregarMago();
					break;
				case 2:
					modificarMago();
					break;
				case 3:
					eliminarMago();
					break;
				case 4:
					agregarHechizo();
					break;
				case 5:
					modificarHechizo();
					break;
				case 6:
					eliminarHechizo();
				case 0:
					volver = true;
					break;
				default: 
					System.out.println("Opcion invalida.");
				
				}
			}
		}
		
		// Agregar un nuevo mago pidiendo nombres y hechizos del catalogo
		static void agregarMago()	{
			System.out.println("--- Agregar Nuevo Mago ---");
			String nombre = sc.nextLine().trim();
			
			if(nombre.isEmpty()) {
				System.out.println("El nombre no puede estar vacio.");
				return;
			}
			if(sistema.buscarMago(nombre) != null) {
				System.out.println("Ya existe un mago con ese nombre.");
				return;
			}
			
			Mago nuevoMago = new Mago(nombre);
			System.out.println("Hechizos disponibles: ");
			listarHechizosSimple();
			
			System.out.println("Escriba los hechizos del mago (´fin´ para terminar):");
			
			while(true) {
				System.out.println("  Hechizo: ");
				String nh = sc.nextLine().trim();
				
				if(nh.equalsIgnoreCase("fin"))break;
				if(nh.isEmpty())continue;
				
				if(nh.isEmpty()) continue;
				
				Hechizo h = sistema.buscarHechizo(nh);
				if(h != null) {
					nuevoMago.agregarHechizo(h);
					System.out.println("  --> " + h.getNombre() + "agregado.");
				}else {
					System.out.println("  --> No se encontro en el catalogo.");
				}
			}
			
			if(sistema.agregarMago(nuevoMago)) {
				System.out.println("Mago " + nombre + " agregado con " + nuevoMago.getHechizos().size() + " hechizo(s). Archivo guardado.");
			
			}
		}	
		/*
		 * Modificar el nombre y la lista de hechizos de un mago exigente
		 */
		
		static void modificarMago() {
			System.out.println(" --- Modificar Mago ---");
			System.out.println("Nombre del mago a modificar: ");
			String nombre = sc.nextLine().trim();
			
			Mago mago = sistema.buscarMago(nombre);
			if(mago == null) {
				System.out.println("Mago no encontrado");
				return;
			}
			
			System.out.println("Mago encontrado: " + mago);
			System.out.println("Nuevo nombre (Enter para mantener ´" + mago.getNombre() + "´):");
			String nuevoNombre = sc.nextLine().trim();
			
			if(nuevoNombre.isEmpty()) nuevoNombre = mago.getNombre();
			
			System.out.println("Hechizos actuales: ");
			
			for(Hechizo h : mago.getHechizos()) {
				System.out.println("  - " + h.getNombre() + " [" + h.getTipo() + "]");
			}
			
			System.out.println("Hechizos disponibles en catalogo: ");
			listarHechizosSimple();
			
			System.out.println("Nueva lista de hechizos (reemplaza la actual, ´fin´ para terminar): ");
			ArrayList<String> nuevosHechizos = new ArrayList<>();
			
			while(true) {
				System.out.println(" Hechizo: ");
				String nh = sc.nextLine().trim();
				if(nh.equalsIgnoreCase("fin"))break;
				if(!nh.isEmpty()) nuevosHechizos.add(nh);
			}
			
			if(sistema.modificarMago(nombre, nuevoNombre, nuevosHechizos)) {
				System.out.println("Mago modificado. Archivo guardado.");
			}
		}
		/*
		 * Elimina un mago del sistema tras confirmacion
		 */
		static void eliminarMago() {
			System.out.println(" --- Eliminar Mago ---");
			System.out.println("Nombre del mago a eliminar: ");
			String nombre = sc.nextLine().trim();
			
			Mago mago = sistema.buscarMago(nombre);
			if(mago == null) {
				System.out.println("Mago no encontrado.");
				return;
			}
			System.out.println("Mago a eliminar: " + mago.getNombre());
			System.out.println("Confirma? (s/n)");
			
			if(sc.nextLine().trim().equalsIgnoreCase("s")) {
				if(sistema.eliminarMago(nombre)) {
				System.out.println("Mago eliminado. Archivo guardado.");
			}
		}else {
			System.out.println("Operacion cancelada.");
			}
		}
		
		/*
		 * Agrega un nuevo hechizo al catalogo
		 * Pide el nombre, tipo y propiedades segun el tipo elegido
		 */
		static void agregarHechizo() {
			System.out.println(" --- Agregar Hechizo ---");
			System.out.println("Nombre del hechizo: ");
			String nombre = sc.nextLine().trim();
			
			if(nombre.isEmpty()) {
				System.out.println("El nombre no puede estar vacio.");
				return;
			}
			if(sistema.buscarHechizo(nombre) != null) {
				System.out.println("Ya existe un hechizo con ese nombre.");
				return;
			}
			Hechizo nuevo = pedirDatosHechizo(nombre);
			if(nuevo == null) return;
			
			if(sistema.agregarHechizo(nuevo)) {
				System.out.printf("Hechizo agregado. Puntaje: %.2f. Archivo guardado.%n" , nuevo.calcularPuntaje());
				
			}
		}
		/*
		 * Modifica un hechizo existente
		 * Usa el Visitor para mostrar sus propiedades actuales antes de editar
		 * Permite cambia nombre, danio y las propiedades especificas del tipo
		 */
		static void modificarHechizo() {
			System.out.println(" --- Modificar Hechizo ---");
			System.out.println("Nombre del hechizo a modificar: ");
			String nombre = sc.nextLine().trim();
			
			Hechizo hechizo = sistema.buscarHechizo(nombre);
			if(hechizo == null) {
				System.out.println("Hechizo no encontrado.");
				return; 
			}
			
			System.out.println("Propiedades actuales: ");
			System.out.println(" " + hechizo);
			
			VisitorDetalles vistor = new VisitorDetalles();
			hechizo.aceptar(vistor);
			System.out.println("Nuevo nombre (Enter para mantener ´" + hechizo.getNombre() + "´): ");
			
			String nuevoNombre = sc.nextLine().trim();
			if(nuevoNombre.isEmpty()) nuevoNombre = hechizo.getNombre();
			
			System.out.println("Nuevo daño (actual: " + hechizo.getDanio() + "): ");
			double nuevoDanio = leerDecimal();
			
			sistema.modficarHechizoBase(nombre, nuevoNombre, nuevoDanio);
			if(hechizo instanceof HechizoDeFuego) {
				
				HechizoDeFuego hf = (HechizoDeFuego) hechizo;
				System.out.println("Nueva duracion quemadura (actual: " + hf.getDuracionQuemadura() + "): ");
				hf.setDuracionQuemadura(leerEntero());
				
			}else if(hechizo instanceof HechizoDeTierra) {
				
				HechizoDeTierra ht = (HechizoDeTierra) hechizo;
				System.out.println("Nueva mejora de defensa (actual: " + ht.getMejoraDefensa() + "): ");
				ht.setMejoraDefensa(leerDecimal());
				
			}else if(hechizo instanceof HechizoDePlanta) {
				HechizoDePlanta hp = (HechizoDePlanta) hechizo;
				System.out.println("Nueva Duracion stun (actual: " + hp.getDuracionStun() + "): ");
				hp.setDuracionStun(leerEntero());
				System.out.println("Nueva cantidad de plantas (actual: " + hp.getCantPlantas() + "): ");
				hp.setCantPlantas(leerEntero());
				
			}else if(hechizo instanceof HechizoDeAgua) {
				HechizoDeAgua ha = (HechizoDeAgua) hechizo;
				System.out.println("Nueva cantidad de heal (actual: " + ha.getCantidadHeal() + "): ");
				ha.setCantidadHeal(leerDecimal());
				System.out.println("Nueva presion del agua (actual: " + ha.getPresionDelAgua() + "): ");
				ha.setPresionDelAgua(leerDecimal());
			}
			sistema.guardarDatos();
			System.out.printf("Hechizo modificado. Nuevo puntaje: %.2f. Archivos guardados.%n" , hechizo.calcularPuntaje());		
		}
		
		/*
		 * Elimina un hechizo del catalogo y de todos los magos que lo tenian
		 */
		static void eliminarHechizo() {
			System.out.println(" --- Eliminar Hehcizo ----");
			System.out.println("Nombre del hechizo a eliminar: ");
			String nombre = sc.nextLine().trim();
			
			Hechizo hechizo = sistema.buscarHechizo(nombre);
			if(hechizo == null) {
				System.out.println("Hechizo no encontrado.");
				return;
			}
			
			System.out.println("Hehcizo a eliminar: " + hechizo.getNombre() + "[" + hechizo.getTipo() + "]");
			System.out.println("ATENCION : Se eliminara de todos los magos que lo tengan.");
			System.out.println("Confirma? (s/n): ");
			
			if(sc.nextLine().trim().equalsIgnoreCase("s")) {
				if(sistema.eliminarHechizo(nombre)) {
					System.out.println("Hechizo eliminado. Archivos guardados.");
				}
				
			}else {
				System.out.println("Operacion cancelada.");
			}
		}
		
		/*
		 * Panel analista
		 */
		static void menuAnalista() {
			boolean volver = false;
			while(!volver) {
				System.out.println("--------------------------");
				System.out.println("   PANEL ANALISTA");
				System.out.println("---------------------------");
				System.out.println("1. Top 10 Mejores Hechizos");
				System.out.println("2. Top 3 Mejores Magos");
				System.out.println("3. Mostrar todos los Hechizos");
				System.out.println("4. Mostrar todos los Magos");
				System.out.println("5. Mostrar Hechizos con puntuacion");
				System.out.println("6. Mostrar Magos con puntuacion");
				System.out.println("0. Volver");
				System.out.println("Seleccione: ");
				
				int op = leerEntero();
				switch(op) {
				case 1:
					mostrarTop10Hechizos();
					break;
				case 2:
					mostrarTop3Magos();
					break;
				case 3:
					mostrarTodosHechizos();
					break;
				case 4:
					mostrarTodosMagos();
					break;
				case 5:
					mostrarHechizosConPuntaje();
					break;
				case 6:
					mostrarMagosConPuntaje();
					break;
				case 0:
					volver = true;
					break;
				default:
					System.out.println("Opcion invalida.");
				}
			}
		}
		/*
		 * Muestra el top 10 hechizos con mayor puntaje
		 */
		static void mostrarTop10Hechizos() {
			System.out.println(" ========== TOP 10 MEJORES HECHIZOS==========");
			ArrayList<Hechizo> top = sistema.getTop10Hechizos();
			if(top.isEmpty()) {
				System.out.println("No hay hechizos.");
				return;
			}
			
			System.out.printf("%-4s %-25s %-7s %10s%n" , "Pos" , "Nombre" , "Tipo" , "Puntaje");
			System.out.println("--------------------------------------------------");
			for(int i = 0; i < top.size(); i++) {
				Hechizo h = top.get(i);
				System.out.printf("%-4d %-25s %-7s %10.2f%n" , i + 1 , h.getNombre() , h.getTipo() , h.calcularPuntaje());
			}
		}
		
		/*
		 * Muestra el top 3 magos con mayor puntaje total
		 */
		static void mostrarTop3Magos() {
			System.out.println("========== TOP 3 MEJORES MAGOS ==========");
			ArrayList<Mago> top = sistema.getTop3Magos();
			if(top.isEmpty()) {
				System.out.println("No hay magos.");
				return;
			}
			for(int i = 0; i < top.size(); i++) {
				Mago m = top.get(i);
				System.out.printf("%d. %-25s | Hechizos: %2d | Puntaje %.2f%n" , i + 1, m.getNombre(), m.getHechizos().size(), m.calcularPuntaje());
				
				
			}
		}
		/*
		 * Muestra todos los hechizos con sus propiedades especificas
		 * Utiliza el patron Visitor (VisitorDetalles) para mostrar
		 * las propiedades de cada tipo
		 */
		static void mostrarTodosHechizos() {
			System.out.println("========== TODOS LOS HECHIZOS ==========}");
			ArrayList<Hechizo> lista = sistema.getListaHechizos();
			
			if(lista.isEmpty()) {
				System.out.println("Catalogo vacio.");
				return;
			}
			VisitorDetalles visitor = new VisitorDetalles();
			
			for(Hechizo h : lista) {
				System.out.println(" " + h.getNombre() + "[" + h.getTipo() + "] - Daño: " + h.getDanio());
				h.aceptar(visitor);
			}
			System.out.println("Total: " + lista.size() + "hechizo(s).");
			}
		
			/*
			 * Muestra todos los magos con sus hechizos
			 */
			static void mostrarTodosMagos() {
				System.out.println("========= TODOS LOS MAGOS =========");
				ArrayList<Mago> lista = sistema.getListaMagos();
				if(lista.isEmpty()) {
					System.out.println("No hay magos.");
					return;
				}
				for(Mago m : lista) {
					System.out.println("Mago: " + m.getNombre() + " (" + m.getHechizos().size() + " hechizo(s))");
					for(Hechizo h : m.getHechizos()) {
						System.out.println(" --> " + h.getNombre() + " [" + h.getTipo() + "]");
					}
				}
				System.out.println("Total: " + lista.size() + " mago(s).");
				}
				
				/*
				 * Muestra todos los hechizos con su puntaje calculado
				 */
				static void mostrarHechizosConPuntaje() {
					System.out.println("========= HECHIZOS CON PUNTUACION =========");
					ArrayList<Hechizo> lista = sistema.getListaHechizos();
					if(lista.isEmpty()) {
						System.out.println("Catalogo vacio.");
						return;
					}
					System.out.printf("%-25s %-7s %8s %12s%n", "Nombre", "Tipo", "Danio", "Puntaje");
					System.out.println("---------------------------------------------------------");
					for(Hechizo h : lista) {
						System.out.printf("%-25s %-7s %8.1f %12.2f%n" , h.getNombre(), h.getTipo(), h.getDanio(), h.calcularPuntaje());
					}
					System.out.println("Total: " + lista.size() + " hechizo(s).");
				}
				/*
				 * Muestra todos los magos con su puntaje total calculado
				 */
				static void mostrarMagosConPuntaje() {
					System.out.println("========= MAGOS CON PUNTUACION =========");
					ArrayList<Mago> lista = sistema.getListaMagos();
					if(lista.isEmpty()) {
						System.out.println("No se magos.");
						return;
					}
					System.out.printf("%-25s %10s %15s%n", "Nombre", "Hechizos", "Puntaje Total");
					System.out.println("-------------------------------------------------------");
					for(Mago m : lista) {
						System.out.printf("%-25s %10d %15.2f%n", m.getNombre(), m.getHechizos().size(), m.calcularPuntaje());
					}
					System.out.println("Total: " + lista.size() + " mago(s).");
				}
				

				/*
				 * Pide al usuario los datos de un hechizo nuevo: tipo, danop y propiedades.
				 * Se reutiliza en agregarHechizo()
				 */
				static Hechizo pedirDatosHechizo(String nombre) {
					System.out.println(" Tipos: ");
					System.out.println("1. Fuego --> Daño * DuracionQuemadura");
					System.out.println("2. Tierra --> (Daño * MejoraDefensa) / 2");
					System.out.println("3. Planta --> Daño + (DurStun * CantPlantas)");
					System.out.println("4. Agua --> (Daño + CantHeal + Presion) * 2");
					System.out.println("Seleccione tipo: ");
					
					int tipo = leerEntero();
					System.out.println(" Daño base: ");
					double danio = leerDecimal();
					
					switch(tipo) {
					case 1: {
						System.out.println(" Duracion quemadura: ");
						return new HechizoDeFuego(nombre, danio, leerEntero());
					}
					case 2: {
						System.out.println(" Mejora de defensa: ");
						return new HechizoDeTierra(nombre,danio,leerDecimal());
					}
					case 3: {
						System.out.println(" Duracion stun: ");
						int ds = leerEntero();
						System.out.println(" Cantidad de plantas: ");
						return new HechizoDePlanta(nombre, danio, ds, leerEntero());
					}
					case 4:{
						System.out.println(" Cantidad de heal: ");
						double heal = leerDecimal();
						System.out.println(" Presion del agua: ");
						return new HechizoDeAgua(nombre, danio, heal, leerDecimal());
					}
					default:
						System.out.println("Tipo invalido. Operacion cancelada.");
						return null;
					}
				}
				/*
				 * Lista nombre y tipo de cada hechizo del catalogo
				 */
				static void listarHechizosSimple() {
					ArrayList<Hechizo> lista = sistema.getListaHechizos();
					if(lista.isEmpty()){
						System.out.println(" (catalogo vacio)");
						return;
					}
					for(Hechizo h : lista) {
						System.out.println("  - " + h.getNombre() + " [" + h.getTipo() + "]" );
					}
				}
				 /*
				  * Lee un entero de consola de forma segura
				  * Si el usuario escribe letras, vuelve a pedir sin caerse
				  */
				static int leerEntero() {
					while(true) {
						try {
							return Integer.parseInt(sc.nextLine().trim());
						}catch(NumberFormatException e) {
							System.out.println("Ingrese un numero entero: ");
						}
					}
				}
				/*
				 * Lee un decimal de consola de forma segura 
				 * Si el usuario escribe algo invalido, vuelve a pedir sin caerse
				 */
				static double leerDecimal() {
					while(true) {
						try {
							return Double.parseDouble(sc.nextLine().trim());
						}catch(NumberFormatException e) {
							System.out.println("Ingrese un numero valido: ");
					}
				}		
			}
		}
	