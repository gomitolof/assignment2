////////////////////////////////////////////////////////////////////
// Francesco Freda 1143643
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.business.exception.RestaurantBillException;

public class MenuItemImpl implements MenuItem{
 itemType type;
 String name;
 double price;

 public MenuItemImpl(itemType type, String name, double price) throws RestaurantBillException {
  super();
  this.type=type;
  this.name=name;
  if(price < 0) {
   throw new RestaurantBillException();
  }
  this.price=price;
 }

 @Override
 public itemType getType() {
  return type;
 }

public void setType(itemType type) {
 this.type = type;
}

 @Override
 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 @Override
 public double getPrice() {
  return price;
 }

 public void setPrice(double price) throws RestaurantBillException{
  if (price < 0) {
   throw new RestaurantBillException();
  }
  this.price = price;
 }
}