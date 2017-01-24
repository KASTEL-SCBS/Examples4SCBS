package webshop2.src;

import webshop2.src.util.Order;
import webshop2.src.util.OrderElement;
import webshop2.src.util.OrderHistory;

public class OrderDB implements OrdersToAccountIF {


	//The process is first shipping, then billing
	
        //toGenerate:
	//one model field per dataset and
	//for each dataset a determines class with \determines low outputs \by low inputs \preserving dataset-model-field
	//take the low-information from all interfaces provided or required by the component (Here: only external interfaces are modeled!).
	//
	//@ model \seq deliveryData;
	//
	//@ \determines \nothing
	//@ \by \nothing
	//@ \preserving deliveryData;


	private Order[] unshipped = new Order[0];
	private Order[] unbilled = new Order[0];
	private Order[] finished = new Order[0];
	
	
	public boolean makeNewOrder(OrderElement[] oe) {
		//add a new order
		Order[] oes = new Order[unshipped.length + 1];
		for (int j = 0; j < unshipped.length; j++) {
			oes[j] = unshipped[j]; 
		}
		oes[unshipped.length] = new Order(oe);
		this.unshipped = oes;
		return false;
	}

	public Order[] getUnshippedOrders() {
		Order[] os = this.unshipped;
		this.unshipped = new Order[0];
		Order[] temp = new Order[os.length + unbilled.length];
		/*@ maintaining 0 <= j && j <= unbilled.length &&
		  @    (\forall int l; 0 <= l && l < j; temp[l] != null);
		  @ modifies temp[*];
		  @ decreasing unbilled.length-j;
		  @*/
		for(int j = 0; j < unbilled.length; j++)
			temp[j] = this.unbilled[j];
		/*@ maintaining 0 <= k && k <= os.length && 
		  @        (\forall int l; 0 <= l && l < k+unbilled.length; temp[l] != null);
		  @ modifies temp[*];
		  @ decreasing os.length-k;
		  @*/
		for(int k = 0; k < os.length; k++) 
			temp[k+unbilled.length] = os[k];
		this.unbilled = temp;
		return os;
	}
	
	public Order[] getUnbilledOrders() {
		Order[] os = this.unbilled;
		this.unbilled = new Order[0];
		Order[] temp = new Order[os.length + finished.length];

		for(int j = 0; j < finished.length; j++)
			temp[j] = this.finished[j];

		for(int k = 0; k < os.length; k++) 
			temp[k+finished.length] = os[k];
		this.finished = temp;
		return os;
	}

	public OrderHistory getAllOrders() {
		return new OrderHistory(this.unshipped, this.unbilled, this.finished);
	}

}
