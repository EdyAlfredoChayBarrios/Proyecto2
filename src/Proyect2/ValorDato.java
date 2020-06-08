package src.Proyect2;

//esta clase es un tipo enumerado
public enum ValorDato {
	
	INT(1),
	LONG(2),
	FLOAT(3),
	DOUBLE(4),
	CHAR(5),
	STRING(6),
	DATE(7);
	
	
	private final int valor;
	
	private ValorDato (int valor) {
		this.valor=valor;
	}
	public int getValor() {
		return valor;
	}
}
