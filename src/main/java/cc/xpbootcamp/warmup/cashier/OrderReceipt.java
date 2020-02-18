package cc.xpbootcamp.warmup.cashier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static cc.xpbootcamp.warmup.cashier.Constant.*;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final char MULTIPLY = 'x';
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
        StringBuilder output = new StringBuilder();

        printHeader(output);

        printDate(output);

        printCustomInfo(output);

        printLineItems(output);

        calculateAndPrintTaxAndAmount(output);

        return output.toString();
    }

    private void printDate(StringBuilder output) {
        LocalDate date = order.getDate();
        output.append(DateTimeFormatter.ofPattern(YY_MM_DD).format(date));
        output.append(COMMA);
        output.append(WEEK_CHAR).append(WEEKS[date.getDayOfWeek().getValue()]);
        output.append(NEW_LINE);
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append(COMMA);
            output.append(TAB);
            output.append(lineItem.getPrice());
            output.append(MULTIPLY);
            output.append(lineItem.getQuantity());
            output.append(COMMA);
            output.append(TAB);
            output.append(lineItem.totalAmount());
            output.append(NEW_LINE);
        }
    }

    private void printTaxAndAmount(StringBuilder output, double totalSalesTax, double totalAmount) {
        output.append(SALES_TAX).append(COLON).append(totalSalesTax).append(NEW_LINE);
        double discount = 0;
        if (order.getDate().getDayOfWeek().getValue() == 3) {
            discount = totalAmount * DISCOUNT_RATE;
            output.append(DISCOUNT).append(COLON)
              .append(BigDecimal.valueOf(discount).setScale(2, RoundingMode.HALF_UP).doubleValue())
              .append(NEW_LINE);
        }
        output.append(TOTAL_AMOUNT).append(COLON)
          .append(BigDecimal.valueOf(totalAmount + totalSalesTax - discount).setScale(2, RoundingMode.HALF_UP).doubleValue())
          .append(NEW_LINE);
    }

    private void printCustomInfo(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
        output.append(NEW_LINE);
    }

    private void printHeader(StringBuilder output) {
        output.append(TITLE_OF_RECEIPT).append(NEW_LINE);
    }

    private void calculateAndPrintTaxAndAmount(StringBuilder output) {
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            totalSalesTax += lineItem.totalAmount() * TAX_RATE;
            totalAmount += lineItem.totalAmount();
        }
        printTaxAndAmount(output, totalSalesTax, totalAmount);
    }
}