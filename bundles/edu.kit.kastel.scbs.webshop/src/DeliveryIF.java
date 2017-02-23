package webshop2.src;

import webshop2.src.util.DeliverySheet;

public interface DeliveryIF {
  public char[] sendToAdress;
  public char[] sendToName;
  public int[] products;
  public int[] amountPerProduct;

        //stereotype <<deliveryData includes \call, \result.sendToAdress.length, \result.sendToAdress[*],
        //                   \result.sendToName.length, \result.sendToName[*],
        //                   \result.products.length, \result.products[*],
        //                   \result.amountPerProduct.length, \result.amountPerProduct[*]>>
	public DeliverySheet[] getDeliverySheets();
	
}
