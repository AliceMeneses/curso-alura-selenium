package br.com.alura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	private WebDriver browser;
	
	public PageObject() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
	}
	
	public PageObject(WebDriver browser) {
		this.browser = browser;
	}

	public WebDriver getBrowser() {
		return this.browser;
	}
	
	public void fechar() {
		getBrowser().quit();		
	}
}
