package Modelo;


import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conexionBase.conexionBD;

public class empleado extends persona{
	public int estado;
	public String contrasena;
	public int cargo;
	public String apellido;
	public String ci;
	public double salario;
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public empleado(String nombre, String correo,String contrasena, int cargo, String apellido, String ci, double salario) {
		super(nombre, correo);
		this.contrasena=contrasena;
		this.cargo=cargo;
		this.apellido=apellido;
		this.ci=ci;
		this.salario=salario;
		// TODO Auto-generated constructor stub
	}
	

	public boolean despedir(String palabraClave) { //para buscar por id o por nombre
		String sql= "SELECT estado from empleados WHERE Nombre LIKE %'"+palabraClave+"'% OR ID_empleado LIKE %'"+palabraClave+"'%;";
		String sql2= "UPDATE empleados SET estado=false WHERE Nombre ='"+palabraClave +"' OR ID_empleado ="+palabraClave+";";
		PreparedStatement ps=null;
		ResultSet rs= null;
		conexionBD conec= new conexionBD();
		Connection conn=conec.conexion();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				ps=conn.prepareStatement(sql2);
				int i=ps.executeUpdate();
				if(i>0) {
					return true;
				}else {
					return false;
				}
				
			}
		}catch(Exception e) {
			return false;
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		return false;
	}
	public boolean contratar() {
		System.out.println(contrasena);
		System.out.println(contrasena.length());
	    String sql = "INSERT INTO empleados (estado, usurario, contrasenia, cargo, Nombre, Apellido, ci, Salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    PreparedStatement ps = null;
	    conexionBD conec = new conexionBD();
	    Connection conn = conec.conexion();
	    
	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setBoolean(1, true);
	        ps.setString(2, correo); 
	        ps.setString(3, contrasena); 
	        ps.setInt(4, cargo); 
	        ps.setString(5, nombre); 
	        ps.setString(6, apellido);
	        ps.setString(7, ci); 
	        ps.setDouble(8, salario); 

	        int i = ps.executeUpdate();
	        if (i > 0) {
	            return true; 
	        } else {
	            return false; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            System.out.println("conexiones cerradas");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	
}
