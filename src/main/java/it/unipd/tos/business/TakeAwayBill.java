////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

public interface TakeAwayBill {
 double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException;
}
