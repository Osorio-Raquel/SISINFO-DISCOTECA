package Modelo;

public class DatosPadre {
	public int numero;
	public String nombre;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public DatosPadre(int numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
	}
}
