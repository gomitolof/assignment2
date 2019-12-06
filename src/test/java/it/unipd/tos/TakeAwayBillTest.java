////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.TakeAwayBillImpl;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemImpl;
import it.unipd.tos.model.itemType;

public class TakeAwayBillTest {
 TakeAwayBill bill=new TakeAwayBillImpl();
 double expectedSum=0;
 double sum=0;
 @Test
 public void testItemsPricesSum() throws RestaurantBillException {
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Bevande,"coca cola",2.50),
  new MenuItemImpl(itemType.Panini,"panino al formaggio",3.0),
  new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.0));
  expectedSum = 11.50;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0);
 }
	
 @Test
 public void testZeroItemsPricesSum() throws RestaurantBillException {
  List<MenuItem> order=Arrays.asList();
  expectedSum = 0.00;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0);
 }
}
