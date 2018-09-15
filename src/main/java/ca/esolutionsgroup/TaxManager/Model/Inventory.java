package ca.esolutionsgroup.TaxManager.Model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import ca.esolutionsgroup.TaxManager.Utils.ItemToken;

@Named
public class Inventory {

  private List<Goods> goods = new ArrayList<Goods>();
 
  public List<Goods> getGoods() {
    return this.goods;
  }

  public void setGoods(final List<Goods> value) {
    this.goods = value;
  }

  public String showGreeting() {
    return "Hello ";
  }
}