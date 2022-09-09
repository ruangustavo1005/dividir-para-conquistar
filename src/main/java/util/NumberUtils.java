package util;

public abstract class NumberUtils {
    
    public static boolean isInt(String value) {
        return value.matches("[0-9]+");
    }
    
    public static boolean isFloat(String value) {
        String valueSemVirgula = value.replace(",", "");
        return value.length() - valueSemVirgula.length() <= 1 && isInt(valueSemVirgula);
    }
    
}
