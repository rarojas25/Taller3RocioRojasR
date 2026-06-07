package sistema;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.*;

import modelo.Hechizo;

public class Sistema {
	private ArrayList<Mago>	listaMagos;
	private ArrayList<Hechizo> listaHechizos;
	
	private static final String RUTA_MAGOS = "Magos.txt";
	private static final String RUTA_HECHIZOS = "Hechizos.txt";
	
	public Sistema() {
		listaMagos = new ArrayList<>();
		listaHechizos = new ArrayList<>();
		}
	
		public void cargaDatos() {
			cargarHechizos();
			cargarMagos();
		}
		public void cargarHechizos() {
			File archivo = new File(RUTA_HECHIZOS);
			if(!archivo.exists()) {
				System.out.println(" (Advertencia: No se encontro " + RUTA_HECHIZOS + ", se iniciara vacio)");
				return;
			}
			try (Scanner sc = new Scanner(archivo, "UTF-8")){
				while(sc.hasNextLine()) {
					String linea = sc.nextLine().trim();
					
					if(linea.isEmpty() || linea.startsWith("NombreHechizo")) {
						continue;
					}
					String[] partes = linea.split(";");
					if(partes.length < 4) continue;
					
					String nombre = partes[0].trim();
					String tipo = partes[1].trim();
					double danio = Double.parseDouble(partes[2].trim());
					String propiedadExtra = partes[3].trim();
					
					if(tipo.equalsIgnoreCase("Fuego")) {
						int duracionQuemadura = Integer.parseInt(propiedadExtra);
						listaHechizos.add(new HechizoDeFuego(nombre, danio, duracionQuemadura));
					
					}else if(tipo.equalsIgnoreCase("Tierra") || tipo.equalsIgnoreCase("Roca")) {
						double mejDefensa = Double.parseDouble(propiedadExtra);
						listaHechizos.add(new HechizoDeTierra(nombre, danio, mejDefensa));
					
					}else if(tipo.equalsIgnoreCase("Planta")) {
						String[] extras = partes[3].split(",");
						int durStun = Integer.parseInt(extras[0].trim());
						int cantPlantas = Integer.parseInt(extras[1].trim());
						listaHechizos.add(new HechizoDePlanta(nombre, danio, durStun, cantPlantas));		
					}else if(tipo.equalsIgnoreCase("Agua")) {
						
						String[] extras = partes[3].split(",");
						double cantHeal = Double.parseDouble(extras[0].trim());
						double presionAgua = Double.parseDouble(extras[1].trim());
						listaHechizos.add(new HechizoDeAgua(nombre, danio, cantHeal, presionAgua));
					}
				}
				
				}catch(Exception e) {
					System.out.println("Error critico al parsear Hechizos.txt: " + e.getMessage());				
				}
			System.out.println(" --> " + listaHechizos.size() + " hechizos cargados.");
		}
		
		private void cargarMagos() {
			try {
				Scanner sc = new Scanner(new File(RUTA_MAGOS), "UTF-8");
				while (sc.hasNextLine()) {
					String linea = sc.nextLine().trim();
					if(linea.isEmpty()) continue;
					
					String[] partes = linea.split(";");
					String nombreMago = partes[0].trim();
					Mago mago = new Mago(nombreMago); 
					
					if (partes.length > 1 && !partes[1].trim().isEmpty()) {
						String[] nombresHechizos = partes[1].split("\\|");//verificar
						for(String nh : nombresHechizos) {
							Hechizo h = buscarHechizo(nh.trim());
							if(h != null) {
								mago.agregarHechizo(h);
							}else {
								System.out.println(" [AVISO] Hechizo ¨" + nh.trim() + "¨ no encontrado en catalogo (mago: " + nombreMago + ").");
								
							}
						}
					}
					listaMagos.add(mago);
				}
				sc.close();
			}catch(FileNotFoundException e) {
				System.out.println(" [AVISO] Magos.txt no encontrado. Se iniciara sin magos.");
			}
		}
		
		public void guardarDatos() {
			guardarHechizos();
			guardarMagos();
		}
		public void guardarHechizos() {
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RUTA_HECHIZOS), "UTF-8"));
				for(Hechizo h : listaHechizos) {
					bw.write(h.toArchivoString());
					bw.newLine();
				}
				bw.close();
			}catch(IOException e) {
				System.out.println(" [ERROR] No se pudo guardar Hechizos.txt: " + e.getMessage());
			}
		}
		public void guardarMagos() {
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RUTA_MAGOS), "UTF-8"));
				for (Mago m : listaMagos) {
					bw.write(m.toArchivoString());
					bw.newLine();
				}
				bw.close();
			}catch(IOException e) {
				System.out.println(" [ERROR] No se pudo guardar Magos.txt: " + e.getMessage());
			}
		}
		public Hechizo buscarHechizo(String nombre) {
			for(Hechizo h: listaHechizos) {
				if(h.getNombre().equalsIgnoreCase(nombre)) {
					return h;
				}
			}
			return null;
		}
		
		public Mago buscarMago(String nombre) {
			for(Mago m : listaMagos) {
				if(m.getNombre().equalsIgnoreCase(nombre)) {
					return m;
				}
			}
			return null;
		}
		
		public boolean agregarMago(Mago mago) {
			if(buscarMago(mago.getNombre()) != null) {
				System.out.println("  Error: Ya existe un mago llamado ´" + mago.getNombre() + "´." );
				return false;
			}
			listaMagos.add(mago);
			guardarMagos();
			return true;
		}
		
		public boolean modificarMago(String nombreOriginal, String nuevoNombre, ArrayList<String> nombresHechizos) {
			Mago mago = buscarMago(nombreOriginal);
			if(mago == null) {
				System.out.println(" Error: No se encontro el mago ¨" + nombreOriginal + "¨.");
				return false;
			}
			mago.setNombre(nuevoNombre);
			mago.getHechizos().clear();
			
			for(String nh: nombresHechizos) {
				Hechizo h = buscarHechizo(nh.trim());
				if(h != null) {
					mago.agregarHechizo(h);
				}else {
					System.out.println(" [AVISO] Hechizo ¨" + nh + "¨ no encontrado, se omite.");
				}
			}
			guardarMagos();
			return true;
			}
		
			public boolean eliminarMago(String nombre) {
				Mago mago = buscarMago(nombre);
				if(mago == null) {
					System.out.println(" Error: No se encontro el mago ´" + nombre + "´.");
					return false;
				}
				listaMagos.remove(mago);
				guardarMagos();
				return true;
			}
			
			public boolean agregarHechizo(Hechizo hechizo) {
				if(buscarHechizo(hechizo.getNombre()) != null){
					System.out.println(" Error: Ya existe un hechizo llamado ´" + hechizo.getNombre() + "´.");
					return false;
				}
				listaHechizos.add(hechizo);
				guardarHechizos();
				return true;
			}
			
			public boolean modficarHechizoBase(String nombreOriginal, String nuevoNombre, double nuevoDanio) {
				Hechizo h = buscarHechizo(nombreOriginal);
				if(h == null)return false;
				
				h.setNombre(nuevoNombre);
				h.setDanio(nuevoDanio);
				
				for(Mago mago : listaMagos) {
					for(Hechizo hechizoMago : mago.getHechizos()) {
						if(hechizoMago.getNombre().equalsIgnoreCase(nombreOriginal)) {
							hechizoMago.setNombre(nuevoNombre);
							hechizoMago.setDanio(nuevoDanio);
						}
					}
				}
				guardarDatos();
				return true;
			}
			
			public boolean eliminarHechizo(String nombre) {
				Hechizo hechizo = buscarHechizo(nombre);
				if(hechizo == null) {
					System.out.println(" Error: No se encontro el hechizo ¨" + nombre + "¨.");
					return false;
				}
				for(Mago m : listaMagos) {
					m.getHechizos().remove(hechizo);
				}
				listaHechizos.remove(hechizo);
				guardarHechizos();
				guardarMagos();
				return true;
			}
			
			public ArrayList<Hechizo> getTop10Hechizos(){
				ArrayList<Hechizo> copia = new ArrayList<>(listaHechizos);
				ordenarHechizosDesc(copia);
				ArrayList<Hechizo> resultado = new ArrayList<>();
				
				int limite = Math.min(10, copia.size());
				for(int i = 0; i < limite; i++) {
					resultado.add(copia.get(i));
				}
				return resultado;
			}
			
			public ArrayList<Mago> getTop3Magos(){
				ArrayList<Mago> copia = new ArrayList<>(listaMagos);
				ordenarMagosDesc(copia);
				ArrayList<Mago> resultado = new ArrayList<>();
				
				int limite = Math.min(3, copia.size());
				for(int i = 0 ; i < limite; i++) {
					resultado.add(copia.get(i));
				}
				return resultado;
			}
			
			public void ordenarHechizosDesc(ArrayList<Hechizo> lista) {
				
				for(int i = 0; i < lista.size() - 1 ; i++) {
					for(int j = 0; j < lista.size() - 1 - i; j++) {		
						if(lista.get(j).calcularPuntaje() < lista.get(j + 1).calcularPuntaje()) {
							Hechizo temp = lista.get(j);
							lista.set(j, lista.get(j + 1));
							lista.set(j + 1, temp);
						}
					}
				}
			}
			
			public void ordenarMagosDesc(ArrayList<Mago> lista) {
				
				for(int i = 0; i < lista.size() - 1 ; i++) {
					for(int j = 0; j < lista.size() - 1 - i; j++) {
						if(lista.get(j).calcularPuntaje() < lista.get(j + 1).calcularPuntaje()) {
							Mago temp = lista.get(j);
							lista.set(j, lista.get(j + 1));
							lista.set(j + 1, temp);
						}
					}
				}
			}

			public ArrayList<Mago> getListaMagos() {
				return listaMagos;
			}

			public ArrayList<Hechizo> getListaHechizos() {
				return listaHechizos;
			}
			
		}