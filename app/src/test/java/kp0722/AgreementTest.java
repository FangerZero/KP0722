package kp0722;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import kp0722.Agreement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgreementTest {
    Agreement agreement = new Agreement();

    @Test void dateFormat() {
        String dateToCheck = agreement.dateFormat(LocalDate.of(2000, 01, 10));
        assertEquals("01/10/00", dateToCheck);
    }
    
    @Test void currencyFormatRoundDown() {
        String currencyToCheck = agreement.currencyFormat(BigDecimal.valueOf(44.444));
        assertEquals("$44.44", currencyToCheck);
    }
    
    @Test void currencyFormatRoundUp() {
        String currencyToCheck = agreement.currencyFormat(BigDecimal.valueOf(44.446));
        assertEquals("$44.45", currencyToCheck);
    }
}
