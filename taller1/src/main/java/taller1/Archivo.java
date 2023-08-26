package taller1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase Archivo
 * @author JuanPabloV
 */

public class Archivo {
    public static int contarPalabraEnArchivo(File archivo, String palabraRequerida) {
        int contador = 0; // Contador de la palabra

        String nombreArchivo = archivo.getName(); // se obtiene el nombre del archivo
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".")); // Se verifica la extension que tiene el archivo

        if (!extension.equals(".txt") && !extension.equals(".xml") && !extension.equals(".json") && !extension.equals(".csv")) { // Se verifica que el archivo cumpla con las extensiones con texto solicitadas
            System.out.println("\nEl archivo " + archivo.getName() + " no contiene texto.");
            return contador;
        }
        
        try (Scanner scanner = new Scanner(archivo, "UTF-8")) { // Se crea el Scanner que lee el archivo- Se específica la codificación UTF-8 para que se reconozcan los carácteres de la manera correcta            
            while (scanner.hasNext()) {
                String palabra = scanner.next();
                palabra = palabra.replaceAll("[^\\p{L}]", ""); // Remueve los carácteres especiales
                if (palabra.equalsIgnoreCase(palabraRequerida)) { // Utilizamos la función equalsIgnoreCase para ignorar si hay mayúsculas y minúsculas
                    contador++; // Aumenta el contador si encuentra la palabra
                }
            }
            System.out.println("En el archivo " + archivo.getName() + ", la palabra '" + palabraRequerida + "' aparece " + contador + " veces.");

        } catch (FileNotFoundException e) {
            System.out.println("\nNo se pudo abrir el archivo " + archivo.getName());
        } catch (Exception e) {
            System.out.println("\nOcurrió un error al procesar el archivo " + archivo.getName());
        }
        return contador; // Devuelve el conteo de la palabra en el archivo
    }
}
