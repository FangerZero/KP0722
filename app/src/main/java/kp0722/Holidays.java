package kp0722;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Holidays {
    private static LocalDate IndependenceDay (int year)
    {
        // July 4th
        return LocalDate.of(year, 7, 4);
    }

    public LocalDate IndependenceDayObserved(int year) {
        LocalDate independencDay = IndependenceDay(year);
        DayOfWeek dayOfWeek = independencDay.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return independencDay.plusDays(1);
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            return independencDay.minusDays(1);
        }
        return independencDay;
    }

    public LocalDate LaborDay(int year) {
        // First Monday of September
        LocalDate septFirst = LocalDate.of(year, 9, 1);

        switch(septFirst.getDayOfWeek()) {
            case SUNDAY: 
                return LocalDate.of(year, 9, 2);
            case MONDAY: 
                return LocalDate.of(year, 9, 1);
            case TUESDAY: 
                return LocalDate.of(year, 9, 7);
            case WEDNESDAY: 
                return LocalDate.of(year, 9, 6);
            case THURSDAY: 
                return LocalDate.of(year, 9, 5);
            case FRIDAY: 
                return LocalDate.of(year, 9, 4);
            case SATURDAY: 
                return LocalDate.of(year, 9, 3);
            default:
                return septFirst;
        }
    }

}
