package src.Proyect2;



public class Cualidades {
	// aca se creara el objeto cualidades donde estara los datos de las sociedades

	private int indicec; //4
	private String nombre; //30
	private int valordeDato; // 4
	private String nombredeDato;
	private int tamano;  //4
	private int bytes;   
	private boolean afirmartamano;
	private byte[] bytesNombrec;
	private ValorDato valorDato;

	public int getIndicec() {
		return indicec;
	}


	public void setIndicec(int indicec) {
		this.indicec = indicec;
	}

	public String getNombrec() {
		return nombre;
	}

	public void setNombrec(String nombre) {
		this.nombre = nombre;
		bytesNombrec = new byte[30];
		for (int i = 0; i < nombre.length(); i++) {
			bytesNombrec[i] = (byte) nombre.charAt(i);
		}
	}

	public int getValordeDato() {
		return valordeDato;
	}

	public void setValordeDato(int valordeDato) {
		this.valordeDato = valordeDato;
		if (valordeDato == ValorDato.STRING.getValor()) {
			this.afirmartamano = true;
		}
	}

	public String getNombredeDato() {
		return nombredeDato;
	}

	
	public void setNombredeDato() {
		
		// este constructor se realizara siempre y cuando se seleccione como un int
		if (this.valordeDato == ValorDato.INT.getValor()) {
			this.nombredeDato = ValorDato.INT.name();
			this.bytes = 4;
			valorDato = ValorDato.INT;
		}
		// este constructor se realizara siempre y cuando se seleccione como un long
		if (this.valordeDato == ValorDato.LONG.getValor()) {
			this.nombredeDato = ValorDato.LONG.name();
			this.bytes = 8;
			valorDato = ValorDato.LONG;
		}
		// este constructor se realizara siempre y cuando se seleccione como un float
		if (this.valordeDato == ValorDato.FLOAT.getValor()) {
			this.nombredeDato = ValorDato.FLOAT.name();
			this.bytes = 4;
			valorDato = ValorDato.FLOAT;
		}
		// este constructor se realizara siempre y cuando se seleccione como un double
		if (this.valordeDato == ValorDato.DOUBLE.getValor()) {
			this.nombredeDato = ValorDato.DOUBLE.name();
			this.bytes = 8;
			valorDato = ValorDato.DOUBLE;
		}
		// este constructor se realizara siempre y cuando se seleccione como un char
		if (this.valordeDato == ValorDato.CHAR.getValor()) {
			this.nombredeDato = ValorDato.CHAR.name();
			this.bytes = 1;
			valorDato = ValorDato.CHAR;
		}
		// este constructor se realizara siempre y cuando se seleccione como un string
		if (this.valordeDato == ValorDato.STRING.getValor()) {
			this.nombredeDato = ValorDato.STRING.name();
			this.bytes = this.tamano;
			valorDato = ValorDato.STRING;
		}
		// este constructor se realizara siempre y cuando se seleccione como un dato
		if (this.valordeDato == ValorDato.DATE.getValor()) {
			this.nombredeDato = ValorDato.DATE.name();
			this.bytes = 28;
			valorDato = ValorDato.DATE;
		}

	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public boolean isAfirmartamano() {
		return afirmartamano;
	}

	
	public byte[] getBytesNombrec() {
		return bytesNombrec;
	}

	public void setBytesNombrec(byte[] bytesNombrec) {
		this.bytesNombrec = bytesNombrec;
		nombre = new String(bytesNombrec);
	}

	public ValorDato getValorDato() {
		return valorDato;
	}



}
