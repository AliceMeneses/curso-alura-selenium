package br.com.alura.leilao.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.LeiloesPage;

public class LoginTest {
	
	private LeiloesPage paginaDeLeilao;

	@BeforeEach
	public void beforeEach() {
		paginaDeLeilao = new LeiloesPage();
	}
	
	@AfterEach
	public void afterEach() {
		paginaDeLeilao.fechar();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		
		LoginPage paginaDeLogin = paginaDeLeilao.navegaParaPaginaDeLogin();
		
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();

		assertFalse(paginaDeLogin.isPaginaDeLogin());
		assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());		
	
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		
		LoginPage paginaDeLogin = paginaDeLeilao.navegaParaPaginaDeLogin();
		
		paginaDeLogin.preencherFormularioDeLogin("fulano-invalido", "invalido");
		paginaDeLogin.efetuarLogin();
		
		assertTrue(paginaDeLogin.isPaginaDeLoginComErro());
		assertNull(paginaDeLogin.getNomeUsuarioLogado());		
	
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		
		LoginPage paginaDeLogin = (LoginPage) paginaDeLeilao.navegaParaPaginaDeLanceDeUmLeilao(2);
		
		assertTrue(paginaDeLogin.isPaginaDeLogin());
		assertFalse(paginaDeLogin.contemTexto("Dados do leilão"));
		
	}

}
