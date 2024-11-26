package Modelo;

public class DatosFinanzas extends DatosPadre{
	public double total;
	public String tipo;
	
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
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public double getTotal() {
		return total;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public DatosFinanzas(int numero, String nombre, double total, String tipo) {
		super(numero, nombre);
		this.total = total;
		this.tipo = tipo;
	}
}
