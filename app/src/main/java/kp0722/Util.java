package kp0722;
import java.util.List;

public class Util {
    public Tools getRentalTool(String toolCode, List<Tools> tools) {
        for (Tools tool: tools) {
            if(tool.toolCode.equalsIgnoreCase(toolCode)) {
                return tool;
            }
        }
        return null;
    }
}
