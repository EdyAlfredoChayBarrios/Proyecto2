package src.Proyect2;

import java.awt.EventQueue;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MaindeProyecto2 {

	// Abrira los Archivos RandomAccessFile con su diferente caracteristica cada una
	RandomAccessFile DatosdeTabla = null;
	RandomAccessFile Sociedad = null;
	RandomAccessFile Cualidades = null;

	// En este paso creara una array list de sociedades
	public List<Sociedad> listadoSociedades = new ArrayList<>();
	public  String rutaOrigen="C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
	public  String direccionSociedad = "Sociedad.dat";
	public String direccionCualidades = "Cualidades.dat";
	public final int totalBytes = 90, bytesSociedad = 45, bytesCualidades = 42;
	Scanner sc = new Scanner(System.in);
	private final static String formatoFecha = "dd/MM/yyyy";
	static DateFormat formato = new SimpleDateFormat(formatoFecha);

	// metodo para acceder a los archivos si es que ya estan creados sino los crea
	boolean accederArchivo() {
		listadoSociedades = new ArrayList<>();
		boolean resultado = false;
		try {
			Sociedad = new RandomAccessFile(direccionSociedad, "rw");
			Cualidades = new RandomAccessFile(direccionCualidades, "rw");
			// tamano toma el valor de tama;o de la sociedad
			long tamano = Sociedad.length();
			// si el tamano es menor o igual a 0 entoces imprimira no existe registro
			if (tamano == 0) {
				System.out.println("NO EXISTE REGISTRO");
				resultado = false;
			//usamps else if para verifique tambien el tamano para poder ingresar 
			} 
			if (tamano >= bytesSociedad) {
				System.out.println("SE CARGARON TODOS LOS ARCHIVOS");
				System.out.println("");
				Sociedad.seek(0);
				Sociedad s = new Sociedad();

				while (tamano >= bytesSociedad) {
					s = new Sociedad();
					s.setIndice(Sociedad.readInt());
					byte[] bytNombre = new byte[30];
					Sociedad.read(bytNombre);
					s.setBytesNombre(bytNombre);
					s.setCantidad(Sociedad.readInt());
					s.setBytes(Sociedad.readInt());
					s.setPosition(Sociedad.readLong());
					Sociedad.readByte();
					tamano -= bytesSociedad;
					listadoSociedades.add(s);
				}
				
				for(Sociedad soc: listadoSociedades) {
					soc.setCualidades(new ArrayList<Cualidades>());
					Cualidades.seek(0);
					while (true) {
						try {
							Cualidades c = new Cualidades();
							c.setIndicec(Cualidades.readInt());
							byte[] bytNombrec = new byte[30];
							Cualidades.read(bytNombrec);
							c.setBytesNombrec(bytNombrec);
							c.setValordeDato(Cualidades.readInt());
							c.setTamano(Cualidades.readInt());
							c.setNombredeDato();
							if(soc.getIndice() == c.getIndicec()) {
								soc.getCualidades().add(c);
							}							
						} catch (Exception exp) {
							break;
						}
					}					
				}
				
				if (listadoSociedades.size() > 0) {
					resultado = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	

	
	private String integrarNombreArchivo(String nombre) {
		return nombre.trim() + ".dat";
	}

	
	//Esta funcion sirve para borra la base de datos ingresados que se han ingresado con aterioridad
	public boolean borrarArchivos() {
		boolean resultado = false;
		try {
			File file;
			for (Sociedad sociedad : listadoSociedades) {
				file = new File(sociedad.getNombredesoc().trim() + ".dat");
				if (file.exists()) {
					file.delete();
				}
				file = null;
			}
			file = new File(direccionCualidades);
			if (file.exists()) {
				file.delete();
			}
			file = null;
			file = new File(direccionSociedad);
			if (file.exists()) {
				file.delete();
			}
			file = null;
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	
	/*********************MODIFICADOS**********************/
	//ESTE METODO ENLISTA LA SOCIEDAD//
	public String listar(int opcion) {
		String cadena ="";
		System.out.println(listadoSociedades.size());
		if (listadoSociedades.size() > 0) {
			
			if (opcion == 1) {
				for (Sociedad sociedad : listadoSociedades) {
					cadena += mostrarSociedad(sociedad);
				}
			} else{
				for (Sociedad sociedad : listadoSociedades) {
					cadena += "\n\n Indice: " + sociedad.getIndice();
					cadena += "\n Nombre: " + sociedad.getNombredesoc();
					cadena += "\n Cantidad de atributos: " + sociedad.getCantidad();
				}
			}
		} else{
			System.out.println("No hay entidades registradas");
		}
		return cadena;
	}
	
	
	//Esta clase mostrar las sociedades y sus cualides en una lista detallada 
	private String mostrarSociedad(Sociedad sociedad) {
		String cadena ="";
		cadena += "\n\n Indice: " + sociedad.getIndice();
		cadena += "\n Nombre: " + sociedad.getNombredesoc();
		cadena += "\n Cantidad de atributos: " + sociedad.getCantidad();
		cadena += "Atributos:";
		int i = 1;
		for (Cualidades cualidades : sociedad.getCualidades()) {
			cadena += "\n\tNo. " + i;
			cadena += "\n\tNombre: " + cualidades.getNombrec();
			cadena += "\n\tTipo de dato: " + cualidades.getNombredeDato();
			
			if (cualidades.isAfirmartamano()) {
				cadena += "\n\ttamano: " + cualidades.getTamano();
			}
			i++;
		}
		return cadena;
	}
	
	//Este metodo sirve para agregar una sociedad con sus cualidades
	public boolean agregarSociedad(Sociedad sociedad) {
		try {
			sociedad.setIndice(listadoSociedades.size() + 1);
			
			sociedad.setPosition(Cualidades.length());
			Cualidades.seek(Cualidades.length());
			for (Cualidades cualidad : sociedad.getCualidades()) {
				Cualidades.writeInt(cualidad.getIndicec());
				Cualidades.write(cualidad.getBytesNombrec());
				Cualidades.writeInt(cualidad.getValordeDato());
				Cualidades.writeInt(cualidad.getTamano());
				//Cualidades.write("\n".getBytes());
			}

			Sociedad.writeInt(sociedad.getIndice());
			Sociedad.write(sociedad.getBytesNombre());
			Sociedad.writeInt(sociedad.getCantidad());
			Sociedad.writeInt(sociedad.getBytes());
			Sociedad.writeLong(sociedad.getPosition());
			Sociedad.write("\n".getBytes());
			listadoSociedades.add(sociedad);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Este metodo sirve para agregar una cualidad individual de una sociedade que ya se ha creado
	public boolean agregarCualidad(int indice, Cualidades cualidad) {
		try {
			Cualidades.seek(Cualidades.length());
			Cualidades.writeInt(cualidad.getIndicec());
			Cualidades.write(cualidad.getBytesNombrec());
			Cualidades.writeInt(cualidad.getValordeDato());
			Cualidades.writeInt(cualidad.getTamano());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Este metodo sirve para modificar el nombre de la cualidad
	public String modificarNombreCualidad(int indice, String nombre, String nuevoNombre) {
		try {				
			Sociedad sociedad = null;
			for (Sociedad s : listadoSociedades) {
				System.out.println(s);
				if (indice == s.getIndice()) {
					sociedad = s;
					break;
				}
			}
			String nombreArchivo = integrarNombreArchivo(sociedad.getNombredesoc());
			DatosdeTabla = new RandomAccessFile(rutaOrigen +nombreArchivo, "rw");
			long tamanoDatos = DatosdeTabla.length();
			DatosdeTabla.close();
			if (tamanoDatos > 0) {
				return "No es posible modificar la entidad debido a que ya tiene datos asociados";
			} 
			
			Cualidades.seek(0);
			Cualidades c;
			int contador = 0;
			while (true) {
				try {
					c = new Cualidades();
					c.setIndicec(Cualidades.readInt());
					byte[] bytNombrec = new byte[30];
					Cualidades.read(bytNombrec);
					c.setBytesNombrec(bytNombrec);
					c.setValordeDato(Cualidades.readInt());
					c.setTamano(Cualidades.readInt());
					c.setNombredeDato();
					if(c.getNombrec().trim().equals(nombre) && sociedad.getIndice() == c.getIndicec()) {
						c.setNombrec(nuevoNombre);
						int position = contador * bytesCualidades;
						Cualidades.seek(position);
						Cualidades.writeInt(c.getIndicec());							
						Cualidades.write(c.getBytesNombrec());							
						return "modificado";
					}
					contador++;
				} catch(IOException ex) {
					break;
				}
			}	
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "";
	}
	
	//Este metdo sirve para eliminar cualidad seleccionada
	public String eliminarCualidad(int indice, String nombre) {
		try {				
			Sociedad sociedad = null;
			for (Sociedad s : listadoSociedades) {
				System.out.println(s);
				if (indice == s.getIndice()) {
					sociedad = s;
					break;
				}
			}
			String nombreArchivo = integrarNombreArchivo(sociedad.getNombredesoc());
			DatosdeTabla = new RandomAccessFile(rutaOrigen +nombreArchivo, "rw");
			long tamanoDatos = DatosdeTabla.length();
			DatosdeTabla.close();
			if (tamanoDatos > 0) {
				return "No es posible modificar la entidad debido a que ya tiene datos asociados";
			} 
			
			Cualidades.seek(0);
			Cualidades c;
			int contador = 0;
			while (true) {
				try {
					c = new Cualidades();
					c.setIndicec(Cualidades.readInt());
					byte[] bytNombrec = new byte[30];
					Cualidades.read(bytNombrec);
					c.setBytesNombrec(bytNombrec);
					c.setValordeDato(Cualidades.readInt());
					c.setTamano(Cualidades.readInt());
					c.setNombredeDato();
					if(c.getNombrec().trim().equals(nombre) && sociedad.getIndice() == c.getIndicec()) {
						int position = contador * bytesCualidades;
						Cualidades.seek(position);
						Cualidades.writeInt(-1);
						return "modificado";
					}
					contador++;
				} catch(IOException ex) {
					break;
				}
			}	
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "";
	}
	
	
	public String modificarTipoCualidad(int indice, String nombre, int tipo) {
		
		try {				
			Sociedad sociedad = null;
			for (Sociedad s : listadoSociedades) {
				if (indice == s.getIndice()) {
					sociedad = s;
					break;
				}
			}
			
			String nombreArchivo = integrarNombreArchivo(sociedad.getNombredesoc());
			DatosdeTabla = new RandomAccessFile(rutaOrigen +nombreArchivo, "rw");
			long tamanoDatos = DatosdeTabla.length();
			DatosdeTabla.close();
			if (tamanoDatos > 0) {
				return "No es posible modificar la entidad debido a que ya tiene datos asociados";
			} 
			Cualidades.seek(0);
			Cualidades c;
			int contador = 0;
			while (true) {
				c = new Cualidades();
				c.setIndicec(Cualidades.readInt());
				byte[] bytNombrec = new byte[30];
				Cualidades.read(bytNombrec);
				c.setBytesNombrec(bytNombrec);
				c.setValordeDato(Cualidades.readInt());
				c.setTamano(Cualidades.readInt());
				c.setNombredeDato();
				if(c.getNombrec().trim().equals(nombre) && sociedad.getIndice() == c.getIndicec()) {
					c.setValordeDato(tipo);
					c.setNombredeDato();
					int position = contador * bytesCualidades ;
					Cualidades.seek(position);
					Cualidades.writeInt(c.getIndicec());
					Cualidades.write(c.getBytesNombrec());
					Cualidades.writeInt(c.getValordeDato());
					
					return "modificado";
				}
				contador++;
			}	
				
			} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "";
	}

	public ArrayList<Object[]> datosTabla(Sociedad sociedad) {
		ArrayList<Object[]> elementos = new ArrayList();
		String rutaOrigen = "C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
		String r = rutaOrigen + sociedad.getNombredesoc().trim() + ".dat";
		byte[] cadena;
		try {
			RandomAccessFile DatosdeTabla = new RandomAccessFile(r, "rw");
			DatosdeTabla.seek(0);
			
			while (true) {
				try {
					Object[] linea = new Object[] {}; 
					for (Cualidades cualidades : sociedad.getCualidades()) {
						switch (cualidades.getValorDato()) {
						case INT:
							int datoInt = DatosdeTabla.readInt();
							linea = appendValue(linea, datoInt);
							break;
						case LONG:
							long datoLong = DatosdeTabla.readLong();
							linea=appendValue(linea, datoLong);
							break;
						case FLOAT:
							float datoflotat = DatosdeTabla.readFloat();
							linea=appendValue(linea, datoflotat);
							break;
						case DOUBLE:
							double tmpDouble = DatosdeTabla.readDouble();
							linea=appendValue(linea, tmpDouble);
							break;
						case CHAR:
							byte caracter = DatosdeTabla.readByte();
							char valor = (char)Integer.parseInt(String.valueOf(caracter));
							linea=appendValue(linea, valor);
							break;
						case STRING:
						case DATE:
							cadena = new  byte[cualidades.getTamano()];
						    DatosdeTabla.read(cadena);
						    String valorString = new String(cadena);
							linea=appendValue(linea, valorString);
							break;
						}
					}
					elementos.add(linea);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (Exception error) {
			System.out.println(
					"Error " + error.getMessage() + " al capturar tipo de dato, vuelva a ingresar el valor: ");

		}
		return elementos;		
	}
	
	private Object[] appendValue(Object[] obj, Object newObj) {
		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		temp.add(newObj);
		return temp.toArray();
	  }
	

}
