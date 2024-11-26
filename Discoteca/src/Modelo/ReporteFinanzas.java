package Modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import conexionBase.conexionBD;

public class ReporteFinanzas extends ReportePapa{
	public ReporteFinanzas() {
		super();
	}
	
	@Override
	public void addTableCell(PdfPTable table, String text) {
		// TODO Auto-generated method stub
		super.addTableCell(table, text);
	}
	
	@Override
	public void addTableHeader(PdfPTable table, Font font, String text) {
		// TODO Auto-generated method stub
		super.addTableHeader(table, font, text);
	}
	
	@Override
	public String Fechita() {
		// TODO Auto-generated method stub
		return super.Fechita();
	}
	
	@Override
	public String horita() {
		// TODO Auto-generated method stub
		return super.horita();
	}
	
	@Override
	public void GenerarReporte() {
        String dest = "ReporteFinanciero.pdf";
        
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            document.open();
            
            String imagePath = "C:\\Documentos\\imag\\logo330x200.png";
            Image imagen = Image.getInstance(imagePath);
            imagen.scaleToFit(100, 100);
            imagen.setAbsolutePosition(10, document.getPageSize().getHeight() - imagen.getScaledHeight() - 10);
            document.add(imagen);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph title = new Paragraph("Reporte de Finanzas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph fechaReporte = new Paragraph("Fecha: " + Fechita() + "            Hora:" + horita(), fechayHora);
            document.add(fechaReporte);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "Razon");
            addTableHeader(table, headerFont, "Tipo");
            addTableHeader(table, headerFont, "Total");
            
            ArrayList<DatosFinanzas> inv = new ArrayList<>();
            inv = obtenerDatos();
            
            for(DatosFinanzas d : inv) {
                addTableCell(table, "" + d.getNumero());
                addTableCell(table, d.getNombre());
                addTableCell(table, d.getTipo());
                addTableCell(table, "" + d.getTotal());
            }
            
            double suma = SumaTotal(inv);
            
            BigDecimal bd = new BigDecimal(Double.toString(suma));
            bd = bd.setScale(2, RoundingMode.FLOOR);
            
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "" + bd.doubleValue());

            document.add(table);

            document.close();

            System.out.println("¡Reporte PDF creado exitosamente!");
            
            File pdfFile = new File(dest);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Apertura de archivos no soportada en este sistema.");
                }
            } else {
                System.out.println("El archivo PDF no fue encontrado.");
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private static ArrayList<DatosFinanzas> obtenerDatos (){
		ArrayList<DatosFinanzas> inv = new ArrayList<>();
		String consulta= "select sum(total) as ganancia\r\n"
				+ "from factura;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				double dou = rs.getDouble("ganancia");
				DatosFinanzas datos1 = new DatosFinanzas(num, "Ventas", dou, "Ganancia");
				inv.add(datos1);
				num++;
			}
			
			consulta = "select sum(salario) as salarios\r\n"
					+ "from empleados \r\n"
					+ "where estado = true;";
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				double sal = rs.getDouble("salarios");
				DatosFinanzas datos2 = new DatosFinanzas(num, "Salarios", sal, "Gasto");
				inv.add(datos2);
				num++;
			}
			
			consulta = "select productos.precio_compra as precio, pedidosReporte.cantidad as cant \r\n"
					+ "from productos, pedidosReporte\r\n"
					+ "where productos.ID_producto = pedidosReporte.id_producto;";
			double gas = 0.0;
			ps=conn.prepareStatement(consulta);
			rs=ps.executeQuery();
			while(rs.next()) {
				double pre = rs.getDouble("precio");
				double cant = rs.getDouble("cant");
				gas += (pre * cant);
			}
			DatosFinanzas datos3 = new DatosFinanzas(num, "Pedidos", gas, "Gasto");
			inv.add(datos3);
			
		}catch(Exception e) {
			
		} finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return inv;
	}
	
	public static double SumaTotal (ArrayList<DatosFinanzas> ped) {
		double suma = 0.0;
		for(DatosFinanzas p : ped) {
			if(p.getTipo().equals("Gasto")) {
				suma -= p.getTotal();
			} else {
			suma += p.getTotal();
			}
		}
		return suma;
	}
}
