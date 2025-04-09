package com.verduleria.service.impl;


import com.verduleria.service.ReporteService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public ResponseEntity<Resource> generaReporte(String reporte, Map<String, Object> parametros, String tipo) throws Exception {
        String pathReporte = "reportes" + File.separator + reporte + ".jasper";

        InputStream jasperStream = getClass().getClassLoader().getResourceAsStream(pathReporte);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource.getConnection());

        File archivoSalida = File.createTempFile("reporte", "." + tipo);
        MediaType mediaType;
        String nombreArchivo;

        switch (tipo.toLowerCase()) {
            case "pdf":
            case "vpdf":
                JasperExportManager.exportReportToPdfFile(jasperPrint, archivoSalida.getAbsolutePath());
                mediaType = MediaType.APPLICATION_PDF;
                break;
            case "xls":
                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                exporterXLS.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(archivoSalida));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setDetectCellType(true);
                configuration.setCollapseRowSpan(false);
                exporterXLS.setConfiguration(configuration);
                exporterXLS.exportReport();
                mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
                break;
            case "csv":
                JRCsvExporter exporterCSV = new JRCsvExporter();
                exporterCSV.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporterCSV.setExporterOutput(new SimpleWriterExporterOutput(archivoSalida));
                exporterCSV.exportReport();
                mediaType = MediaType.TEXT_PLAIN;
                break;
            default:
                throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(archivoSalida));
        nombreArchivo = reporte + "." + tipo;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, tipo.equals("vpdf") ? "inline; filename=\"" + nombreArchivo + "\"" : "attachment; filename=\"" + nombreArchivo + "\"")
                .contentLength(archivoSalida.length())
                .contentType(mediaType)
                .body(resource);
    }
}
