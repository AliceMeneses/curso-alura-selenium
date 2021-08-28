package br.com.alura.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.PageObject;

public class CadastroLeilaoPage extends PageObject{

	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);
	}

	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
		
		super.getBrowser().findElement(By.id("nome")).sendKeys(nome);
		super.getBrowser().findElement(By.id("valorInicial")).sendKeys(valorInicial);
		super.getBrowser().findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
		
		super.getBrowser().findElement(By.id("button-submit")).click();	
		return new LeiloesPage(super.getBrowser());
	
	}

	public boolean isMensagensDeValidacaoVisivel() {
		String pageSource = super.getBrowser().getPageSource();
		
		return pageSource.contains("minimo 3 caracteres") 
				&& pageSource.contains("não deve estar em branco") 
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}
	
}
