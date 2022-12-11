import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;
public class Class__4_Settings {
    Class_1_Parameters Parameters = new Class_1_Parameters();
    public void FuncSettings(@NotNull WebDriver driver) throws InterruptedException {
        driver.get("https://www.binance.com/ru/trade/"+Parameters.Ticker +"?theme=dark&type=spot");
        try {
            driver.findElement(By.xpath("//div[@class='css-4rbxuz']")).click(); // Добро пожаловать
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        try {
            driver.findElement(By.xpath("//div[@class='css-1sh2brw']")).click(); // Выберите монетную пару
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        try {
            driver.findElement(By.xpath("//div[@class='css-4rbxuz']")).click(); // О рисках
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        try {
            driver.findElement(By.xpath("//div[@class='hint-content-text css-4cffwv']/following-sibling::div"))
                    .findElement(By.cssSelector(".css-tdpz7s")).click(); // О рисках
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        driver.findElement(By.xpath("//div[@class='css-pqahre']")).click(); // Шестеренка
        driver.findElement(By.xpath("//div[@class='css-3r5t6m']")).click(); // Во весь экран
        try {
            driver.findElement(By.xpath("//div[@tooltip='Закрыть']")).click(); // Индикатор
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        try {
            driver.findElement(By.xpath("//div[@tooltip='Закрыть']")).click(); // Индикатор
        } catch (NoSuchElementException e) {
            System.out.print("");
        }
        driver.findElement(By.xpath("(//div[@class='css-jj3wml'])[2]")).click(); // Скрыть другие пары
        driver.findElement(By.xpath("//div[@class='css-1f9551p']")).click(); // Стрелка предпросмотров
        driver.findElement(By.xpath("(//div[@class='css-1iqe90x'])[3]")).click(); // Нет предпросмотра
        driver.findElement(By.xpath("//div[@class='css-1mfpw7s']")).click(); // Стрелка с интервалами
        driver.findElement(By.xpath("//*[text()='"+Parameters.TimeFrame +"']")).click(); // интервал
        sleep(10000);
    }
}
