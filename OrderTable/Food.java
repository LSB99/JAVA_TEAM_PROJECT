package bean;

public class Food extends Object{
	private int id = 0;
	private String name;
	private int amount;
	//private String size;
	private String pay;
	private int price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/*public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}*/
	
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return "Food [id=" + id +", name=" + name + ", amount=" + amount + ", pay=" + pay + ", price=" + price
				+ "]";
	}
	/*public String toString() {
		return "Food [name=" + name + ", amount=" + amount + ", pay=" + pay + ", price=" + price
				+ "]";
	}*/
	
}
