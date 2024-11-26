package Modelo;

public class DatosFacturaPDF extends DatosPadre{
	public double cantidad;
	public double precio;
	public double subtotal;
	
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
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
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
	
	public double getCantidad() {
		return cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public DatosFacturaPDF(int numero, String nombre, double cantidad, double precio, double subtotal) {
		super(numero, nombre);
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}
}
