package nhb.logparser;

public class VelocityTools {

    public static String formatPercent(float x, float y) {
        return String.format("%.2f", (x * 100) / y);
    }

}
