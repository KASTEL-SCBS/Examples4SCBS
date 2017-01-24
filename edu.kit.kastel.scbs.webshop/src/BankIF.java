package webshop2.src;

// Interface used by the system to access the bank for payments

public interface BankIF {

  //stereotype <<deliveryData includes \call, \result>>
  public boolean makePayment(char[] name, int ccnr, int cvc, int amount);

}
