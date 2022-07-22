package kp0722;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import kp0722.Checkout;
import kp0722.ToolTypes;
import kp0722.Tools;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckoutTest {
    Checkout checkout = new Checkout();    
    
    //////////////////////
    // Rent A Tool
    @Test void rentAtoolTest1() {
        Tools tool = new Tools("JAKR", ToolTypes.JACKHAMMER, "Ridgid");
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 5;
        int discount = 101;

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals(null, checkout.agreement.toolCode);
    }

    @Test void rentAtoolTest2() {
        Tools tool = new Tools("LADW", ToolTypes.LADDER, "Werner");
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 3;
        int discount = 10;

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals("LADW", checkout.agreement.toolCode);
        assertEquals(ToolTypes.LADDER.type, checkout.agreement.toolType);
        assertEquals("Werner", checkout.agreement.toolBrand);
        assertEquals(3, checkout.agreement.rentalDays);
        assertEquals(LocalDate.of(2020, 7, 2), checkout.agreement.checkoutDate);
        assertEquals(LocalDate.of(2020, 7, 5), checkout.agreement.dueDate);
        assertEquals(2, checkout.agreement.chargableDays);
        assertEquals(BigDecimal.valueOf(3.98), checkout.agreement.preDiscountTotal);
        assertEquals(10, checkout.agreement.discount);
        assertEquals(BigDecimal.valueOf(0.40).setScale(2, RoundingMode.HALF_UP), checkout.agreement.discountAmt);
        assertEquals(BigDecimal.valueOf(3.58), checkout.agreement.finalCharge);
    }

    @Test void rentAtoolTest3() {
        Tools tool = new Tools("CHNS", ToolTypes.CHAINSAW, "Stihl");
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 5;
        int discount = 25;

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals("CHNS", checkout.agreement.toolCode);
        assertEquals(ToolTypes.CHAINSAW.type, checkout.agreement.toolType);
        assertEquals("Stihl", checkout.agreement.toolBrand);
        assertEquals(5, checkout.agreement.rentalDays);
        assertEquals(LocalDate.of(2015, 7, 2), checkout.agreement.checkoutDate);
        assertEquals(LocalDate.of(2015, 7, 7), checkout.agreement.dueDate);
        assertEquals(3, checkout.agreement.chargableDays);
        assertEquals(BigDecimal.valueOf(4.47), checkout.agreement.preDiscountTotal);
        assertEquals(25, checkout.agreement.discount);
        assertEquals(BigDecimal.valueOf(1.12), checkout.agreement.discountAmt);
        assertEquals(BigDecimal.valueOf(3.35), checkout.agreement.finalCharge);
    }
    
    @Test void rentAtoolTest4() {
        Tools tool = new Tools("JAKD", ToolTypes.JACKHAMMER, "Ridgid");
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 6;
        int discount = 0;
        BigDecimal expectedDiscAmt = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals("JAKD", checkout.agreement.toolCode);
        assertEquals(ToolTypes.JACKHAMMER.type, checkout.agreement.toolType);
        assertEquals("Ridgid", checkout.agreement.toolBrand);
        assertEquals(6, checkout.agreement.rentalDays);
        assertEquals(LocalDate.of(2015, 9, 3), checkout.agreement.checkoutDate);
        assertEquals(LocalDate.of(2015, 9, 9), checkout.agreement.dueDate);
        assertEquals(3, checkout.agreement.chargableDays);
        assertEquals(BigDecimal.valueOf(8.97), checkout.agreement.preDiscountTotal);
        assertEquals(0, checkout.agreement.discount);
        assertEquals(expectedDiscAmt, checkout.agreement.discountAmt);
        assertEquals(BigDecimal.valueOf(8.97), checkout.agreement.finalCharge);
    }

    @Test void rentAtoolTest5() {
        Tools tool = new Tools("JAKR", ToolTypes.JACKHAMMER, "Ridgid");
        LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
        int rentalDays = 9;
        int discount = 0;
        BigDecimal expectedDiscAmt = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_UP);

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals("JAKR", checkout.agreement.toolCode);
        assertEquals(ToolTypes.JACKHAMMER.type, checkout.agreement.toolType);
        assertEquals("Ridgid", checkout.agreement.toolBrand);
        assertEquals(9, checkout.agreement.rentalDays);
        assertEquals(LocalDate.of(2015, 7, 2), checkout.agreement.checkoutDate);
        assertEquals(LocalDate.of(2015, 7, 11), checkout.agreement.dueDate);
        assertEquals(5, checkout.agreement.chargableDays);
        assertEquals(BigDecimal.valueOf(14.95), checkout.agreement.preDiscountTotal);
        assertEquals(0, checkout.agreement.discount);
        assertEquals(expectedDiscAmt, checkout.agreement.discountAmt);
        assertEquals(BigDecimal.valueOf(14.95), checkout.agreement.finalCharge);
    }

    @Test void rentAtoolTest6() {
        Tools tool = new Tools("JAKR", ToolTypes.JACKHAMMER, "Ridgid");
        LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 4;
        int discount = 50;
        BigDecimal expectedDiscAmt = BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP);

        checkout.RentATool(tool, rentalDays, discount, checkoutDate);
        assertEquals("JAKR", checkout.agreement.toolCode);
        assertEquals(ToolTypes.JACKHAMMER.type, checkout.agreement.toolType);
        assertEquals("Ridgid", checkout.agreement.toolBrand);
        assertEquals(4, checkout.agreement.rentalDays);
        assertEquals(LocalDate.of(2020, 7, 2), checkout.agreement.checkoutDate);
        assertEquals(LocalDate.of(2020, 7, 6), checkout.agreement.dueDate);
        assertEquals(1, checkout.agreement.chargableDays);
        assertEquals(BigDecimal.valueOf(2.99), checkout.agreement.preDiscountTotal);
        assertEquals(50, checkout.agreement.discount);
        assertEquals(expectedDiscAmt, checkout.agreement.discountAmt);
        assertEquals(BigDecimal.valueOf(1.49), checkout.agreement.finalCharge);
    }
    
    //////////////////////
    // Chargeable Days
    @Test void getChargeableDaysCHNS() {
        int chargeableDays = checkout.getChargeableDays(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 10), ToolTypes.CHAINSAW);
        assertEquals(5, chargeableDays);
    }
    
    @Test void getChargeableDaysLAD() {
        int chargeableDays = checkout.getChargeableDays(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 10), ToolTypes.LADDER);
        assertEquals(8, chargeableDays);
    }
    @Test void getChargeableDaysJKHMR() {
        int chargeableDays = checkout.getChargeableDays(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 10), ToolTypes.JACKHAMMER);
        assertEquals(4, chargeableDays);
    }

    //////////////////////
    // Get Holiday Count
    @Test void getHolidayCountWeekday() {
        int holidayCount = checkout.getHolidayCount(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 10));
        assertEquals(1, holidayCount);
    }
    @Test void getHolidayCountWeekend() {
        int holidayCount = checkout.getHolidayCount(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 10));
        assertEquals(1, holidayCount);
    }
    @Test void getHolidayCountOverAYear() {
        int holidayCount = checkout.getHolidayCount(LocalDate.of(2021, 7, 1), LocalDate.of(2022, 7, 10));
        assertEquals(3, holidayCount);
    }
    
    //////////////////////
    // Get Weekend Count
    @Test void getWeekendCount() {
        int weekendCount = checkout.getWeekendCount(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 16));
        assertEquals(5, weekendCount);
    }
        
    //////////////////////
    // Get Weekday Count
    @Test void getWeekdayCount() {
        int weekDayCount = checkout.getWeekdayCount(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 15));
        assertEquals(11, weekDayCount);
    }
    
}
