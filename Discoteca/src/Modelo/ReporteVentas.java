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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class ReporteVentas extends ReportePapa{
	public ReporteVentas() {
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
	
	public ArrayList<DatosVentas> obtenerDatos (LocalDate inicio, LocalDate fin){
		ArrayList<DatosVentas> inv = new ArrayList<>();
		String consulta = "select persona.nombre, persona.NIT, DATE(factura.fecha) AS fechita, factura.total \r\n"
				+ "from persona, factura \r\n"
				+ "where persona.id_persona = factura.id_persona\r\n"
				+ "and DATE(factura.fecha) >= ? and DATE(factura.fecha) <= ?\r\n"
				+ "order by fechita;";
		conexionBD conec= new conexionBD();
		Connection conn= conec.conexion();
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			
			ps=conn.prepareStatement(consulta);
			ps.setString(1, inicio.format(DateTimeFormatter.ISO_LOCAL_DATE));
			ps.setString(2, fin.format(DateTimeFormatter.ISO_LOCAL_DATE));
			rs=ps.executeQuery();
			int num = 1;
			while(rs.next()) {
				String nombre = rs.getString("nombre");
                String nit = rs.getString("NIT");
                Date fecha = rs.getDate("fechita");
                LocalDate fechaLocal = fecha.toLocalDate();
                fechaLocal = fechaLocal.plusDays(1);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaActualStr = fechaLocal.format(formato);
                double total = rs.getDouble("total");
                BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
                double totalRedondeado = bd.doubleValue();
                DatosVentas ped = new DatosVentas(num, nombre, nit, fechaActualStr, totalRedondeado);
                inv.add(ped);
				num++;
			}
			System.out.println("Funciona sql");
		}catch(Exception e) {
			 e.printStackTrace();
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
		return inv;
	}
	
	public void GenerarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        String dest = "ReporteVentas.pdf";
        
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
            Paragraph title = new Paragraph("Reporte de Ventas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            Paragraph espaciador = new Paragraph(" ");
            espaciador.setSpacingBefore(10);
            document.add(espaciador);
            
            Font fechayHora = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
            Paragraph fechaReporte = new Paragraph("Fecha de Reporte: " + Fechita() + "            Hora de Reporte:" + horita(), fechayHora);
            document.add(fechaReporte);
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaActualStrI = fechaInicio.format(formato);
            String fechaActualStrF = fechaFin.format(formato);
            
            
            document.add(espaciador);
            
            Paragraph intervaloInforme = new Paragraph("Reporte del: " + fechaActualStrI + " al " + fechaActualStrF, fechayHora);
            document.add(intervaloInforme);
            
            document.add(espaciador);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            addTableHeader(table, headerFont, "Número");
            addTableHeader(table, headerFont, "NIT");
            addTableHeader(table, headerFont, "Nombre");
            addTableHeader(table, headerFont, "Fecha");
            addTableHeader(table, headerFont, "Total");
            
            ArrayList<DatosVentas> inv = new ArrayList<>();
            inv = obtenerDatos(fechaInicio, fechaFin);
            
            for(DatosVentas d : inv) {
            	addTableCell(table, "" + d.getNumero());
            	addTableCell(table, "" + d.getNit());
                addTableCell(table, "" + d.getNombre());
                addTableCell(table, d.getFecha());
                addTableCell(table, "" + d.getGanancia());
            }
             
            double suma = SumaTotal(inv);
            
            BigDecimal bd = new BigDecimal(suma).setScale(2, RoundingMode.HALF_UP);
            double totalRedondeado = bd.doubleValue();
            
            addTableCell(table, "TOTAL");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "");
            addTableCell(table, "" + totalRedondeado);

            document.add(table);

            document.close();
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
	
	public static double SumaTotal (ArrayList<DatosVentas> ped) {
		double suma = 0.0;
		for(DatosVentas p : ped) {
			suma += p.getGanancia();
		}
		return suma;
	}
}
