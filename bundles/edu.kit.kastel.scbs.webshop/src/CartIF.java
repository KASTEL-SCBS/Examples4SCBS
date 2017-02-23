package webshop2.src;

import webshop2.src.util.OrderElement;

// Interface for the customer to access the shopping cart

public interface CartIF {

  //stereotype <<deliveryData includes \call, prodId, amount>>
  public boolean addToCart(int prodId, int amount);

  public OrderElement[] getCartContent();

} 
