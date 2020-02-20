package cc.xpbootcamp.warmup.cashier;

public class LineItem {
	private String desc;
	private double price;
	private int quantity;

	public LineItem(String desc, double price, int quantity) {
		super();
		this.desc = desc;
		this.price = price;
		this.quantity = quantity;
	}

	public String getDescription() {
		return desc;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    double getTotalAmount() {
        return price * quantity;
    }

	String getLineItemInformation() {
		return String.format("%s,\t%.2f\tx\t%s,\t%.2f", getDescription(), getPrice(), getQuantity(), getTotalAmount());
	}
}