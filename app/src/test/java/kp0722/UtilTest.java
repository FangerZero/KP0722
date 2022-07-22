package kp0722;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import kp0722.ToolTypes;
import kp0722.Tools;
import kp0722.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {
    Util util = new Util();
    
    //////////////////////
    // Find Rental Tools in list
    @Test void getRentalTool() {
        List<Tools> tools = new ArrayList<>();
        tools.add(new Tools("CHNS", ToolTypes.CHAINSAW, "Stihl"));
        tools.add(new Tools("LADW", ToolTypes.LADDER, "Werner"));
        Tools rentalTool = util.getRentalTool("CHNS", tools);
        assertEquals("CHNS", rentalTool.toolCode);
        assertEquals("Stihl", rentalTool.brand);
    }

    @Test void getNullRentalTool() {
        List<Tools> tools = new ArrayList<>();
        tools.add(new Tools("CHNS", ToolTypes.CHAINSAW, "Stihl"));
        tools.add(new Tools("LADW", ToolTypes.LADDER, "Werner"));
        Tools rentalTool = util.getRentalTool("CHNA", tools);
        assertEquals(null, rentalTool);
    }
}
