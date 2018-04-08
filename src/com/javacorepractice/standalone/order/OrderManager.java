package com.javacorepractice.standalone.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javacorepractice.standalone.ordermanagement.model.OrderDetails;
import com.javacorepractice.standalone.ordermanagement.model.OrderItem;
import com.javacorepractice.standalone.ordermanagement.model.Product;
import com.javacorepractice.standalone.ordermanagement.model.ProductInventory;
import com.javacorepractice.standalone.ordermanagement.model.User;

public class OrderManager {

	
	private static OrderManager self=null;

	private Map<Integer,Product> productRepo;
	private Map<Integer,ProductInventory> productInventory;
	private Map<String,User> usersRepo;
	private Map<User,List<OrderDetails>> orderStack;
	
	
	
	
	private OrderManager(){
		
		productRepo = new HashMap<Integer,Product>(){
			{
				put(1, new Product(1,"REDMI","Xiaomi Redmi","EA",new BigDecimal("10000"),Calendar.getInstance().getTime()));
				put(2, new Product(2,"Samsung","Samsung","EA",new BigDecimal("50000"),Calendar.getInstance().getTime()));
				put(3, new Product(3,"Engage","Pocket Perfume","EA",new BigDecimal("60"),Calendar.getInstance().getTime()));
				put(4, new Product(1,"Philips","Philips Trimmer","EA",new BigDecimal("900"),Calendar.getInstance().getTime()));
				
			}
		};
		
		productInventory  = new HashMap<Integer, ProductInventory>(){
			{
				put(1, new  ProductInventory(productRepo.get(1), 10, new Date()));
				put(2, new ProductInventory(productRepo.get(2), 5, new Date()));
				put(3, new ProductInventory(productRepo.get(3), 50, new Date()));
				put(4, new ProductInventory(productRepo.get(4), 16, new Date()));
			}
		};
		
		usersRepo = new HashMap<String,User>(){
			{
				put("mani.challa1990@gmail.com", new User("mani.challa1990@gmail.com","Mani Challa","27","Bangalore BTM","9703779308","560120","Winthegame"));
				put("saisree.dasini@gmail.com", new User("saisree.dasini@gmail.com","Saisree Dasini","24","Bangalore BTM","9703779308","560120","Winthegame"));
				put("divya369@gmail.com", new User("divya369@gmail.com","Divya Surname","25","Koramangal","9703779308","560120","Nothing"));
			}
		};
		
		orderStack = new HashMap<User,List<OrderDetails>>();
	}
	
	
	
	
	public Map<Integer, Product> getProductRepo() {
		return productRepo;
	}




	public void setProductRepo(Map<Integer, Product> productRepo) {
		this.productRepo = productRepo;
	}




	public Map<Integer, ProductInventory> getProductInventory() {
		return productInventory;
	}




	public void setProductInventory(Map<Integer, ProductInventory> productInventory) {
		this.productInventory = productInventory;
	}




	public Map<String, User> getUsersRepo() {
		return usersRepo;
	}




	public void setUsersRepo(Map<String, User> usersRepo) {
		this.usersRepo = usersRepo;
	}




	public Map<User, List<OrderDetails>> getOrderStack() {
		return orderStack;
	}




	public void setOrderStack(Map<User, List<OrderDetails>> orderStack) {
		this.orderStack = orderStack;
	}




	//singleton design pattern.
	public static OrderManager getInstance(){
		
		if(self == null){
			synchronized(OrderManager.class){
				
				if(self==null){
					self = new OrderManager();
				}
			}
		}
		
		return self;
	}
	
	//order item processing 
	public boolean orderItemProcess(Integer productId, Integer quantity,User user, OrderDetails orderDetails) throws NoStockException{
		
		
		if(getProductInventory().containsKey(productId)){
			
			if(getProductInventory().get(productId).getQuantity() >= quantity){
				
				OrderItem orderItem = new OrderItem(getProductRepo().get(productId),
						quantity, new Date(), null, 
						getProductRepo().get(productId).getPrice().multiply(new BigDecimal(quantity)));
							
				orderDetails.getOrderItems().add(orderItem);
				orderDetails.setCumulativePrice(orderDetails.getCumulativePrice().add(orderItem.getLineItemPrice()));
				getProductInventory().get(productId).setQuantity(getProductInventory().get(productId).getQuantity() - quantity);
				
				if(null == getOrderStack().get(user)){
					getOrderStack().put(user, new ArrayList<>());
				}
				
				if(!getOrderStack().get(user).contains(orderDetails))
					getOrderStack().get(user).add(orderDetails);
				
			}else{
				throw new NoStockException(getProductRepo().get(productId).getProductCode(), "Stock not available, required ["+quantity+"], available ["+getProductInventory().get(productId).getQuantity()+"]");
			}
			
			return true;
		}
		return false;
	}
		
}
