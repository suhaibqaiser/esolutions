package ca.esolutionsgroup.TaxManager.Model;

import java.math.BigDecimal;
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
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal salesTax = BigDecimal.ZERO;

        for(Goods goods : inventory) {
            receipt.append(goods.toString());
            total = total.add(goods.getTotal());
            salesTax = salesTax.add(goods.getTotalTax());
            receipt.append(System.lineSeparator());
        }
        receipt.append("Sales Tax : " + salesTax.toString());
        receipt.append(System.lineSeparator());
        receipt.append("Total : " + total.toString());
        receipt.append(System.lineSeparator());
        return receipt.toString();
    }

    
}