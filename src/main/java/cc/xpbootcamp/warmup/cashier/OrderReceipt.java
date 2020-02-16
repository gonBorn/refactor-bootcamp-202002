package cc.xpbootcamp.warmup.cashier;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    public static final double TAX_RATE = .10;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeader(output);

        // prints lineItems
        order.getLineItems().forEach(lineItem -> printItemLine(output, lineItem));

        TotalTax totalTax = calculateTax();

        printFooter(output, totalTax.totSalesTx, totalTax.totalAmount);

        return output.toString();
    }

    private TotalTax calculateTax() {
        TotalTax totalTax = new TotalTax();
        for (LineItem lineItem : order.getLineItems()) {
            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * TAX_RATE;
            totalTax.totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalTax.totalAmount += lineItem.totalAmount() + salesTax;
        }
        return totalTax;
    }


    private void printItemLine(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }

    private void printFooter(StringBuilder output, double totSalesTx, double totalAmount) {
        // prints the state tax
        output.append("Sales Tax").append('\t').append(totSalesTx);

        // print total amount
        output.append("Total Amount").append('\t').append(totalAmount);
    }

    private void printHeader(StringBuilder output) {
        // print headers
        output.append("======Printing Orders======\n");

        // print date, bill no, customer name
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    public static void main(String[] args) {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();
        System.out.println(output);
    }


    static class TotalTax {
        double totSalesTx = 0d;
        double totalAmount = 0d;
    }
}