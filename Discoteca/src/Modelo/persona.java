package Modelo;

public class persona {
	public String nombre;
	public String correo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public persona(String nombre, String correo) {

		this.nombre = nombre;
		this.correo = correo;
	}
	public String ingresar() {
		String sql= "INSERT INTO";
		return sql;
	}
	
	
}
