package net.skhu.dto;

public class Order {

	int orderId;
	String foodName;
	int amount;
	int price;

	public Order() {
	}

	public Order(int orderId, String foodName, int amount, int price) {
		this.orderId = orderId;
		this.foodName = foodName;
		this.amount = amount;
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
