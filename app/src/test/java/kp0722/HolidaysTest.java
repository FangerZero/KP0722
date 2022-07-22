package kp0722;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import kp0722.Holidays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HolidaysTest {
    Holidays holidays = new Holidays();
    
    //////////////////////
    // Independence Day
    @Test void IndependenceDay() {
        LocalDate independencDay = holidays.IndependenceDayObserved(2022);
        assertEquals(LocalDate.of(2022, 7, 4), independencDay);
    }

    @Test void IndependenceDayObserved() {
        LocalDate independencDay = holidays.IndependenceDayObserved(2022);
        assertEquals(LocalDate.of(2022, 7, 4), independencDay);
    }

    @Test void IndependenceDayObservedSunday () {
        LocalDate independencDay = holidays.IndependenceDayObserved(2021);
        assertEquals(LocalDate.of(2021, 7, 5), independencDay);
    }

    @Test void IndependenceDayObservedSaturady () {
        LocalDate independencDay = holidays.IndependenceDayObserved(2026);
        assertEquals(LocalDate.of(2026, 7, 3), independencDay);
    }

    //////////////////////
    // Labor Day
    @Test void LaborDaySept1stSunday() {
        LocalDate laborDay = holidays.LaborDay(2002);
        assertEquals(LocalDate.of(2002, 9, 2), laborDay);
    }

    @Test void LaborDaySept1stMonday() {
        LocalDate laborDay = holidays.LaborDay(2003);
        assertEquals(LocalDate.of(2003, 9, 1), laborDay);
    }

    @Test void LaborDaySept1stTuesday() {
        LocalDate laborDay = holidays.LaborDay(2009);
        assertEquals(LocalDate.of(2009, 9, 7), laborDay);
    }

    @Test void LaborDaySept1stWednesday() {
        LocalDate laborDay = holidays.LaborDay(2004);
        assertEquals(LocalDate.of(2004, 9, 6), laborDay);
    }

    @Test void LaborDaySept1stThursday() {
        LocalDate laborDay = holidays.LaborDay(2005);
        assertEquals(LocalDate.of(2005, 9, 5), laborDay);
    }

    @Test void LaborDaySept1stFriday() {
        LocalDate laborDay = holidays.LaborDay(2000);
        assertEquals(LocalDate.of(2000, 9, 4), laborDay);
    }

    @Test void LaborDaySept1stSaturday() {
        LocalDate laborDay = holidays.LaborDay(2001);
        assertEquals(LocalDate.of(2001, 9, 3), laborDay);
    }

}
