package webshop2.src;

import webshop2.src.util.Order;

public interface DeliveryToAccount {

    Order[] getOrdesForDelivery();
    
    char[] getName();
	
   char[] getAdress();
    
}
