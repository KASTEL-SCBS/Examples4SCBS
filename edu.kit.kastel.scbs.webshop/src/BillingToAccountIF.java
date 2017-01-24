package webshop2.src;

import webshop2.src.util.Order;

public interface BillingToAccountIF {


   char[] getBillingAdress();
	
   int getBillingCreditCard();
	
   char[] getBillingName();
	
   Order[] getOrdersToBill();
	
}
