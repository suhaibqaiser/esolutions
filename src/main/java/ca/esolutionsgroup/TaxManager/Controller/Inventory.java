package ca.esolutionsgroup.TaxManager.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import ca.esolutionsgroup.TaxManager.Model.Goods;
import ca.esolutionsgroup.TaxManager.Model.Receipt;
import ca.esolutionsgroup.TaxManager.Utils.ItemToken;
import ca.esolutionsgroup.TaxManager.Utils.Tokenizer;
import ca.esolutionsgroup.TaxManager.Utils.Validator;
import ca.esolutionsgroup.TaxManager.Utils.Tokenizer.TokenType;

@Named
public class Inventory {

  private List<Goods> goods = new ArrayList<Goods>();

  private String fileText;

  private Receipt receipt = new Receipt();

  private String receiptText;

  public String getReceiptText() {
    return this.receiptText;
  }

  public String showGreeting() {
    return "Receipt has been processed and available for display";
  }

  public void setReceiptText(final String value) {
    this.receiptText = value;
  }

  public String getFileText() {
    return this.fileText;
  }

  public void setFileText(final String value) {
    this.fileText = value;
  }

  public List<Goods> getGoods() {
    return this.goods;
  }

  public void setGoods(final List<Goods> value) {
    this.goods = value;
  }

  public void processInventory(ActionEvent action) {
    List<ItemToken> itemTokens;

    if(!Validator.validateText(this.fileText)) {
      FacesMessage message = new FacesMessage("Error", "File contents cannot be empty");
      FacesContext.getCurrentInstance().addMessage(null, message); 
      return;
    }

    String[] inputLines = this.fileText.split("\\r?\\n");
    List<Goods> goodsList = new ArrayList<Goods>();
    if (inputLines != null) {
      for (String inputLine : inputLines) {
        itemTokens = Tokenizer.parseInputString(inputLine);
        Goods goods = new Goods();
        
        for (ItemToken token : itemTokens) {
          if (token.getItemType().equals(TokenType.IMPORTED)) {
            goods.setIsImported(true);
          } else if (token.getItemType().equals(TokenType.NAME)) {
            goods.appendName(token.getItemValue() + " ");
          } else if (token.getItemType().equals(TokenType.QUANTITY)) {
            goods.setQuantity(Integer.valueOf(token.getItemValue()));
          } else if (token.getItemType().equals(TokenType.PRICE)) {
            goods.setPrice(BigDecimal.valueOf(Double.valueOf(token.getItemValue())));
          }
        }
        goodsList.add(goods);
      }
    }
    setGoods(goodsList);
    this.receiptText = receipt.generateReceipt(getGoods());
  }

}