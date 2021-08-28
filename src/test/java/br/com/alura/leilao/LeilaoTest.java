package br.com.alura.leilao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private CadastroLeilaoPage paginaDeCadastroDeLeilao;
	private LeiloesPage paginaDeLeiloes;

	@BeforeEach
	public void beforeEach() {
		
		paginaDeLeiloes = new LeiloesPage();	
		
		LoginPage paginaDeLogin = paginaDeLeiloes.navegaParaPaginaDeLogin();
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLeiloes = paginaDeLogin.efetuarLogin();
		
		paginaDeCadastroDeLeilao = paginaDeLeiloes.navegarParaPaginaDeCadastroDeLeiLao();
		
	}
	
	@AfterEach
	public void afterEach() {
		paginaDeCadastroDeLeilao.fechar();
	}

	@Test
	public void deveriaCadastrarLeilao() {
		
		paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("Notebook Samsung", "20000.00", "30/08/2021");
		
		assertTrue(paginaDeLeiloes.contemTexto("Leilão salvo com sucesso"));
		assertTrue(paginaDeLeiloes.isLeilaoCadastrado("Notebook Samsung", "20000.00", "30/08/2021"));
				
	}
	
	@Test
	public void deveriaValidarCadastroDoLeilao() {
		
		paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("", "", "");

		assertTrue(paginaDeLeiloes.isPaginaAtual());
		assertTrue(paginaDeCadastroDeLeilao.isMensagensDeValidacaoVisivel());
		
	}
	
}
