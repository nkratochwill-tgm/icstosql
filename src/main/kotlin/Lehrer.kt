import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver

fun main(args: Array<String>) {
    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver")
    val driver = FirefoxDriver()
    driver.get("https://neilo.webuntis.com/WebUntis/?school=tgm#/basic/main")
    driver.findElementByCssSelector("input[type=text][placeholder='Benutzer']").sendKeys("xx")
    driver.findElementByCssSelector("input[type=password]").sendKeys("xx")
    var buttons: List<WebElement> = driver.findElementsByCssSelector("button[title=Login]")
    buttons[1].click()
    driver.get("https://neilo.webuntis.com/WebUntis/?school=tgm#/basic/timetable?type=2")
}
