package nhb.logparser;


import junit.framework.Assert;
import org.junit.Test;
import java.text.DecimalFormatSymbols;

public class VelocityToolsTest {

    @Test
    public void formatPercent_shouldReturnXasPercentOfY(){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        char sep = dfs.getDecimalSeparator();
        Assert.assertTrue(VelocityTools.formatPercent(42, 4242).equals("0" + sep + "99"));
    }

}
