import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.time.Duration;
import static java.lang.Thread.sleep;
public class Class__5_Trading {
    Class_2_GetInfo GetInfo = new Class_2_GetInfo();
    Class_3_Orders Orders = new Class_3_Orders();
    public void FuncTrading(WebDriver driver, double[] ParametersForTrading) throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        double TakeProfitPercentage = ParametersForTrading[0];
        double ImpulseValueUpPercentage = ParametersForTrading[1];
        double ImpulseValueDownPercentage = ParametersForTrading[2];
        double DeviationToStopLossPercentage = ParametersForTrading[3];
        boolean OrderSell = false;
        double PriceOpenCandleOld = 0;
        double DealResult;
        while (GetInfo.FuncGetBrowserCheck()) {
            double PriceOpenCandle = GetInfo.FuncGetPriceOpenCandle(driver);
            double PriceCloseCandle = GetInfo.FuncGetPriceCloseCandle(driver);
            double PriceBuyD = PriceOpenCandle - (PriceOpenCandle / 100 * ImpulseValueDownPercentage);
            String PriceBuyS = String.format("%."+GetInfo.FuncDecimals(driver)+"f", PriceBuyD);
            double PriceBuyStopLossD = PriceOpenCandle + (PriceOpenCandle / 100 * ImpulseValueUpPercentage);
            String PriceBuyStopLossS = String.format("%."+GetInfo.FuncDecimals(driver)+"f", PriceBuyStopLossD);
            double PriceBuyLimitD = PriceBuyStopLossD + (PriceBuyStopLossD / 100 * DeviationToStopLossPercentage);
            String PriceBuyLimitS = String.format("%."+GetInfo.FuncDecimals(driver)+"f", PriceBuyLimitD);
            double PriceSellD = PriceBuyStopLossD + (PriceBuyStopLossD / 100 * TakeProfitPercentage);
            String PriceSellS = String.format("%."+GetInfo.FuncDecimals(driver)+"f", PriceSellD);
            if (PriceCloseCandle < PriceBuyStopLossD && PriceOpenCandle != PriceOpenCandleOld) {
                Orders.FuncOrderOcoBuy(driver, PriceBuyS, PriceBuyStopLossS, PriceBuyLimitS);
                sleep(1000);
                while (true) {
                    if (driver.findElements(By.xpath("//*[text()='У вас нет открытых ордеров.']")).size() > 0) {
                        GetInfo.FuncGetDateTime();
                        System.out.println("Покупка по ОСО ордеру.");
                        Orders.FuncOrderLimitSell(driver, PriceSellS);
                        OrderSell = true;
                        sleep(1000);
                        break;
                    }
                    if (GetInfo.FuncTimeConditions()) {
                        driver.findElement(By.xpath("//div[@class='css-8yenei']")).click();
                        sleep(1000);
                        break;
                    }
                }
                if (OrderSell) {
                    while (true) {
                        if (driver.findElements(By.xpath("//*[text()='У вас нет открытых ордеров.']")).size() > 0) {
                            DealResult = (PriceSellD-PriceBuyStopLossD)/PriceBuyStopLossD*100;
                            GetInfo.FuncGetDateTime();
                            System.out.println("Продажа по лимитному ордеру.");
                            System.out.println("+" + String.format("%.1f", DealResult) + "% от вложенных средств в сделку.");
                            System.out.println("----------------------------------------");
                            OrderSell = false;
                            sleep(1000);
                            break;
                        }
                        if (GetInfo.FuncTimeConditions()) {
                            driver.findElement(By.xpath("//div[@class='css-8yenei']")).click();
                            sleep(1000);
                            Orders.FuncOrderMarketSell(driver);
                            PriceCloseCandle = GetInfo.FuncGetPriceCloseCandle(driver);
                            DealResult = (PriceCloseCandle-PriceBuyStopLossD)/PriceBuyStopLossD*100;
                            GetInfo.FuncGetDateTime();
                            System.out.println("Продажа по рыночному ордеру.");
                            if (DealResult >= 0) {
                                System.out.println("Примерно +" + String.format("%.1f", DealResult) + "% от вложенных средств в сделку.");
                            } else {
                                System.out.println("Примерно " + String.format("%.1f", DealResult) + "% от вложенных средств в сделку.");
                            }
                            System.out.println("----------------------------------------");
                            OrderSell = false;
                            sleep(1000);
                            break;
                        }
                    }
                }
                PriceOpenCandleOld = GetInfo.FuncGetPriceOpenCandle(driver);
            }
        }
    }
}
