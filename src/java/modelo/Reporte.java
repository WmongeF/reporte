/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Walter Monge
 */

public class Reporte {
    public static void main(String[] args) throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(
				//"C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\ReporteAlumnos.jasper", null,
                "C:\\Users\\Walter Monge\\JaspersoftWorkspace\\MyReports\\reportes.jasper", null,
                Conexion.conexiondb);
		JRPdfExporter exp = new JRPdfExporter();
		exp.setExporterInput(new SimpleExporterInput(jasperPrint));
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Reportes.pdf"));
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exp.setConfiguration(conf);
		exp.exportReport();
 
		// se muestra en una ventana aparte para su descarga
		JasperPrint jasperPrintWindow;
        jasperPrintWindow = JasperFillManager.fillReport(
                //"C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\ReporteAlumnos.jasper", null,
                "C:\\Users\\Walter Monge\\JaspersoftWorkspace\\MyReports\\reportes.jasper", null,
                Conexion.conexiondb);
		JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
		jasperViewer.setVisible(true);
	}
}
