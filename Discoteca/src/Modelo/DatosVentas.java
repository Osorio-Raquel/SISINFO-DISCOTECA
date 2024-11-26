package Modelo;

public class DatosVentas extends DatosPadre{
	public String nit;
	public String fecha;
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
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
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
	
	public String getFecha() {
		return fecha;
	}
	
	public double getGanancia() {
		return ganancia;
	}
	
	public String getNit() {
		return nit;
	}
	
	public DatosVentas(int numero, String nombre, String nit, String fecha, double ganancia) {
		super(numero, nombre);
		this.nit = nit;
		this.fecha = fecha;
		this.ganancia = ganancia;
	}
}
