package src.Proyect2;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


public class MaindeProyecto2 {

	
	//Abrira los Archivos RandomAccessFile con su diferente caracteristica cada una	
	RandomAccessFile DatosdeTabla=null;
	RandomAccessFile Sociedad=null;
	RandomAccessFile Cualidades=null;
	
	//En este paso creara una array list de sociedades
	private List<Sociedad> listadoSociedades = new ArrayList<>();
	private String direccionSociedad="Sociedad.dat";
	private String direccionCualidades="Cualidades.dat";
	private int bytesSociedad=45;
	private int bytesCualidades=45;
	
	//metodo para acceder a los archivos si es que ya estan creados sino los crea
	private boolean accederArchivo(){
		boolean resultado=false;
		try {
			Sociedad = new RandomAccessFile(direccionSociedad, "rw");
			Cualidades= new RandomAccessFile(direccionCualidades, "rw");
			//tamano toma el valor de tama;o de la sociedad
			long tamano= Sociedad.length();
			
			
			if(tamano <= 0) {
				System.out.println("NO Existe registros");
				resultado=false;
				
			}else if (tamano >=bytesSociedad) {
				Sociedad.seek(0);
				Sociedad s=new Sociedad();
				
				while(tamano>=bytesSociedad) {
				s=new Sociedad();
				s.setIndice(Sociedad.readInt());
				byte[] bytNombre=new byte[25];
				Sociedad.read(bytNombre);
				s.setBytesNombre(bytNombre);
				s.setCantidad(Sociedad.readInt());
				s.setBytes(Sociedad.readInt());
				s.setPosition(Sociedad.readLong());
				Sociedad.readByte();
				tamano-=bytesSociedad;
				long TamanoCualidades= Sociedad.length();
				
				if(TamanoCualidades<=0) {
					System.out.println("No existe registros");
					resultado=false;
					break;
				}
				Cualidades.seek(s.getPosition());
				Cualidades c;
				TamanoCualidades=s.getCantidad()*bytesSociedad;
				
				while(TamanoCualidades>=bytesCualidades) {
					c =new Cualidades();
					c.setIndicec(Cualidades.readInt());
					byte[]bytNombrec=new byte[25];
					Cualidades.read(bytNombrec);
					c.setBytesNombrec(bytNombrec);
					c.setValorDato(Cualidades.readInt());
					c.setTamano(Cualidades.readInt());
					c.setNombredeDato();
					Cualidades.readByte();
					s.setCualidad(c);
					TamanoCualidades-=bytesCualidades;
					
				}
				
				
				
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void main(String[] args) {

	}

}
