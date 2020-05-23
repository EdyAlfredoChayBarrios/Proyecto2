package src.Proyect2;

import java.util.ArrayList;
import java.util.List;

public class Sociedad {
 //aca se creara el objeto sociedad donde estara las propiedades de la sociedad
	
	//en este paso se creara las cualidades de la sociedad con una lisya
	private List<Cualidades>cualidades;
	
	private int indice;
	private String Nombredesoc;
	private long Position;
	private int Cantidad;
	private byte[]bytesNombre;
	public List<Cualidades> getCualidades() {
		return cualidades;
	}
	public void setCualidades(List<Cualidades> cualidades) {
		this.cualidades = cualidades;
	}
	
	public void setCualidad(Cualidades Cualidad) {
		if (this.cualidades == null) {
			this.cualidades = new ArrayList<>();
		}
		this.cualidades.add(Cualidad);
		this.Cantidad = this.cualidades.size();
		
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public String getNombredesoc() {
		return Nombredesoc;
	}
	public void setNombredesoc(String nombredesoc) {
		Nombredesoc = nombredesoc;
	}
	public long getPosition() {
		return Position;
	}
	public void setPosition(long position) {
		Position = position;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public byte[] getBytesNombre() {
		return bytesNombre;
	}
	public void setBytesNombre(byte[] bytesNombre) {
		this.bytesNombre = bytesNombre;
	}
	public int getBytes() {
		return bytes;
	}
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	private int bytes=1;
	
}
