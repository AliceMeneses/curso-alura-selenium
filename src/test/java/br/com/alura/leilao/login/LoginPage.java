package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import br.com.alura.PageObject;
import br.com.alura.leilao.LeiloesPage;

public class LoginPage extends PageObject{

	private static final String URL_LOGIN = "http://localhost:8080/login";
		
	public LoginPage() {
		super();
		getBrowser().navigate().to(URL_LOGIN);
	}
	
	public LoginPage(WebDriver browser) {
		super(browser);
	}

	public void preencherFormularioDeLogin(String nome, String senha) {
		getBrowser().findElement(By.name("username")).sendKeys(nome);
		getBrowser().findElement(By.name("password")).sendKeys(senha);
	}

	public LeiloesPage efetuarLogin() {
		getBrowser().findElement(By.id("login-form")).submit();
		return new LeiloesPage(getBrowser());
	}
	
	public boolean isPaginaDeLogin() {
		return getBrowser().getCurrentUrl().equals(URL_LOGIN);
	}

	public String getNomeUsuarioLogado() {	
		try {
			return getBrowser().findElement(By.className("font-italic")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public boolean isPaginaDeLoginComErro() {
		return getBrowser().getCurrentUrl().equals(URL_LOGIN + "?error");
	}

	public boolean contemTexto(String texto) {
		return getBrowser().getPageSource().contains(texto);
	}	
	
}
