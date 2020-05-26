package src.Proyect2;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MaindeProyecto2 {

	// Abrira los Archivos RandomAccessFile con su diferente caracteristica cada una
	RandomAccessFile DatosdeTabla = null;
	RandomAccessFile Sociedad = null;
	RandomAccessFile Cualidades = null;

	// En este paso creara una array list de sociedades
	private List<Sociedad> listadoSociedades = new ArrayList<>();
	private String rutaOrigen="C:\\Users\\edy chay\\eclipse-workspace\\Proyecto2\\";
	private  String direccionSociedad = "Sociedad.dat";
	private String direccionCualidades = "Cualidades.dat";
	private final int totalBytes = 90, bytesSociedad = 45, bytesCualidades = 45;
	Scanner sc = new Scanner(System.in);
	private final static String formatoFecha = "dd/MM/yyyy";
	static DateFormat formato = new SimpleDateFormat(formatoFecha);

	// metodo para acceder a los archivos si es que ya estan creados sino los crea
	private boolean accederArchivo() {
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
					TamanoCualidades = s.getCantidad() * bytesSociedad;

					while (TamanoCualidades >= bytesCualidades) {
						c = new Cualidades();
						c.setIndicec(Cualidades.readInt());
						byte[] bytNombrec = new byte[30];
						Cualidades.read(bytNombrec);
						c.setBytesNombrec(bytNombrec);
						c.setValordeDato(Cualidades.readInt());
						c.setTamano(Cualidades.readInt());
						c.setNombredeDato();
						Cualidades.readByte();
						s.setCualidades(c);
						TamanoCualidades -= bytesCualidades;

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

	private boolean agregarSociedad() {
		boolean resultado = false;
		try {
			Sociedad sociedad = new Sociedad();
			sociedad.setIndice(listadoSociedades.size() + 1);
			System.out.println("Ingrese el nombre de la entidad");
			String strNombre = "";
			int tamano = 0;
			do {
				strNombre = sc.nextLine();
				tamano = strNombre.length();
				if (tamano < 2 || tamano > 30) {
					System.out.println("La tamano del nombre no es valida [3 - 30]");
				} else {
					if (strNombre.contains(" ")) {
						System.out
								.println("El nombre no puede contener espacios, sustituya por guion bajo (underscore)");
						tamano = 0;
					}
				}
			} while (tamano < 2 || tamano > 25);
			sociedad.setNombredesoc(strNombre);
			System.out.println("cualidades de la sociedad");
			int bndDetener = 0;
			do {
				Cualidades cualidades = new Cualidades();
				cualidades.setIndicec(sociedad.getIndice());
				tamano = 0;
				System.out.println("Escriba el nombre del atributo no. " + (sociedad.getCantidad() + 1));
				do {
					strNombre = sc.nextLine();
					tamano = strNombre.length();
					if (tamano < 2 || tamano > 30) {
						System.out.println("La tamano del nombre no es valida [3 - 30]");
					} else {
						if (strNombre.contains(" ")) {
							System.out.println(
									"El nombre no puede contener espacios, sustituya por guion bajo (underscore)");
							tamano = 0;
						}
					}
				} while (tamano < 2 || tamano > 30);
				cualidades.setNombrec(strNombre);
				System.out.println("Seleccione el tipo de dato");
				System.out.println(ValorDato.INT.getValor() + " .......... " + ValorDato.INT.name());
				System.out.println(ValorDato.LONG.getValor() + " .......... " + ValorDato.LONG.name());
				System.out.println(ValorDato.FLOAT.getValor() + " .......... " + ValorDato.FLOAT.name());
				System.out.println(ValorDato.DOUBLE.getValor() + " .......... " + ValorDato.DOUBLE.name());
				System.out.println(ValorDato.CHAR.getValor() + " .......... " + ValorDato.CHAR.name());
				System.out.println(ValorDato.STRING.getValor() + " .......... " + ValorDato.STRING.name());
				System.out.println(ValorDato.DATE.getValor() + " .......... " + ValorDato.DATE.name());
				
				cualidades.setValordeDato(sc.nextInt());
				if (cualidades.isAfirmartamano()) {
					System.out.println("Ingrese la tamano");
					cualidades.setTamano(sc.nextInt());
				} else {
					cualidades.setTamano(0);
				}
				cualidades.setNombredeDato();
				sociedad.setCualidades(cualidades);
				System.out.println("Desea agregar otro atributo presione cualquier numero, de lo contrario 0");
				bndDetener = sc.nextInt();
			} while (bndDetener != 0);
			System.out.println("Los datos a registrar son: ");
			mostrarSociedad(sociedad);
			System.out.println("Presione 1 para guardar 0 para cancelar");
			tamano = sc.nextInt();
			if (tamano == 1) {

				sociedad.setPosition(Cualidades.length());
				Cualidades.seek(Cualidades.length());
				for (Cualidades cualidad : sociedad.getCualidades()) {
					Cualidades.writeInt(cualidad.getIndicec());
					Cualidades.write(cualidad.getBytesNombrec());
					Cualidades.writeInt(cualidad.getValordeDato());
					Cualidades.writeInt(cualidad.getTamano());
					Cualidades.write("\n".getBytes());
				}

				Sociedad.writeInt(sociedad.getIndice());
				Sociedad.write(sociedad.getBytesNombre());
				Sociedad.writeInt(sociedad.getCantidad());
				Sociedad.writeInt(sociedad.getBytes());
				Sociedad.writeLong(sociedad.getPosition());
				Sociedad.write("\n".getBytes());
				listadoSociedades.add(sociedad);
				resultado = true;
			} else {
				System.out.println("No se guardo la entidad debido a que el usuario decidio cancelarlo");
				resultado = false;
			}

			System.out.println("Presione una tecla para continuar...");
			System.in.read();
		} catch (Exception e) {
			resultado = false;
			e.printStackTrace();
		}
		return resultado;
	}

	private void modificarSociedad() {
		try {
		
				int indice = 0;
				while (indice < 1 || indice > listadoSociedades.size()) {
					for (Sociedad sociedad : listadoSociedades) {
						System.out.println(sociedad.getIndice() + " ...... " + sociedad.getNombredesoc());
					}
					System.out.println("Seleccione la entidad que desea modificar");
					indice = sc.nextInt();
							
				}
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
				System.out.println("No es posible modificar la entidad debido a que ya tiene datos asociados");
			} else {
				boolean bndEncontrado = false, bndModificado = false;
				Sociedad.seek(0);
				long tamano = Sociedad.length();
				int registros = 0, salir = 0, i;
				Sociedad s;
				byte[] tmpBytes;
				while (tamano > totalBytes) {
					s = new Sociedad();
					s.setIndice(Sociedad.readInt());
					tmpBytes = new byte[30];
					Sociedad.read(tmpBytes);
					s.setBytesNombre(tmpBytes);
					s.setCantidad(Sociedad.readInt());
					s.setBytes(Sociedad.readInt());
					s.setPosition(Sociedad.readLong());
					if (sociedad.getIndice() == s.getIndice()) {
						System.out.println("Si no desea modificar el campo presione enter");
						System.out.println("Ingrese el nombre");
						String tmpStr = "";
						int len = 0;
						long position;
						do {
							tmpStr = sc.nextLine();
							len = tmpStr.length();
							if (len == 1 || len > 30) {
								System.out.println("La tamano del nombre no es valida [2 - 30]");
							}
						} while (len == 1 || len > 30);
						if (len > 0) {
							s.setNombredesoc(tmpStr);
							position = registros * totalBytes;
							DatosdeTabla.seek(position);
							DatosdeTabla.skipBytes(4);
							DatosdeTabla.write(s.getBytesNombre());
							bndModificado = true;
						}
						i = 1;
						for (Cualidades c : sociedad.getCualidades()) {
							System.out.println("Modificando atributo 1");
							System.out.println(c.getNombrec().trim());
						}

						break;
					}
					registros++;

					tamano -= totalBytes;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	private String integrarNombreArchivo(String nombre) {

		return nombre.trim() + ".dat";
	}

	private void mostrarSociedad(Sociedad sociedad) {
		System.out.println("Indice: " + sociedad.getIndice());
		System.out.println("Nombre: " + sociedad.getNombredesoc());
		System.out.println("Cantidad de Cualidades: " + sociedad.getCantidad());
		System.out.println("Atributos:");
		int i = 1;
		for (Cualidades cualidades : sociedad.getCualidades()) {
			System.out.println("\tNo. " + i);
			System.out.println("\tNombre: " + cualidades.getNombrec());
			System.out.println("\tTipo de dato: " + cualidades.getNombredeDato());
			if (cualidades.isAfirmartamano()) {
				System.out.println("\ttamano: " + cualidades.getTamano());
			}
			i++;
		}
	}

	private void menuLaSociedad(boolean mostrarAgregarRegistro) {
		int opcion = 1;
		while (opcion != 0) {
			System.out.println("Elija su opcion");
			System.out.println("1 ........ Agregar Sociedad");
			System.out.println("2 ........ Modificar Sociedad");
			System.out.println("3 ........ enListar Sociedad");
			if (mostrarAgregarRegistro) {
				System.out.println("4 ........ Agregar datos a la sociedad");
			}
			System.out.println("5 ........ Borrar los dato totales del archivo");

			System.out.println("0 ........ Salir");
			opcion = sc.nextInt();
			switch (opcion) {
			case 0:
				System.out.println("Gracias por usar nuestra aplicacion");
				break;
			case 1:
				if (agregarSociedad()) {
					System.out.println("Entidad agregada con exito");
					mostrarAgregarRegistro = true;
				}
				break;
			case 2:
				
				break;
			case 3:
				if (listadoSociedades.size() > 0) {
					int tmpInt = 0;
					System.out.println("Desea imprimir los detalles. Si, presione 1. No, presione 0?");
					tmpInt = sc.nextInt();
					if (tmpInt == 1) {
						for (Sociedad sociedad : listadoSociedades) {
							mostrarSociedad(sociedad);
						}
					} else{
						for (Sociedad sociedad : listadoSociedades) {
							System.out.println("Indice: " + sociedad.getIndice());
							System.out.println("Nombre: " + sociedad.getNombredesoc());
							System.out.println("Cantidad de atributos: " + sociedad.getCantidad());
						}
					}
				} else{
					System.out.println("No hay entidades registradas");
				}
				break;
			case 4:
				int indice = 0;
				while(indice < 1 || indice > listadoSociedades.size()) {
					for (Sociedad sociedad : listadoSociedades) {
						System.out.println(sociedad.getIndice() + " ...... " + sociedad.getNombredesoc());
					}
					System.out.println("Seleccione la entidad que desea trabajar");
					indice = sc.nextInt();
				}
				iniciar(indice);
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
		}
	}

	private void iniciar(int indice) {
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

	private boolean borrarArchivos() {
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
	
	public static void main(String[] args) {
		MaindeProyecto2 mdp = new MaindeProyecto2();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalGrafico frame = new MenuPrincipalGrafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if (mdp.accederArchivo()) {
			mdp.menuLaSociedad(true);
		} else {
			mdp.menuLaSociedad(false);
		}
		System.exit(0);
	}
	
	
}
