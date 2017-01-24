package webshop2.src;

import webshop2.src.util.Bill;

public interface BillingIF {

        //stereotype <<deliveryData includes \call, \result.length, \result[*], \result.billingName.length, \result.billingName[*],
        //                  \result.billingAdress.length, \result.billingAdress[*], \result.paymentMethod,
        //                  \result.productsPayed.length, \result.productsPayed.[*],
        //                  \result.amountPerProduct.length, \result.amountPerProduct.[*],
        //                  \result.pricePerProduct.length, \result.pricePerProduct.[*]>>
	Bill[] getBillsToSend();
	
}
