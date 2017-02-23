package webshop2.src;

import webshop2.src.util.DeliverySheet;
import webshop2.src.util.Order;

public class Delivery implements DeliveryIF {


	private DeliveryToAccount account;
	
        //toGenerate:
	//one model field per dataset and
	//for each dataset a determines class with \determines low outputs \by low inputs \preserving dataset-model-field
	//take the low-information from all interfaces provided or required by the component (Here: only external interfaces are modeled!).
	//
	//@ model \seq deliveryData;
	//
	//@ \determines this.deliverySheets(\result.sendToAdress.length, \result.sendToAdress[*],
        //@                \result.sendToName.length, \result.sendToName[*],
        //@                \result.products.length, \result.products[*],
        //@                \result.amountPerProduct.length, \result.amountPerProduct[*])
	//@ \by this.getDeliverySheets(\call)
	//@ \preserving deliveryData;



	public Delivery(DeliveryToAccount account) {
		this.account = account;
	}
	
	
	public DeliverySheet[] getDeliverySheets() {
		Order[] toSend = account.getOrdesForDelivery();
		
		DeliverySheet[] res = new DeliverySheet[toSend.length];
		
		if (res.length == 0)
			return res;
		
		char[] adr = account.getAdress();
		char[] name = account.getName();
		
		for (int i = 0; i < toSend.length; i++) {
			int[] prods = new int[toSend[i].elementsInOrder.length];
			int[] amount = new int[toSend[i].elementsInOrder.length];

			for (int j = 0; j < toSend[i].elementsInOrder.length; j++) {
				prods[j] = toSend[i].elementsInOrder[j].productId;
				amount[j] = toSend[i].elementsInOrder[j].amount;
			}
			
			res[i] = new DeliverySheet(adr, name, prods, amount);
		}
		
		
		
		return res;
	}

}
