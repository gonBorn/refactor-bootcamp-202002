package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class OrderReceiptTest {
    private List<LineItem> lineItems = new ArrayList<LineItem>() {{
        add(new LineItem("milk", 10.0, 2));
        add(new LineItem("biscuits", 5.0, 5));
        add(new LineItem("chocolate", 20.0, 1));
    }};
    private LocalDate tuesday = LocalDate.of(2020, 2, 18);
    private LocalDate wednesday = LocalDate.of(2020, 2, 19);

    @Test
    void shouldPrintCustomerInformationOnOrder() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>(), tuesday);
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("Mr X"));
        assertThat(output, containsString("Chicago, 60601"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, tuesday));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk,\t10.00\tx\t2,\t20.00\n"));
        assertThat(output, containsString("biscuits,\t5.00\tx\t5,\t25.00\n"));
        assertThat(output, containsString("chocolate,\t20.00\tx\t1,\t20.00\n"));
        assertThat(output, containsString("税额：6.50"));
        assertThat(output, containsString("总价：71.50"));
    }

    @Test
    public void shouldPrintDate() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, tuesday));

        String output = receipt.printReceipt();

        assertThat(output, containsString("2020年2月18日，星期二"));
    }

    @Test
    public void shouldPrintDiscount() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems, wednesday));

        String output = receipt.printReceipt();

        assertThat(output, containsString("税额：6.50"));
        assertThat(output, containsString("折扣：1.30"));
        assertThat(output, containsString("总价：70.20"));
    }
}