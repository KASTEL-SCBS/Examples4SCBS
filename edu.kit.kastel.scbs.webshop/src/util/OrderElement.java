package webshop2.src.util;

// Represents an element in an Order

public class OrderElement {

  public int productId;

  public int price;

  public int amount;

  /*@
    @ public normal_behavior
    @ requires true;
    @ ensures this.productId == prodId &&
    @         this.amount == amount2 && this.price == price2;
    @ modifies \nothing;
    @*/
  public OrderElement(int prodId, int amount2, int price2) {
	this.productId = prodId;
	this.price = price2;
	this.amount = amount2;
  }
  
} 
