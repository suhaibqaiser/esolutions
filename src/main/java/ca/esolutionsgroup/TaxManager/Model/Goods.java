package ca.esolutionsgroup.TaxManager.Model;

import java.math.BigDecimal;

import javax.inject.Named;

/**
 * 
 */
@Named
public class Goods {

    private Integer quantity;

    private String name = "";

    private boolean isImported = false;

    private BigDecimal price;

    @Override
    public String toString() {
        StringBuffer returnValue = new StringBuffer();

        if(quantity != null) {
            returnValue.append(quantity.toString() + " ");
        }        
        if(isImported) {
            returnValue.append("imported ");
        }
        if(name != null) {
            returnValue.append(name + " ");
        }        
        if(price != null) {
            returnValue.append(": " + price.toString());
        }
        return returnValue.toString();
    }

    public void appendName(final String value) {
        this.name = this.name.concat(value);
    }
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer value) {
        this.quantity = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    public Boolean isImported() {
        return this.isImported;
    }

    public void setIsImported(final Boolean value) {
        this.isImported = value;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal value) {
        this.price = value;
    }

}