
package Servidor;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.io.*;

/**
 *
 * @author Jose
 */
public class ThermalPrinter {
    // Método para encontrar la impresora térmica por nombre
    public static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().equalsIgnoreCase(printerName)) {
                return printService;
            }
        }
        return null;
    }

    // Método para imprimir texto
    public void printText(String printerName, String text) throws PrintException {
        // Encontrar la impresora
        PrintService printService = findPrintService(printerName);
        if (printService == null) {
            System.err.println("Impresora no encontrada: " + printerName);
            return;
        }

        // Crear un job de impresión
        DocPrintJob job = printService.createPrintJob();

        // Convertir el texto a bytes
        byte[] bytes = text.getBytes();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(bytes, flavor, null);

        // Imprimir el documento
        job.print(doc, null);
    }

    // Método para imprimir desde un archivo
    public void printFile(String printerName, String filePath) throws IOException, PrintException {
        // Encontrar la impresora
        PrintService printService = findPrintService(printerName);
        if (printService == null) {
            System.err.println("Impresora no encontrada: " + printerName);
            return;
        }

        // Crear un job de impresión
        DocPrintJob job = printService.createPrintJob();

        // Leer el archivo
        FileInputStream fis = new FileInputStream(filePath);
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(fis, flavor, null);

        // Imprimir el documento
        job.print(doc, null);

        // Cerrar el InputStream
        fis.close();
    }

}
