package sv.edu.udb.www.utils;
import java.util.regex.*;

public class Validaciones {

    private static int entero;
    private static double decimal;

    public static boolean esEntero(String cadena) {
        try {
            entero = Integer.parseInt(cadena.trim());
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esEnteroPositivo(String cadena) {
        try {
            entero = Integer.parseInt(cadena.trim());
            if (entero <= 0) {
                return false;
            }
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean isEmpty(String mensaje) {
        return mensaje.trim().equals("");
    }

    public static boolean esDecimal(String cadena) {
        try {
            decimal = Double.parseDouble(cadena.trim());
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esDecimalPositivo(String cadena) {
        try {
            decimal = Double.parseDouble(cadena.trim());
            if (decimal <= 0) {
                return false;
            }
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esTelefono(String cadena) {
    	Pattern pat = Pattern.compile("[267][0-9]{3}-[0-9]{4}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }

    public static boolean esCodigoEditorial(String cadena) {
    	Pattern pat = Pattern.compile("EDI[0-9]{3}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean esCodigoAutor(String cadena) {
        Pattern pat = Pattern.compile("AUT[0-9]{3}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean esCodigoLibro(String cadena) {
        Pattern pat = Pattern.compile("LIB[0-9]{6}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }

}

