import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;
public class Class__3_Login {
    public void FuncLogin(@NotNull WebDriver driver) throws InterruptedException {
        driver.get("https://accounts.binance.com/ru/login?return_to=aHR0cHM6Ly93d3cuYmluYW5jZS5jb20vcnUvbXkvZGFzaGJvYXJk");
        sleep(20000);
    }
}
