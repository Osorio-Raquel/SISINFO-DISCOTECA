package Modelo;

public class DatosVentas extends DatosPadre{
	public double ganancia;
	
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
	
	
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
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
	
	
	public double getGanancia() {
		return ganancia;
	}
	
	
	public DatosVentas(int numero, String nombre, double ganancia) {
		super(numero, nombre);
		this.ganancia = ganancia;
	}
}
