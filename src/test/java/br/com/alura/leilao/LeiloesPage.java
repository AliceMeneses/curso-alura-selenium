package br.com.alura.leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.PageObject;
import br.com.alura.leilao.login.LoginPage;

public class LeiloesPage extends PageObject{
	
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";

	public LeiloesPage() {
		super();
		getBrowser().navigate().to(URL_LEILOES);
	}

	public LeiloesPage(WebDriver browser) {
		super(browser);
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

	public CadastroLeilaoPage navegarParaPaginaDeCadastroDeLeiLao() {
		super.getBrowser().findElement(By.id("novo_leilao_link")).click();
		return new CadastroLeilaoPage(super.getBrowser());
		
	}

	public boolean contemTexto(String texto) {
		
		return super.getBrowser().getPageSource().contains(texto);	
	}

	public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataDeAbertura) {
		WebElement ultimaLinhaDaTabela = super.getBrowser().findElement(By.cssSelector("table tbody tr:last-child"));
		
		WebElement colunaNome = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataDeAbertura = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

		return colunaNome.getText().equals(nome) && colunaDataDeAbertura.getText().equals(dataDeAbertura) 
				&& colunaValorInicial.getText().equals(valorInicial);
	}

	
	
}
