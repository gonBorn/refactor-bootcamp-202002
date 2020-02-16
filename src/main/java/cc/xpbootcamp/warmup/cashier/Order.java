package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

public class Order {
    Customer customer;
    List<LineItem> lineItems;

    public Order(String customerName, String address, List<LineItem> lineItems) {
        this.customer = new Customer(customerName, address);
        this.lineItems = lineItems;
    }

    public String getCustomerName() {
        return customer.name;
    }

    public String getCustomerAddress() {
        return customer.address;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
