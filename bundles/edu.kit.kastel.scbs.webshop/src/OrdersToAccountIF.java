package webshop2.src;

import webshop2.src.util.Order;
import webshop2.src.util.OrderElement;
import webshop2.src.util.OrderHistory;

// IF used by account to access orders

public interface OrdersToAccountIF {

  public boolean makeNewOrder(OrderElement[] oe);

  public Order[] getUnbilledOrders();

  public Order[] getUnshippedOrders();

  public OrderHistory getAllOrders();

} 
