import org.openqa.selenium.WebDriver;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Class_0_ProductInformation ProductInformation = new Class_0_ProductInformation();
        Class__1_Analysis Analysis = new Class__1_Analysis();
        Class__2_BrowserLaunch BrowserLaunch = new Class__2_BrowserLaunch();
        Class__3_Login Login = new Class__3_Login();
        Class__4_Settings Settings = new Class__4_Settings();
        Class__5_Trading Trading = new Class__5_Trading();
        ProductInformation.FuncProductInformation();
        double[] ParametersForTrading = Analysis.FuncAnalysis();
        WebDriver driver = BrowserLaunch.FuncBrowserLaunch();
        Login.FuncLogin(driver);
        Settings.FuncSettings(driver);
        Trading.FuncTrading(driver, ParametersForTrading);
    }
}
