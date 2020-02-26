import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testing {

    Pen pen1;
    Pen pen2;
    Pen pen3;

    @DataProvider(name="invalidConstructParams")
    Object[][] getValidConstructParams(){
        return new Object[][]{
                {-100, 2, "BLUE"},
                {0, 2, "BLUE"},
                {5,-100,"BLUE"},
                {5, 0, "BLUE"},
                {5, 3, null},
        };
    }
    @Test(dataProvider = "invalidConstructParams")
    void invalidConstructTest(final int inkContainerValue, final double sizeLetter, String colour){
            Assert.expectThrows(Exception.class,()->new Pen(inkContainerValue));
            Assert.expectThrows(Exception.class,()->new Pen(inkContainerValue,sizeLetter));
            Assert.expectThrows(Exception.class,()->new Pen(inkContainerValue,sizeLetter,colour));
    }

    @DataProvider(name="validConstructParams")
    Object[][] getInvalidConstructParams(){
        return new Object[][]{
                {0, 2, "Blue"},
                {5, 0.1, "RED"},
                {5, 100, "blue"},
        };
    }
    @Test(dataProvider = "validConstructParams")
    void validConstructTest(final int inkContainerValue, final double sizeLetter, String colour){
        try{
            pen1=new Pen(inkContainerValue);
            pen2=new Pen(inkContainerValue, sizeLetter);
            pen3=new Pen(inkContainerValue,sizeLetter,colour);
        }
        catch (Exception e){
            Assert.fail("Something wrong with valid params");
        }
    }


    @DataProvider(name="colourParams")
    Object[][] getColour(){
        return new Object[][]{
                {"Blue"},
                {"RED"},
                {"blue"},
                {"BLUE"}
        };
    }
    @Test(dataProvider = "colourParams")
    void getColourTest(String colour){
        pen3=new Pen(10,1,colour);
        Assert.assertEquals(pen3.getColor(), colour, "Something wrong with colours. Expacted: " + colour + " Got: " + pen3.getColor());
    }


}
