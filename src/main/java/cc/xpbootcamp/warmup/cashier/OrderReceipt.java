package cc.xpbootcamp.warmup.cashier;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private final double TAX_RATE = 0.1;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeader(output);

        printCustomInfo(output);

        printLineItems(output);

        calculateAndPrintTaxAndAmount(output);

        return output.toString();
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
        }
    }

    private void printTaxAndAmount(StringBuilder output, double totalSalesTax, double totalAmount) {
        output.append("Sales Tax").append('\t').append(totalSalesTax);
        output.append("Total Amount").append('\t').append(totalAmount + totalSalesTax);
    }

    private void printCustomInfo(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printHeader(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }

    private void calculateAndPrintTaxAndAmount(StringBuilder output) {
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for(LineItem lineItem: order.getLineItems()) {
            totalSalesTax += lineItem.totalAmount() * TAX_RATE;
            totalAmount += lineItem.totalAmount();
        }
        printTaxAndAmount(output, totalSalesTax, totalAmount);
    }
}