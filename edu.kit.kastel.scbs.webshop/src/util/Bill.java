package webshop2.src.util;

//Contains all the information necessary for billing

public class Bill {

  public char[] billingName;
  public char[] billingAdress;
  public int paymentMethod;
  
  public int[] productsPayed;
  public int[] amountPerProduct;
  public int[] pricePerProduct;
  
    /*@
    @ public normal_behavior
    @ requires true;
    @ ensures this.billingName == name && this.billingAdress == adr &&
    @         this.paymentMethod == pay && this.productsPayed == prods && 
    @         this.amountPerProduct == amounts && this.pricePerProduct == prices;
    @ modifies \nothing;
    @*/
  public Bill(char[] name, char[] adr, int pay, int[] prods, int[] amounts, int[] prices) {
	  this.billingName = name;
	  this.billingAdress = adr;
	  this.paymentMethod = pay;
	  this.productsPayed = prods;
	  this.amountPerProduct = amounts;
	  this.pricePerProduct = prices;
  }
	
}
