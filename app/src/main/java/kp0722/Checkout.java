package kp0722;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Checkout {
    Holidays holidays = new Holidays();
    Agreement agreement = new Agreement();
    
    public void RentATool(Tools tool, int rentalDays, int discount, LocalDate checkoutDate) {
        if (rentalDays <= 0 || discount > 100 || discount < 0) {return;}
        agreement.toolCode = tool.toolCode;
        agreement.toolType = tool.toolType.type;
        agreement.toolBrand = tool.brand;
        agreement.rentalDays = rentalDays;
        agreement.checkoutDate = checkoutDate;
        agreement.dueDate = checkoutDate.plusDays(rentalDays);
        agreement.chargableDays = getChargeableDays(checkoutDate, agreement.dueDate, tool.toolType);
        agreement.preDiscountTotal = tool.toolType.dailyCharge.multiply(BigDecimal.valueOf(agreement.chargableDays));
        agreement.discount = discount;
        BigDecimal discAmt = agreement.preDiscountTotal.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        agreement.discountAmt = discAmt;
        agreement.finalCharge = agreement.preDiscountTotal.subtract(discAmt);
    }
    
    
    public int getChargeableDays(LocalDate startDate, LocalDate endDate, ToolTypes toolType) {
        int chargeableDays = 0;
        LocalDate trueStartDate = startDate.plusDays(1);

        if(!toolType.holidayCharge) {
            chargeableDays -= this.getHolidayCount(trueStartDate, endDate);
        } 
        if(toolType.weekendCharge) {
            chargeableDays += this.getWeekendCount(trueStartDate, endDate);
        }
        if(toolType.weekdayCharge) {
            chargeableDays += this.getWeekdayCount(trueStartDate, endDate);
        }

        return chargeableDays;
    }

    public int getHolidayCount(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        int year = startDate.getYear();
        int endYear = endDate.getYear();
        
        do {
            if (startDate.isEqual(holidays.IndependenceDayObserved(year)) || startDate.isBefore(holidays.IndependenceDayObserved(year)) && endDate.isAfter(holidays.IndependenceDayObserved(year))) {
                count++;
            }
            if (startDate.isEqual(holidays.LaborDay(year)) || startDate.isBefore(holidays.LaborDay(year)) && endDate.isAfter(holidays.LaborDay(year))) {
                count++;
            }
            year++;
        } while(year <= endYear);

        
        
        return count; 
    }

    public int getWeekendCount(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        LocalDate date = startDate;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
                count++;
            } 
            date = date.plusDays(1);
        }
        
        return count; 
    }
    
    public int getWeekdayCount(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        LocalDate date = startDate;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY) {
                count++;
            } 
            date = date.plusDays(1);
        }
        
        return count; 
    }
}
