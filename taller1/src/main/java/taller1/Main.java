package taller1;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Clase Main
 * @author JuanPabloV
 */

public class Main {
    public static void main(String[] args) {
        String carpeta = ""; // Se crea el string que guardará la ruta de nuestra carpeta
        System.out.println("\nSeleccione la carpeta que desea explorar...");
        JFileChooser selector = new JFileChooser(); // Se crea una instancia que permitirá seleccionar la carpeta
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo se permite seleccionar carpetas
        int resultado = selector.showOpenDialog(null); // Abre el cuadro de diálogo para buscar la carpeta
        if (resultado == JFileChooser.APPROVE_OPTION) { // Se verifica la selección de la carpeta
            carpeta = selector.getSelectedFile().getAbsolutePath(); // Se obtiene obtiene la carpeta con getSelectedFile y se obtiene la ruta de la carpeta con getAbsolutePath
            System.out.println("Carpeta seleccionada: " + carpeta); // Se imprime por consola la ruta de la carpeta seleccionada
        }

        String palabraRequerida = ""; // Se crea el string que guardará la palabra que se quiere buscar y contar en los archivos
        try (Scanner scanner = new Scanner(System.in)) { // Se usa try para asegurar que una vez se leyó la palabra se cierre bien el recurso scanner
            System.out.print("\nIngresa la palabra que deseas contar: ");
            palabraRequerida = scanner.nextLine(); // Se pide la palabra que se quiere buscar y contar en los archivos
        }

        File directorio = new File(carpeta); // Se crea una instancia file para poder leer los archivos que se encuentran en la ruta
        if (!directorio.isDirectory()) { // Si directorio no es una carpeta se da el aviso de que la ruta no es válida
            System.out.println("\nNo se encuentra la carpeta indicada en la ruta.");
            return;
        }

        File[] archivos = directorio.listFiles(); // Se listan los archivos que se encuentran en directorio y se guardan en el array archivos
        if (archivos == null || archivos.length == 0) { // Se verifica que existan archivos dentro del array archivos
            System.out.println("\nLa carpeta está vacía."); // Si no existen archivos se devuelve el mensaje
            return;
        }

        int contadorCarpeta = 0; // Cuenta la cantidad de veces que aparece la palabra en la carpeta
        for (File archivo : archivos) { // Se recorre el array archivos
            if (archivo.isFile()) { // Se verifica que archivo que se tomó del array si sea un archivo
                int contadorArchivo = taller1.Archivo.contarPalabraEnArchivo(archivo, palabraRequerida); // Se llama al método contador de palabras
                contadorCarpeta += contadorArchivo;
            }
        }
        System.out.println("\nLa palabra '" + palabraRequerida + "' aparece un total de " + contadorCarpeta + " veces en la carpeta.");
    }
}