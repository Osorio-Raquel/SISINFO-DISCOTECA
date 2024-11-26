package Modelo;

public class Bebidas {
	public int id;
	public String nombre;
	public int cantidad;
	public double precio;
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public Bebidas(int id, String nombre, int cantidad, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
}
