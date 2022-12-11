import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Class_3_Orders {
    Class_1_Parameters Parameters = new Class_1_Parameters();
    public void FuncOrderMarketSell(WebDriver driver) {
        driver.findElement(By.xpath("//*[text()='Маркет']")).click();
        driver.findElement(By.xpath("(//div[@class='bn-slider-available-bar css-16vu25q'])[2]/following-sibling::div[8]")).click();
        driver.findElement(By.xpath("//button[@class='buySellButton css-ty50m1']")).click();
        driver.findElement(By.xpath("(//div[@class='bn-slider-available-bar css-16vu25q'])[2]/following-sibling::div[4]")).click();
    }
    public void FuncOrderLimitSell(WebDriver driver, String Price) {
        driver.findElement(By.xpath("(//*[text()='Лимит'])[1]")).click();
        WebElement PriceOrder = driver.findElement(By.xpath("(//input[@class=' css-1wlt96c'])[4]"));
        driver.findElement(By.xpath("(//div[@class='bn-slider-available-bar css-16vu25q'])[2]/following-sibling::div[8]")).click();
        PriceOrder.click();
        PriceOrder.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        PriceOrder.sendKeys(Price);
        driver.findElement(By.xpath("//button[@class='buySellButton css-ty50m1']")).click();
        driver.findElement(By.xpath("(//div[@class='bn-slider-available-bar css-16vu25q'])[2]/following-sibling::div[4]")).click();
    }
    public void FuncOrderOcoBuy(WebDriver driver, String PriceBuyS, String PriceBuyStopLossS, String PriceBuyLimitS) {
        driver.findElement(By.xpath("//div[@class='css-zh6lrt']")).click();
        driver.findElement(By.xpath("//*[text()='OCO']")).click();

        WebElement PriceOrder = driver.findElement(By.xpath("(//input[@class=' css-1wlt96c'])[1]"));
        WebElement StopOrder = driver.findElement(By.xpath("(//input[@class=' css-1wlt96c'])[2]"));
        WebElement LimitOrder = driver.findElement(By.xpath("(//input[@class=' css-1wlt96c'])[3]"));
        WebElement QuantityOrder = driver.findElement(By.xpath("(//input[@class=' css-1wlt96c'])[5]"));

        PriceOrder.click();
        PriceOrder.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        PriceOrder.sendKeys(PriceBuyS);

        StopOrder.click();
        StopOrder.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        StopOrder.sendKeys(PriceBuyStopLossS);

        LimitOrder.click();
        LimitOrder.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        LimitOrder.sendKeys(PriceBuyLimitS);

        QuantityOrder.click();
        QuantityOrder.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        QuantityOrder.sendKeys(Parameters.Quantity);

        driver.findElement(By.xpath("//button[@class='buySellButton css-1qka0vb']")).click();
    }
}
