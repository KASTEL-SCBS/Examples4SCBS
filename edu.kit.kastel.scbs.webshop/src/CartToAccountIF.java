package webshop2.src;

import webshop2.src.util.OrderElement;

//Interface used by the Account

public interface CartToAccountIF {

    /*@ public normal_behaviour
    @ requires true;
    @ ensures Main.hist == \seq_concat(\old(Main.hist), 
    @         \seq_concat(\seq_singleton(\dl_event(\dl_servcall, \dl_out, this, \dl_CartToAccountIF_getCartElementsForOrder, \seq_empty, \dl_heap)), 
    @                     \seq_singleton(\dl_event(\dl_servterm, \dl_in, this, \dl_CartToAccountIF_getCartElementsForOrder, \seq_singleton(\result), \dl_heap))));
    @ modifies Main.hist;
   */
  public /*@ helper */ OrderElement[] getCartElementsForOrder();

} 
