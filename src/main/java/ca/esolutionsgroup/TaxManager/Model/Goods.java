package ca.esolutionsgroup.TaxManager.Model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.inject.Named;

/**
 * 
 */
@Named
public class Goods {

    private Integer quantity;

    private static final BigDecimal DUTY_PERCENT = BigDecimal.valueOf(0.05D);

    private static final BigDecimal TAX_PERCENT = BigDecimal.valueOf(0.10D);

    private static final BigDecimal ROUND_PERCENT = BigDecimal.valueOf(20D);

    private String[] foodItems = {"chocolate"};

    private String[] bookItems = {"book"};

    private String[] medicalItems = {"pills"};

    private String name = "";

    private boolean isImported = false;

    private BigDecimal price;

    private BigDecimal salesTax;

    private BigDecimal duty;

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
            returnValue.append(": " + getTotal().toString());
        }
        return returnValue.toString();
    }

    public BigDecimal getTotal() {
        return price.add(getTotalTax()).setScale(2, RoundingMode.UP);
    }

    public BigDecimal getTotalTax() {
        return customRound(salesTax.add(duty)).setScale(2, RoundingMode.UP);
    }



    public BigDecimal customRound(final BigDecimal input) {
        if(input != null && input.doubleValue() > 0.0D) {
            return input.multiply(ROUND_PERCENT).setScale(0, RoundingMode.UP).abs()
            .setScale(2, RoundingMode.UP).divide(ROUND_PERCENT);
        }
        return input;
        
    }

    private void applyTaxes() {
        if(!isExempted()) {
            salesTax = price.multiply(TAX_PERCENT);
        } else {
            salesTax = BigDecimal.ZERO;
        }
    }

    private boolean isExempted() {
        for(String item : foodItems) {
            if(name.contains(item)) {
                return true;
            }
        }
        for(String item : bookItems) {
            if(name.contains(item)) {
                return true;
            }
        }
        for(String item : medicalItems) {
            if(name.contains(item)) {
                return true;
            }
        }
        return false;        
    }

    private void applyDuty() {
        if(isImported) {
            duty = price.multiply(DUTY_PERCENT);
        } else {
            duty = BigDecimal.ZERO;
        }
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
        applyTaxes();
        applyDuty();
    }

}