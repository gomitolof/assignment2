////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class RestaurantBillException extends Exception implements TakeAwayBillException{
 private static final long serialVersionUID = 1L;
 public RestaurantBillException() {}
 public RestaurantBillException(boolean e) {
  System.err.print("ERRORE: ordinazione con più di 30 elementi, alleggerire il carico!");
 }
}
