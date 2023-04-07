package Web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class WebAuthTest {
    protected static WebDriver driver;


    @BeforeAll
    static void init() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--InPrivate");
        options.addArguments("start-maximized");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://test-stand.gb.ru/login");
    }
    @BeforeEach
    void start() {
        driver.get("https://test-stand.gb.ru/login");
    }

    @Test
    void authTest1() {
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input")).sendKeys("ustinovskyk");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input")).sendKeys("2e10c081f3");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[1]/h1")).getText();
        Assertions.assertEquals("Blog",result,"Авторизация не прошла");
    }
    @Test
    void authTest2() {
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input")).sendKeys("usustinov!%");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input")).sendKeys("2e10c081f3");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[2]/p[1]")).getText();
        Assertions.assertEquals("Invalid credentials.",result,"Некорректное поведение приложения");
    }
    @Test
    void authTest3() {
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input")).sendKeys("us");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input")).sendKeys("2e10c081f3");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[2]/p[1]")).getText();
        Assertions.assertEquals("Invalid credentials.",result,"Некорректное поведение приложения");
    }
    @Test
    void authTest4() {
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input")).sendKeys("2e10c081f3");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[2]/p[1]")).getText();
        Assertions.assertEquals("Invalid credentials.",result,"Некорректное поведение приложения");
    }
}
