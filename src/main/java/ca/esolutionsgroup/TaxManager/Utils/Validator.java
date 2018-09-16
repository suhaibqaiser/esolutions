package ca.esolutionsgroup.TaxManager.Utils;

/**
 * 
 */

public class Validator {

    public static boolean validateText(final String text) {
        if(text != null && !text.isEmpty()) {
            return true;
        }
        return false;
    }
}