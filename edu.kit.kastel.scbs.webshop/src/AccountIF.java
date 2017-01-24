package webshop2.src;



//Interface provided to the Customer to access the account

public interface AccountIF {
  
  //stereotype <<deliveryData includes \call>>
  public boolean orderElementsInCart();
	
  //stereotype <<deliveryData includes \call, name.length, name[*]>>
  public boolean setName(char[] name);

  //stereotype <<deliveryData includes \call, address.length, address[*]>>
  public boolean setAdress(char[] address);

  public boolean setCCNr(int ccnr);

  public boolean setCVC(int cvc);

} 
