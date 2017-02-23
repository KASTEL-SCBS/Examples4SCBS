package webshop2.src;

import webshop2.src.util.Bill;
import webshop2.src.util.Order;

public class Billing implements BillingIF {

	private BillingToAccountIF account;
	

	//toGenerate:
        //one model field per dataset and
        //for each dataset a determines class with \determines low outputs \by low inputs \preserving dataset-model-field
        //take the low-information from all interfaces provided or required by the component (Here: only external interfaces are modeled!).
        //
        //@ model \seq deliveryData;
        //
        //@ \determines this.getBillsToSend(\result.length, \result[*], \result.billingName.length, \result.billingName[*],
        //                  \result.billingAdress.length, \result.billingAdress[*], \result.paymentMethod,
        //                  \result.productsPayed.length, \result.productsPayed.[*],
        //                  \result.amountPerProduct.length, \result.amountPerProduct.[*],
        //                  \result.pricePerProduct.length, \result.pricePerProduct.[*])
        //@ \by this.getBillsToSend(\call)
        //@ \preserving deliveryData;




	public Billing(BillingToAccountIF account) {
		this.account = account;
	}
	
        
	public Bill[] getBillsToSend() { 
		
		Order[] toBill = account.getOrdersToBill();
		
		Bill[] res = new Bill[toBill.length];
		
		if (toBill.length == 0)
			return res;

		char[] adr = account.getBillingAdress();
		char[] name = account.getBillingName();
		int paymeth = account.getBillingCreditCard();
		
		
		for(int i = 0; i < toBill.length; i++) {
			int[] prods = new int[toBill[i].elementsInOrder.length];
			int[] amounts = new int[toBill[i].elementsInOrder.length];
			int[] prices = new int[toBill[i].elementsInOrder.length];
			
			
			for (int j = 0; j < toBill[i].elementsInOrder.length; j++) {
				prods[j] = toBill[i].elementsInOrder[j].productId;
				amounts[j] = toBill[i].elementsInOrder[j].amount;
				prices[j] = toBill[i].elementsInOrder[j].price;
			}
			
			res[i] = new Bill(name, adr, paymeth, prods, amounts, prices);
		}
		
		return res;
	}
    
}
