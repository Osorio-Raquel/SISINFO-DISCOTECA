package Modelo;

import java.time.LocalDate;

public class DatosInventario extends DatosPadre{
	public String categoria;
	public double stock;
	public LocalDate fechaVencimiento;
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	
	public void setStock(double stock) {
		this.stock = stock;
	}
	
	public String getCategoria() {
		return categoria;
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
	
	
	public double getStock() {
		return stock;
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public DatosInventario (String categoria, int numero, String nombre, double stock, LocalDate fechaVencimiento) {
		super(numero, nombre);
		this.categoria = categoria;
		this.stock = stock;
		this.fechaVencimiento = fechaVencimiento;
	}
}
