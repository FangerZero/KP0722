package kp0722;

import java.io.Console;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Agreement {
    String toolCode;
    String toolType;
    String toolBrand;
    int rentalDays;
    LocalDate checkoutDate;
    LocalDate dueDate;
    int chargableDays;
    BigDecimal preDiscountTotal;
    int discount;
    BigDecimal discountAmt;
    BigDecimal finalCharge;
    
    public void displayAgreement() {
        Console console = System.console();
        console.format("**********************\n");
        console.format("AGREEMENT\n");
        console.format("**********************\n");
        console.format("Tool Code: %s\n", toolCode);
        console.format("Tool Type: %s\n", toolType);
        console.format("Toold Brand: %s\n", toolBrand);
        console.format("Rental Days: %s\n", rentalDays);
        console.format("Checkout Date: %s\n", dateFormat(checkoutDate));
        console.format("Due Date: %s\n", dateFormat(dueDate));
        console.format("Chargeable Days: %s\n", chargableDays);
        console.format("Pre-discount Total: %s\n", currencyFormat(preDiscountTotal));
        console.format("Discount: %s%%\n", discount);
        console.format("Discount Amount: %s\n", discountAmt);
        console.format("Final Charge: %s\n", finalCharge);
    }

    public String dateFormat(LocalDate date) {
        //mm/dd/yy    
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/uu");
        return date.format(formatters);
    }
    public String currencyFormat(BigDecimal currency) {
        //$9,999.99
        return NumberFormat.getCurrencyInstance().format(currency);
    }
}
