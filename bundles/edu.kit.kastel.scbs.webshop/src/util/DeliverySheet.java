package webshop2.src.util;

// Contains all the information necessary for shipment

public class DeliverySheet {
  public char[] sendToAdress;
  public char[] sendToName;
  public int[] products;
  public int[] amountPerProduct;
  
  public DeliverySheet(char[] adr, char[] name, int[] prods, int[] amount) {
	  this.sendToAdress = adr;
	  this.sendToName = name;
	  this.products = prods;
	  this.amountPerProduct = amount;
  }
}
