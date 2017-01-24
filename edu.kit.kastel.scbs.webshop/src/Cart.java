package webshop2.src;

import webshop2.src.util.OrderElement;

// Logic for the shopping Cart

public class Cart implements CartIF, CartToAccountIF {


  //Required Interfaces
  private ProductDBIF prodDb;


  //toGenerate:
  //one model field per dataset and
  //for each dataset a determines class with \determines low outputs \by low inputs \preserving dataset-model-field
  //take the low-information from all interfaces provided or required by the component (Here: only external interfaces are modeled!).
  //
  //@ model \seq deliveryData;
  //
  //@ \determines \nothing
  //@ \by this.addToCart(\call, prodId, amount)
  //@ \preserving deliveryData;


  private OrderElement[] charItems = new OrderElement[0];
  
  public Cart(ProductDBIF prodDb) {
	  this.prodDb = prodDb;
  }
  
  public boolean addToCart(int prodId, int amount) {
    int price = prodDb.getProductPrice(prodId);
    OrderElement newOrder = new OrderElement(prodId, amount, price);
    
    //add the new element
    OrderElement[] it = new OrderElement[charItems.length + 1];
    for (int j=0; j < charItems.length; j++) {
      it[j] = charItems[j];
    }
    it[it.length-1] = newOrder;
    charItems = it;

    return true;
  }


  public OrderElement[] getCartContent() {
    return charItems;
  }


  public OrderElement[] getCartElementsForOrder() {
    OrderElement[] res = charItems;
    charItems = new OrderElement[0];
    return res;
  }

} 
