package Web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class WebPostsTest {
    protected static WebDriver driver;

    void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @BeforeAll
    static void init() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--InPrivate");
        options.addArguments("start-maximized");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://test-stand.gb.ru/login");

        driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/label/input")).sendKeys("ustinovskyk");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/label/input")).sendKeys("2e10c081f3");
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/button")).click();
    }

    @BeforeEach
    void start() {
        sleep();
        driver.get("https://test-stand.gb.ru/?sort=createdAt&order=ASC");
    }

    @Test
    void postsTest1() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[2]/img")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[1]/div/div[3]")).getText();
        Assertions.assertEquals("Шашлык",result,"Некоректное поведение");
    }
    @Test
    void postsTest2() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[2]/div/a[2]")).click();
        sleep();
        driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[2]/div/a[1]")).click();
        sleep();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[2]/h2")).getText();
        Assertions.assertEquals("Блины",result,"Некоректное поведение");
    }
    @Test
    void postsTest3() {
        String result1 = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[1]/div")).getText();
        String result2 = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[1]/h2")).getText();
        String result3 = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[1]/img")).getText();
        Assertions.assertEquals("",result1,"Некоректное поведение");
        Assertions.assertEquals("Бургер",result2,"Некоректное поведение");
        Assertions.assertNotNull(result3,"Некоректное поведение");
    }
    @Test
    void postsTest4() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[2]/div[2]/div[1]/button")).click();
        String result = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div[3]/div[1]/a[1]/h2")).getText();
        Assertions.assertEquals("Пельмени",result,"Некоректное поведение");
    }

}
