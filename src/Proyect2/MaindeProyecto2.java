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
					long TamanoCualidades = Sociedad.length();

					if (TamanoCualidades <= 0) {
						System.out.println("No existe registros");
						resultado = false;
						break;
					}
					Cualidades.seek(s.getPosition());
					Cualidades c;
					TamanoCualidades = s.getCantidad() * 42;

					while (TamanoCualidades>=42) {
						try {
							c = new Cualidades();
							c.setIndicec(Cualidades.readInt());
							byte[] bytNombrec = new byte[30];
							Cualidades.read(bytNombrec);
							c.setBytesNombrec(bytNombrec);
							c.setValordeDato(Cualidades.readInt());
							c.setTamano(Cualidades.readInt());
							c.setNombredeDato();
							s.setCualidades(c);
							TamanoCualidades -= 42;
						} catch (Exception exp) {
							break;
						}
					}

					listadoSociedades.add(s);

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

	

	void menuLaSociedad(int opcion, int opcion2) {
		boolean mostrarAgregarRegistro = this.accederArchivo();
		
			switch (opcion) {
		
			case 1:
				break;
			case 2:
				
				break;
			case 3:
				
			case 4:
				
				break;
			case 5:
				int confirmar = 0;
				System.out.println(
						"Esta seguro de borrar los archivos de base de datos, presione 1 de lo contrario cualquier numero para cancelar? Esta accion no se podra reversar");
				confirmar = sc.nextInt();
				if (confirmar == 1) {
					cerrarArchivos();
					if (borrarArchivos()) {
						listadoSociedades = null;
						listadoSociedades = new ArrayList<>();
						mostrarAgregarRegistro = false;
						System.out.println("Archivos borrados");
					}
				}
				break;

			default:
				System.out.println("Opcion no valida");
				break;
			}
		//}
	}

	public void iniciar(int indice) {
		int opcion = 0;
		String datosdeTabla = "";
		try {
			Sociedad sociedad = null;
			for (Sociedad s : listadoSociedades) {
				if (indice == s.getIndice()) {
					datosdeTabla= integrarNombreArchivo(s.getNombredesoc());
					sociedad = s;
					break;
				}
			}
			DatosdeTabla = new RandomAccessFile(rutaOrigen + datosdeTabla,"rw");
			System.out.println("Bienvenido (a)");
			Cualidades c = sociedad.getCualidades().get(0);
			do {
				try {
					System.out.println("Seleccione su opcion");
					System.out.println("1.\t\tAgregar");
					System.out.println("2.\t\tListar");
					System.out.println("3.\t\tBuscar");
					System.out.println("4.\t\tModificar");
					System.out.println("0.\t\tRegresar al menu anterior");
					opcion = sc.nextInt();
					switch (opcion) {
					case 0:
						System.out.println("");
						break;
					case 1:
						grabarRegistro(sociedad);
						break;
					case 2:
						enListarRegistros(sociedad);
						break;
					case 3:
						System.out.println("Se hara la busqueda en la primera columna ");
						System.out.println("Ingrese " + c.getNombrec().trim() + " a buscar");
						break;
					case 4:
						System.out.println("Ingrese el carne a modificar: ");
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} catch (Exception e) { // capturar cualquier excepcion que ocurra
					System.out.println("Error: " + e.getMessage());
				}
			} while (opcion != 0);
			DatosdeTabla.close();
		} catch (Exception e) { // capturar cualquier excepcion que ocurra
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void enListarRegistros(Sociedad sociedad) {
		try {
			long tamano = DatosdeTabla.length();
			if (tamano <= 0) {
				System.out.println("No hay registros");
				return;
			}
			
			DatosdeTabla.seek(0);
			byte[] tmpArrayByte;
			String linea = "";
			for (Cualidades cualidades : sociedad.getCualidades()) {
				linea += cualidades.getNombrec().toString().trim() + "\t\t";
			}
			System.out.println(linea);
			while (tamano >= sociedad.getBytes()) {
				linea = "";
				for (Cualidades cualidades : sociedad.getCualidades()) {
					switch (cualidades.getValorDato()) {
					case INT:
						int tmpInt = DatosdeTabla.readInt();
						linea += String.valueOf(tmpInt) + "\t\t";
						break;
					case LONG:
						long tmpLong = DatosdeTabla.readLong();
						linea += String.valueOf(tmpLong) + "\t\t";
						break;
					case STRING:
						tmpArrayByte = new byte[cualidades.getTamano()];
						DatosdeTabla.read(tmpArrayByte);
						String tmpString = new String(tmpArrayByte);
						linea += tmpString.trim() + "\t\t";
						break;
					case DOUBLE:
						double tmpDouble = DatosdeTabla.readDouble();
						linea += String.valueOf(tmpDouble) + "\t\t";
						break;
					case FLOAT:
						float tmpFloat = DatosdeTabla.readFloat();
						linea += String.valueOf(tmpFloat) + "\t\t";
						break;
					case DATE:
						tmpArrayByte = new byte[cualidades.getBytes()];
						DatosdeTabla.read(tmpArrayByte);
						tmpString = new String(tmpArrayByte);
						linea += tmpString.trim() + "\t\t";
						break;
					case CHAR:
						char tmpChar = (char) DatosdeTabla.readByte();
						linea += tmpChar + "\t\t";
						break;
					}
				}
				DatosdeTabla.readByte();

				tamano -= sociedad.getBytes();
				System.out.println(linea);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void cerrarArchivos() {
		if (DatosdeTabla != null) {
			try {
				DatosdeTabla.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Cualidades != null) {
			try {
				Cualidades.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Sociedad != null) {
			try {
				Sociedad.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
	}

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

	private boolean grabarRegistro(Sociedad sociedad) {
		boolean resultado = false;
		try {
			
			DatosdeTabla.seek(DatosdeTabla.length());
			boolean valido;
			byte[] bytesString;
			String tmpString = "";
			for (Cualidades cualidades : sociedad.getCualidades()) {
				valido = false;
				System.out.println("Ingrese " + cualidades.getNombrec().trim());
				while (!valido) {
					try {
						switch (cualidades.getValorDato()) {
						case INT:
							int tmpInt = sc.nextInt();
							DatosdeTabla.writeInt(tmpInt);
							sc.nextLine();
							break;
						case LONG:
							long tmpLong = sc.nextLong();
							DatosdeTabla.writeLong(tmpLong);
							break;
						
						case FLOAT:
							float tmpFloat = sc.nextFloat();
							DatosdeTabla.writeFloat(tmpFloat);
							break;
							
						case DOUBLE:
							double tmpDouble = sc.nextDouble();
							DatosdeTabla.writeDouble(tmpDouble);
							break;	
						
						case CHAR:
							int tamano = 0;
							do {
								tmpString = sc.nextLine();
								tamano = tmpString.length();
								if (tamano < 1 || tamano > 1) {
									System.out.println("Solo se permite un caracter");
								}
							} while (tamano < 1 || tamano > 1);
							byte caracter = (byte) tmpString.charAt(0);
							DatosdeTabla.writeByte(caracter);
							break;
							
						case STRING:
							do {
								tmpString = sc.nextLine();
								tamano = tmpString.length();
								if (tamano <= 1 || tamano > cualidades.getTamano()) {
									System.out.println("La tamano de " + cualidades.getNombrec().trim()
											+ " no es valida [1 - " + cualidades.getTamano() + "]");
								}
							} while (tamano <= 0 || tamano > cualidades.getTamano());
							bytesString = new byte[cualidades.getTamano()];
							for (int i = 0; i < tmpString.length(); i++) {
								bytesString[i] = (byte) tmpString.charAt(i);
							}
							DatosdeTabla.write(bytesString);
							break;
						
					
						case DATE:
							Date date = null;
							tmpString = "";
							while (date == null) {
								System.out.println("Formato de fecha: " + formatoFecha);
								tmpString = sc.nextLine();
								date = strintaDate(tmpString);
							}
							bytesString = new byte[cualidades.getBytes()];
							for (int i = 0; i < tmpString.length(); i++) {
								bytesString[i] = (byte) tmpString.charAt(i);
							}
							DatosdeTabla.write(bytesString);
							break;
						
						}
						valido = true;
					} catch (Exception e) {
						System.out.println(
								"Error " + e.getMessage() + " al capturar tipo de dato, vuelva a ingresar el valor: ");
						sc.nextLine();
					}
				} 
			} 
			DatosdeTabla.write("\n".getBytes()); 
			resultado = true;
		} catch (Exception e) {
			resultado = false;
			System.out.println("Error al agregar el registro " + e.getMessage());
		}
		return resultado;
	}

	public Date strintaDate(String stringFecha) {
		Date fecha = null;
		try {
			fecha = formato.parse(stringFecha);
		} catch (Exception e) {
			fecha = null;
			System.out.println("ERROR  EN FECHA: " + e.getMessage());
		}
		return fecha;
	}

	public String datoAString(Date fecha) {
		String stringFecha;
		stringFecha = formato.format(fecha);
		return stringFecha;
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
	
	public String modificarNombreCualidad(int indice, String nombre, String nuevoNombre) {
		
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
				
			Sociedad.seek(0);
			long tamano = Sociedad.length();
			int registros = 0;
			Sociedad s;
			long position;
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
				long TamanoCualidades = Sociedad.length();

				if (TamanoCualidades <= 0) {
					return "No existe registros";
				}
				Cualidades.seek(s.getPosition());
				Cualidades c;
				TamanoCualidades = s.getCantidad() * bytesCualidades;
				if (sociedad.getIndice() == s.getIndice()) {
					while (TamanoCualidades >= bytesCualidades) {
						c = new Cualidades();
						c.setIndicec(Cualidades.readInt());
						byte[] bytNombrec = new byte[30];
						Cualidades.read(bytNombrec);
						c.setBytesNombrec(bytNombrec);
						c.setValordeDato(Cualidades.readInt());
						c.setTamano(Cualidades.readInt());
						c.setNombredeDato();
						//Cualidades.readByte();						
						TamanoCualidades -= bytesCualidades;
						if(c.getNombrec().trim().equals(nombre)) {
							c.setNombrec(nuevoNombre);
							position = registros * bytesCualidades;
							//if(registros>0) position--;
							Cualidades.seek(position);
							Cualidades.writeInt(c.getIndicec());							
							Cualidades.write(c.getBytesNombrec());							
							return "modificado";
						}
						registros++;
					}	
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
				
			Sociedad.seek(0);
			long tamano = Sociedad.length();
			int registros = 0;
			Sociedad s;
			long position;
			
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
				long TamanoCualidades = Sociedad.length();

				if (TamanoCualidades <= 0) {
					return "No existe registros";
				}
				Cualidades.seek(s.getPosition());
				Cualidades c;
				TamanoCualidades = s.getCantidad() * bytesCualidades;
				if (sociedad.getIndice() == s.getIndice()) {
					while (TamanoCualidades >= bytesCualidades) {
						c = new Cualidades();
						c.setIndicec(Cualidades.readInt());
						byte[] bytNombrec = new byte[30];
						Cualidades.read(bytNombrec);
						c.setBytesNombrec(bytNombrec);
						c.setValordeDato(Cualidades.readInt());
						c.setTamano(Cualidades.readInt());
						c.setNombredeDato();
						s.setCualidades(c);
						TamanoCualidades -= bytesCualidades;
						if(c.getNombrec().trim().equals(nombre)) {
							c.setValordeDato(tipo);
							c.setNombredeDato();
							position = registros * bytesCualidades ;
							Cualidades.seek(position);
							Cualidades.writeInt(c.getIndicec());
							Cualidades.write(c.getBytesNombrec());
							Cualidades.writeInt(c.getValordeDato());
							
							return "modificado";
						}
						registros++;
					}	
				}
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
