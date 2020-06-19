package com.projetocsc;

import org.junit.Assert;
import org.junit.Test;

import com.projetocsc.facade.LoginFacade;

public class TesteLogin {

	LoginFacade login = new LoginFacade();
	
	@Test
	public void loginValido() {
		Assert.assertTrue((login.logar("admin", "admin")));
	}

	@Test
	public void loginUsuarioInvalida() {
		Assert.assertFalse((login.logar("adminInvalido", "123456")));
	}
	
	@Test
	public void loginSenhaInvalida() {
		Assert.assertFalse((login.logar("admin", "senhaInvalida")));
	}
	
}
