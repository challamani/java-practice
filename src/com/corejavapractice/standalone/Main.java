package com.corejavapractice.standalone;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.corejavapractice.standalone.order.NoStockException;
import com.corejavapractice.standalone.order.OrderManager;
import com.corejavapractice.standalone.order.model.OrderDetails;
import com.corejavapractice.standalone.order.model.OrderItem;
import com.corejavapractice.standalone.order.model.Product;
import com.corejavapractice.standalone.order.model.ProductInventory;
import com.corejavapractice.standalone.order.model.User;

public class Main {

	private OrderManager orderManager;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main mainObject = new Main(OrderManager.getInstance());
		System.out.println("enter user# and password: ");
		String userId = sc.next();
		String password = sc.next();

		//System.out.println("userId and password"+userId+","+password);
		if (mainObject.doLogin(userId, password)) {
			mainObject.setUser(mainObject.getOrderManager().getUsersRepo().get(userId));
			System.out.println("Welcome to order management");

			Integer wannacontinue = 0;
			do {
				mainObject.displayMenu();
				System.out.println("Choose one option: ");
				Integer opInteger = sc.nextInt();

				switch (opInteger) {
					case 1:
						mainObject.displayProducts();
						break;
					case 2:
						mainObject.displayInventory();
						break;
					case 3:
						mainObject.processOrderBooking(sc, null);
						break;
					case 4:
						mainObject.displayOrderStack();
						break;
					case 5:
						System.out.println("Thank you for your time with us :)");
						System.exit(0);
						break;
					default:
						break;
				}
				System.out.println("Do you wanna continue features execution (1/0) ?");
				wannacontinue = sc.nextInt();
			} while (wannacontinue != 0);
		} else {
			System.out.println("Invalid user details in Mani database");
		}
	}

	public Main(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}


	private boolean doLogin(String userId, String password) {

		if (orderManager.getUsersRepo().containsKey(userId)) {
			User user = orderManager.getUsersRepo().get(userId);
			return (user.getId().equals(userId) && user.getPassword().equals(password));
		}
		return false;
	}

	private void displayProducts() {
		for (Map.Entry<Integer, Product> prEntry : orderManager.getProductRepo().entrySet()) {
			System.out.println(prEntry.getKey() + " :: " + prEntry.getValue().getProductCode() + ", " + prEntry.getValue().getProductionName() + ", " + prEntry.getValue().getUnits() + ", " + prEntry.getValue().getPrice() + " INR");
		}
	}

	private void displayMenu() {
		System.out.println("\n 1. Product List \n 2. Available Inventory \n 3. Order Booking \n 4. Order Stack \n 5. Exit");
	}

	private void displayInventory() {
		for (Map.Entry<Integer, ProductInventory> prEntry : orderManager.getProductInventory().entrySet()) {
			System.out.println(prEntry.getKey() + " :: " + prEntry.getValue().getProduct().getProductionName() + " Quantity: " + prEntry.getValue().getQuantity());
		}
	}

	private void processOrderBooking(Scanner sc, OrderDetails orderDetails) {

		Integer wannaContinue = 0;
		displayProducts();

		System.out.println("Choose product and quantity respectively ?");
		Integer productId = sc.nextInt();
		Integer quantity = sc.nextInt();

		if (null == orderDetails) {
			orderDetails = new OrderDetails();
			orderDetails.setOrderItems(new ArrayList<OrderItem>());
			orderDetails.setCumulativePrice(new BigDecimal("0.00"));
			orderDetails.setOrderDate(new Date());
			orderDetails.setOrderCode(new Long(Calendar.getInstance().getTime().getTime()).toString());
		}

		try {
			if (orderManager.orderItemProcess(productId, quantity, this.getUser(), orderDetails)) {
				System.out.println("Cart item: " + orderManager.getProductRepo().get(productId).getProductionName() + ", Qty " + quantity + " " + orderManager.getProductRepo().get(productId).getUnits());
			}

		} catch (NoStockException e) {
			System.out.println("message  [" + e.getMessage() + "]");
		}

		System.out.println("do you wanna continue product purchase (1/0) ?");
		wannaContinue = sc.nextInt();
		if (wannaContinue > 0) {
			//self recursive function.
			processOrderBooking(sc, orderDetails);
		}
	}


	private void displayOrderStack() {
		for (Map.Entry<User, List<OrderDetails>> orderStackIteration : orderManager.getOrderStack().entrySet()) {
			System.out.println("User: " + orderStackIteration.getKey().getName() + "\n--------------------------------------------------------");

			for (OrderDetails orderDetails : orderStackIteration.getValue()) {
				System.out.println("Order# " + orderDetails.getOrderCode() + ", " + orderDetails.getOrderDate() + ", " + orderDetails.getCumulativePrice() + " INR");

				for (OrderItem orderItem : orderDetails.getOrderItems()) {
					System.out.println(orderItem.getProduct().getProductionName() + ", " + orderItem.getQuantity() + ", " + orderItem.getLineItemPrice() + " INR");
				}
				System.out.println("---------------------------------------------------");
			}
		}
	}
}