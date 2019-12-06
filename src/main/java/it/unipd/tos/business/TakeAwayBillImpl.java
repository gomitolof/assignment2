////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.util.NoSuchElementException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType;
import it.unipd.tos.business.exception.RestaurantBillException;

public class TakeAwayBillImpl implements TakeAwayBill{
 @Override
 public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException{
  double sum=0.D;
  long count=itemsOrdered.stream().filter(x->x.getType()==itemType.Panini).count();
  if (itemsOrdered.size() == 0) {
   return 0.0;
  }
  else if(itemsOrdered.size() >= 31) {
   throw new RestaurantBillException(true);
  }
  else if (count >= 5) {
   double min = itemsOrdered.stream().filter(x->x.getType()==itemType.Panini).mapToDouble(x->x.getPrice()).min().orElseThrow(NoSuchElementException::new);
   boolean visited=false;
   for(MenuItem item: itemsOrdered) {
    if(item.getPrice() == min && !visited && item.getType()==itemType.Panini) {
     sum=sum+min/2.D;
     visited=true;
    }
    else {
     sum=sum+item.getPrice();
    }
   }
  }
  else {
   sum = itemsOrdered.stream().mapToDouble(e->e.getPrice()).sum();
  }
  if(isInOffer(itemsOrdered)) {
   sum=sum-sum*0.1D;
  }
  sum = sum < 10D ? sum+=0.5D : sum;
  return sum;
 }
 
 public boolean isInOffer(List<MenuItem> itemsOrdered) {
  double sum=0.D;
  for(MenuItem item: itemsOrdered) {
   if(item.getType()==itemType.Panini || item.getType()==itemType.Fritti) {
    sum+=item.getPrice();
   }
  }
  return sum >= 50 ? true : false;
 }
}
