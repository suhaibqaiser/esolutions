package ca.esolutionsgroup.TaxManager.Model;

import java.util.List;

/**
 * 
 */

public class Receipt {

    public String generateReceipt(final List<Goods> inventory) {
        StringBuffer receipt = new StringBuffer();
        if(inventory == null || inventory.size() == 0) {
            return "";
        }

        for(Goods goods : inventory) {
            receipt.append(goods.toString());
            receipt.append(System.lineSeparator());
        }

        return receipt.toString();
    }

    
}