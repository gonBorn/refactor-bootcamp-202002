package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.util.List;

public class Order {
    String customerName;
    String addr;
    List<LineItem> lineItemList;
    LocalDate date;

    public Order(String customName, String addr, List<LineItem> lineItemList, LocalDate date) {
        this.customerName = customName;
        this.addr = addr;
        this.lineItemList = lineItemList;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }
}
