package br.com.alura.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.PageObject;
import br.com.alura.leilao.login.LoginPage;

public class LeiloesPage extends PageObject{
	
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";

	public LeiloesPage() {
		super();
		getBrowser().navigate().to(URL_LEILOES);
	}

	public LoginPage navegaParaPaginaDeLogin() {
		getBrowser().findElement(By.linkText("Entrar")).click();
		
		return new LoginPage(getBrowser());
	}

	public PageObject navegaParaPaginaDeLanceDeUmLeilao(int idleilao) {
		getBrowser().navigate().to(URL_LEILOES + "/" + idleilao);
		
		if(getBrowser().getCurrentUrl().equals(URL_LEILOES + "/" + idleilao)) {
			return new LeilaoPage(getBrowser());
		} else {
			return new LoginPage(getBrowser());
		}
	}

	
	
}
