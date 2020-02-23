package cc.xpbootcamp.warmup.cashier;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final String TITLE_OF_RECEIPT = "===== 老王超市，值得信赖 =====";
    private static final String SALES_TAX = "税额";
    private static final String DISCOUNT = "折扣";
    private static final String TOTAL_AMOUNT = "总价";
    private static final double DISCOUNT_RATE = 0.02;
    private static final double TAX_RATE = 0.1;

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return getHeader()
            + getDate()
            + getCustomer()
            + getLineItems()
            + getAmount();
    }

    private String getHeader() {
        return TITLE_OF_RECEIPT + lineSeparator();
    }

    private String getDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy年M月dd日，EEE", Locale.CHINA);
        return dateFormat.format(order.getDate()) + lineSeparator();
    }

    private String getCustomer() {
        return String.format("%s，%s", order.getCustomerName(), order.getCustomerAddress()) + lineSeparator();
    }

    private String getLineItems() {
        return order.getLineItems().stream()
            .map(LineItem::getLineItemInformation)
            .collect(Collectors.joining(lineSeparator())) + lineSeparator();
    }

    private double calculateAmountWithoutTaxAndDiscount() {
        return order.getLineItems().stream()
            .mapToDouble(LineItem::getTotalAmount)
            .sum();
    }

    private double calculateSalesTax(double amount) {
        return amount * TAX_RATE;
    }

    private double calculateDiscount(double amount) {
        return isDiscountDay() ? amount * DISCOUNT_RATE : 0;
    }

    private boolean isDiscountDay() {
        return order.getDate().getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    private String getAmount() {
        double amount = calculateAmountWithoutTaxAndDiscount();
        double salesTax = calculateSalesTax(amount);
        double discount = calculateDiscount(amount);
        double totalAmount = amount - discount + salesTax;

        return String.format("%s：%.2f", SALES_TAX, salesTax)
            + lineSeparator()
            + (isDiscountDay() ? String.format("%s：%.2f", DISCOUNT, discount) + lineSeparator() : "")
            + String.format("%s：%.2f", TOTAL_AMOUNT, totalAmount)
            + lineSeparator();
    }
}