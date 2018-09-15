package ca.esolutionsgroup.TaxManager.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

public class Tokenizer {

    public enum TokenType {
        QUANTITY, PRICE, IMPORTED, NAME;
    }

    public static List<ItemToken> parseInputString(final String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        List<ItemToken> itemTokens = new ArrayList<ItemToken>();

        String[] tokens = text.split(" ");
        for(String token : tokens) {
            ItemToken itemToken = new ItemToken();
            itemToken.setItemValue(token);
            itemToken.setItemType(identifyToken(token));
            itemTokens.add(itemToken);
        }

        return itemTokens;

    }

    private static TokenType identifyToken(final String value) {
        if (value != null && value.equalsIgnoreCase(TokenType.IMPORTED.name())) {
            return TokenType.IMPORTED;
        } else if (value != null && isNumeric(value)) {
            return TokenType.QUANTITY;
        } else if (value != null && isDecimal(value)) {
            return TokenType.PRICE;
        } else {
            return TokenType.NAME;
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static boolean isDecimal(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}