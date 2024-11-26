package Modelo;

import java.time.LocalDate;

public class PedidosDatos extends DatosPadre{
	public double cantidad;
	public String fecha;
	public double costo;
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	
	public double getCantidad() {
		return cantidad;
	}
	
	public String getFecha() {
		return fecha;
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
	
	public PedidosDatos (int numero, String nombre, double cantidad, String fecha, double costo) {
		super(numero, nombre);
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.costo = costo;
	}
	
}
