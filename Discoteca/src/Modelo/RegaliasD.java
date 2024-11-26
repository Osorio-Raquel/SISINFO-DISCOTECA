package Modelo;

public class RegaliasD {
	public int id;
	public String nombre;
	public int cantidad;
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public RegaliasD (int id, String nombre, int cant) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cant;
	}
}
