package ca.esolutionsgroup.TaxManager.Model;

import java.math.BigDecimal;

import javax.inject.Named;

/**
 * 
 */
@Named
public class Goods {

    private Integer quantity;

    private String name;

    private boolean isImported;

    private BigDecimal price;

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