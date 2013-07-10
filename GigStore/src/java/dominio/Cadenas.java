/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class Cadenas {
    
    public static String formatoUrl(String cadena){
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñç";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunc";
        String resultado = cadena.toLowerCase().replace(" ", "-");
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            resultado = resultado.replace(original.charAt(i), ascii.charAt(i));
        }
        
        return resultado;
    }
}
