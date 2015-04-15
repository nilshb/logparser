package nhb.logparser;


import junit.framework.Assert;
import org.junit.Test;

public class VelocityToolsTest {

    @Test
    public void formatPercent_shouldReturnXasPercentOfY(){
        Assert.assertTrue(VelocityTools.formatPercent(42, 4242).equals("0.99"));
    }

}
