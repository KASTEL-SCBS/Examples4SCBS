package webshop2.src.util;

 
public class OrderHistory {

  public Order[] unshippedOrders;
  public Order[] unbilledOrders;
  public Order[] finishedOrders;

  public OrderHistory(Order[] unshipped, Order[] unbilled, Order[] finished) {
    this.unshippedOrders = unshipped;
    this.unbilledOrders = unbilled;
    this.finishedOrders = finished;
  }
  
}