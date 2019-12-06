////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

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
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Bevande,"coca cola",2.5D),
   new MenuItemImpl(itemType.Panini,"panino al formaggio",3.D),
   new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.D));
  expectedSum = 11.5D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
	
 @Test
 public void testZeroItemsPricesSum() throws RestaurantBillException {
  List<MenuItem> order=Arrays.asList();
  expectedSum = 0.D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }

 @Test
 public void testScontoMetàPrezzoSuPaninoMenoCaro() throws RestaurantBillException{
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.D),
   new MenuItemImpl(itemType.Panini, "panino e cotto",3.D),
   new MenuItemImpl(itemType.Panini, "panino e bresaola",4.D),
   new MenuItemImpl(itemType.Panini, "panino e bresaola",4.D),
   new MenuItemImpl(itemType.Panini, "panino e bresaola",4.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Bevande, "pepsi 0.25L", 3.D));
  expectedSum = 32.5D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 @Test
 public void testNoScontoMetàPrezzoSuPaninoMenoCaroConMenuDi5Panini() throws RestaurantBillException{
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.D),
   new MenuItemImpl(itemType.Panini, "panino e bresaola",4.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Bevande, "pepsi 0.25L", 3.D));
  expectedSum = 23D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 
 @Test
 public void testScontoMetàPrezzoSuPaninoGratis() throws RestaurantBillException{
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.D),
   new MenuItemImpl(itemType.Panini, "panino e salame",0.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",4.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",5.D),
   new MenuItemImpl(itemType.Bevande, "pepsi 0.25L", 3.D));
  expectedSum = 28D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 
 @Test
 public void testScontoMetàPrezzoSuPaninoConCostoMinimoConPiùPrezziMinimi() throws RestaurantBillException{
  List<MenuItem> order=Arrays.asList(new MenuItemImpl(itemType.Fritti,"frittura di pesce",6.D),
   new MenuItemImpl(itemType.Panini, "panino e salame",4.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",4.D),
   new MenuItemImpl(itemType.Panini, "panino e porchetta",4.D),
   new MenuItemImpl(itemType.Panini, "panino e salame",5.D),
   new MenuItemImpl(itemType.Panini, "panino e prosciutto",6.D),
   new MenuItemImpl(itemType.Bevande, "pepsi 0.25L", 3.D));
  expectedSum = 30D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 
 @Test
 public void testScontoSeComproPiùDi50EuroInPaniniOFritti() throws RestaurantBillException {
  List<MenuItem> order=new LinkedList<>();
  order.add(new MenuItemImpl(itemType.Bevande,"coca",5.D));
  order.add(new MenuItemImpl(itemType.Fritti,"patatine",7.D));
  order.add(new MenuItemImpl(itemType.Fritti,"patatine",7.D));
  order.add(new MenuItemImpl(itemType.Fritti,"patatine",7.D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",5.D));
  order.add(new MenuItemImpl(itemType.Bevande,"coca",5.D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",5.D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  expectedSum = 70.65;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 
 @Test
 public void testScontoMetàPrezzoSuPaninoEComproPiùDi50EuroInPaniniOFritti() throws RestaurantBillException {
  List<MenuItem> order=new LinkedList<>();
  order.add(new MenuItemImpl(itemType.Bevande,"coca",5.D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger",5.D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger doppio",7.D));
  order.add(new MenuItemImpl(itemType.Fritti,"patatine",7.D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",5.D));
  order.add(new MenuItemImpl(itemType.Bevande,"coca",5.D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",5.D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  expectedSum = 66.6D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
 
 @Test
 public void testNoScontoSeComproPiùDi50EuroNonInPaniniOFritti() throws RestaurantBillException {
  List<MenuItem> order=new LinkedList<>();
  order.add(new MenuItemImpl(itemType.Bevande,"prosecco",10.5D));
  order.add(new MenuItemImpl(itemType.Fritti,"patatine",4.D));
  order.add(new MenuItemImpl(itemType.Bevande,"prosecco",10.5D));
  order.add(new MenuItemImpl(itemType.Bevande,"vino",14D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",7D));
  order.add(new MenuItemImpl(itemType.Bevande,"coca",5D));
  order.add(new MenuItemImpl(itemType.Bevande,"fanta",5.D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger",5.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger",5.5D));
  order.add(new MenuItemImpl(itemType.Panini,"hamburger triplo",12.5D));
  expectedSum = 79.5D;
  sum = bill.getOrderPrice(order);
  assertEquals(expectedSum,sum,0.D);
 }
}
