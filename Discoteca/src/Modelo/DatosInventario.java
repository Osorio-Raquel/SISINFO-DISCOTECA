package Modelo;

import java.time.LocalDate;

public class DatosInventario extends DatosPadre{
	public int stock;
	
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
	
	public void setStock(int stock) {
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
	
	
	public int getStock() {
		return stock;
	}
	
	public DatosInventario (int numero, String nombre, int stock) {
		super(numero, nombre);
		this.stock = stock;
	}
}
