package br.com.alura.leilao.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	private WebDriver browser;
	
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();

		browser.navigate().to(URL_LEILOES);
	}
	
	@AfterEach
	public void afterEach() {
		browser.quit();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		
		browser.findElement(By.linkText("Entrar")).click();
		browser.findElement(By.name("username")).sendKeys("fulano");
		browser.findElement(By.name("password")).sendKeys("pass");

		browser.findElement(By.id("login-form")).submit();

		assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		assertEquals("fulano", browser.findElement(By.className("font-italic")).getText());		
	
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		
		browser.findElement(By.linkText("Entrar")).click();
		browser.findElement(By.name("username")).sendKeys("fulano-invalido");
		browser.findElement(By.name("password")).sendKeys("invalido");
		
		browser.findElement(By.id("login-form")).submit();
		
		assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
		assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
		assertThrows(NoSuchElementException.class, () -> browser.findElement(By.className("font-italic")));		
	
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		
		browser.navigate().to(URL_LEILOES + "/2");
		assertEquals(URL_LOGIN, browser.getCurrentUrl());
		assertFalse(browser.getPageSource().contains("Dados do Leilão"));
		
	}

}
