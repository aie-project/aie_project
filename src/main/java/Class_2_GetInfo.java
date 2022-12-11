import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class Class_2_GetInfo {
    Class_1_Parameters Parameters = new Class_1_Parameters();
    public boolean FuncGetBrowserCheck() throws IOException {
        boolean flag = false;
        String pr_name = "chrome.exe";
        String process_line;
        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((process_line = input.readLine()) != null & !flag) {
            if (process_line.contains(pr_name)){
                flag = true ;
            }
        }
        input.close();
        return flag;
    }
    public void FuncGetDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.print(formatter.format(date) + " - ");
    }
    public int FuncGetMinute() {
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date date = new Date();
        return Integer.parseInt((formatter.format(date)).trim());
    }
    public int FuncGetSecond() {
        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        Date date = new Date();
        return Integer.parseInt((formatter.format(date)).trim());
    }
    public boolean FuncTimeConditions() {
        int Minute = FuncGetMinute();
        int Second = FuncGetSecond();
        int ComparingSeconds = 58;
        if (Objects.equals(Parameters.TimeFrame, "1м")) {
            return (Second == ComparingSeconds);
        }
        if ((Objects.equals(Parameters.TimeFrame, "3м")) || (Objects.equals(Parameters.TimeFrame, "5м"))) {
            if (Minute >= 10) {
                Minute = Minute % 10;
            }
            if (Objects.equals(Parameters.TimeFrame, "3м")) {
                return (Minute == 2 || Minute == 5 || Minute == 8) && (Second == ComparingSeconds);
            }
            if (Objects.equals(Parameters.TimeFrame, "5м")) {
                return (Minute == 4 || Minute == 9) && (Second == ComparingSeconds);
            }
        }
        if (Objects.equals(Parameters.TimeFrame, "15м")) {
            return (Minute == 14 || Minute == 29 || Minute == 44 || Minute == 59) && (Second == ComparingSeconds);
        }
        if (Objects.equals(Parameters.TimeFrame, "30м")) {
            return (Minute == 29 || Minute == 59) && (Second == ComparingSeconds);
        }
        if (Objects.equals(Parameters.TimeFrame, "1Ч")) {
            return (Minute == 59) && (Second == ComparingSeconds);
        }
        return false;
    }
    public double FuncGetPriceOpenCandle(@NotNull WebDriver driver) {
        return Double.parseDouble
               (driver.findElement
               (By.xpath("(//div[@class='chart-title-indicator-container']/span[@class='default-label-box'])[3]"))
               .getText());
    }
    public double FuncGetPriceCloseCandle(@NotNull WebDriver driver) {
        return Double.parseDouble
               (driver.findElement
               (By.xpath("(//div[@class='chart-title-indicator-container']/span[@class='default-label-box'])[9]"))
               .getText());
    }
    public int FuncDecimals(@NotNull WebDriver driver) {
        String Number = driver.findElement
                        (By.xpath("(//div[@class='chart-title-indicator-container']/span[@class='default-label-box'])[3]"))
                        .getText();
        return Number.substring(Number.lastIndexOf('.')).length()-1;
    }
}
