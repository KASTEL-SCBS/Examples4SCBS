package webshop2.src;

import webshop2.src.util.Order;
import webshop2.src.util.OrderElement;

public class Account implements AccountIF, BillingToAccountIF, DeliveryToAccount {

      
        //toGenerate:
        //one model field per dataset and
        //for each dataset a determines class with \determines low outputs \by low inputs \preserving dataset-model-field
        //take the low-information from all interfaces provided or required by the component (Here: only external interfaces are modeled!).
        //
        //@ model \seq deliveryData;
        //
        //@ \determines bank.makePayment(\call)
        //@ \by this.orderElementsInCart(\call), 
        //@     this.setName(\call, name.length, name[*]),
        //@     this.setAdress(\call, address.length, address[*]),
        //@     bank.makePayment(\result)
        //@ \preserving deliveryData;

	private CartToAccountIF cart;
	private BankIF bank;
	private OrdersToAccountIF orders;
	
	private char[] name;
	private char[] adress;
	private int creditcardnr;
	private int cvc;
	
	public Account(CartToAccountIF cart, BankIF bank, OrdersToAccountIF orders) {
		this.cart = cart;
		this.bank = bank;
		this.orders = orders;
		
		name = new String("No Name").toCharArray();
		adress = new String("No Street").toCharArray();
	}
	
	
	public boolean setName(char[] name) {
                if (name == null) {
                  this.name = new char[0];
		} else {
		  this.name = name;
		}
		return true;
	}

	
	public boolean setAdress(char[] adress) {
		this.adress = adress;
		return true;
	}

	
	public boolean setCCNr(int ccnr) {
		this.creditcardnr = ccnr;
		return true;
	}

	
	public boolean setCVC(int cvc) {
		this.cvc = cvc;
		return true;
	}

	
	public char[] getBillingAdress() {
		return this.adress;
	}

	
	public int getBillingCreditCard() {
		return this.creditcardnr % 10000;
	}
	
	
	public Order[] getOrdersToBill() {
		return orders.getUnbilledOrders();
	}

	
	public char[] getName() {
		return this.name;
	}

	
        public char[] getBillingName() {
		return this.name;
        }
	

        
	public boolean orderElementsInCart() {
		OrderElement[] oes = cart.getCartElementsForOrder();		
		int sum = 0;

		for (int i = 0; i < oes.length; i++)
			sum += oes[i].price*oes[i].amount;
		if (bank.makePayment(this.name, this.creditcardnr, this.cvc, sum)) {
			orders.makeNewOrder(oes);
			return true;
		} else {
			//purchase failed for undisclosed reasons
			return false;
		}
	}

	
	public Order[] getOrdesForDelivery() {
		return orders.getUnshippedOrders();
	}

	
        
	public char[] getAdress() {
		return this.adress;
	}
	
	

}
