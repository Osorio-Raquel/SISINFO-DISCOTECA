package Controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;

import Modelo.EnviarEmailFactura;
import Modelo.FacturaEnPDF;
import Vista.Factura;
import Vista.Reportes;
import Vista.Administrador;
import Vista.Bartender;

import Vista.Login;
import Vista.Pedido;
import Vista.Regalias;
import Vista.Relacionador;
import conexionBase.conexionBD;

public class menuPrincipal {

	public static void main(String[] args) {

		Login l = new Login();
		l.setVisible(true);
	}
}