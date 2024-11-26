package Controlador;


import java.math.BigDecimal;
import java.math.RoundingMode;

import Modelo.EnviarEmailFactura;
import Modelo.FacturaEnPDF;
import Vista.Factura;
import Vista.Reportes;
import Vista.MenuGerente;
import Vista.Ventas;

//import Vista.MenuGerente;

import Vista.login;
import conexionBase.conexionBD;

public class menuPrincipal {

	public static void main(String[] args) {
		login l = new login();
		l.setVisible(true);
		
	}
}