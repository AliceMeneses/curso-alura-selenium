package br.com.alura.leilao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {

	@Test
	public void deveriaCadastrarLeilao() {
		
		LeiloesPage paginaDeLeiloes = new LeiloesPage();	
		
		LoginPage paginaDeLogin = paginaDeLeiloes.navegaParaPaginaDeLogin();
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLeiloes = paginaDeLogin.efetuarLogin();
		
		CadastroLeilaoPage paginaDeCadastroDeLeilao = paginaDeLeiloes.navegarParaPaginaDeCadastroDeLeiLao();
		
		paginaDeLeiloes = paginaDeCadastroDeLeilao.cadastrarLeilao("Notebook Samsung", "20000.00", "30/08/2021");
		
		assertTrue(paginaDeLeiloes.contemTexto("Leilão salvo com sucesso"));
		assertTrue(paginaDeLeiloes.isLeilaoCadastrado("Notebook Samsung", "20000.00", "30/08/2021"));
		
		paginaDeCadastroDeLeilao.fechar();
		
	}
	
}
