////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItemImpl;
import it.unipd.tos.model.itemType;

public class MenuItemTest {
 MenuItemImpl item;
 
 @Before
 public void before() throws RestaurantBillException {
  item= new MenuItemImpl(itemType.Panini, "cotto e mozzarella", 5.0);
 }	

 @Test
 public void testSetTypeTest() {
  itemType oldType=item.getType();
  itemType newType=itemType.Bevande;
  item.setType(newType);
  assertEquals(newType, item.getType());
  assertNotEquals(oldType, item.getType());
 }
	
 @Test
 public void testSetName() {
  String newName="crudo e ricotta";
  String oldName=item.getName();
  item.setName(newName);
  assertEquals(newName, item.getName());
  assertNotEquals(oldName, item.getName());
 }

 @Test
 public void testSetPrice() throws RestaurantBillException {
  double newPrice=8.0D;
  double oldPrice=item.getPrice();
  item.setPrice(newPrice);
  assertEquals(newPrice, item.getPrice(),0.0);
  assertNotEquals(oldPrice, item.getPrice());
 }
 
 @Test (expected = RestaurantBillException.class)
 public void testNegativePriceInConstructor_ThrowsException() throws RestaurantBillException{
  @SuppressWarnings("unused")
  MenuItemImpl negative= new MenuItemImpl(itemType.Bevande,"coca",-3.0);
 }
 
 @Test (expected = RestaurantBillException.class)
 public void testSetNegativePrice_ThrowsException() throws RestaurantBillException{
  item.setPrice(-3.0);
 }
}
