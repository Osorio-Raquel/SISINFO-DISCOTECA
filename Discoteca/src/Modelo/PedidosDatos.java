package Modelo;

import java.time.LocalDate;

public class PedidosDatos extends DatosPadre{
	public int cantidad;
	public double costo;
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
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
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public int getCantidad() {
		return cantidad;
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
	
	public double getCosto() {
		return costo;
	}
	
	public PedidosDatos (int numero, String nombre, int cantidad, double costo) {
		super(numero, nombre);
		this.cantidad = cantidad;
		this.costo = costo;
	}
	
}
