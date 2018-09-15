package ca.esolutionsgroup.TaxManager.Utils;

import ca.esolutionsgroup.TaxManager.Utils.Tokenizer.TokenType;

/**
 * 
 */

public class ItemToken {
    private String itemValue;

    private TokenType itemType;

    public String getItemValue() {
        return this.itemValue;
    }

    public void setItemValue(final String value) {
        this.itemValue = value;
    }

    public TokenType getItemType() {
        return this.itemType;
    }

    public void setItemType(final TokenType value) {
        this.itemType = value;
    }
}