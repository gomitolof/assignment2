////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.model.MenuItem;

import it.unipd.tos.business.exception.RestaurantBillException;

public class TakeAwayBillImpl implements TakeAwayBill{
 @Override
 public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException{
  if (itemsOrdered.size() == 0) {
   return 0.0;
  }
  double sum = itemsOrdered.stream().mapToDouble(e->e.getPrice()).sum();
  return sum;
 }
}
