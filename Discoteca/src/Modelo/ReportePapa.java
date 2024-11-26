package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ReportePapa {
	public String horita () {
		LocalTime ahora = LocalTime.now();
        DateTimeFormatter formato24Horas = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActual = ahora.format(formato24Horas);
        return horaActual;
	}
	
	public String Fechita () {
		LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaActualStr = fechaActual.format(formato);
        return fechaActualStr;
	}
	
	public void addTableHeader(PdfPTable table, Font font, String text) {
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Phrase(text, font));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }

    public void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
    
    public void addTableCellRed(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.RED); // Establece el fondo rojo
        table.addCell(cell);
    }

    
    public void GenerarReporte() {
    	
    }
    
    public ReportePapa() {
    	
	}
}
