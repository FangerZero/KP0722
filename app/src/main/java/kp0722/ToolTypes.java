package kp0722;

import java.math.BigDecimal;

public enum ToolTypes {
    CHAINSAW("Chainsaw", BigDecimal.valueOf(1.49), true, false, true),
    LADDER("Ladder", BigDecimal.valueOf(1.99), true, true, false),
    JACKHAMMER("Jackhammer", BigDecimal.valueOf(2.99), true, false, false);
    
    public final String type;
    public final BigDecimal dailyCharge;
    public final boolean weekdayCharge, weekendCharge, holidayCharge;


    private ToolTypes(String type, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.type = type;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
}