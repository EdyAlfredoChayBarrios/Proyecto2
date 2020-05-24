package src.Proyect2;

import java.util.ArrayList;

import java.util.List;




public class Sociedad {
 //aca se creara el objeto sociedad donde estara las propiedades de la sociedad
	
	//en este paso se creara las cualidades de la sociedad con una lisya
	
	
	private int indice;
	private String Nombredesoc;
	private long Position;
	private int Cantidad;
	private byte[]bytesNombre;
	private int bytes=1;
	private List<Cualidades>cualidades;
	
	
	
	
	
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public String getNombredesoc() {
		return Nombredesoc;
	}
	public void setNombredesoc(String nombre) {
		this.Nombredesoc = nombre;
		bytesNombre = new byte[30]; 
		for (int i = 0; i < nombre.length(); i++) {
			bytesNombre[i] = (byte)nombre.charAt(i);
		}
	}
	
	
	
	public long getPosition() {
		return Position;
	}
	public void setPosition(long position) {
		this.Position = position;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		this.Cantidad = cantidad;
	}
	
	
	public byte[] getBytesNombre() {
		return bytesNombre;
	}
	public void setBytesNombre(byte[] bytesNombre) {
		this.bytesNombre = bytesNombre;
		Nombredesoc=new String(bytesNombre);
	}
	
	
	
	public List<Cualidades> getCualidades() {
		return cualidades;
	}
	
	public void setCualidades(List<Cualidades> cualidades) {
		this.cualidades= cualidades;
	}
	
	public void setCualidades(Cualidades cualidades) {
		if (this.cualidades == null) {
			this.cualidades = new ArrayList<>();
		}
		this.cualidades.add(cualidades);
		this.Cantidad= this.cualidades.size();
	}
	
	public void removeAtributo(Cualidades cualidades) {
		if (this.cualidades != null) {
			if (this.cualidades.size() > 0) {
				this.cualidades.remove(cualidades);
				this.Cantidad = this.cualidades.size();
			}
		}
	}
	
	public int getBytes() {	
		bytes = 1;
		for (Cualidades cualidades : cualidades) {
			bytes += cualidades.getBytes();
		}
		return bytes;
	}
	
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	
}
