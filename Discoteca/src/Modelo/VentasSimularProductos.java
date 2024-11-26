package Modelo;

public class VentasSimularProductos extends DatosPadre{
	public double precio;
	public int tipo;
	public double stock;
	
	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}
	
	@Override
	public void setNumero(int numero) {
		// TODO Auto-generated method stub
		super.setNumero(numero);
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public void setStock(double stock) {
		this.stock = stock;
	}
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}
	
	@Override
	public int getNumero() {
		// TODO Auto-generated method stub
		return super.getNumero();
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public double getStock() {
		return stock;
	}
	
	public VentasSimularProductos (int numero, String nombre, double precio, int tipo, double stock) {
		super(numero, nombre);
		this.precio = precio;
		this.tipo = tipo;
		this.stock = stock;
	}
}
